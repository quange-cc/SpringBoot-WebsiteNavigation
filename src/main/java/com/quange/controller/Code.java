package com.quange.controller;

/**
 * 声明返回的状态码值
 */
public class Code {

    // 登录成功
    public static final Integer LOGIN_OK = 1001;

    // 登录失败
    public static final Integer LOGIN_ERR = 1002;

    //保存成功返回码：2001
    public static final Integer SAVE_OK = 2001;

    //删除成功返回码：2002
    public static final Integer DELETE_OK = 2002;

    //修改成功返回码：2003
    public static final Integer UPDATE_OK = 2003;

    //查询成功状态码：2004
    public static final Integer GET_OK = 2004;

    //保存失败返回码：4041
    public static final Integer SAVE_ERR = 4041;

    // 删除失败返回码:4042
    public static final Integer DELETE_ERR = 4042;

    // 修改失败返回码: 4043
    public static final Integer UPDATE_ERR = 4043;

    //查询失败返回码: 4044
    public static final Integer GET_ERR = 4044;


    // 系统错误码：5001
    public static final Integer SYSTEM_ERR = 5001;

    public static final Integer SYSTEM_TIMEOUT_ERR = 5002;

    public static final Integer SYSTEM_UNKNOW_ERR = 5999;

    public static final Integer BUSINESS_ERR = 6001;



    // token 有效
    public static final Integer TOKEN_OK = 7000;
    // token 无效
    public static final Integer TOKEN_ERR = 7404;
}
