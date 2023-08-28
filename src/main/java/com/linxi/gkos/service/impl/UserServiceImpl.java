package com.linxi.gkos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.common.util.SMSUtil;
import com.linxi.gkos.mapper.UserMapper;
import com.linxi.gkos.pojo.dto.UserDto;
import com.linxi.gkos.pojo.po.User;
import com.linxi.gkos.pojo.vo.LoginVo;
import com.linxi.gkos.pojo.vo.json.JsonVo;
import com.linxi.gkos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.linxi.gkos.pojo.result.CodeMsg.*;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public JsonVo sendCode(String phone) {
        //生成六位数验证码
        int code = new Random().nextInt(899999) + 100000;
        SMSUtil.sendMessage(phone,code);
        redisTemplate.opsForValue().set(phone+"_code", ""+code, 5, TimeUnit.MINUTES);
        return JsonVos.ok(MESSAGE_SEND_OK);
    }

    @Override
    public LoginVo loginByCode(String phone, String code) {
        String makeCode = (String) redisTemplate.opsForValue().get(phone+"_code");
        if (!makeCode.equals(code)){
            return JsonVos.raise(CODE_ERROR);
        }
        UserDto userDto = mapper.findUserByPhone(phone);
        if (userDto == null ){
            mapper.insertUser(phone);
            return new LoginVo(mapper.findUserByPhone(phone));
        }
        return new LoginVo(userDto);
    }

    @Override
    public LoginVo loginByPassword(String phone, String password) {
        UserDto userDto = mapper.findUserByPhone(phone);
        if (userDto == null){
            return JsonVos.raise(WRONG_USERNAME);
        }
        if (userDto.getPassword().equals("sdgda5651rg")){
            return JsonVos.raise(DEFAULT_PASSWORD);
        }
        if(!userDto.getPassword().equals(password)){
            return JsonVos.raise(WRONG_PASSWORD);
        }
        return new LoginVo(userDto);
    }

}
