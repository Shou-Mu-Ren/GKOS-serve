package com.linxi.gkos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.common.util.SMSUtil;
import com.linxi.gkos.mapper.UserMapper;
import com.linxi.gkos.pojo.dto.UniversityDto;
import com.linxi.gkos.pojo.dto.UserDto;
import com.linxi.gkos.pojo.po.User;
import com.linxi.gkos.pojo.vo.FriendVo;
import com.linxi.gkos.pojo.vo.LoginVo;
import com.linxi.gkos.pojo.vo.json.JsonVo;
import com.linxi.gkos.pojo.vo.req.CollectAndFillReqVo;
import com.linxi.gkos.pojo.vo.req.LoginReqVo;
import com.linxi.gkos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
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
//        System.out.println(code);
        redisTemplate.opsForValue().set(phone+"_code", ""+code, 5, TimeUnit.MINUTES);
        return JsonVos.ok(MESSAGE_SEND_OK);
    }

    @Override
    public LoginVo loginByCode(String phone, String code) {
        String makeCode = (String) redisTemplate.opsForValue().get(phone+"_code");
        if(makeCode == null){
            return JsonVos.raise(CODE_NOT_VALID);
        }
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

    @Override
    public JsonVo sendForget(String phone) {
        //生成六位数验证码
        int code = new Random().nextInt(899999) + 100000;
        SMSUtil.sendMessage(phone,code);
//        System.out.println(code);
        redisTemplate.opsForValue().set(phone+"_forget", ""+code, 5, TimeUnit.MINUTES);
        return JsonVos.ok(MESSAGE_SEND_OK);
    }

    @Override
    public JsonVo forget(LoginReqVo loginReqVo) {
        String makeCode = (String) redisTemplate.opsForValue().get(loginReqVo.getPhone()+"_forget");
        if(makeCode == null){
            return JsonVos.raise(CODE_NOT_VALID);
        }
        if (!loginReqVo.getCode().equals(makeCode)){
            return JsonVos.raise(CODE_ERROR);
        }
        mapper.updateUserPassword(loginReqVo);

        return JsonVos.ok(REQUEST_OK);
    }

    @Override
    public JsonVo analyse(User user) {
        try {
            mapper.updateUserByAnalyse(user);
        }catch (Exception e){
            return JsonVos.error(REQUEST_ERROR);
        }
        return JsonVos.ok(REQUEST_OK);
    }

    @Override
    public JsonVo info(User user) {
        try {
            mapper.updateUserByInfo(user);
        }catch (Exception e){
            return JsonVos.error(REQUEST_ERROR);
        }
        return JsonVos.ok(REQUEST_OK);
    }

    @Override
    public JsonVo collect(CollectAndFillReqVo collectAndFillReqVo) {
        if(collectAndFillReqVo.getState()==0){
            redisTemplate.opsForSet().add("collect_"+ collectAndFillReqVo.getPhone(), collectAndFillReqVo.getMajorId());
        }else{
            redisTemplate.opsForSet().remove("collect_"+ collectAndFillReqVo.getPhone(), collectAndFillReqVo.getMajorId());
        }
        return JsonVos.ok(REQUEST_OK);
    }

    @Override
    public JsonVo fill(CollectAndFillReqVo collectAndFillReqVo) {
        if(collectAndFillReqVo.getState()==0){
            redisTemplate.opsForSet().add("fill_"+ collectAndFillReqVo.getPhone(), collectAndFillReqVo.getMajorId());
        }else{
            redisTemplate.opsForSet().remove("fill_"+ collectAndFillReqVo.getPhone(), collectAndFillReqVo.getMajorId());
        }
        return JsonVos.ok(REQUEST_OK);
    }

    @Override
    public JsonVo vip(User user) {
        Integer i = mapper.findResultId();
        Integer id = new Random().nextInt(i) + 1;
        user.setResultId(id);
        if(user.getVip() == 1){
            mapper.updateUserVip(user);
        }else{
            redisTemplate.opsForValue().set(user.getPhone()+"_vip", user.getPhone(), 60*24 , TimeUnit.MINUTES);
        }
        mapper.updateUserResultId(user);
        redisTemplate.opsForSet().add("message_"+ id, user.getId());
        return JsonVos.ok(REQUEST_OK);
    }

    @Override
    public List<FriendVo> friend(Integer id) {
        Set ids = redisTemplate.opsForSet().members("message_"+id);
        List<FriendVo> friendVos = new ArrayList<>();
        for (Object o : ids){
            friendVos.add(new FriendVo(mapper.findUserById(Integer.parseInt(o.toString()))));
        }
        return friendVos;
    }
}
