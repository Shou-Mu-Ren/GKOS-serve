package com.linxi.gkos.pojo.dto;

import com.linxi.gkos.pojo.po.Major;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Data
public class MajorDto extends Major {
    private boolean isCollect;
    private boolean isFill;
    private List<AdmitDto> admits;

    public void setIs(RedisTemplate redisTemplate, String phone){
        isCollect = redisTemplate.opsForSet().isMember("collect_"+phone,super.getId().toString());
        isFill = redisTemplate.opsForSet().isMember("fill_"+phone,super.getId().toString());
    }
}
