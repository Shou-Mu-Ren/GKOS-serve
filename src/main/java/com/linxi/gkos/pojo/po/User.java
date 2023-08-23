package com.linxi.gkos.pojo.po;

import lombok.Data;

@Data
public class User {
    Integer id;
    String name;
    String phone;
    String password;
    String subject;
    Integer grand;
    Integer rank;
    //0不是 1是
    Integer vip;
    Integer resultId;
    //0用户 1导师 2admin
    Integer role;
}
