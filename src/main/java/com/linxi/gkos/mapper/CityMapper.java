package com.linxi.gkos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linxi.gkos.pojo.dto.CityDto;
import com.linxi.gkos.pojo.dto.UniversityDto;
import com.linxi.gkos.pojo.po.City;
import com.linxi.gkos.pojo.vo.req.CityReqVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CityMapper extends BaseMapper<City> {
    List<Integer> findHeatId();
    CityDto findHeatCityById(Integer id);
    List<CityDto> list(CityReqVo cityReqVo);
}
