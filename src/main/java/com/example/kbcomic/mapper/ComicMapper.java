package com.example.kbcomic.mapper;

import com.example.kbcomic.entity.Comic;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComicMapper {
    //查询所有漫画
    List<Comic> queryAll();
    //根据id查询漫画
    Comic queryById(@Param(value = "id") Integer id);
    //插入漫画
    Integer insertComic(Comic comic);
    //根据关键字查漫画
    List<Comic> queryComicByKeyword(@Param(value = "keyword")String keyword);
    //根据漫画名准确查询漫画
    Comic queryComicByComicName(@Param(value = "comicName")String comicName);
    //更新漫画简介
    Integer updateComicMessage(@Param(value = "comicId") Integer comicId,@Param(value = "comic") Comic comic);
}
