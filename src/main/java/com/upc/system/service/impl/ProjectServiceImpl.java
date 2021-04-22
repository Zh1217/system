package com.upc.system.service.impl;

import com.upc.system.config.CodeMsg;
import com.upc.system.entity.*;
import com.upc.system.mapper.CompanyMapper;
import com.upc.system.mapper.ProductMapper;
import com.upc.system.mapper.ProjectMapper;
import com.upc.system.service.ProjectService;
import com.upc.system.util.NetworkUtil;
import com.upc.system.vo.ProjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

import static com.upc.system.config.CodeMsg.*;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Project> showProject(HttpServletRequest httpServletRequest, String companyName) {
        long companyId = companyMapper.selectCompanyIdByCompanyName(companyName);
        List<Project> projectList = projectMapper.getProjectsByCompany(companyId);
        return projectList;
    }
    @Override
    public List<Project> getProject(HttpServletRequest httpServletRequest, ProjectVo projectVo) {
        long companyId = companyMapper.selectCompanyIdByCompanyName(projectVo.getCompanyName());
        long productId = productMapper.getProductIdByProductName(projectVo.getProductName());
        List<Project> projectList = projectMapper.getProjectsByProduct(companyId,productId);
        return projectList;
    }
    @Override
    public Project getProjectMessage(HttpServletRequest httpServletRequest, ProjectVo projectVo) {
        long projectId= projectMapper.getProjectIdByProjectName(projectVo.getProjectName());
        Project project = projectMapper.getProject(projectId);
        return project;
    }

    @Override
    public CodeMsg<Object> addProject(HttpServletRequest httpServletRequest, ProjectVo projectVo) {
        if (projectMapper.selectProjectByCode(projectVo.getProjectCode()) == null) {
            long companyId = companyMapper.selectCompanyIdByCompanyName(projectVo.getCompanyName());
            long productId = 0;
            if (!projectVo.getProductName().equals("")){
                productId = productMapper.getProductIdByProductName(projectVo.getProductName());
                projectVo.setProductId(productId);
            }

            projectVo.setCompanyId(companyId);
            projectVo.setOperateTime(new Timestamp(System.currentTimeMillis()));
            projectVo.setOperateIp(NetworkUtil.getIpAddress(httpServletRequest));
            User userTmp = (User) httpServletRequest.getSession().getAttribute("user");
            projectVo.setOperator(userTmp.getUsername());
            projectVo.setDelFlag(0);
            int count = projectMapper.insertProject(projectVo);
            if (count>0){
                long projectId = projectMapper.getProjectIdByProjectName(projectVo.getProjectName());
                ProjectRelation projectRelation = new ProjectRelation();
                projectRelation.setProjectId(projectId);
                projectRelation.setProductId(productId);
                projectRelation.setCompanyId(companyId);
                projectRelation.setOperateIp(NetworkUtil.getIpAddress(httpServletRequest));
                projectRelation.setOperateTime(new Timestamp(System.currentTimeMillis()));
                projectRelation.setOperator(userTmp.getUsername());
                projectRelation.setDelFlag(0);
                projectMapper.insertProjectRelation(projectRelation);
                return new CodeMsg<>(200, "增加专利成功！", null);
            } else{
                return new CodeMsg<>(-2, "该专利已与该公司或该产品或项目关联！", null);
            }
        } else {
            return new CodeMsg<>(-1, "该专利已经添加！", null);
        }
    }

    @Override
    public List<Project> getAllProject(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute("user");
        List<Project> projectList = projectMapper.selectProjectByUserId(user.getId());
        return projectList;
    }

    @Override
    public List<String> getProjectNameByCompanyNameList(HttpServletRequest httpServletRequest, String companyName) {
        long companyId = companyMapper.selectCompanyIdByCompanyName(companyName);
        List<String> projectNameList = projectMapper.selectProjectNameByCompanyName(companyId);
        return projectNameList;
    }

    @Override
    public List<String> getProjectNameByCompanyProductNameList(HttpServletRequest httpServletRequest, String companyName, String productName) {
        long companyId = companyMapper.selectCompanyIdByCompanyName(companyName);
        long productId = productMapper.getProductIdByProductName(productName);
        List<String> projectList = projectMapper.getProjectNameByProductNameCompanyName(companyId,productId);
        return projectList;
    }

    @Override
    public CodeMsg updateProject(HttpServletRequest httpServletRequest, Project project) {
        Project project1 = new Project();
        project1.setOperateTime(new Timestamp(System.currentTimeMillis()));
        project1.setOperateIp(NetworkUtil.getIpAddress(httpServletRequest));
        User userTmp = (User) httpServletRequest.getSession().getAttribute("user");
        project1.setId(project.getId());
        project1.setOperator(userTmp.getUsername());
        project1.setProjectCode(project.getProjectCode());
        project1.setProjectName(project.getProjectName());
        project1.setEvidence(project.getEvidence());
        project1.setExpense(project.getExpense());
        project1.setProjectEndTime(project.getProjectEndTime());
        project1.setProjectStartTime(project.getProjectStartTime());
        int result = projectMapper.updateProject(project1);
        if (result !=0 )
            return  new CodeMsg(200,"信息修改成功！",null);
        return  new CodeMsg(-1,"信息修改失败！",null);
    }
    @Override
    public CodeMsg deleteProject(HttpServletRequest httpServletRequest, Project project) {
        project.setOperateTime(new Timestamp(System.currentTimeMillis()));
        project.setOperateIp(NetworkUtil.getIpAddress(httpServletRequest));
        User userTmp = (User) httpServletRequest.getSession().getAttribute("user");
        project.setOperator(userTmp.getUsername());
        int result1 = projectMapper.deleteProject(project);
        int result2 = projectMapper.deleteProjectRelation(project);
        if (result1 == 0||result2==0){
            return new CodeMsg(-1, "删除失败，该项目户不存在！",null);
        } else {
            return new CodeMsg(200, "删除项目成功！",null);
        }
    }

}
