package com.linxi.gkos.pojo.dto;

import com.linxi.gkos.pojo.po.City;
import lombok.Data;

@Data
public class CityDto extends City {
    private String provinceName;
}
