package com.linxi.gkos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linxi.gkos.mapper.ArticleMapper;
import com.linxi.gkos.pojo.dto.ArticleDto;
import com.linxi.gkos.pojo.po.Article;
import com.linxi.gkos.pojo.vo.req.ArticleReqVo;
import com.linxi.gkos.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    private ArticleMapper mapper;

    @Override
    public List<ArticleDto> list(ArticleReqVo articleReqVo) {
        if (articleReqVo.getPageSize() != null){
            articleReqVo.setPageSize((articleReqVo.getPageSize()-1) * articleReqVo.getPageNum());
        }
        return mapper.list(articleReqVo);
    }
}
