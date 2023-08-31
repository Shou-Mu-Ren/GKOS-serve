package com.linxi.gkos.pojo.vo.req;

import lombok.Data;

import java.util.Date;

@Data
public class MessageReqVo {
    private Integer toId;
    private Integer fromId;
    private String content;
}
