package com.linxi.gkos.pojo.po;

import lombok.Data;

@Data
public class User {
    Integer id;
    String name;
    String password;
    String subject;
    Integer grand;
    Integer rank;
    Integer vip;
    Integer resultId;
    Integer role;
}
