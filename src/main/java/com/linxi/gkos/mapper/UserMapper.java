package com.linxi.gkos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linxi.gkos.pojo.dto.UniversityDto;
import com.linxi.gkos.pojo.dto.UserDto;
import com.linxi.gkos.pojo.po.University;
import com.linxi.gkos.pojo.po.User;
import com.linxi.gkos.pojo.vo.req.LoginReqVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    UserDto findUserById(Integer id);
    UserDto findUserByPhone(String phone);
    void insertUser(String phone);
    void updateUserPassword(LoginReqVo loginReqVo);
    void updateUserByAnalyse(User user);
    void updateUserByInfo(User user);
    void updateUserVip(User user);
    Integer findResultId();
    void updateUserResultId(User user);
    Integer vipCount();
    void updateByAdmin(User user);
    void insertByAdmin(User user);
    void deleteByAdmin(User user);
}
