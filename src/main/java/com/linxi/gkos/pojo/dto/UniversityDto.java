package com.linxi.gkos.pojo.dto;

import com.linxi.gkos.pojo.po.University;
import lombok.Data;

import java.util.List;

@Data
public class UniversityDto extends University {
   private String cityName;
   private String provinceId;
   private String provinceName;
   private List<MajorDto> majors;
}
