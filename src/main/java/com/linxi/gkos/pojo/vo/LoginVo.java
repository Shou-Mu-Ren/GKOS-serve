package com.linxi.gkos.pojo.vo;

import com.linxi.gkos.common.util.JwtUtil;
import com.linxi.gkos.pojo.dto.ResultDto;
import com.linxi.gkos.pojo.dto.UserDto;
import lombok.Data;


@Data
public class LoginVo {
    Integer id;
    String name;
    String phone;
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
    private String token;
    public LoginVo(){}
    public LoginVo(ResultDto resultDto){
        id = resultDto.getId();
        name = resultDto.getName();
        phone = resultDto.getPhone();
        this.token = JwtUtil.getToken(resultDto.getPhone(),resultDto.getPassword());
    }
    public LoginVo(UserDto userDto){
        id = userDto.getId();
        name = userDto.getName();
        phone = userDto.getPhone();
        place = userDto.getPlace();
        school = userDto.getSchool();
        subject = userDto.getSubject();
        grand = userDto.getGrand();
        rank = userDto.getRank();
        state = userDto.getState();
        vip = userDto.getVip();
        resultId = userDto.getResultId();
        year = userDto.getYear();
        this.token = JwtUtil.getToken(userDto.getPhone(),userDto.getPassword());
    }
}
