package com.upc.system.mapper;

import com.upc.system.entity.Company;
import com.upc.system.entity.UserCompany;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CompanyMapper {
    // 根据用户的id来查找该用户绑定的所有公司的所有信息
    @Select("SELECT * FROM company WHERE id IN (SELECT company_id FROM user_company " +
            "WHERE user_id = #{userId} AND del_flag = 0) AND del_flag = 0")
    List<Company> selectCompanyByUserId(@Param("userId") long userId);
    //根据公司名称查询公司是否存在
    @Select("SELECT count(*) FROM company WHERE company_name = #{companyName} AND del_flag = 0")
    int selectCompanyByCompanyName(@Param("companyName") String companyName);
    // 插入公司信息
    @Insert("INSERT INTO company (company_name, register_time, declare_time, introduction, field, member, researcher, income, all_income, register_area, operate_time, operate_ip, operator, del_flag) " +
            "VALUES ( #{companyName}, #{registerTime}, #{declareTime}, #{introduction}, #{field}, #{member}, #{researcher}, #{income}, #{allIncome}, #{registerArea}, #{operateTime}, #{operateIp}, #{operator}, #{delFlag})")
    int insertCompany(Company company);
    //  根据公司名查找公司id
    @Select("SELECT id FROM company WHERE company_name = #{companyName} AND del_flag = 0")
    long selectCompanyIdByCompanyName(@Param("companyName") String companyName);
    //查询用户和该公司是否已经绑定
    @Select("SELECT count(*) FROM user_company WHERE user_id = #{userId} AND company_id = #{companyId} AND del_flag = 0")
    int selectUserCompany(@Param("userId") long userId, @Param("companyId") long companyId);
    //根据用户id和公司id增加二者关系
    @Insert("INSERT INTO user_company (user_id, company_id, operate_time, operate_ip, operator, del_flag) " +
            "VALUES (#{userId},#{companyId}, #{operateTime}, #{operateIp}, #{operator}, #{delFlag})")
    int addUserCompany(UserCompany userCompany);
    //查询单个公司信息
    @Select("SELECT * FROM company WHERE id = #{companyId} AND del_flag = 0")
    Company getCompanyById(long companyId);
    //  根据公司id修改公司信息
    @Update("UPDATE company SET company_name = #{companyName}, register_time = #{registerTime}, declare_time = #{declareTime}, introduction = #{introduction}, field = #{field}, member = #{member}, researcher = #{researcher}, income = #{income}, all_income = #{allIncome}, register_area = #{registerArea}, operate_time = #{operateTime}, operate_ip =  #{operateIp}, operator =  #{operator} WHERE id = #{id} AND del_flag = 0")
    int updateCompanyById(Company company);
    //根据公司id删除公司
    @Update("UPDATE company SET operate_time = #{operateTime}, operate_ip =  #{operateIp}, operator =  #{operator}, del_flag = id WHERE id = #{id}")
    int deleteCompanyById(Company company);
    //删除用户与公司的关联
    @Update("UPDATE user_company SET del_flag = id, operate_time = #{operateTime}, operate_ip = #{operateIp}, operator = #{operator} WHERE user_id = #{userId} AND company_id = #{companyId} AND del_flag = 0")
    int deleteUserCompanyById(@Param("operateTime") Timestamp operateTime, @Param("operateIp") String operateIp, @Param("operator") String operator, @Param("userId") long userId, @Param("companyId") long companyId);
    //根据用户id获取公司名称列表
    @Select("SELECT company_name FROM company WHERE id IN (SELECT company_id FROM user_company WHERE user_id = #{userId} AND del_flag = 0) AND del_flag = 0")
    List<String> selectCompanyNameByUserId(@Param("userId") long userId);
}
