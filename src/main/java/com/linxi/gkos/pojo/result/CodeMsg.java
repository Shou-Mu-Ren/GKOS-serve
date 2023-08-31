package com.linxi.gkos.pojo.result;


public enum CodeMsg {

    REQUEST_OK(200, "success"),
    REQUEST_ERROR(200, "请求出错"),



    VALIDATE_ERROR(200, "验证失败"),
    //用
    EMPTY_ACCESS_TOKEN(200, "Token不能为空，请登录后再访问"),
    TOKEN_FORMAT_ERROR(200, "Token格式错误"),
    TOKEN_WRONG_PASSWORD(200, "Token密码错误"),
    TOKEN_TO_DTO_WRONG(200, "Token转换异常"),
    LACK_PERMISSION(200, "用户缺少权限"),
    WRONG_USERNAME(200, "手机号未注册，请使用验证码登录"),
    WRONG_PASSWORD(200, "密码错误"),
    DEFAULT_PASSWORD(200, "密码未初始化"),
    MESSAGE_SEND_OK(200, "短信发送成功"),
    MESSAGE_SEND_ERROR(200, "短信发送失败"),
    CODE_ERROR(200, "验证码错误"),
    CODE_NOT_VALID(200, "验证码失效"),
    EXISTS_USERNAME(200, "手机号已存在"),
    WRONG_DOUBLE_PASSWORD(200, "两次密码不同"),
    WRONG_LOGIN_POSITION(200, "登录位置错误");
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
