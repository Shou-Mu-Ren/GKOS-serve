package com.linxi.gkos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linxi.gkos.pojo.dto.ResultDto;
import com.linxi.gkos.pojo.po.Result;
import com.linxi.gkos.pojo.po.User;
import com.linxi.gkos.pojo.vo.req.LoginReqVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResultMapper extends BaseMapper<Result> {
    ResultDto findResultById(Integer id);
    ResultDto findResultByPhone(String phone);
    void insertResult(String phone);
    void updateResultPassword(LoginReqVo loginReqVo);
    void updateByAdmin(Result result);
    void insertByAdmin(Result result);
    void deleteByAdmin(Result result);
}
