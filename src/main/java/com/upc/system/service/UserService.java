package com.upc.system.service;

import com.upc.system.config.CodeMsg;
import com.upc.system.entity.User;
import com.upc.system.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

    CodeMsg<Object> register(HttpServletRequest httpServletRequest, User user);
    CodeMsg<Object> login(HttpServletRequest httpServletRequest, User user);
    CodeMsg<Object> logOut(HttpServletRequest httpServletRequest);
    CodeMsg<Object> deleteUser(HttpServletRequest httpServletRequest, User user);
    CodeMsg<Object> findPassword(User user);
    CodeMsg<Object> findPhone(String phone);
    //管理员部分
    CodeMsg<Object> getUserById(HttpServletRequest httpServletRequest);
    CodeMsg updateUserMessage(HttpServletRequest httpServletRequest, User user);
    List<UserVo> getAllUser(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest);

}
