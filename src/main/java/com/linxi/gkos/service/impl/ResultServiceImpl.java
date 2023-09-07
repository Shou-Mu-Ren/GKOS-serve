package com.linxi.gkos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.common.util.SMSUtil;
import com.linxi.gkos.mapper.ResultMapper;
import com.linxi.gkos.pojo.dto.ResultDto;
import com.linxi.gkos.pojo.po.Result;
import com.linxi.gkos.pojo.vo.FriendVo;
import com.linxi.gkos.pojo.vo.LoginVo;
import com.linxi.gkos.pojo.vo.json.JsonVo;
import com.linxi.gkos.pojo.vo.req.LoginReqVo;
import com.linxi.gkos.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.linxi.gkos.pojo.result.CodeMsg.*;

@Service
@Transactional
public class ResultServiceImpl extends ServiceImpl<ResultMapper, Result> implements ResultService {

    @Autowired
    private ResultMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public JsonVo sendCode(String phone) {
        //生成六位数验证码
        int code = new Random().nextInt(899999) + 100000;
        SMSUtil.sendMessage(phone,code);
//        System.out.println(code);
        redisTemplate.opsForValue().set(phone+"_code_result", ""+code, 5, TimeUnit.MINUTES);
        return JsonVos.ok(MESSAGE_SEND_OK);
    }

    @Override
    public LoginVo loginByCode(String phone, String code) {
        String makeCode = (String) redisTemplate.opsForValue().get(phone+"_code_result");
        if(makeCode == null){
            return JsonVos.raise(CODE_NOT_VALID);
        }
        if (!makeCode.equals(code)){
            return JsonVos.raise(CODE_ERROR);
        }
        ResultDto resultDto = mapper.findResultByPhone(phone);
        if (resultDto == null ){
            mapper.insertResult(phone);
            return new LoginVo(mapper.findResultByPhone(phone));
        }
        return new LoginVo(resultDto);
    }

    @Override
    public LoginVo loginByPassword(String phone, String password) {
        ResultDto resultDto = mapper.findResultByPhone(phone);
        if (resultDto == null){
            return JsonVos.raise(WRONG_USERNAME);
        }
        if(resultDto.getPassword().equals("sdgda5651rg")){
            return JsonVos.raise(DEFAULT_PASSWORD);
        }
        if(!resultDto.getPassword().equals(password)){
            return JsonVos.raise(WRONG_PASSWORD);
        }
        return new LoginVo(resultDto);
    }

    @Override
    public JsonVo sendForget(String phone) {
        //生成六位数验证码
        int code = new Random().nextInt(899999) + 100000;
        SMSUtil.sendMessage(phone,code);
//        System.out.println(code);
        redisTemplate.opsForValue().set(phone+"_forget_result", ""+code, 5, TimeUnit.MINUTES);
        return JsonVos.ok(MESSAGE_SEND_OK);
    }

    @Override
    public JsonVo forget(LoginReqVo loginReqVo) {
        String makeCode = (String) redisTemplate.opsForValue().get(loginReqVo.getPhone()+"_forget_result");
        if(makeCode == null){
            return JsonVos.raise(CODE_NOT_VALID);
        }
        if (!loginReqVo.getCode().equals(makeCode)){
            return JsonVos.raise(CODE_ERROR);
        }
        mapper.updateResultPassword(loginReqVo);
        return JsonVos.ok(REQUEST_OK);
    }

    @Override
    public FriendVo friend(Integer id) {
        return new FriendVo(mapper.findResultById(id));
    }

    @Override
    public JsonVo updateByAdmin(Result result) {
        mapper.updateByAdmin(result);
        return JsonVos.ok(REQUEST_OK);
    }

    @Override
    public JsonVo insertByAdmin(Result result) {
        mapper.insertByAdmin(result);
        return JsonVos.ok(REQUEST_OK);
    }

    @Override
    public JsonVo deleteByAdmin(Result result) {
        mapper.deleteByAdmin(result);
        return JsonVos.ok(REQUEST_OK);
    }
}
