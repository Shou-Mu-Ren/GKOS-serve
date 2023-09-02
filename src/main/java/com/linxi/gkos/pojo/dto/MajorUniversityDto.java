package com.linxi.gkos.pojo.dto;


import com.linxi.gkos.pojo.po.Major;
import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Data
public class MajorUniversityDto extends Major {
    private String universityName;
    private Integer cityId;
    private String cityName;
    private Integer provinceId;
    private String provinceName;
    private String type;
    private String nature;
    private Integer f11;
    private Integer f211;
    private Integer f985;
    private String universityLevel;
    private String img;
    private boolean isCollect;
    private boolean isFill;
    private List<AdmitDto> admits;

    public void setIs(RedisTemplate redisTemplate, String phone){
        isCollect = redisTemplate.opsForSet().isMember("collect_"+phone,super.getId().toString());
        isFill = redisTemplate.opsForSet().isMember("fill_"+phone,super.getId().toString());
    }

}
