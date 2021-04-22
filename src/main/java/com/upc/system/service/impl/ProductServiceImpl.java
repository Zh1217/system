package com.upc.system.service.impl;

import com.upc.system.config.CodeMsg;
import com.upc.system.entity.Product;
import com.upc.system.entity.ProductRelation;
import com.upc.system.entity.User;
import com.upc.system.mapper.CompanyMapper;
import com.upc.system.mapper.ProductMapper;
import com.upc.system.service.ProductService;
import com.upc.system.util.NetworkUtil;
import com.upc.system.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<Product> getAllProduct(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute("user");
        List<Product> productList = productMapper.selectProductByUserId(user.getId());
        return productList;
    }

    @Override
    public List<Product> showProduct(HttpServletRequest httpServletRequest, String companyName) {
        long companyId = companyMapper.selectCompanyIdByCompanyName(companyName);
        List<Product> productList = productMapper.selectProductByCompanyId(companyId);
        return productList;
    }

    @Override
    public CodeMsg<Object> addProduct(HttpServletRequest httpServletRequest, ProductVo productVo) {
        long companyId = companyMapper.selectCompanyIdByCompanyName(productVo.getCompanyName());
        productVo.setCompanyId(companyId);
        int result1 = productMapper.selectProductByProductName(productVo.getProductName());
        if (result1 == 0){
            HttpSession httpSession = httpServletRequest.getSession();
            User user = (User) httpSession.getAttribute("user");
            productVo.setOperator(user.getUsername());
            productVo.setOperateTime(new Timestamp(System.currentTimeMillis()));
            productVo.setOperateIp(NetworkUtil.getIpAddress(httpServletRequest));
            productVo.setDelFlag(0);
            int result2 = productMapper.addProduct(productVo);
            if (result2 != 0){
                long productId = productMapper.getProductIdByProductName(productVo.getProductName());
                // 检查用户是否已经绑定该公司
                int result4 = productMapper.selectCompanyProduct(productId, productVo.getCompanyId());
                if (result4 == 0){
                    ProductRelation productRelation = new ProductRelation();
                    productRelation.setProductId(productId);
                    productRelation.setCompanyId(productVo.getCompanyId());
                    productRelation.setOperateTime(new Timestamp(System.currentTimeMillis()));
                    productRelation.setOperateIp(NetworkUtil.getIpAddress(httpServletRequest));
                    productRelation.setOperator(user.getUsername());
                    productRelation.setDelFlag(0);
                    int result3 = productMapper.addProductRelation(productRelation);
                    if (result3 != 0){
                        return new CodeMsg<>(200, "公司增加产品成功！", null);
                    }
                    return new CodeMsg<>(-4, "增加产品失败（数据库错误）！", null);
                }
                return new CodeMsg<>(-3, "该公司已经绑定此产品！", null);
            }
            return new CodeMsg<>(-2, "添加产品失败（数据库错误）！", null);
        }

        return new CodeMsg<>(-1, "添加产品失败，该产品已经存在！", null);
    }

    @Override
    public CodeMsg<Object> getProductById(long productId) {
        Product product = productMapper.getProductIdById(productId);
        if (product == null)
            return new CodeMsg<>(-1, "查找产品信息失败！", null);
        return new CodeMsg<>(200, "查找产品信息成功！", product);
    }

    @Override
    public CodeMsg<Object> alterProduct(HttpServletRequest httpServletRequest, Product product) {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute("user");
        product.setOperateTime(new Timestamp(System.currentTimeMillis()));
        product.setOperateIp(NetworkUtil.getIpAddress(httpServletRequest));
        product.setOperator(user.getUsername());
        int result = productMapper.updateProductById(product);
        if (result == 0 )
            return new CodeMsg<>(-1, "修改产品信息失败！", null);
        return new CodeMsg<>(200, "修改产品信息成功！", null);
    }

    @Override
    public CodeMsg<Object> deleteProduct(HttpServletRequest httpServletRequest, ProductVo productVo) {
        long companyId = companyMapper.selectCompanyIdByCompanyName(productVo.getCompanyName());
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute("user");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String operateIp = NetworkUtil.getIpAddress(httpServletRequest);
        productVo.setCompanyId(companyId);
        productVo.setOperateTime(timestamp);
        productVo.setOperateIp(operateIp);
        productVo.setOperator(user.getUsername());
        int result1 = productMapper.deleteProductById(productVo);

        int result2 = productMapper.deleteCompanyProductById(timestamp,operateIp, user.getUsername(),productVo.getCompanyId(), productVo.getId());
        if (result1 ==0 ||result2 ==0)
            return new CodeMsg<>(-1, "删除产品失败！", null);
        return new CodeMsg<>(200, "删除产品成功！", null);
    }

    @Override
    public List<String> ProductNameList(HttpServletRequest httpServletRequest, String companyName) {
        long companyId = companyMapper.selectCompanyIdByCompanyName(companyName);
        List<String> productNameList = productMapper.selectProductNameByCompanyName(companyId);
        return productNameList;
    }


}
