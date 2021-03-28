package com.example.kbcomic.service.impl;

import com.example.kbcomic.entity.Chapter;
import com.example.kbcomic.entity.Comic;
import com.example.kbcomic.mapper.ChapterMapper;
import com.example.kbcomic.mapper.ComicMapper;
import com.example.kbcomic.service.ChapterService;
import com.example.kbcomic.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterMapper mapper;
    @Override
    public List<Chapter> queryAll() {
        return mapper.queryAll();
    }

    @Override
    public Chapter queryById(Integer id) {
        return mapper.queryById(id);
    }

    @Override
    public Integer insertChapter(Chapter chapter) {
        return mapper.insertChapter(chapter);
    }

    @Override
    public Chapter queryByChapterNameAndComicId(String chapterName, Integer comicId) {
        return mapper.queryByChapterNameAndComicId(chapterName,comicId);
    }

    @Override
    public List<Chapter> searchChapters(Integer comicId) {
        return mapper.searchChapters(comicId);
    }
}
