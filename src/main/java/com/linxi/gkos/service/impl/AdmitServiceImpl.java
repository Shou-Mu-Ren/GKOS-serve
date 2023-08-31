package com.linxi.gkos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linxi.gkos.mapper.AdmitMapper;
import com.linxi.gkos.mapper.UserMapper;
import com.linxi.gkos.pojo.po.Admit;
import com.linxi.gkos.service.AdmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdmitServiceImpl extends ServiceImpl<AdmitMapper, Admit> implements AdmitService {
    @Autowired
    private AdmitMapper mapper;
}
