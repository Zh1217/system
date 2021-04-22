package com.upc.system.service;

import com.upc.system.config.CodeMsg;
import com.upc.system.entity.Product;
import com.upc.system.vo.ProductVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProductService {

    List<Product> getAllProduct(HttpServletRequest httpServletRequest);

    List<Product> showProduct(HttpServletRequest httpServletRequest, String companyName);

    CodeMsg<Object> addProduct(HttpServletRequest httpServletRequest, ProductVo productVo);

    CodeMsg<Object> getProductById(long productId);

    CodeMsg<Object> alterProduct(HttpServletRequest httpServletRequest, Product product);

    CodeMsg<Object> deleteProduct(HttpServletRequest httpServletRequest, ProductVo productVo);


    List<String> ProductNameList(HttpServletRequest httpServletRequest, String companyName);
}
