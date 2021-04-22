package com.upc.system.service.impl;

import com.upc.system.config.CodeMsg;
import com.upc.system.entity.User;
import com.upc.system.mapper.CompanyMapper;
import com.upc.system.mapper.UserMapper;
import com.upc.system.service.UserService;
import com.upc.system.util.MD5Util;
import com.upc.system.util.NetworkUtil;
import com.upc.system.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.upc.system.config.CodeMsg.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CompanyMapper companyMapper;


    //  用户注册
    @Override
    public CodeMsg<Object> register(HttpServletRequest httpServletRequest, User user) {
        //  注册需要提供用户名、手机号、密码、所属地区
        //  注册时间为当前时间、操作ip、操作者是自己、操作时间和当前时间一致、
        //  身份标识、登陆状态和软删除标志手动指定
        //  前端传来的输入信息合法
        //  先检验该用户是否已经存在，手机号唯一
        if (userMapper.selectUserByPhone(user.getPhone()) == null) {
            user.setPassword(MD5Util.md5(user.getPassword()));
            user.setRegisterTime(new Timestamp(System.currentTimeMillis()));
            user.setOperateTime(user.getRegisterTime());
            user.setOperateIp(NetworkUtil.getIpAddress(httpServletRequest));
            user.setOperator(user.getUsername());
            user.setIdentity(2);
            user.setStatus(0);
            user.setDelFlag(0);
            int insertResult = userMapper.insertRegisterUser(user);
            if (insertResult != 0){
                return REGISTER_SUCCESS;
            } else{
                return REGISTER_ERROR_DATABASE_ERROR;
            }

        } else {
            return REGISTER_ERROR_USER_EXIST;
        }
    }


    //  登录
    @Override
    public CodeMsg<Object> login(HttpServletRequest httpServletRequest, User user) {
        // 验证帐号、密码是否为空,输入为空的时候
        if ("".equals(user.getPhone()) || user.getPhone() == null) {  //  账号为空
            return LOGIN_ERROR_PHONE_EMPTY;
        } else if ("".equals(user.getPassword()) || user.getPassword() == null) {   //  密码为空
            return LOGIN_ERROR_PASSWORD_EMPTY;
        }
        // 从数据库，根据tel_number查找用户，用User userQuery对象保存
        User userQuery = userMapper.selectUserByPhone(user.getPhone());
        // 验证输入的帐号密码 是否与查找到的userQuery的帐号密码匹配。
        if (userQuery == null) {    //  用户不存在
            return LOGIN_ERROR_USER_ERROR;
        } else if (!userQuery.getPassword().equals(MD5Util.md5(user.getPassword()))) {   //  输入的密码错误
            return LOGIN_ERROR_PASSWORD_ERROR;
        } else if (userQuery.getStatus() == 1){
            return LOGIN_ERROR_USER_ONLINE;
        }
        //  登陆成功
        /*
        将数据库中查询得到的对象转存为userQuery，存到session中，
        再将userTmp更新登录相关状态存入数据库中
        然后清除session中的密码
         */
        //  向数据库更新本次登陆信息
        //  需要更新的信息有在线状态，当前时间，当前IP，当前操作者
        User userTmp = userMapper.selectUserByPhone(user.getPhone());
        //userTmp.setStatus(1);
        userTmp.setOperateTime(new Timestamp(System.currentTimeMillis()));
        userTmp.setOperateIp(NetworkUtil.getIpAddress(httpServletRequest));
        userTmp.setOperator(userQuery.getUsername());
        //  写入数据库
        userMapper.loginUpdateUserByPhone(userTmp);
        //返回前端的信息
        userQuery.setPassword("");
        userQuery.setStatus(1);
        //获取session
        HttpSession httpSession = httpServletRequest.getSession();
        //设置session属性，将用户存入session
        httpSession.setAttribute("user", userQuery);
        //设置session保留时长：3小时 3*60*60=10800s
        httpSession.setMaxInactiveInterval(10800);
        //  判断登录的用户的身份
        if (userQuery.getIdentity() == 0){
            return new CodeMsg<>(201, "登录成功！", userQuery);
        }
        if (userQuery.getIdentity() == 1){
            return new CodeMsg<>(202, "登录成功！", userQuery);
        }
        return new CodeMsg<>(200, "登录成功！", userQuery);
    }
    //  用户退出
    @Override
    public CodeMsg<Object> logOut(HttpServletRequest httpServletRequest) {
        //获取session，使session无效，即可退出登录，并修改用户登录状态
        HttpSession httpSession = httpServletRequest.getSession();
        httpServletRequest.getSession();
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        httpSession.invalidate();
        userMapper.logOutUpdateUserStatusByPhone(0,user.getPhone());
        return LOGOUT_SUCCESS;
    }
    //  管理员删除用户
    @Override
    public CodeMsg<Object> deleteUser(HttpServletRequest httpServletRequest, User user) {
        user.setOperateTime(new Timestamp(System.currentTimeMillis()));
        user.setOperateIp(NetworkUtil.getIpAddress(httpServletRequest));
        User userTmp = (User) httpServletRequest.getSession().getAttribute("user");
        user.setOperator(userTmp.getUsername());
        int result = userMapper.deleteUserByPhone(user);
        if (result == 0){
            return new CodeMsg(-1, "删除用户失败，该用户不存在！",null);
        } else {
            return new CodeMsg(200, "删除用户成功！",null);
        }
    }
    //  检查用户是否存在，存在发送验证码
    @Override
    public CodeMsg<Object> findPhone(String phone) {
        User user = userMapper.selectUserByPhone(phone);
        if (user == null){
            return new CodeMsg<>(-1, "查找失败，该用户不存在！", null);
        }
        //int checkCode =  new Random().nextInt((899999)+100000);
        int checkCode =  123456;
        //留着写发短信的接口


        return new CodeMsg(200, "验证码发送成功，请注意查收！",checkCode);
    }
    //重置密码
    @Override
    public CodeMsg<Object> findPassword(User user) {
        int result = userMapper.findPasswordByPhone(MD5Util.md5(user.getPassword()), user.getPhone());
        if (result == 0){
            return FIND_PASSWORD_FAILED;
        } else {
            return FIND_PASSWORD_SUCCESS;
        }
    }

    //  获取单个用户的信息
    @Override
    public CodeMsg getUserById(HttpServletRequest httpServletRequest) {
        User user1 = (User)httpServletRequest.getSession().getAttribute("user");
        Long userId = user1.getId();
        User user = userMapper.getById(userId);
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setUsername(user.getUsername());
        userVo.setPassword(user.getPassword());
        userVo.setPhone(user.getPhone());
        userVo.setArea(user.getArea());
        userVo.setIdentity(user.getIdentity());
        userVo.setRegisterTime(user.getRegisterTime());
        userVo.setOperateIp(user.getOperateIp());
        userVo.setOperateTime(user.getOperateTime());
        userVo.setOperator(user.getOperator());
        userVo.setCompanys(companyMapper.selectCompanyByUserId(userId));
        return new CodeMsg<>(200,"查找成功！",userVo);
    }
    //  更新用户的信息
    @Override
    public CodeMsg updateUserMessage(HttpServletRequest httpServletRequest, User user) {
        User user2 = (User) httpServletRequest.getSession().getAttribute("user");
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPhone(user.getPhone());
        user1.setOperateTime(new Timestamp(System.currentTimeMillis()));
        user1.setOperateIp(NetworkUtil.getIpAddress(httpServletRequest));
        user1.setOperator(user2.getUsername());
        user1.setStatus(user.getStatus());
        user1.setIdentity(user.getIdentity());
        user1.setRegisterTime(user.getRegisterTime());
        int result = userMapper.updateUser(user1);
        if (result !=0 )
            return  new CodeMsg(200,"用户信息修改成功！",null);
        return  new CodeMsg(-1,"用户信息修改失败！",null);
    }
    //  获取全部用户信息
    @Override
    public List<UserVo> getAllUser(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
        List <User> userList = userMapper.getAllUser();
        List<UserVo> userVoList = new ArrayList<>();
        for (User user:userList){
            UserVo userVo = new UserVo();
            userVo.setId(user.getId());
            userVo.setUsername(user.getUsername());
            userVo.setPassword(user.getPassword());
            userVo.setPhone(user.getPhone());
            userVo.setIdentity(user.getIdentity());
            userVo.setStatus(user.getStatus());
            userVo.setOperateIp(user.getOperateIp());
            userVo.setOperateTime(user.getOperateTime());
            userVo.setOperator(user.getOperator());
            userVo.setCompanys(companyMapper.selectCompanyByUserId(user.getId()));
            userVoList.add(userVo);
        }
        return userVoList;
    }

}
