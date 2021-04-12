package com.example.kbcomic.service;

import com.example.kbcomic.entity.Comic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface ComicService {
    List<Comic> queryAll();
    Comic queryById(Integer id);
    Integer insertComic(Comic comic);
    List<Comic> queryByKeyword(String keyword);
    Comic queryByComicName(String comicName);
    Integer updateComicMessage(Integer comicId,Comic comic);
    Integer updateComicUpdateTime(Date date, Integer comicId);
    //根据漫画id删除漫画简介
    Integer deleteComic(Integer comicId);
}
