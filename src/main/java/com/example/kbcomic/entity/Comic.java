package com.example.kbcomic.entity;

import lombok.Data;

import java.sql.Date;
@Data
public class Comic {
    private Integer comicId;
    private String comicName;
    private String comicDescription;
    private String comicCover;
    private Date createTime;
    private Date updateTime;
}
