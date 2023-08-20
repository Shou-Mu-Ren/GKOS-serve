package com.linxi.gkos.pojo.vo.list;

import lombok.Data;

import java.util.List;

@Data
//列表返回结果
public class ListPageVo<T> {
    //总数
    private long count;

    //总页数
    private long pages;

    //具体数据
    private List<T> data;
}
