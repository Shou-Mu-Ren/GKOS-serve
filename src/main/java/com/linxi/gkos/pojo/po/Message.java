package com.linxi.gkos.pojo.po;

import lombok.Data;

import java.util.Date;


@Data
public class Message {
    Integer id;
    String toId;
    String fromId;
    String content;
    Date createTime;
}
