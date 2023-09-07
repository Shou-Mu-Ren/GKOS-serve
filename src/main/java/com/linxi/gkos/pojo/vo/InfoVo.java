package com.linxi.gkos.pojo.vo;

import com.linxi.gkos.pojo.dto.ResultDto;
import com.linxi.gkos.pojo.dto.UserDto;
import com.linxi.gkos.pojo.po.Result;
import com.linxi.gkos.pojo.po.User;
import lombok.Data;

@Data
public class InfoVo {
    private Integer id;
    private String name;
    private String phone;
    private Integer age;
    private Integer sex;
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
        age = resultDto.getAge();
        sex = resultDto.getSex();
        place = resultDto.getPlace();
    }
    public InfoVo(Result result){
        id = result.getId();
        name = result.getName();
        phone = result.getPhone();
        age = result.getAge();
        sex = result.getSex();
        place = result.getPlace();
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

    public InfoVo(User user){
        id = user.getId();
        name = user.getName();
        phone = user.getPhone();
        place = user.getPlace();
        school = user.getSchool();
        subject = user.getSubject();
        grand = user.getGrand();
        rank = user.getRank();
        state = user.getState();
        vip = user.getVip();
        resultId = user.getResultId();
        year = user.getYear();
    }
}
