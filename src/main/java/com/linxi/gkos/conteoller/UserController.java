package com.linxi.gkos.conteoller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.common.annotation.LoginResult;
import com.linxi.gkos.common.annotation.LoginUser;
import com.linxi.gkos.common.annotation.MemberLoginToken;
import com.linxi.gkos.common.annotation.PassToken;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.common.util.MD5Util;
import com.linxi.gkos.pojo.dto.ResultDto;
import com.linxi.gkos.pojo.dto.UserDto;
import com.linxi.gkos.pojo.po.User;
import com.linxi.gkos.pojo.vo.FriendVo;
import com.linxi.gkos.pojo.vo.LoginVo;
import com.linxi.gkos.pojo.vo.json.DataJsonVo;
import com.linxi.gkos.pojo.vo.json.JsonVo;
import com.linxi.gkos.pojo.vo.json.ListJsonVo;
import com.linxi.gkos.pojo.vo.req.CollectAndFillReqVo;
import com.linxi.gkos.pojo.vo.req.LoginReqVo;
import com.linxi.gkos.pojo.vo.req.UserReqVo;
import com.linxi.gkos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @MemberLoginToken
    @PostMapping("/analyse")
    @ResponseBody
    public JsonVo analyse(@RequestBody UserReqVo userReqVo, @LoginUser UserDto userDto) {
        User user = new User();
        user.setPhone(userDto.getPhone());
        user.setYear(userReqVo.getYear());
        user.setGrand(userReqVo.getGrand());
        user.setRank(userReqVo.getRank());
        user.setSubject(userReqVo.getSubject());
        return service.analyse(user);
    }

    @MemberLoginToken
    @PostMapping("/info")
    @ResponseBody
    public JsonVo info(@RequestBody UserReqVo userReqVo, @LoginUser UserDto userDto) {
        if (!userReqVo.getPassword().equals(userReqVo.getMakePassword())){
            return JsonVos.error(WRONG_DOUBLE_PASSWORD);
        }
        User user = new User();
        user.setPhone(userDto.getPhone());
        user.setPlace(userReqVo.getPlace());
        user.setSchool(userReqVo.getSchool());
        user.setYear(userReqVo.getYear());
        user.setSubject(userReqVo.getSubject());
        user.setState(userReqVo.getState());
        try {
            user.setPassword(MD5Util.encryMD5(userReqVo.getPassword().getBytes()));
        } catch (Exception e) {
            return JsonVos.raise("密码加密失败");
        }
        return service.info(user);
    }

    @MemberLoginToken
    @PostMapping("/collect")
    @ResponseBody
    public JsonVo collect(@RequestBody CollectAndFillReqVo collectAndFillReqVo, @LoginUser UserDto userDto) {
        collectAndFillReqVo.setPhone(userDto.getPhone());
        return service.collect(collectAndFillReqVo);
    }

    @MemberLoginToken
    @PostMapping("/fill")
    @ResponseBody
    public JsonVo fill(@RequestBody CollectAndFillReqVo collectAndFillReqVo, @LoginUser UserDto userDto) {
        collectAndFillReqVo.setPhone(userDto.getPhone());
        return service.fill(collectAndFillReqVo);
    }

    @MemberLoginToken
    @PostMapping("/vip")
    @ResponseBody
    public JsonVo vip(@RequestBody UserReqVo userReqVo, @LoginUser UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setPhone(userDto.getPhone());
        user.setVip(userReqVo.getVip());
        return service.vip(user);
    }

    @MemberLoginToken
    @PostMapping("/friend")
    @ResponseBody
    public ListJsonVo<FriendVo> friend(@LoginResult ResultDto resultDto) {

        return JsonVos.ok(service.friend(resultDto.getId()));
    }
}
