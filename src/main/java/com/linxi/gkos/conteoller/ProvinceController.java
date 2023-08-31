package com.linxi.gkos.conteoller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.common.annotation.UserLoginToken;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.pojo.po.Province;
import com.linxi.gkos.pojo.vo.json.ListJsonVo;
import com.linxi.gkos.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/province")
public class ProvinceController extends BaseController<Province>{

    @Autowired
    private ProvinceService service;

    @Override
    protected IService<Province> getService() {
        return service;
    }

    @UserLoginToken
    @PostMapping("/list")
    @ResponseBody
    public ListJsonVo<Province> list() {
        return JsonVos.ok(service.list());
    }

}
