package com.linxi.gkos.pojo.vo.req;

import lombok.Data;
import org.omg.PortableInterceptor.INACTIVE;

@Data
public class MajorReqVo {
    private String name;
    private String level;
    private Integer pageSize;
    private Integer pageNum;
}
