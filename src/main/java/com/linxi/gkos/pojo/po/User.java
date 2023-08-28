package com.linxi.gkos.pojo.po;

import lombok.Data;

@Data
public class User {
    Integer id;
    String name;
    String phone;
    String password;
    String place;
    String school;
    String subject;
    Integer grand;
    Integer rank;
    String state;
    //0不是 1是
    Integer vip;
    Integer resultId;
    String year;
}
