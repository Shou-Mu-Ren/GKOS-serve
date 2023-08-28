package com.linxi.gkos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.pojo.po.Result;
import com.linxi.gkos.pojo.vo.LoginVo;
import com.linxi.gkos.pojo.vo.json.JsonVo;

public interface ResultService extends IService<Result> {
    JsonVo sendCode(String phone);
    LoginVo loginByCode(String phone, String code);
    LoginVo loginByPassword(String phone, String password);

}
