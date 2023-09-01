package com.linxi.gkos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linxi.gkos.mapper.CityMapper;
import com.linxi.gkos.mapper.UserMapper;
import com.linxi.gkos.pojo.dto.CityDto;
import com.linxi.gkos.pojo.dto.UniversityDto;
import com.linxi.gkos.pojo.po.City;
import com.linxi.gkos.pojo.vo.CityVo;
import com.linxi.gkos.pojo.vo.req.CityReqVo;
import com.linxi.gkos.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {
    @Autowired
    private CityMapper mapper;

    @Override
    public List<CityVo> heat() {
        List<Integer> ids = mapper.findHeatId();
        List<CityVo> cityVos = new ArrayList<>();
        for(int i = 0; i < 8; i++ ){
            cityVos.add(new CityVo(mapper.findHeatCityById(ids.get(i)),
                    mapper.findImgById(ids.get(i))));
        }
        return cityVos;
    }

    @Override
    public List<CityVo> list(CityReqVo cityReqVo) {
        List<CityDto> cityDtos = mapper.list(cityReqVo);
        List<CityVo> cityVos = new ArrayList<>();
        for (CityDto cityDto : cityDtos){
            cityVos.add(new CityVo(cityDto,mapper.findImgById(cityDto.getId())));
        }
        return cityVos;
    }
}
