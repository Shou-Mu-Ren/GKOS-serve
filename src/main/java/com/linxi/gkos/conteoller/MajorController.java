package com.linxi.gkos.conteoller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.common.annotation.LoginUser;
import com.linxi.gkos.common.annotation.UserLoginToken;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.pojo.dto.UniversityDto;
import com.linxi.gkos.pojo.dto.UserDto;
import com.linxi.gkos.pojo.po.Major;
import com.linxi.gkos.pojo.vo.json.ListJsonVo;
import com.linxi.gkos.pojo.vo.req.MajorReqVo;
import com.linxi.gkos.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/major")
public class MajorController extends BaseController<Major>{

    @Autowired
    private MajorService service;

    @Override
    protected IService<Major> getService() {
        return service;
    }

    @UserLoginToken
    @PostMapping("/heat")
    @ResponseBody
    public ListJsonVo<UniversityDto> heat(@LoginUser UserDto userDto) {
        return JsonVos.ok(service.heat(userDto.getPhone()));
    }

    @UserLoginToken
    @PostMapping("/list")
    @ResponseBody
    public ListJsonVo<UniversityDto> list(@RequestBody MajorReqVo majorReqVo, @LoginUser UserDto userDto) {
        return JsonVos.ok(service.list(majorReqVo, userDto.getPhone()));
    }

    @UserLoginToken
    @PostMapping("/collectList")
    @ResponseBody
    public ListJsonVo<UniversityDto> collectList(@LoginUser UserDto userDto) {
        return JsonVos.ok(service.collectList(userDto.getPhone()));
    }

    @UserLoginToken
    @PostMapping("/fillList")
    @ResponseBody
    public ListJsonVo<UniversityDto> fillList(@LoginUser UserDto userDto) {
        return JsonVos.ok(service.fillList(userDto.getPhone()));
    }

}
