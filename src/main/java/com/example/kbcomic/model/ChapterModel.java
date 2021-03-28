package com.example.kbcomic.model;

import lombok.Data;

import java.util.List;

@Data
public class ChapterModel {
    private Integer chapterId;
    private String chapterName;
    private List<String> picList;
}
