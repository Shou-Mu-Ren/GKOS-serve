package com.linxi.gkos.conteoller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.common.annotation.UserLoginToken;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.pojo.dto.ArticleDto;
import com.linxi.gkos.pojo.dto.ImgDto;
import com.linxi.gkos.pojo.po.Article;
import com.linxi.gkos.pojo.vo.json.ListJsonVo;
import com.linxi.gkos.pojo.vo.req.ArticleReqVo;
import com.linxi.gkos.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController<Article> {
    @Autowired
    private ArticleService service;


    @Override
    protected IService<Article> getService() {
        return service;
    }

    @UserLoginToken
    @PostMapping("/list")
    @ResponseBody
    public ListJsonVo<ArticleDto> list(@RequestBody ArticleReqVo articleReqVo) {

        return JsonVos.ok(service.list(articleReqVo));
    }
}
