package com.linxi.gkos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.mapper.UniversityMapper;
import com.linxi.gkos.mapper.UserMapper;
import com.linxi.gkos.pojo.dto.MajorDto;
import com.linxi.gkos.pojo.dto.UniversityDto;
import com.linxi.gkos.pojo.po.University;
import com.linxi.gkos.pojo.po.User;
import com.linxi.gkos.pojo.result.CodeMsg;
import com.linxi.gkos.pojo.vo.req.MajorReqVo;
import com.linxi.gkos.pojo.vo.req.UniversityReqVo;
import com.linxi.gkos.service.UniversityService;
import com.sun.xml.internal.ws.encoding.soap.SOAP12Constants;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<UniversityDto> list(UniversityReqVo universityReqVo) {
        return mapper.list(universityReqVo);
    }
}
