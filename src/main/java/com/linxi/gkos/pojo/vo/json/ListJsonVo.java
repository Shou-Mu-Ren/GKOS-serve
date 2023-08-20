package com.linxi.gkos.pojo.vo.json;


import com.linxi.gkos.pojo.result.CodeMsg;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
//响应结果--带列表
public class ListJsonVo<T> extends DataJsonVo<List<T>> {

    public ListJsonVo(List<T> data) {
        super(data);
    }

    public ListJsonVo() {
    }

    public ListJsonVo(boolean ok) {
        super(ok);
    }

    public ListJsonVo(boolean ok, String msg) {
        super(ok, msg);
    }

    public ListJsonVo(int code, String msg) {
        super(code, msg);
    }

    public ListJsonVo(CodeMsg codeMsg) {
        super(codeMsg);
    }

    public ListJsonVo(String msg, List<T> data) {
        super(msg, data);
    }
    public ListJsonVo(CodeMsg msg, List<T> data) {
        super(msg, data);
    }
}
