package com.linxi.gkos.conteoller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.common.annotation.LoginUser;
import com.linxi.gkos.common.annotation.UserLoginToken;
import com.linxi.gkos.common.annotation.PassToken;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.common.util.MD5Util;
import com.linxi.gkos.pojo.dto.UserDto;
import com.linxi.gkos.pojo.po.Result;
import com.linxi.gkos.pojo.vo.FriendVo;
import com.linxi.gkos.pojo.vo.LoginVo;
import com.linxi.gkos.pojo.vo.json.DataJsonVo;
import com.linxi.gkos.pojo.vo.json.JsonVo;
import com.linxi.gkos.pojo.vo.req.LoginReqVo;
import com.linxi.gkos.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.linxi.gkos.pojo.result.CodeMsg.WRONG_DOUBLE_PASSWORD;

@RestController
@RequestMapping("/result")
public class ResultController extends BaseController<Result>{
    @Autowired
    private ResultService service;

    @Override
    protected IService<Result> getService() {
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

    @PassToken
    @PostMapping("/sendForget")
    @ResponseBody
    public JsonVo sendForget(@RequestBody LoginReqVo loginReqVo) {
        return service.sendForget(loginReqVo.getPhone());
    }

    @PassToken
    @PostMapping("/forget")
    @ResponseBody
    public JsonVo forget(@RequestBody LoginReqVo loginReqVo) {
        if (!loginReqVo.getPassword().equals(loginReqVo.getMakePassword())){
            return JsonVos.error(WRONG_DOUBLE_PASSWORD);
        }
        try {
            loginReqVo.setPassword(MD5Util.encryMD5(loginReqVo.getPassword().getBytes()));
        } catch (Exception e) {
            return JsonVos.raise("密码加密失败");
        }
        return service.forget(loginReqVo);

    }

    @UserLoginToken
    @PostMapping("/friend")
    @ResponseBody
    public DataJsonVo<FriendVo> friend(@LoginUser UserDto userDto) {
        return JsonVos.ok(service.friend(userDto.getResultId()));
    }
}
