package com.linxi.gkos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linxi.gkos.common.exception.CommonException;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.mapper.UserMapper;
import com.linxi.gkos.pojo.dto.UserDto;
import com.linxi.gkos.pojo.po.User;
import com.linxi.gkos.pojo.result.CodeMsg;
import com.linxi.gkos.pojo.vo.LoginVo;
import com.linxi.gkos.pojo.vo.json.JsonVo;
import com.linxi.gkos.pojo.vo.req.RegisterReqVo;
import com.linxi.gkos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.linxi.gkos.pojo.result.CodeMsg.*;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public LoginVo login(String phone, String password) {
        UserDto userDto = mapper.findUserByPhone(phone);
        if (userDto == null){
            JsonVos.raise(WRONG_USERNAME);
        }
        if(!userDto.getPassword().equals(password)){
            JsonVos.raise(WRONG_PASSWORD);
        }
        if(userDto.getRole() != 0){
            JsonVos.raise(WRONG_LOGIN_POSITION);
        }
        return new LoginVo(userDto);
    }

    @Override
    public JsonVo register(RegisterReqVo registerReqVo) {
        if (mapper.findUserByPhone(registerReqVo.getPhone()) != null ){
            return JsonVos.error(EXISTS_USERNAME);
        }
        User user = new User();
        user.setPhone(registerReqVo.getPhone());
        user.setPassword(registerReqVo.getPassword());
        mapper.insertUser(user);
        return JsonVos.ok(REQUEST_OK);
    }

}
