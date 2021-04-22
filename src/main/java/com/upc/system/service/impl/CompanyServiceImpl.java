package com.upc.system.service.impl;

import com.upc.system.config.CodeMsg;
import com.upc.system.entity.Company;
import com.upc.system.entity.User;
import com.upc.system.entity.UserCompany;
import com.upc.system.mapper.CompanyMapper;
import com.upc.system.service.CompanyService;
import com.upc.system.util.NetworkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;


    @Override
    public List<Company> showCompany(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute("user");
        List<Company> companyList = companyMapper.selectCompanyByUserId(user.getId());

        return companyList;
    }

    @Override
    public CodeMsg<Object> addCompany(HttpServletRequest httpServletRequest, Company company) {
        //先查询公司是否已经存在
        int result1 = companyMapper.selectCompanyByCompanyName(company.getCompanyName());
        if (result1 == 0){
            HttpSession httpSession = httpServletRequest.getSession();
            User user = (User) httpSession.getAttribute("user");
            company.setOperateTime(new Timestamp(System.currentTimeMillis()));
            company.setOperateIp(NetworkUtil.getIpAddress(httpServletRequest));
            company.setOperator(user.getUsername());
            company.setDelFlag(0);
            //将公司添加进数据库
            int result2 = companyMapper.insertCompany(company);
            if (result2 != 0){
                long companyId = companyMapper.selectCompanyIdByCompanyName(company.getCompanyName());
                // 检查用户是否已经绑定该公司
                int result3 = companyMapper.selectUserCompany(user.getId(), companyId);
                if (result3 == 0){
                    UserCompany userCompany = new UserCompany();
                    userCompany.setUserId(user.getId());
                    userCompany.setCompanyId(companyId);
                    userCompany.setOperateTime(new Timestamp(System.currentTimeMillis()));
                    userCompany.setOperateIp(NetworkUtil.getIpAddress(httpServletRequest));
                    userCompany.setOperator(user.getUsername());
                    userCompany.setDelFlag(0);
                    int result4 = companyMapper.addUserCompany(userCompany);
                    if (result4 != 0)
                        return new CodeMsg<>(200, "增加用户管理公司成功！", null);
                    return new CodeMsg<>(-4, "增加用户管理公司失败（数据库错误）！", null);
                }
                return new CodeMsg<>(-3, "该用户已经绑定此公司！", null);
            }
            return new CodeMsg<>(-2, "添加公司失败（数据库错误）！", null);
        }
        return new CodeMsg<>(-1, "添加公司失败，该公司已经存在！", null);
    }

    @Override
    public CodeMsg<Object> getCompanyById(long companyId) {
        Company company = companyMapper.getCompanyById(companyId);
        if (company == null)
            return new CodeMsg<>(-1, "查找公司信息失败！", null);
        return new CodeMsg<>(200, "查找公司信息成功！", company);
    }

    @Override
    public List<String> getCompanyNameList(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute("user");
        List<String> companyList = companyMapper.selectCompanyNameByUserId(user.getId());
        return companyList;
    }

    @Override
    public CodeMsg<Object> alterCompany(HttpServletRequest httpServletRequest, Company company) {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute("user");
        company.setOperateTime(new Timestamp(System.currentTimeMillis()));
        company.setOperateIp(NetworkUtil.getIpAddress(httpServletRequest));
        company.setOperator(user.getUsername());
        int result = companyMapper.updateCompanyById(company);
        if (result == 0 )
            return new CodeMsg<>(-1, "修改公司信息失败！", null);
        return new CodeMsg<>(200, "修改公司信息成功！", null);
    }
    @Override
    public CodeMsg<Object> deleteCompany(HttpServletRequest httpServletRequest, Company company) {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute("user");
        company.setOperateTime(new Timestamp(System.currentTimeMillis()));
        company.setOperateIp(NetworkUtil.getIpAddress(httpServletRequest));
        company.setOperator(user.getUsername());
        int result1 = companyMapper.deleteCompanyById(company);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String operateIp = NetworkUtil.getIpAddress(httpServletRequest);
        int result2 = companyMapper.deleteUserCompanyById(timestamp,operateIp, user.getUsername(), user.getId(), company.getId());
        if (result1 ==0 ||result2 ==0)
            return new CodeMsg<>(-1, "删除公司失败！", null);
        return new CodeMsg<>(200, "删除公司成功！", null);
    }

}
