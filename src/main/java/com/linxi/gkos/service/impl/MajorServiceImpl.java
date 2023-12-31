package com.linxi.gkos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linxi.gkos.mapper.MajorMapper;
import com.linxi.gkos.mapper.UserMapper;
import com.linxi.gkos.pojo.dto.MajorDto;
import com.linxi.gkos.pojo.dto.MajorUniversityDto;
import com.linxi.gkos.pojo.dto.UniversityDto;
import com.linxi.gkos.pojo.po.Major;
import com.linxi.gkos.pojo.vo.list.ListPageVo;
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
    public List<UniversityDto> heat(String phone) {
        List<Integer> ids = mapper.findHeatId();
        List<UniversityDto> universityDtos = new ArrayList<>();
        for(int i = 0; i < 9; i++ ){
            universityDtos.add(mapper.findHeatMajorById(ids.get(i)));
        }
        for(UniversityDto universityDto : universityDtos){
            for (MajorDto majorDto: universityDto.getMajors()){
                majorDto.setIs(redisTemplate, phone);
            }
        }
        return universityDtos;
    }

    @Override
    public ListPageVo<UniversityDto> list(MajorReqVo majorReqVo, String phone) {
        if (majorReqVo.getPageSize() != null){
            majorReqVo.setPageSize((majorReqVo.getPageSize()-1) * majorReqVo.getPageNum());
        }
        List<UniversityDto> universityDtos = mapper.list(majorReqVo);
        for(UniversityDto universityDto : universityDtos){
            for (MajorDto majorDto: universityDto.getMajors()){
                majorDto.setIs(redisTemplate, phone);
            }
        }
        ListPageVo<UniversityDto> universityDtoListPageVo = new ListPageVo<>(universityDtos);
        universityDtoListPageVo.setCount(mapper.count(majorReqVo));
        return universityDtoListPageVo;
    }

    @Override
    public List<MajorUniversityDto> collectList(String phone) {
        Set ids = redisTemplate.opsForSet().members("collect_"+phone);
        List<MajorUniversityDto> majorUniversityDtos = new ArrayList<>();
        for (Object o : ids){
            majorUniversityDtos.add(mapper.findMajorById(Integer.parseInt(o.toString())));
        }
        for(MajorUniversityDto majorUniversityDto: majorUniversityDtos){
            majorUniversityDto.setIs(redisTemplate,phone);
        }
        return majorUniversityDtos;
    }

    @Override
    public List<MajorUniversityDto> fillList(String phone) {
        Set ids = redisTemplate.opsForSet().members("fill_"+phone);
        List<MajorUniversityDto> majorUniversityDtos = new ArrayList<>();
        for (Object o : ids){
            majorUniversityDtos.add(mapper.findMajorById(Integer.parseInt(o.toString())));
        }
        for(MajorUniversityDto majorUniversityDto: majorUniversityDtos){
            majorUniversityDto.setIs(redisTemplate,phone);
        }
        return majorUniversityDtos;
    }

    @Override
    public ListPageVo<MajorUniversityDto> find(MajorReqVo majorReqVo, String phone) {
        if (majorReqVo.getPageSize() != null){
            majorReqVo.setPageSize((majorReqVo.getPageSize()-1) * majorReqVo.getPageNum());
        }
        List<MajorUniversityDto> majorUniversityDtos = mapper.find(majorReqVo);
        for (MajorUniversityDto majorUniversityDto : majorUniversityDtos) {
            majorUniversityDto.setIs(redisTemplate, phone);
        }
        ListPageVo<MajorUniversityDto> majorUniversityDtoListPageVo = new ListPageVo<>(majorUniversityDtos);
        majorUniversityDtoListPageVo.setCount(mapper.count(majorReqVo));
        return majorUniversityDtoListPageVo;
    }
}
