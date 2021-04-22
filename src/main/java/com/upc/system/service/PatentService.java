package com.upc.system.service;

import com.upc.system.config.CodeMsg;
import com.upc.system.entity.Patent;
import com.upc.system.vo.PatentVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PatentService {
    List<Patent> getAllPatent(HttpServletRequest httpServletRequest);

    List<Patent> showPatent(HttpServletRequest httpServletRequest, String companyName);

    List<String> getPatentNameByCompanyNameList(HttpServletRequest httpServletRequest, String companyName);

    List<String> getPatentNameByCompanyProductNameList(HttpServletRequest httpServletRequest, String companyName, String productName);

    List<Patent> getPatentByProduct(HttpServletRequest httpServletRequest, PatentVo patentVo);

    List<Patent> getPatentByProject(HttpServletRequest httpServletRequest, PatentVo patentVo);

    List<String> getPatentNameByProject(HttpServletRequest httpServletRequest, String projectName);

    Patent getPatentMessage(HttpServletRequest httpServletRequest, PatentVo patentVo);

    CodeMsg addPatent(HttpServletRequest httpServletRequest, PatentVo patentVo);
}
