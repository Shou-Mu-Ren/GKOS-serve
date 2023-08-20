package com.linxi.gkos.pojo.po;

import lombok.Data;

import java.util.Date;


@Data
public class Message {
    Integer id;
    Integer toId;
    Integer fromId;
    String content;
    Date createTime;
}
