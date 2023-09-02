package com.linxi.gkos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.pojo.dto.MajorUniversityDto;
import com.linxi.gkos.pojo.dto.UniversityDto;
import com.linxi.gkos.pojo.po.Major;
import com.linxi.gkos.pojo.vo.req.MajorReqVo;

import java.util.List;

public interface MajorService extends IService<Major> {
    List<UniversityDto> heat(String phone);
    List<UniversityDto> list(MajorReqVo majorReqVo, String phone);
    List<UniversityDto> collectList(String phone);
    List<UniversityDto> fillList(String phone);
    List<MajorUniversityDto> find(MajorReqVo majorReqVo, String phone);
}
