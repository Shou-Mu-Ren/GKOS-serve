package com.linxi.gkos.pojo.vo;

import com.linxi.gkos.pojo.dto.ResultDto;
import com.linxi.gkos.pojo.dto.UserDto;
import lombok.Data;

@Data
public class InfoVo {
    private Integer id;
    private String name;
    private String phone;
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
    public InfoVo(){}
    public InfoVo(ResultDto resultDto){
        id = resultDto.getId();
        name = resultDto.getName();
        phone = resultDto.getPhone();
    }
    public InfoVo(UserDto userDto){
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
    }
}
