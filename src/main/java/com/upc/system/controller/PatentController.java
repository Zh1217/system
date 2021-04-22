package com.upc.system.controller;

import com.upc.system.config.CodeMsg;
import com.upc.system.entity.Patent;
import com.upc.system.entity.Project;
import com.upc.system.service.PatentService;
import com.upc.system.vo.PatentVo;
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
@RequestMapping("/patent")
public class PatentController {
    @Autowired
    private PatentService patentService;

    //根据用户id获取全部专利
    @PostMapping("/getAllPatent")
    public CodeMsg<Object> getAllProduct(HttpServletRequest httpServletRequest){
        try{
            List<Patent> patentList = patentService.getAllPatent(httpServletRequest);
            if (!patentList.isEmpty())
                return new CodeMsg<>(200,"查询该用户的所有专利列表成功！",patentList);
            return new CodeMsg<>(-1,"没有专利，请先添加专利！",null);
        }catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }

    @PostMapping("/getPatentByCompany")
    public CodeMsg<Object> showPatent(HttpServletRequest httpServletRequest, @RequestBody PatentVo patentVo){
        try {
            //  根据公司id获取绑定项目列表
            List<Patent> patentList = patentService.showPatent(httpServletRequest, patentVo.getCompanyName());
            if (!patentList.isEmpty())
                return new CodeMsg<>(200,"查询该公司的项目列表成功！",patentList);
            return new CodeMsg<>(-1,"没有项目，请先添加项目！",null);
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }

    //根据公司名获取写专利名称列表
    @PostMapping("/getPatentNameByCompanyNameList")
    public CodeMsg<Object> getPatentNameByCompanyNameList(HttpServletRequest httpServletRequest, @RequestBody PatentVo patentVo){
        try {
            //  根据公司名称获取绑定专利名称列表
            List<String> patentNameList = patentService.getPatentNameByCompanyNameList(httpServletRequest,patentVo.getCompanyName());
            if (!patentNameList.isEmpty())
                return new CodeMsg<>(200,"查询该公司管理的专利列表成功！",patentNameList);
            return new CodeMsg<>(-1,"该公司没有专利，请先添加专利！",null);
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
    //根据公司名和产品名获取专利名称列表
    @PostMapping("/getPatentNameByCompanyProductNameList")
    public CodeMsg<Object> getPatentNameByCompanyProductNameList(HttpServletRequest httpServletRequest, @RequestBody PatentVo patentVo){
        try {
            //  根据公司名称获取绑定产品名称列表
            List<String> patentNameList = patentService.getPatentNameByCompanyProductNameList(httpServletRequest,patentVo.getCompanyName(),patentVo.getProductName());
            if (!patentNameList.isEmpty())
                return new CodeMsg<>(200,"查询该公司产品的专利列表成功！",patentNameList);
            return new CodeMsg<>(-1,"该公司产品没有专利，请先添加专利！",null);
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }

    @PostMapping("/getPatentByProduct")
    public CodeMsg<Object> getPatentByProduct(HttpServletRequest httpServletRequest, @RequestBody PatentVo patentVo){
        try {
            //  根据产品id获取绑定专利列表
            List<Patent> patentList = patentService.getPatentByProduct(httpServletRequest,patentVo);
            if (!patentList.isEmpty())
                return new CodeMsg<>(200,"查询该产品的专利列表成功！",patentList);
            return new CodeMsg<>(-1,"没有专利，请先添加专利！",null);
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }

    @PostMapping("/getPatentByProject")
    public CodeMsg<Object> getPatentByProject(HttpServletRequest httpServletRequest, @RequestBody PatentVo patentVo){
        try {
            //  根据项目id获取绑定专利列表
            List<Patent> patentList = patentService.getPatentByProject(httpServletRequest,patentVo);
            if (!patentList.isEmpty())
                return new CodeMsg<>(200,"查询该产品的专利列表成功！",patentList);
            return new CodeMsg<>(-1,"没有专利，请先添加专利！",null);
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }

    @PostMapping("/getPatentNameByProject")
    public CodeMsg<Object> getPatentNameByProject(HttpServletRequest httpServletRequest, @RequestBody PatentVo patentVo){
        try {
            //  根据项目id获取绑定专利名列表
            List<String> patentNameList = patentService.getPatentNameByProject(httpServletRequest,patentVo.getProjectName());
            if (!patentNameList.isEmpty())
                return new CodeMsg<>(200,"查询该产品的专利列表成功！",patentNameList);
            return new CodeMsg<>(-1,"没有专利，请先添加专利！",null);
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }

    @PostMapping("/getPatentMessage")
    public CodeMsg<Object> getPatentMessage(HttpServletRequest httpServletRequest, @RequestBody PatentVo patentVo){
        try {
            //  查看某专利的信息
            Patent patent = patentService.getPatentMessage(httpServletRequest,patentVo);
            if (patent != null)
                return new CodeMsg<>(200,"查询该专利的信息成功！",patent);
            return new CodeMsg<>(-1,"查询该专利的信息失败！",null);
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
    @PostMapping("/addPatent")
    public CodeMsg<Object> addPatent(HttpServletRequest httpServletRequest, @RequestBody PatentVo patentVo){
        try {
            //  添加项目，并关联到关系表（若不成关系则id为0）
            CodeMsg codeMsg = patentService.addPatent(httpServletRequest,patentVo);
            return codeMsg;
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
}
