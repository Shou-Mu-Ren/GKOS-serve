package com.linxi.gkos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linxi.gkos.pojo.dto.UniversityDto;
import com.linxi.gkos.pojo.po.Major;
import com.linxi.gkos.pojo.vo.req.MajorReqVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MajorMapper extends BaseMapper<Major> {
    List<Integer> findHeatId();
    UniversityDto findHeatMajorById(Integer id);
    List<UniversityDto> list(MajorReqVo majorReqVo);
}
