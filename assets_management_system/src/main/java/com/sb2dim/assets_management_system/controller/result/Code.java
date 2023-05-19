package com.sb2dim.assets_management_system.controller.result;

public class Code {
    // 查询成功
    public static final Integer QUERY_OK = 1100000;
    // 查询失败
    public static final Integer QUERY_ERR = 1100001;

    // 查询全部成功
    public static final Integer QUERY_ALL_OK = 1100100;
    // 查询全部失败
    public static final Integer QUERY_ALL_ERR = 1100101;

    // 按页查询成功
    public static final Integer QUERY_PAGE_OK = 1100200;
    // 按页查询失败
    public static final Integer QUERY_PAGE_ERR = 1100201;

    // 创建成功
    public static final Integer CREATE_OK = 1100300;
    // 创建失败
    public static final Integer CREATE_ERR = 1100301;

    // 更新成功
    public static final Integer UPDATE_OK = 1100400;
    // 更新失败
    public static final Integer UPDATE_ERR = 1100401;

    // 删除成功
    public static final Integer DELETE_OK = 1100500;
    // 删除失败
    public static final Integer DELETE_ERR = 1100501;

    // 登录成功
    public static final Integer LOGIN_SUCCEED = 1200000;
    // 登录失败
    public static final Integer LOGIN_FAILED = 1200001;
    // 已登录
    public static final Integer LOGGED_IN = 1200002;
    // 未登录
    public static final Integer  NOT_LOGGED_IN = 1200003;

    // 文件上传成功
    public static final Integer UPLOAD_OK = 1300000;
    // 文件上传失败
    public static final Integer UPLOAD_ERR = 1300001;
}
