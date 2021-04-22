package com.upc.system.controller;

import com.upc.system.config.CodeMsg;
import com.upc.system.entity.Company;
import com.upc.system.entity.Product;
import com.upc.system.service.ProductService;
import com.upc.system.vo.ProductVo;
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
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    //根据用户id获取全部产品
    @PostMapping("/getAllProduct")
    public CodeMsg<Object> getAllProduct(HttpServletRequest httpServletRequest){
        try{
            List<Product> productList = productService.getAllProduct(httpServletRequest);
            if (!productList.isEmpty())
                return new CodeMsg<>(200,"查询该用户的所有产品列表成功！",productList);
            return new CodeMsg<>(-1,"没有产品，请先添加产品！",null);
        }catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
    // 查询公司下的产品
    @PostMapping("/main")
    public CodeMsg<Object> showProduct( HttpServletRequest httpServletRequest,@RequestBody ProductVo productVo){
        try{
            List<Product> productList = productService.showProduct(httpServletRequest, productVo.getCompanyName());
            if (!productList.isEmpty())
                return new CodeMsg<>(200,"查询该公司的产品列表成功！",productList);
            return new CodeMsg<>(-1,"没有产品，请先添加产品！",null);
        }catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
    //获取公司产品名称列表
    @RequestMapping("/getProductNameList")
    public CodeMsg<Object> getProductNameList(HttpServletRequest httpServletRequest, @RequestBody ProductVo productVo){
        try {
            //  根据公司名称获取绑定产品名称列表
            List<String> productNameList = productService.ProductNameList(httpServletRequest,productVo.getCompanyName());
            if (!productNameList.isEmpty())
                return new CodeMsg<>(200,"查询该公司管理的产品列表成功！",productNameList);
            return new CodeMsg<>(-1,"该公司没有产品，请先添加产品！",null);
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }

    //公司增加产品
    @PostMapping("/addProduct")
    public CodeMsg<Product> addProduct(HttpServletRequest httpServletRequest,@RequestBody ProductVo productVo){
        try{
            CodeMsg msg = productService.addProduct(httpServletRequest,productVo);
            return msg;
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
    //查询单个产品
    @RequestMapping("/getProduct")
    public CodeMsg<Object> getProduct(HttpServletRequest httpServletRequest,@RequestBody Product product) {
        try {
            CodeMsg codeMsg = productService.getProductById(product.getId());
            return codeMsg;
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
    //修改产品信息
    @RequestMapping("/alterProduct")
    public CodeMsg<Company> alterProduct(HttpServletRequest httpServletRequest,@RequestBody Product product){
        try{
            CodeMsg msg = productService.alterProduct(httpServletRequest,product);
            return msg;
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }

    //删除产品
    @RequestMapping("/deleteProduct")
    public CodeMsg<Company> deleteProduct(HttpServletRequest httpServletRequest,@RequestBody ProductVo productVo){
        try{
            CodeMsg msg = productService.deleteProduct(httpServletRequest,productVo);
            return msg;
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }

}
