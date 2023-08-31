package com.linxi.gkos.conteoller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.common.annotation.LoginUser;
import com.linxi.gkos.common.annotation.MemberLoginToken;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.pojo.dto.UniversityDto;
import com.linxi.gkos.pojo.dto.UserDto;
import com.linxi.gkos.pojo.po.Major;
import com.linxi.gkos.pojo.po.User;
import com.linxi.gkos.pojo.vo.json.ListJsonVo;
import com.linxi.gkos.pojo.vo.req.MajorReqVo;
import com.linxi.gkos.pojo.vo.req.UniversityReqVo;
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

    @MemberLoginToken
    @PostMapping("/heat")
    @ResponseBody
    public ListJsonVo<UniversityDto> heat() {
        return JsonVos.ok(service.heat());
    }

    @MemberLoginToken
    @PostMapping("/list")
    @ResponseBody
    public ListJsonVo<UniversityDto> list(@RequestBody MajorReqVo majorReqVo) {
        return JsonVos.ok(service.list(majorReqVo));
    }

    @MemberLoginToken
    @PostMapping("/collectList")
    @ResponseBody
    public ListJsonVo<UniversityDto> collectList(@LoginUser UserDto userDto) {
        return JsonVos.ok(service.collectList(userDto.getPhone()));
    }

    @MemberLoginToken
    @PostMapping("/fillList")
    @ResponseBody
    public ListJsonVo<UniversityDto> fillList(@LoginUser UserDto userDto) {
        return JsonVos.ok(service.fillList(userDto.getPhone()));
    }
}
