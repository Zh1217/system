package com.upc.system.mapper;

import com.upc.system.entity.Product;
import com.upc.system.entity.ProductRelation;
import com.upc.system.vo.ProductVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ProductMapper {

    //根据公司ID获取产品信息
    @Select("SELECT * FROM product where del_flag = 0 AND id IN (SELECT product_id FROM product_relation WHERE company_id = #{companyId} AND del_flag = 0)")
    List<Product> selectProductByCompanyId(@Param("companyId") long companyId);
    //根据产品名查看产品是否存在
    @Select("SELECT count(*) FROM product WHERE product_name = #{productName} AND del_flag = 0")
    int selectProductByProductName(@Param("productName") String productName);
    //插入产品信息
    @Insert("INSERT INTO product (product_name, operate_time, operate_ip, operator, del_flag) " +
            "VALUES (#{productName}, #{operateTime}, #{operateIp}, #{operator}, #{delFlag})")
    int addProduct(ProductVo productVo);
    //根据产品名称获取产品id
    @Select("SELECT id FROM product WHERE product_name = #{productName} AND del_flag = 0")
    long getProductIdByProductName(@Param("productName") String productName);
    //增加公司产品关系
    @Insert("INSERT INTO product_relation(company_id,product_id, operate_time, operate_ip, operator, del_flag) " +
            "VALUES (#{companyId}, #{productId}, #{operateTime}, #{operateIp}, #{operator}, #{delFlag})")
    int addProductRelation(ProductRelation productRelation);
    //检查该公司是否已经绑定该产品
    @Select("SELECT count(*) FROM product_relation WHERE product_id = #{productId} AND company_id = #{companyId} AND del_flag = 0")
    int selectCompanyProduct(@Param("productId")long productId, @Param("companyId") long companyId);
    //根据产品id获取产品
    @Select("SELECT * FROM product WHERE id = #{productId} AND del_flag = 0")
    Product getProductIdById(long productId);
    //修改产品信息
    @Update("UPDATE product SET product_name = #{productName},operate_time = #{operateTime}, operate_ip = #{operateIp}, operator = #{operator} WHERE id = #{id} AND del_flag = 0")
    int updateProductById(Product product);
    //删除产品
    @Update("UPDATE product SET del_flag = id,operate_time = #{operateTime}, operate_ip = #{operateIp}, operator = #{operator} WHERE id = #{id} AND del_flag = 0")
    int deleteProductById(ProductVo productVo);
    //删除产品与公司的关联
    @Update("UPDATE product_relation SET del_flag = id, operate_time = #{operateTime}, operate_ip = #{operateIp}, operator = #{operator} WHERE company_id = #{companyId} AND product_id = #{productId} AND del_flag = 0")
    int deleteCompanyProductById(@Param("operateTime")Timestamp operateTime,@Param("operateIp") String operateIp,@Param("operator") String operator,@Param("companyId") long companyId,@Param("productId") long productId);
    //根据用户id获取所有产品信息
    @Select("SELECT * FROM product where del_flag = 0 AND id IN (SELECT product_id FROM product_relation WHERE company_id IN(SELECT id FROM user_company WHERE user_id = #{userId} AND del_flag = 0) AND del_flag = 0)")
    List<Product> selectProductByUserId(@Param("userId") long userId);
    //根据公司id获取所有产品名称列表
    @Select("SELECT product_name FROM product where del_flag = 0 AND id IN (SELECT product_id FROM product_relation WHERE company_id = #{companyId} AND del_flag = 0)")
    List<String> selectProductNameByCompanyName(@Param("companyId") long companyId);

}
