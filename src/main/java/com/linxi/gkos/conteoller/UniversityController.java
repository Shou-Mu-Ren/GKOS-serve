package com.linxi.gkos.conteoller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.common.annotation.LoginUser;
import com.linxi.gkos.common.annotation.PassToken;
import com.linxi.gkos.common.annotation.UserLoginToken;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.pojo.dto.MajorUniversityDto;
import com.linxi.gkos.pojo.dto.UniversityDto;
import com.linxi.gkos.pojo.dto.UserDto;
import com.linxi.gkos.pojo.po.University;
import com.linxi.gkos.pojo.vo.json.DataJsonVo;
import com.linxi.gkos.pojo.vo.json.ListJsonVo;
import com.linxi.gkos.pojo.vo.json.ListPageJsonVo;
import com.linxi.gkos.pojo.vo.req.UniversityReqVo;
import com.linxi.gkos.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/university")
public class UniversityController extends BaseController<University>{

    @Autowired
    private UniversityService service;

    @Override
    protected IService<University> getService() {
        return service;
    }

    @UserLoginToken
    @PostMapping("/heat")
    @ResponseBody
    public ListJsonVo<UniversityDto> heat(@RequestBody UniversityReqVo universityReqVo) {

        return JsonVos.ok(service.heat(universityReqVo.getLevel()));
    }

    @UserLoginToken
    @PostMapping("/list")
    @ResponseBody
    public ListPageJsonVo<UniversityDto> list(@RequestBody UniversityReqVo universityReqVo) {
        return JsonVos.ok(service.list(universityReqVo));
    }

    @UserLoginToken
    @PostMapping("/find")
    @ResponseBody
    public ListPageJsonVo<MajorUniversityDto> find(@RequestBody UniversityReqVo universityReqVo, @LoginUser UserDto userDto) {
        return JsonVos.ok(service.find(universityReqVo,userDto.getPhone()));
    }

    @UserLoginToken
    @GetMapping("/type")
    @ResponseBody
    public ListJsonVo<String> type() {
        return JsonVos.ok(service.type());
    }

}
