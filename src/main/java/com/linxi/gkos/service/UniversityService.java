package com.linxi.gkos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.pojo.dto.MajorDto;
import com.linxi.gkos.pojo.dto.UniversityDto;
import com.linxi.gkos.pojo.po.University;
import com.linxi.gkos.pojo.po.User;
import com.linxi.gkos.pojo.vo.req.MajorReqVo;
import com.linxi.gkos.pojo.vo.req.UniversityReqVo;

import java.util.List;

public interface UniversityService extends IService<University> {
    List<UniversityDto> heat(String level);
    List<UniversityDto> list(UniversityReqVo universityReqVo);
}
