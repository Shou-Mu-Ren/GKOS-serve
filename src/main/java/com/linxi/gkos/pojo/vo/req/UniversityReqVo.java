package com.linxi.gkos.pojo.vo.req;

import lombok.Data;

@Data
public class UniversityReqVo {
    private String name;
    private String provinceName;
    private String cityName;
    private String type;
    private String level;
    private Integer pageSize;
    private Integer pageNum;
}
