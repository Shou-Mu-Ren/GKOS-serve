package com.linxi.gkos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.pojo.po.User;
import com.linxi.gkos.pojo.vo.LoginVo;

public interface AdminService extends IService<User> {
    LoginVo login(String phone, String password);
}
