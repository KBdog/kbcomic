package com.example.kbcomic.entity;

import lombok.Data;

@Data
public class Chapter {
    private Integer chapterId;
    private String chapterName;
    private Integer comicId;
}
