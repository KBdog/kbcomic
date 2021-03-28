package com.example.kbcomic.entity;

import lombok.Data;

@Data
public class Pic {
    private Integer id;
    private Integer picId;
    private String picUrl;
    private Integer chapterId;
}
