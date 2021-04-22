package com.upc.system.service;

import com.upc.system.config.CodeMsg;
import com.upc.system.entity.Company;
import com.upc.system.entity.Product;
import com.upc.system.entity.Project;
import com.upc.system.vo.ProjectVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProjectService {


    List<Project> showProject(HttpServletRequest httpServletRequest, String companyName);

    List<Project> getProject(HttpServletRequest httpServletRequest, ProjectVo projectVo);

    Project getProjectMessage(HttpServletRequest httpServletRequest, ProjectVo projectVo);

    CodeMsg<Object> addProject(HttpServletRequest httpServletRequest, ProjectVo projectVo);

    List<Project> getAllProject(HttpServletRequest httpServletRequest);

    List<String> getProjectNameByCompanyNameList(HttpServletRequest httpServletRequest, String companyName);

    List<String> getProjectNameByCompanyProductNameList(HttpServletRequest httpServletRequest, String companyName, String productName);

    CodeMsg updateProject(HttpServletRequest httpServletRequest, Project project);

    CodeMsg deleteProject(HttpServletRequest httpServletRequest, Project project);
}
