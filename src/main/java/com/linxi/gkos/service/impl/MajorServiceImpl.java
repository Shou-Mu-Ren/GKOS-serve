package com.linxi.gkos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linxi.gkos.mapper.MajorMapper;
import com.linxi.gkos.mapper.UserMapper;
import com.linxi.gkos.pojo.dto.UniversityDto;
import com.linxi.gkos.pojo.po.Major;
import com.linxi.gkos.pojo.vo.req.MajorReqVo;
import com.linxi.gkos.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService {
    @Autowired
    private MajorMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public List<UniversityDto> heat() {
        List<Integer> ids = mapper.findHeatId();
        List<UniversityDto> universityDtos = new ArrayList<>();
        for(int i = 0; i < 9; i++ ){
            universityDtos.add(mapper.findHeatMajorById(ids.get(i)));
        }
        return universityDtos;
    }

    @Override
    public List<UniversityDto> list(MajorReqVo majorReqVo) {
        return mapper.list(majorReqVo);
    }

    @Override
    public List<UniversityDto> collectList(String phone) {
        Set ids = redisTemplate.opsForSet().members("collect_"+phone);
        List<UniversityDto> universityDtos = new ArrayList<>();
        for (Object o : ids){
            universityDtos.add(mapper.findHeatMajorById(Integer.parseInt(o.toString())));
        }
        return universityDtos;
    }

    @Override
    public List<UniversityDto> fillList(String phone) {
        Set ids = redisTemplate.opsForSet().members("fill_"+phone);
        List<UniversityDto> universityDtos = new ArrayList<>();
        for (Object o : ids){
            universityDtos.add(mapper.findHeatMajorById(Integer.parseInt(o.toString())));
        }
        return universityDtos;
    }
}
