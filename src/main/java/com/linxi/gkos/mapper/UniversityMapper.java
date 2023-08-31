package com.linxi.gkos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linxi.gkos.pojo.dto.UniversityDto;
import com.linxi.gkos.pojo.po.University;
import com.linxi.gkos.pojo.po.User;
import com.linxi.gkos.pojo.vo.req.MajorReqVo;
import com.linxi.gkos.pojo.vo.req.UniversityReqVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UniversityMapper extends BaseMapper<University> {
    List<String> findHeatCode(String level);
    UniversityDto findHeatUniversityByCode(String code);
    List<UniversityDto> list(UniversityReqVo universityReqVo);
}
