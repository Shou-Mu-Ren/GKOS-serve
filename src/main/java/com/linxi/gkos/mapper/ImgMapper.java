package com.linxi.gkos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linxi.gkos.pojo.dto.ImgDto;
import com.linxi.gkos.pojo.po.Img;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImgMapper extends BaseMapper<Img> {
    List<ImgDto> list();
}
