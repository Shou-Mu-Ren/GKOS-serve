package com.linxi.gkos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linxi.gkos.pojo.dto.ArticleDto;
import com.linxi.gkos.pojo.po.Article;
import com.linxi.gkos.pojo.vo.req.ArticleReqVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.poi.sl.usermodel.Background;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    List<ArticleDto> list(ArticleReqVo articleReqVo);
}
