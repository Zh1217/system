package com.upc.system.mapper;

import com.upc.system.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    //  查询用户是否已经存在
    //  根据手机号查询
    @Select("SELECT * FROM user WHERE phone = #{phone} AND del_flag = 0")
    User selectUserByPhone(@Param("phone") String phone);
    //  注册插入语句
    @Insert("INSERT INTO user ( username, phone, password, area, identity, status, register_time, operate_time, operate_ip, operator, del_flag) VALUES ( #{username}, #{phone}, #{password}, #{area}, #{identity}, #{status}, #{registerTime}, #{operateTime}, #{operateIp}, #{operator}, #{delFlag})")
    int insertRegisterUser(User user);
    //  根据账号更新用户相关信息        更新用户登录IP、时间、操作者和在线状态
    @Update("UPDATE user SET status = #{status}, operate_time = #{operateTime}, operate_ip =  #{operateIp}, operator =  #{operator} WHERE phone = #{phone} and del_flag = 0")
    int loginUpdateUserByPhone(User user);
    // 用户退出更新登录状态
    @Update("UPDATE user SET status = #{status} WHERE phone = #{phone} and del_flag = 0")
    int logOutUpdateUserStatusByPhone(@Param("status") int status, @Param("phone") String phone);
    // 管理员删除用户
    @Update("UPDATE user SET del_flag = id, operate_ip = #{operateIp}, operator = #{operator}, operate_time = #{operateTime} WHERE phone = #{phone} AND del_flag = 0")
    int deleteUserByPhone(User user);
    //  找回密码
    @Update("UPDATE user SET password = #{password} WHERE phone = #{phone} and del_flag = 0")
    int findPasswordByPhone(@Param("password") String password, @Param("phone") String phone);

    //  根据id查询用户信息
    @Select("SELECT * FROM user WHERE id = #{id} AND del_flag = 0 ")
    User getById(Long userId);
    //  管理员修改更新用户信息
    @Update("update user set username = #{username}, phone=#{phone}, status=#{status}, identity=#{identity}, register_time=#{registerTime}, operate_ip = #{operateIp}, operator = #{operator}, operate_time = #{operateTime} where id = #{id} AND del_flag = 0")
    int updateUser(User user);
    //  管理员获取全部用户信息
    @Select("select * from user where del_flag = 0")
    List<User> getAllUser();
}
