package com.linxi.gkos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linxi.gkos.mapper.ImgMapper;
import com.linxi.gkos.pojo.dto.ImgDto;
import com.linxi.gkos.pojo.po.Img;
import com.linxi.gkos.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgServiceImpl extends ServiceImpl<ImgMapper, Img> implements ImgService {
    @Autowired
    private ImgMapper mapper;


    @Override
    public List<ImgDto> list(Integer state) {
        return mapper.list();
    }
}
