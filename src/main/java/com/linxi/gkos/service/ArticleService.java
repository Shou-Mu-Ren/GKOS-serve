package com.linxi.gkos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.pojo.dto.ArticleDto;
import com.linxi.gkos.pojo.po.Article;
import com.linxi.gkos.pojo.vo.req.ArticleReqVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ArticleService extends IService<Article> {
    List<ArticleDto> list(ArticleReqVo articleReqVo);
}
