package com.linxi.gkos.conteoller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.common.annotation.UserLoginToken;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.pojo.dto.CityDto;
import com.linxi.gkos.pojo.po.City;
import com.linxi.gkos.pojo.vo.CityVo;
import com.linxi.gkos.pojo.vo.json.ListJsonVo;
import com.linxi.gkos.pojo.vo.req.CityReqVo;
import com.linxi.gkos.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/city")
public class CityController extends BaseController<City>{

    @Autowired
    private CityService service;

    @Override
    protected IService<City> getService() {
        return service;
    }

    @UserLoginToken
    @PostMapping("/heat")
    @ResponseBody
    public ListJsonVo<CityVo> heat() {
        return JsonVos.ok(service.heat());
    }

    @UserLoginToken
    @PostMapping("/list")
    @ResponseBody
    public ListJsonVo<CityVo> list(@RequestBody CityReqVo cityReqVo) {
        return JsonVos.ok(service.list(cityReqVo));
    }
}
