package com.example.kbcomic.model;

import lombok.Data;

import java.util.List;

@Data
public class Message {
    private Integer comicId;
    private String comicName;
    private List<ChapterModel> chapterList;
}
