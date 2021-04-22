package com.upc.system.controller;

import com.upc.system.config.CodeMsg;
import com.upc.system.entity.Company;
import com.upc.system.entity.Product;
import com.upc.system.entity.Project;
import com.upc.system.service.ProjectService;
import com.upc.system.vo.ProductVo;
import com.upc.system.vo.ProjectVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    //根据用户id获取全部项目
    @PostMapping("/getAllProject")
    public CodeMsg<Object> getAllProduct(HttpServletRequest httpServletRequest){
        try{
            List<Project> productList = projectService.getAllProject(httpServletRequest);
            if (!productList.isEmpty())
                return new CodeMsg<>(200,"查询该用户的所有项目列表成功！",productList);
            return new CodeMsg<>(-1,"没有项目，请先添加项目！",null);
        }catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }

    @PostMapping("/getProjectByCompany")
    public CodeMsg<Object> showProject(HttpServletRequest httpServletRequest,@RequestBody ProjectVo projectVo){
        try {
            //  根据公司id获取绑定项目列表
            List<Project> projectList = projectService.showProject(httpServletRequest, projectVo.getCompanyName());
            if (!projectList.isEmpty())
                return new CodeMsg<>(200,"查询该公司的项目列表成功！",projectList);
            return new CodeMsg<>(-1,"没有项目，请先添加项目！",null);
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
    //根据公司名获取项目名称列表
    @PostMapping("/getProjectNameByCompanyNameList")
    public CodeMsg<Object> getProjectNameByCompanyNameList(HttpServletRequest httpServletRequest, @RequestBody ProjectVo projectVo){
        try {
            //  根据公司名称获取绑定产品名称列表
            List<String> projectNameList = projectService.getProjectNameByCompanyNameList(httpServletRequest,projectVo.getCompanyName());
            if (!projectNameList.isEmpty())
                return new CodeMsg<>(200,"查询该公司管理的项目列表成功！",projectNameList);
            return new CodeMsg<>(-1,"该公司没有项目，请先添加项目！",null);
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
    //根据公司名和产品名获取项目名称列表
    @PostMapping("/getProjectNameByCompanyProductNameList")
    public CodeMsg<Object> getProjectNameByCompanyProductNameList(HttpServletRequest httpServletRequest, @RequestBody ProjectVo projectVo){
        try {
            //  根据公司名称获取绑定产品名称列表
            List<String> projectNameList = projectService.getProjectNameByCompanyProductNameList(httpServletRequest,projectVo.getCompanyName(),projectVo.getProductName());
            if (!projectNameList.isEmpty())
                return new CodeMsg<>(200,"查询该公司产品的项目列表成功！",projectNameList);
            return new CodeMsg<>(-1,"该公司产品没有项目，请先添加项目！",null);
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
    @PostMapping("/getProjectByProduct")
    public CodeMsg<Object> getProject(HttpServletRequest httpServletRequest, @RequestBody ProjectVo projectVo){
        try {
            //  根据产品id获取绑定项目列表
            List<Project> projectList = projectService.getProject(httpServletRequest,projectVo);
            if (!projectList.isEmpty())
                return new CodeMsg<>(200,"查询该产品的项目列表成功！",projectList);
            return new CodeMsg<>(-1,"没有项目，请先添加项目！",null);
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
    @PostMapping("/getProjectMessage")
    public CodeMsg<Object> getProjectMessage(HttpServletRequest httpServletRequest, @RequestBody ProjectVo projectVo){
        try {
            //  查看某项目的信息
            Project project = projectService.getProjectMessage(httpServletRequest,projectVo);
            if (project != null)
                return new CodeMsg<>(200,"查询该产品的信息成功！",project);
            return new CodeMsg<>(-1,"查询该产品的信息失败！",null);
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
    @PostMapping("/addProject")
    public CodeMsg<Object> addProject(HttpServletRequest httpServletRequest, @RequestBody ProjectVo projectVo){
        try {
            //  添加项目，并关联到关系表（若不成关系则id为0）
            CodeMsg codeMsg = projectService.addProject(httpServletRequest,projectVo);
            return codeMsg;
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
    //修改项目
    @PostMapping("/updateProject")
    public CodeMsg<Object> updateProject(HttpServletRequest httpServletRequest, @RequestBody Project project){
        try {
            //  修改项目信息
            CodeMsg codeMsg = projectService.updateProject(httpServletRequest,project);
            return codeMsg;
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
    @PostMapping("/deleteProject")
    public CodeMsg<Object> deleteProject(HttpServletRequest httpServletRequest, @RequestBody Project project){
        try {
            //  删除项目
            CodeMsg codeMsg = projectService.deleteProject(httpServletRequest,project);
            return codeMsg;
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
}