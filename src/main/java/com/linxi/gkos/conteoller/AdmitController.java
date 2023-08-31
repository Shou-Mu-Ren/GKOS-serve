package com.linxi.gkos.conteoller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.pojo.po.Admit;
import com.linxi.gkos.pojo.po.User;
import com.linxi.gkos.service.AdmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admit")
public class AdmitController extends BaseController<Admit>{

    @Autowired
    private AdmitService service;

    @Override
    protected IService<Admit> getService() {
        return service;
    }
}
