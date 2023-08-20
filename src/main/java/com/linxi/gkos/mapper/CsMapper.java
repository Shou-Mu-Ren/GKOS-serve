package com.linxi.gkos.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linxi.gkos.pojo.po.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

@Mapper
public interface CsMapper extends BaseMapper<Province> {

    void insertProvince(String name);
    Integer findProvinceIdByName(String name);
    void insertCity(City city);
    Integer findCityIdByName(String name);
    void insertUniversity(University university);
    void insertUniversityByAll(University university);
    String findUniversityNameByCode(String code);
    void insertMajorByAll(Major major);
    Integer findMajorIdByCodeAndUniversityCode(Major major);
    void insertAdmit(Admit admit);
    void insertAdmitByAll(Admit admit);
    void insertAdmit2(Admit admit);
    void insertAdmitByAll2(Admit admit);
    Integer findAdmitIdByMajorIdAndYear(Admit admit);
    void updateAdmitGrand(Admit admit);
    void updateAdmitGrandByRank(Admit admit);
}
