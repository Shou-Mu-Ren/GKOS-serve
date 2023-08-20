package com.linxi.gkos.pojo.vo.json;


import com.linxi.gkos.pojo.result.CodeMsg;
import lombok.Data;


//响应结果
@Data
public class JsonVo {
    protected static final int CODE_OK = CodeMsg.REQUEST_OK.getCode();
    public static final int CODE_ERROR = CodeMsg.REQUEST_ERROR.getCode();

    private Integer code;

    //描述信息

    private String msg;

    public JsonVo() {
        this(true);
    }

    public JsonVo(boolean ok) {
        this(ok, null);
    }

    public JsonVo(boolean ok, String msg) {
        this(ok ? CODE_OK : CODE_ERROR, msg);
    }

    public JsonVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonVo(CodeMsg codeMsg) {
        this(codeMsg.getCode(), codeMsg.getMsg());
    }
}
