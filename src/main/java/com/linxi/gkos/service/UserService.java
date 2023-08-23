package com.linxi.gkos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.pojo.po.User;
import com.linxi.gkos.pojo.vo.LoginVo;
import com.linxi.gkos.pojo.vo.json.JsonVo;
import com.linxi.gkos.pojo.vo.req.RegisterReqVo;

public interface UserService extends IService<User> {
    LoginVo login(String phone, String password);
    JsonVo register(RegisterReqVo registerReqVo);
}
