package com.linxi.gkos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.pojo.po.Result;
import com.linxi.gkos.pojo.po.User;
import com.linxi.gkos.pojo.vo.FriendVo;
import com.linxi.gkos.pojo.vo.LoginVo;
import com.linxi.gkos.pojo.vo.json.JsonVo;
import com.linxi.gkos.pojo.vo.req.LoginReqVo;

import java.util.List;

public interface ResultService extends IService<Result> {
    JsonVo sendCode(String phone);
    LoginVo loginByCode(String phone, String code);
    LoginVo loginByPassword(String phone, String password);
    JsonVo sendForget(String phone);
    JsonVo forget(LoginReqVo loginReqVo);
    FriendVo friend(Integer id);
    JsonVo updateByAdmin(Result result);
    JsonVo insertByAdmin(Result result);
    JsonVo deleteByAdmin(Result result);
}
