package com.linxi.gkos.conteoller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.common.annotation.UserLoginToken;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.pojo.dto.ImgDto;
import com.linxi.gkos.pojo.dto.UniversityDto;
import com.linxi.gkos.pojo.po.Img;
import com.linxi.gkos.pojo.vo.json.ListJsonVo;
import com.linxi.gkos.pojo.vo.req.UniversityReqVo;
import com.linxi.gkos.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/img")
public class ImgController extends BaseController<Img>{
    @Autowired
    private ImgService service;

    @Override
    protected IService<Img> getService() {
        return service;
    }

    @UserLoginToken
    @PostMapping("/list")
    @ResponseBody
    public ListJsonVo<ImgDto> list() {
        return JsonVos.ok(service.list(1));
    }
}
