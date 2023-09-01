package com.linxi.gkos.pojo.vo;

import com.linxi.gkos.pojo.dto.CityDto;
import lombok.Data;

@Data
public class CityVo {
    private Integer id;
    private String name;
    private Integer provinceId;
    private String provinceName;
    private String img;


    public CityVo(){}
    public CityVo(CityDto cityDto, String img){
        id = cityDto.getId();
        name = cityDto.getName();
        provinceId = cityDto.getProvinceId();
        provinceName = cityDto.getProvinceName();
        this.img = img;
    }
}
