package com.example.kbcomic.service;

import com.example.kbcomic.entity.Chapter;
import com.example.kbcomic.entity.Comic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChapterService {
    List<Chapter> queryAll();
    Chapter queryById(Integer id);
    Integer insertChapter(Chapter chapter);
    Chapter queryByChapterNameAndComicId(String chapterName,Integer comicId);
    /**
     * 供api使用
     * 根据漫画id查章节列表
     */
    List<Chapter> searchChapters(Integer comicId);
}
