package com.upc.system.service;

import com.upc.system.config.CodeMsg;
import com.upc.system.entity.Company;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CompanyService {
    List<Company> showCompany(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
    CodeMsg<Object> addCompany(HttpServletRequest httpServletRequest, Company company);
    CodeMsg<Object> alterCompany(HttpServletRequest httpServletRequest, Company company);
    CodeMsg<Object> deleteCompany(HttpServletRequest httpServletRequest, Company company);
    CodeMsg<Object> getCompanyById(long companyId);


    List<String> getCompanyNameList(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
