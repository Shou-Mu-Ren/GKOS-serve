package com.linxi.gkos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.mapper.UniversityMapper;
import com.linxi.gkos.mapper.UserMapper;
import com.linxi.gkos.pojo.dto.MajorDto;
import com.linxi.gkos.pojo.dto.MajorUniversityDto;
import com.linxi.gkos.pojo.dto.UniversityDto;
import com.linxi.gkos.pojo.po.University;
import com.linxi.gkos.pojo.vo.list.ListPageVo;
import com.linxi.gkos.pojo.vo.req.UniversityReqVo;
import com.linxi.gkos.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.linxi.gkos.pojo.result.CodeMsg.REQUEST_ERROR;

@Service
@Transactional
public class UniversityServiceImpl extends ServiceImpl<UniversityMapper, University> implements UniversityService{
    @Autowired
    private UniversityMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public List<UniversityDto> heat(String level) {
       List<String> codes = mapper.findHeatCode(level);
       List<UniversityDto> universityDtos = new ArrayList<>();
       for(int i = 0; i < 9; i++ ){
           universityDtos.add(mapper.findHeatUniversityByCode(codes.get(i)));
       }
        return universityDtos;
    }

    @Override
    public ListPageVo<UniversityDto> list(UniversityReqVo universityReqVo) {
        if (universityReqVo.getPageSize() != null){
            universityReqVo.setPageSize((universityReqVo.getPageSize()-1) * universityReqVo.getPageNum());
        }
        ListPageVo<UniversityDto> universityDtoListPageVo = new ListPageVo<>(mapper.list(universityReqVo));
        universityDtoListPageVo.setCount(mapper.count(universityReqVo));
        return universityDtoListPageVo;
    }

    @Override
    public ListPageVo<MajorUniversityDto> find(UniversityReqVo universityReqVo, String phone) {
        if (universityReqVo.getPageSize() != null){
            universityReqVo.setPageSize((universityReqVo.getPageSize()-1) * universityReqVo.getPageNum());
        }
        List<MajorUniversityDto> majorUniversityDtos = mapper.find(universityReqVo);
        for (MajorUniversityDto majorUniversityDto : majorUniversityDtos) {
            majorUniversityDto.setIs(redisTemplate, phone);
        }
        ListPageVo<MajorUniversityDto> majorUniversityDtoListPageVo = new ListPageVo<>(majorUniversityDtos);
        majorUniversityDtoListPageVo.setCount(mapper.count(universityReqVo));
        return majorUniversityDtoListPageVo;
    }

    @Override
    public List<String> type() {
        return mapper.type();
    }

}
