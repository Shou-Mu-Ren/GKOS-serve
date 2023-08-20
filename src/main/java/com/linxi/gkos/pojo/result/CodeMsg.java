package com.linxi.gkos.pojo.result;


public enum CodeMsg {

    REQUEST_OK(200, "success"),
    REQUEST_ERROR(200, "请求出错"),



    VALIDATE_ERROR(200, "验证失败"),
    //用
    EMPTY_ACCESS_TOKEN(200, "Token不能为空，请登录后再访问"),
    TOKEN_FORMAT_ERROR(200, "Token格式错误"),
    LACK_PERMISSION(200, "用户缺少权限"),
    FILE_LACK_PERMISSION(500, "用户缺少权限"),
    WRONG_USERNAME(200, "用户名不存在"),
    WRONG_PASSWORD(200, "密码错误");

    public static final int ERROR_STATUS_CODE = 200;
    public static final String ERROR_MESSAGE = "error";
    public static final int OK_STATUS_CODE = 200;
    public static final String OK_MESSAGE = "success";
    private final int code;
    private final String msg;

    CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString(){
        return "{\"code\":" + getCode()+",\"msg\":\""+getMsg()+"\"}";
    }

}
