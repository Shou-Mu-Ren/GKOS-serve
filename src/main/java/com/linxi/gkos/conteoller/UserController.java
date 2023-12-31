package com.linxi.gkos.conteoller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.common.annotation.*;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.common.util.MD5Util;
import com.linxi.gkos.pojo.dto.ResultDto;
import com.linxi.gkos.pojo.dto.UserDto;
import com.linxi.gkos.pojo.po.Result;
import com.linxi.gkos.pojo.po.User;
import com.linxi.gkos.pojo.vo.FriendVo;
import com.linxi.gkos.pojo.vo.LoginVo;
import com.linxi.gkos.pojo.vo.InfoVo;
import com.linxi.gkos.pojo.vo.VipVo;
import com.linxi.gkos.pojo.vo.json.DataJsonVo;
import com.linxi.gkos.pojo.vo.json.JsonVo;
import com.linxi.gkos.pojo.vo.json.ListJsonVo;
import com.linxi.gkos.pojo.vo.req.CollectAndFillReqVo;
import com.linxi.gkos.pojo.vo.req.LoginReqVo;
import com.linxi.gkos.pojo.vo.req.UniversityReqVo;
import com.linxi.gkos.pojo.vo.req.UserReqVo;
import com.linxi.gkos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.linxi.gkos.pojo.result.CodeMsg.REQUEST_ERROR;
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


    @UserLoginToken
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

    @UserLoginToken
    @GetMapping("/info")
    @ResponseBody
    public DataJsonVo<InfoVo> info(@LoginUser UserDto userDto) {
        return JsonVos.ok(new InfoVo(userDto));
    }

    @UserLoginToken
    @PostMapping("/infoUpdate")
    @ResponseBody
    public JsonVo infoUpdate(@RequestBody UserReqVo userReqVo, @LoginUser UserDto userDto) {
        User user = new User();
        user.setPhone(userDto.getPhone());
        user.setPlace(userReqVo.getPlace());
        user.setSchool(userReqVo.getSchool());
        user.setYear(userReqVo.getYear());
        user.setSubject(userReqVo.getSubject());
        user.setState(userReqVo.getState());
        return service.infoUpdate(user);
    }

    @UserLoginToken
    @PostMapping("/collect")
    @ResponseBody
    public JsonVo collect(@RequestBody CollectAndFillReqVo collectAndFillReqVo, @LoginUser UserDto userDto) {
        collectAndFillReqVo.setPhone(userDto.getPhone());
        return service.collect(collectAndFillReqVo);
    }

    @UserLoginToken
    @PostMapping("/fill")
    @ResponseBody
    public JsonVo fill(@RequestBody CollectAndFillReqVo collectAndFillReqVo, @LoginUser UserDto userDto) {
        collectAndFillReqVo.setPhone(userDto.getPhone());
        return service.fill(collectAndFillReqVo);
    }

    @UserLoginToken
    @PostMapping("/vip")
    @ResponseBody
    public JsonVo vip(@RequestBody UserReqVo userReqVo, @LoginUser UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setPhone(userDto.getPhone());
        user.setVip(userReqVo.getVip());
        return service.vip(user);
    }

    @ResultLoginToken
    @PostMapping("/friend")
    @ResponseBody
    public ListJsonVo<FriendVo> friend(@LoginResult ResultDto resultDto) {
        return JsonVos.ok(service.friend(resultDto.getId()));
    }

    @PassToken
    @GetMapping("/list")
    @ResponseBody
    public ListJsonVo<InfoVo> list() {
        List<InfoVo> infoVos = new ArrayList<>();
        for(User user : service.list()){
            infoVos.add(new InfoVo(user));
        }
        return JsonVos.ok(infoVos);
    }

    @AdminLoginToken
    @GetMapping("/vipCount")
    @ResponseBody
    public ListJsonVo<VipVo> vipCount() {
        return JsonVos.ok(service.vipCount());
    }

    @AdminLoginToken
    @PostMapping("/updateByAdmin")
    @ResponseBody
    public JsonVo updateByAdmin(@RequestBody UserReqVo userReqVo) {
        User user = new User();
        user.setPhone(userReqVo.getPhone());
        user.setSchool(userReqVo.getSchool());
        user.setResultId(userReqVo.getResultId());
        user.setSubject(userReqVo.getSubject());
        user.setGrand(userReqVo.getGrand());
        user.setYear(userReqVo.getYear());
        user.setPlace(userReqVo.getPlace());
        return service.updateByAdmin(user);
    }

    @AdminLoginToken
    @PostMapping("/insertByAdmin")
    @ResponseBody
    public JsonVo insertByAdmin(@RequestBody UserReqVo userReqVo) {
        User user = new User();
        user.setPhone(userReqVo.getPhone());
        user.setSchool(userReqVo.getSchool());
        user.setResultId(userReqVo.getResultId());
        user.setSubject(userReqVo.getSubject());
        user.setGrand(userReqVo.getGrand());
        user.setYear(userReqVo.getYear());
        user.setPlace(userReqVo.getPlace());
        return service.insertByAdmin(user);
    }

    @AdminLoginToken
    @PostMapping("/deleteByAdmin")
    @ResponseBody
    public JsonVo deleteByAdmin(@RequestBody UserReqVo userReqVo) {
        User user = new User();
        user.setPhone(userReqVo.getPhone());
        return service.deleteByAdmin(user);
    }


    @PassToken
    @PostMapping("/adminLogin")
    @ResponseBody
    public DataJsonVo<LoginVo> adminLogin(@RequestBody LoginReqVo loginReqVo) {
        UserDto userDto = new UserDto();
        userDto.setPhone("admin");
        userDto.setPassword("admin");
        if(loginReqVo.getPhone().equals(userDto.getPhone())
                &&loginReqVo.getPassword().equals(userDto.getPassword())){
            return JsonVos.ok(new LoginVo(userDto));
        }
        return JsonVos.raise(REQUEST_ERROR);
    }

}
