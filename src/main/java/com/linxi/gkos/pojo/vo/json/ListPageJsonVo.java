package com.linxi.gkos.pojo.vo.json;


import com.linxi.gkos.pojo.result.CodeMsg;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
//响应结果--带列表
public class ListPageJsonVo<T> extends DataJsonVo<List<T>> {
    //总数
    private Long count;

    public ListPageJsonVo(List<T> data) {
        super(data);
    }

    public ListPageJsonVo() {
    }

    public ListPageJsonVo(boolean ok) {
        super(ok);
    }

    public ListPageJsonVo(boolean ok, String msg) {
        super(ok, msg);
    }

    public ListPageJsonVo(int code, String msg) {
        super(code, msg);
    }

    public ListPageJsonVo(CodeMsg codeMsg) {
        super(codeMsg);
    }

    public ListPageJsonVo(String msg, List<T> data) {
        super(msg, data);
    }
}
