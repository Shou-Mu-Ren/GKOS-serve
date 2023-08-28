package com.linxi.gkos.conteoller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.common.annotation.PassToken;
import com.linxi.gkos.common.exception.CommonException;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.common.util.MD5Util;
import com.linxi.gkos.pojo.po.User;
import com.linxi.gkos.pojo.vo.LoginVo;
import com.linxi.gkos.pojo.vo.json.DataJsonVo;
import com.linxi.gkos.pojo.vo.json.JsonVo;
import com.linxi.gkos.pojo.vo.req.LoginReqVo;
import com.linxi.gkos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User>{

    @Autowired
    private UserService service;

    @Override
    protected IService<User> getService() {
        return service;
    }


    @PassToken
    @PostMapping("/sendCode")
    @ResponseBody
    public JsonVo sendCode(@RequestBody LoginReqVo loginReqVo) {
        return service.sendCode(loginReqVo.getPhone());
    }

    @PassToken
    @PostMapping("/loginByCode")
    @ResponseBody
    public DataJsonVo<LoginVo> loginByCode(@RequestBody LoginReqVo loginReqVo) {
        return JsonVos.ok(service.loginByCode(loginReqVo.getPhone(), loginReqVo.getCode()));
    }

    @PassToken
    @PostMapping("/loginByPassword")
    @ResponseBody
    public DataJsonVo<LoginVo> loginByPassword(@RequestBody LoginReqVo loginReqVo) {

        try {
            loginReqVo.setPassword(MD5Util.encryMD5(loginReqVo.getPassword().getBytes()));
        } catch (Exception e) {
            return JsonVos.raise("密码加密失败");
        }
        return JsonVos.ok(service.loginByPassword(loginReqVo.getPhone(), loginReqVo.getPassword()));
    }


}
