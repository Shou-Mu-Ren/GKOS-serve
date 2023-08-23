package com.linxi.gkos.pojo.vo;

import com.linxi.gkos.common.util.JwtUtil;
import com.linxi.gkos.pojo.dto.UserDto;
import lombok.Data;


@Data
public class LoginVo {
    private Integer id;
    private String name;
    private String phone;
    private String subject;
    private Integer grand;
    private Integer rank;
    private Integer vip;
    private Integer resultId;
    private Integer role;
    private String token;
    public LoginVo(){}
    public LoginVo(UserDto userDto){
        id = userDto.getId();
        name = userDto.getName();
        phone = userDto.getPhone();
        subject = userDto.getSubject();
        grand = userDto.getGrand();
        rank = userDto.getRank();
        vip = userDto.getVip();
        resultId = userDto.getResultId();
        role = userDto.getRole();
        this.token = JwtUtil.getToken(userDto.getPhone(),userDto.getPassword());
    }
}
