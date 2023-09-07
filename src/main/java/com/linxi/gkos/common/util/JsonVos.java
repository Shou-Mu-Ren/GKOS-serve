package com.linxi.gkos.common.util;


import com.linxi.gkos.common.exception.CommonException;
import com.linxi.gkos.pojo.result.CodeMsg;
import com.linxi.gkos.pojo.vo.json.DataJsonVo;
import com.linxi.gkos.pojo.vo.json.JsonVo;
import com.linxi.gkos.pojo.vo.json.ListJsonVo;
import com.linxi.gkos.pojo.vo.json.ListPageJsonVo;
import com.linxi.gkos.pojo.vo.list.ListPageVo;

import java.util.List;

import static com.linxi.gkos.pojo.result.CodeMsg.REQUEST_OK;


public class JsonVos {
    public static JsonVo ok(String msg) {
        return new JsonVo(true,msg);
    }
    public static JsonVo ok(CodeMsg codeMsg) {
        return new JsonVo(codeMsg);
    }

    public static <T> DataJsonVo<T> ok(T data) {
        return new DataJsonVo<>(REQUEST_OK,data);
    }

    public static <T> ListJsonVo<T> ok(List<T> dates) {
        return new ListJsonVo<>(REQUEST_OK,dates);
    }

    public static <T> ListPageJsonVo<T> ok(ListPageVo<T> resVo) {
        ListPageJsonVo<T> r = new ListPageJsonVo<>("success",resVo.getData());
        r.setCount(resVo.getCount());
        return r;
    }

    public static JsonVo ok() {
        return new JsonVo();
    }

    public static <T> DataJsonVo<T> error(CodeMsg codeMsg,T data) {
        return new DataJsonVo<>(codeMsg,data);
    }

    public static JsonVo error(CodeMsg codeMsg) {
        return new JsonVo(codeMsg);
    }

    public static JsonVo error() {
        return new JsonVo(false);
    }

    public static JsonVo error(String msg) {
        return new JsonVo(false, msg);
    }

    public static JsonVo error(Integer code, String msg) {
        return new JsonVo(code, msg);
    }

    public static <T> T raise(String msg) throws CommonException {
        throw new CommonException(msg);
    }

    public static <T> T raise(CodeMsg codeMsg) throws CommonException {
        throw new CommonException(codeMsg);
    }
}
