package com.linxi.gkos.common.exception;


import com.linxi.gkos.pojo.result.CodeMsg;
import com.linxi.gkos.pojo.vo.json.JsonVo;

public class CommonException extends RuntimeException {
    private final int code;

    public CommonException() {
        this(JsonVo.CODE_ERROR, null, null);
    }

    public CommonException(String msg) {
        this(msg, null);
    }

    public CommonException(int code, String msg) {
        this(code, msg, null);
    }

    public CommonException(String msg, Throwable cause) {
        this(JsonVo.CODE_ERROR, msg, cause);
    }

    public CommonException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    public CommonException(CodeMsg codeMsg) {
        this(codeMsg, null);
    }

    public CommonException(CodeMsg codeMsg, Throwable cause) {
        this(codeMsg.getCode(), codeMsg.getMsg(), cause);
    }

    public int getCode() {
        return code;
    }
}
