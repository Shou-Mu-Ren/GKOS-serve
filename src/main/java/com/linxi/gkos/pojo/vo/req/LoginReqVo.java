package com.linxi.gkos.pojo.vo.req;

import lombok.Data;

@Data
public class LoginReqVo {
    private String phone;
    private String password;
    private String code;
}
