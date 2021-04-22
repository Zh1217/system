package com.upc.system.controller;

import com.upc.system.config.CodeMsg;
import com.upc.system.entity.User;
import com.upc.system.service.UserService;
import com.upc.system.vo.UserVo;
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
@RequestMapping("/user")
public class UserController {
    /*
     * 实现前端的账户页面相关操作
     * 包含登录、注册、修改密码、注销登录
     */
    @Autowired
    private UserService userService;
    //  注册部分
    @PostMapping("/register")
    public CodeMsg<Object> register(HttpServletRequest httpServletRequest,
                                    @RequestBody User user){
        try {
            CodeMsg codeMsg = userService.register(httpServletRequest, user);
            return codeMsg;
        }catch (Exception e){
            log.error("\n###"+e.getMessage(),e);
            return new CodeMsg<>(500, e.getMessage(),null);
        }
    }

    //  登录部分
    @PostMapping("/login")
    public CodeMsg<Object> login(HttpServletRequest httpServletRequest,
                                 @RequestBody User user){
        try {
            CodeMsg<Object> codeMsg = userService.login(httpServletRequest,user);
            //  正常登录的状态码为
            //  200，普通用户
            //  201，普通管理员
            //  202，超级管理员
            if (codeMsg.getCode() != 201 && codeMsg.getCode() != 202 && codeMsg.getCode() != 200) {
                log.info("log:"+ user.getUsername() + codeMsg.getMsg());
                return codeMsg;
            }
            log.info("log: " + user.getPhone() + "登录了!他的sessionId = " + httpServletRequest.getSession().getId());
            log.info("log: "+ codeMsg.getData());
            return codeMsg;
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }

    }

    //  用户退出
    @PostMapping("/logout")
    public CodeMsg<Object> logOut(HttpServletRequest httpServletRequest){
        try {
            User user = (User) httpServletRequest.getSession().getAttribute("user");
            log.info("log: " + user.getUsername()+ "退出了!");
            CodeMsg<Object> codeMsg = userService.logOut(httpServletRequest);
            return codeMsg;
        }catch (Exception e){
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
    //  删除用户
    @PostMapping("/deleteUser")
    public CodeMsg deleteUser(HttpServletRequest httpServletRequest,@RequestBody User user){
        try {
            CodeMsg codeMsg = userService.deleteUser(httpServletRequest, user);
            return codeMsg;
        }catch (Exception e){
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg(500, "删除失败" + e.getMessage());
        }
    }
    //  找回密码
    //  先查询用户是否存在，存在才发送
    @PostMapping("/findPhone")
    public CodeMsg<Object> findPhone(@RequestBody User user){
        try {
            CodeMsg codeMsg = userService.findPhone(user.getPhone());
            return codeMsg;
        }catch (Exception e){
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg(500, "找回密码失败：" + e.getMessage(),null);
        }
    }
    @PostMapping("/findPassword")
    public CodeMsg<Object> findPassword(@RequestBody User user){
        try {
            CodeMsg codeMsg = userService.findPassword(user);
            return codeMsg;
        }catch (Exception e){
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg(500, "找回密码失败：" + e.getMessage());
        }
    }

    //获取用户个人信息
    @PostMapping("/getUserMessage")
    public CodeMsg<Object> getUserMessage(HttpServletRequest httpServletRequest) {
        try {
            CodeMsg codeMsg = userService.getUserById(httpServletRequest);
            return codeMsg;
        } catch (Exception e) {
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
    //修改用户信息
    @PostMapping("/updateUser")
    public CodeMsg<Object> updateUser(HttpServletRequest httpServletRequest,@RequestBody User user){
        try{
            CodeMsg codeMsg = userService.updateUserMessage(httpServletRequest,user);
            return  codeMsg;
        } catch (Exception e){
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }
    //查看所有用户列表
    @PostMapping("/getAllUsers")
    public CodeMsg<Object> getAllUser(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest){
        try {
            List<UserVo> userList = userService.getAllUser(httpServletResponse,httpServletRequest);
            return new CodeMsg<>(200,"successful",userList);
        }catch (Exception e){
            log.error("\n####" + e.getMessage(), e);
            return new CodeMsg<>(500, e.getMessage(), null);
        }
    }

}
