package com.linxi.gkos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.pojo.dto.ImgDto;
import com.linxi.gkos.pojo.po.Img;

import java.util.List;

public interface ImgService extends IService<Img> {
    List<ImgDto> list(Integer state);
}
