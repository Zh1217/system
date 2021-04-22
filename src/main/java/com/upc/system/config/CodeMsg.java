package com.upc.system.config;



public class CodeMsg<T>{
    private int code;
    private String msg;
    private T data;

    /**
     * 通过静态方法调用CodeMsg对象获取对应error信息 自定义error 信息
     */

    //  注册异常
    public static CodeMsg REGISTER_ERROR_DATABASE_ERROR = new CodeMsg(-1,"用户保存失败！");
    public static CodeMsg REGISTER_ERROR_USER_EXIST = new CodeMsg(-2,"该用户已经注册，请直接登录！");
    public static CodeMsg REGISTER_SUCCESS = new CodeMsg<>(200, "注册成功，请登录！", null);


    //登陆异常
    public static CodeMsg LOGIN_ERROR_PHONE_EMPTY = new CodeMsg(-1,"登录失败，帐号不能为空！");
    public static CodeMsg LOGIN_ERROR_PASSWORD_EMPTY = new CodeMsg(-2,"登录失败，请输入密码！");
    public static CodeMsg LOGIN_ERROR_USER_ERROR = new CodeMsg(-3, "登录失败，帐号不存在！");
    public static CodeMsg LOGIN_ERROR_PASSWORD_ERROR = new CodeMsg(-4, "登录失败，密码错误！");
    public static CodeMsg LOGIN_ERROR_USER_ONLINE = new CodeMsg(-5, "登录失败，该用户已在线！");



    //  安全退出
    public static CodeMsg LOGOUT_SUCCESS = new CodeMsg<>(200, "已安全退出系统！", null);

    //  密码找回
    public static CodeMsg FIND_PASSWORD_FAILED = new CodeMsg<>(-1, "密码修改失败！", null);
    public static CodeMsg FIND_PASSWORD_SUCCESS = new CodeMsg<>(200, "密码修改成功！", null);


    //  公司相关
    public static CodeMsg COMPANY_ERROR_NAME_EMPTY = new CodeMsg<>(-1, "公司名为空！", null);


    /**
     * 服务端异常
     */
    public static CodeMsg SUCCESS = new CodeMsg(0, "成功");
    public static CodeMsg SERVER_ERROR = new CodeMsg(100, "系统异常");

    /**用占位符 传入一个参数*/
    public static CodeMsg BIND_ERROR = new CodeMsg(500800, "(绑定异常)参数校验异常：%s");
    public static CodeMsg SESSION_ERROR = new CodeMsg(500111, "没有SESSION！");
    public static CodeMsg REQUEST_ERROR = new CodeMsg(500789, "非法请求！");
    public static CodeMsg REQUEST_OVER_LIMIT = new CodeMsg(500999, "请求次数过多！");





    /**
     * 上传文件异常
     */

    public static CodeMsg UPLOAD_FILE_NULL = new CodeMsg(-1, "文件为空");
    public static CodeMsg UPLOAD_WORK_NAME_EMPTY = new CodeMsg(-2, "文件名不能为空");
    public static CodeMsg UPLOAD_COURSEID_EMPTY = new CodeMsg(-3, "失败，课程不能为空");
    public static CodeMsg UPLOAD_FORMID_EMPTY = new CodeMsg(-4, "失败，作品格式不能为空");
    public static CodeMsg UPLOAD_DEADLINE_OVER = new CodeMsg(-5, "提交截止时间已过，不能提交");




    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public CodeMsg(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 该方法用于返回一个CodeMsg对象 便于 全局异常处理的调用
     * 全局异常处理传入 objects 参数，并返回一个CodeMsg 对象
     * 该方法根据入参 显示 对应的异常code , 以及加入 异常信息的msg显示
     *
     * 利用可变长参数定义 ：适用于 参数类型可知，但是个数未知的情况
     */

    public CodeMsg fillArgs(Object... objects) {
        int code = this.code;
        String message = String.format(this.msg, objects);
        return new CodeMsg(code, message);
    }




}
