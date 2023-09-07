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
import com.linxi.gkos.pojo.vo.InfoVo;
import com.linxi.gkos.pojo.vo.LoginVo;
import com.linxi.gkos.pojo.vo.json.DataJsonVo;
import com.linxi.gkos.pojo.vo.json.JsonVo;
import com.linxi.gkos.pojo.vo.json.ListJsonVo;
import com.linxi.gkos.pojo.vo.req.LoginReqVo;
import com.linxi.gkos.pojo.vo.req.ResultReqVo;
import com.linxi.gkos.pojo.vo.req.UserReqVo;
import com.linxi.gkos.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @ResultLoginToken
    @GetMapping("/info")
    @ResponseBody
    public DataJsonVo<InfoVo> info(@LoginResult ResultDto resultDto) {

        return JsonVos.ok(new InfoVo(resultDto));
    }

    @UserLoginToken
    @PostMapping("/friend")
    @ResponseBody
    public DataJsonVo<FriendVo> friend(@LoginUser UserDto userDto) {
        return JsonVos.ok(service.friend(userDto.getResultId()));
    }

    @PassToken
    @GetMapping("/list")
    @ResponseBody
    public ListJsonVo<InfoVo> list() {
        List<InfoVo> infoVos = new ArrayList<>();
        for(Result result : service.list()){
            infoVos.add(new InfoVo(result));
        }
        return JsonVos.ok(infoVos);
    }

    @AdminLoginToken
    @PostMapping("/updateByAdmin")
    @ResponseBody
    public JsonVo updateByAdmin(@RequestBody ResultReqVo resultReqVo) {
        Result result = new Result();
        result.setPhone(resultReqVo.getPhone());
        result.setName(resultReqVo.getName());
        result.setAge(resultReqVo.getAge());
        result.setSex(resultReqVo.getSex());
        result.setPlace(resultReqVo.getPlace());
        return service.updateByAdmin(result);
    }

    @AdminLoginToken
    @PostMapping("/insertByAdmin")
    @ResponseBody
    public JsonVo insertByAdmin(@RequestBody ResultReqVo resultReqVo) {
        Result result = new Result();
        result.setPhone(resultReqVo.getPhone());
        result.setName(resultReqVo.getName());
        result.setAge(resultReqVo.getAge());
        result.setSex(resultReqVo.getSex());
        result.setPlace(resultReqVo.getPlace());
        return service.insertByAdmin(result);
    }

    @AdminLoginToken
    @PostMapping("/deleteByAdmin")
    @ResponseBody
    public JsonVo deleteByAdmin(@RequestBody ResultReqVo resultReqVo) {
        Result result = new Result();
        result.setPhone(resultReqVo.getPhone());
        return service.deleteByAdmin(result);
    }



}
