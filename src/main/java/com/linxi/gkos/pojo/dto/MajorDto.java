package com.linxi.gkos.pojo.dto;

import com.linxi.gkos.pojo.po.Major;
import lombok.Data;

import java.util.List;

@Data
public class MajorDto extends Major {
    private List<AdmitDto> admits;
}
