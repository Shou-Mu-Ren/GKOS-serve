package com.linxi.gkos.conteoller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.common.annotation.LoginUserOrAdmin;
import com.linxi.gkos.common.annotation.MemberLoginToken;
import com.linxi.gkos.common.annotation.PassToken;
import com.linxi.gkos.common.exception.CommonException;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.common.util.MD5Util;
import com.linxi.gkos.pojo.dto.UserDto;
import com.linxi.gkos.pojo.po.User;
import com.linxi.gkos.pojo.vo.LoginVo;
import com.linxi.gkos.pojo.vo.json.DataJsonVo;
import com.linxi.gkos.pojo.vo.json.JsonVo;
import com.linxi.gkos.pojo.vo.req.LoginReqVo;
import com.linxi.gkos.pojo.vo.req.RegisterReqVo;
import com.linxi.gkos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.linxi.gkos.pojo.result.CodeMsg.REQUEST_OK;
import static com.linxi.gkos.pojo.result.CodeMsg.WRONG_DOUBLE_PASSWORD;

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


    @PassToken
    @PostMapping("/register")
    @ResponseBody
    public JsonVo register(@RequestBody RegisterReqVo registerReqVo){
        if (!registerReqVo.getPassword().equals(registerReqVo.getMakePassword())){
            return JsonVos.error(WRONG_DOUBLE_PASSWORD);
        }
        try {
            registerReqVo.setPassword(MD5Util.encryMD5(registerReqVo.getPassword().getBytes()));
            registerReqVo.setMakePassword(MD5Util.encryMD5(registerReqVo.getMakePassword().getBytes()));
        } catch (Exception e) {
            throw new CommonException("密码加密失败",e.getCause());
        }

        return service.register(registerReqVo);
    }

    @MemberLoginToken
    @PostMapping("/test")
    @ResponseBody
    public JsonVo test(@LoginUserOrAdmin UserDto userDto){
        System.out.println(userDto.getPhone());
        System.out.println(userDto.getPassword());
        System.out.println(userDto.getRole());
        return JsonVos.ok(REQUEST_OK);
    }

}
