package com.upc.system.controller;

import com.upc.system.config.CodeMsg;
import com.upc.system.entity.Company;
import com.upc.system.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@RestController
@Slf4j
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/main")
    public CodeMsg<Object> showCompany(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        try {
            //  根据用户id获取绑定公司列表
            List<Company> companyList = companyService.showCompany(httpServletRequest,httpServletResponse);
            if (!companyList.isEmpty())
                return new CodeMsg<>(200,"查询该用户管理的公司列表成功！",companyList);
            return new CodeMsg<>(-1,"该用户没有管理的公司，请先添加公司！",null);
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
    //增加单个公司
    @RequestMapping("/addCompany")
    public CodeMsg<Company> addCompany(HttpServletRequest httpServletRequest,@RequestBody Company company){
        try{
            CodeMsg msg = companyService.addCompany(httpServletRequest,company);
            return msg;
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
    //获取单个公司信息
    @RequestMapping("/getCompany")
    public CodeMsg<Object> getCompany(HttpServletRequest httpServletRequest,@RequestBody Company company) {
        try {
            CodeMsg codeMsg = companyService.getCompanyById(company.getId());
            return codeMsg;
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
    //修改公司信息
    @RequestMapping("/alterCompany")
    public CodeMsg<Company> alterCompany(HttpServletRequest httpServletRequest,@RequestBody Company company){
        try{
            CodeMsg msg = companyService.alterCompany(httpServletRequest,company);
            return msg;
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }

    //删除公司
    @RequestMapping("/deleteCompany")
    public CodeMsg<Company> deleteCompany(HttpServletRequest httpServletRequest,@RequestBody Company company){
        try{
            CodeMsg msg = companyService.deleteCompany(httpServletRequest,company);
            return msg;
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }

    //获取公司名称列表
    @RequestMapping("/getCompanyNameList")
    public CodeMsg<Object> getCompanyNameList(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        try {
            //  根据用户id获取绑定公司名称列表
            List<String> companyList = companyService.getCompanyNameList(httpServletRequest,httpServletResponse);
            if (!companyList.isEmpty())
                return new CodeMsg<>(200,"查询该用户管理的公司列表成功！",companyList);
            return new CodeMsg<>(-1,"该用户没有管理的公司，请先添加公司！",null);
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
}
