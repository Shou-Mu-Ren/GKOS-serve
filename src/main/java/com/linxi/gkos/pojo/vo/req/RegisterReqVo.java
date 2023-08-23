package com.linxi.gkos.pojo.vo.req;

import lombok.Data;

@Data
public class RegisterReqVo {
    private String phone;
    private String password;
    private String makePassword;
}
