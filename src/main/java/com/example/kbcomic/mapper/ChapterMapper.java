package com.example.kbcomic.mapper;

import com.example.kbcomic.entity.Chapter;
import com.example.kbcomic.entity.Comic;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterMapper {
    //查询所有章节
    List<Chapter> queryAll();
    //根据id查询章节
    Chapter queryById(@Param(value = "id") Integer id);
    //插入漫画
    Integer insertChapter(Chapter chapter);
    //根据章节名和漫画id查章节
    Chapter queryByChapterNameAndComicId(@Param(value = "chapterName")String chapterName,@Param(value = "comicId")Integer comicId);

    /**
     * 供api使用
     * 根据漫画id查章节列表
     */
    List<Chapter> searchChapters(@Param(value = "comicId")Integer comicId);
}
