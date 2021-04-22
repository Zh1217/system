package com.upc.system.service.impl;

import com.upc.system.config.CodeMsg;
import com.upc.system.entity.*;
import com.upc.system.mapper.CompanyMapper;
import com.upc.system.mapper.PatentMapper;
import com.upc.system.mapper.ProductMapper;
import com.upc.system.mapper.ProjectMapper;
import com.upc.system.service.PatentService;
import com.upc.system.util.NetworkUtil;
import com.upc.system.vo.PatentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Service
public class PatentServiceImpl implements PatentService {
    @Autowired
    private PatentMapper patentMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProjectMapper projectMapper;


    @Override
    public List<Patent> getAllPatent(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute("user");
        List<Patent> patentList = patentMapper.selectPatentByUserId(user.getId());
        return patentList;
    }

    @Override
    public List<Patent> showPatent(HttpServletRequest httpServletRequest, String companyName) {
        long companyId = companyMapper.selectCompanyIdByCompanyName(companyName);
        List<Patent> projectList = patentMapper.getPatentsByCompany(companyId);
        return projectList;
    }

    @Override
    public List<String> getPatentNameByCompanyNameList(HttpServletRequest httpServletRequest, String companyName) {
        long companyId = companyMapper.selectCompanyIdByCompanyName(companyName);
        List<String> patentNameList = patentMapper.selectPatentNameByCompanyName(companyId);
        return patentNameList;
    }

    @Override
    public List<String> getPatentNameByCompanyProductNameList(HttpServletRequest httpServletRequest, String companyName, String productName) {
        long companyId = companyMapper.selectCompanyIdByCompanyName(companyName);
        long productId = productMapper.getProductIdByProductName(productName);
        List<String> patentList = patentMapper.getProjectNameByProductNameCompanyName(companyId,productId);
        return patentList;
    }

    @Override
    public List<Patent> getPatentByProduct(HttpServletRequest httpServletRequest, PatentVo patentVo) {
        long companyId = companyMapper.selectCompanyIdByCompanyName(patentVo.getCompanyName());
        long productId = productMapper.getProductIdByProductName(patentVo.getProductName());
        List<Patent> patentList = patentMapper.getPatentsByProduct(companyId,productId);
        return patentList;
    }

    @Override
    public List<Patent> getPatentByProject(HttpServletRequest httpServletRequest, PatentVo patentVo) {
        long projectId = projectMapper.selectProjectIdByProjectame(patentVo.getProjectName());
        List<Patent> patentList = patentMapper.getPatentsByProject(projectId);
        return patentList;
    }

    @Override
    public List<String> getPatentNameByProject(HttpServletRequest httpServletRequest, String projectName) {
        long projectId = projectMapper.selectProjectIdByProjectame(projectName);
        List<String> patentNameList = patentMapper.getPatentNameByProject(projectId);
        return patentNameList;
    }

    @Override
    public Patent getPatentMessage(HttpServletRequest httpServletRequest, PatentVo patentVo) {
        long patentId= patentMapper.getPatentIdByPatentName(patentVo.getPatentName());
        Patent patent = patentMapper.getPatent(patentId);
        return patent;
    }

    @Override
    public CodeMsg addPatent(HttpServletRequest httpServletRequest, PatentVo patentVo) {
        if (patentMapper.selectPatentByName(patentVo.getPatentName()) == null) {
            long companyId = companyMapper.selectCompanyIdByCompanyName(patentVo.getCompanyName());
            long productId = 0;
            if (!patentVo.getProductName().equals("")){
                productId = productMapper.getProductIdByProductName(patentVo.getProductName());
                patentVo.setProductId(productId);
            }
            long projectId = 0;
            if (!patentVo.getProjectName().equals("")){
                projectId = projectMapper.getProjectIdByProjectName(patentVo.getProductName());
                patentVo.setProjectId(projectId);
            }
            patentVo.setCompanyId(companyId);
            patentVo.setOperateTime(new Timestamp(System.currentTimeMillis()));
            patentVo.setOperateIp(NetworkUtil.getIpAddress(httpServletRequest));
            User userTmp = (User) httpServletRequest.getSession().getAttribute("user");
            patentVo.setOperator(userTmp.getUsername());
            patentVo.setDelFlag(0);
            int count = patentMapper.insertPatent(patentVo);
            if (count>0){
                long patentId = projectMapper.getProjectIdByProjectName(patentVo.getProjectName());
                PatentRelation patentRelation = new PatentRelation();
                patentRelation.setProjectId(projectId);
                patentRelation.setProductId(productId);
                patentRelation.setCompanyId(companyId);
                patentRelation.setPatentId(patentId);
                patentRelation.setOperateIp(NetworkUtil.getIpAddress(httpServletRequest));
                patentRelation.setOperateTime(new Timestamp(System.currentTimeMillis()));
                patentRelation.setOperator(userTmp.getUsername());
                patentRelation.setDelFlag(0);
                patentMapper.insertPatentRelation(patentRelation);
                return new CodeMsg<>(200, "增加项目成功！", null);
            } else{
                return new CodeMsg<>(-2, "该项目已与该公司或该产品关联！", null);
            }
        } else {
            return new CodeMsg<>(-1, "该项目已经添加，请检查项目名与项目编号！", null);
        }
    }
}
