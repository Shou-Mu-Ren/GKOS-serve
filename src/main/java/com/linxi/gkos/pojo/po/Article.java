package com.linxi.gkos.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class Article {
    private Integer id;
    private String title;
    private String content;
    private String author;
    private Date createTime;
    private Integer lookCount;
    private Integer favouriteCount;
}
