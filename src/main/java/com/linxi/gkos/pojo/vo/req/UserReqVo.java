package com.linxi.gkos.pojo.vo.req;

import lombok.Data;

@Data
public class UserReqVo {
    private String name;
    private String phone;
    private String password;
    private String makePassword;
    private String code;
    private String place;
    private String school;
    private String subject;
    private Integer grand;
    private Integer rank;
    private String state;
    //0不是 1是
    private Integer vip;
    private Integer resultId;
    private String year;
}
