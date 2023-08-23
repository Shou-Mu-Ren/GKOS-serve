package com.linxi.gkos.conteoller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.common.annotation.PassToken;
import com.linxi.gkos.common.exception.CommonException;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.common.util.MD5Util;
import com.linxi.gkos.pojo.po.User;
import com.linxi.gkos.pojo.vo.LoginVo;
import com.linxi.gkos.pojo.vo.json.DataJsonVo;
import com.linxi.gkos.pojo.vo.req.LoginReqVo;
import com.linxi.gkos.service.AdminService;
import com.linxi.gkos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController<User>{
    @Autowired
    private AdminService service;

    @Override
    protected IService<User> getService() {
        return service;
    }

    @PassToken
    @PostMapping("/login")
    @ResponseBody
    public DataJsonVo<LoginVo> login(@RequestBody LoginReqVo loginReqVo) {
        try {
            loginReqVo.setPassword(MD5Util.encryMD5(loginReqVo.getPassword().getBytes()));
        } catch (Exception e) {
            throw new CommonException("密码加密失败",e.getCause());
        }
        return JsonVos.ok(service.login(loginReqVo.getPhone(), loginReqVo.getPassword()));
    }
}
