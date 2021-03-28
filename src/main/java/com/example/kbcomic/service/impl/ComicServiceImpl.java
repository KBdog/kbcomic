package com.example.kbcomic.service.impl;

import com.example.kbcomic.entity.Comic;
import com.example.kbcomic.mapper.ComicMapper;
import com.example.kbcomic.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class ComicServiceImpl implements ComicService {
    @Autowired
    private ComicMapper mapper;
    @Override
    public List<Comic> queryAll() {
        return mapper.queryAll();
    }

    @Override
    public Comic queryById(Integer id) {
        return mapper.queryById(id);
    }

    @Override
    public Integer insertComic(Comic comic) {
        return mapper.insertComic(comic);
    }

    @Override
    public List<Comic> queryByKeyword(String keyword) {
        return mapper.queryComicByKeyword(keyword);
    }

    @Override
    public Comic queryByComicName(String comicName) {
        return mapper.queryComicByComicName(comicName);
    }

    @Override
    public Integer updateComicMessage(Integer comicId, Comic comic) {
        return mapper.updateComicMessage(comicId,comic);
    }


}
