package com.linxi.gkos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linxi.gkos.mapper.ProvinceMapper;
import com.linxi.gkos.mapper.UserMapper;
import com.linxi.gkos.pojo.po.Province;
import com.linxi.gkos.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province> implements ProvinceService {
    @Autowired
    private ProvinceMapper mapper;
}
