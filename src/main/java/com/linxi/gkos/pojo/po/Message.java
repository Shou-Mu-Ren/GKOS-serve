package com.linxi.gkos.pojo.po;

import lombok.Data;

import java.util.Date;


@Data
public class Message {
    private Integer id;
    private String toId;
    private String fromId;
    private String content;
    private Date createTime;
}
