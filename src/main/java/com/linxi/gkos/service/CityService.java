package com.linxi.gkos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.pojo.dto.CityDto;
import com.linxi.gkos.pojo.po.City;
import com.linxi.gkos.pojo.vo.req.CityReqVo;

import java.util.List;

public interface CityService extends IService<City> {
    List<CityDto> heat();
    List<CityDto> list(CityReqVo cityReqVo);
}
