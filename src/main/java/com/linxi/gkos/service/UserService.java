package com.linxi.gkos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.pojo.po.User;
import com.linxi.gkos.pojo.vo.LoginVo;
import com.linxi.gkos.pojo.vo.json.JsonVo;


public interface UserService extends IService<User> {
    JsonVo sendCode(String phone);
    LoginVo loginByCode(String phone, String code);
    LoginVo loginByPassword(String phone, String password);

}
