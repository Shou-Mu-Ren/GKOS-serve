package com.linxi.gkos.pojo.vo;

import lombok.Data;

@Data
public class VipVo {
    private String type;
    private Integer count;
    public VipVo(String type, Integer count){
        this.type = type;
        this.count = count;
    }
}
