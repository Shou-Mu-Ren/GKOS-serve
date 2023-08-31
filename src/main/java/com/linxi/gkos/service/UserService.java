package com.linxi.gkos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.pojo.po.User;
import com.linxi.gkos.pojo.vo.FriendVo;
import com.linxi.gkos.pojo.vo.LoginVo;
import com.linxi.gkos.pojo.vo.json.JsonVo;
import com.linxi.gkos.pojo.vo.req.CollectAndFillReqVo;
import com.linxi.gkos.pojo.vo.req.LoginReqVo;

import java.util.List;


public interface UserService extends IService<User> {
    JsonVo sendCode(String phone);
    LoginVo loginByCode(String phone, String code);
    LoginVo loginByPassword(String phone, String password);
    JsonVo sendForget(String phone);
    JsonVo forget(LoginReqVo loginReqVo);
    JsonVo analyse(User user);
    JsonVo info(User user);
    JsonVo collect(CollectAndFillReqVo collectAndFillReqVo);
    JsonVo fill(CollectAndFillReqVo collectAndFillReqVo);
    JsonVo vip(User user);
    List<FriendVo> friend(Integer id);
}
