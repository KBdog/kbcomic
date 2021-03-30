package com.example.kbcomic.mapper;

import com.example.kbcomic.model.ChapterModel;
import com.example.kbcomic.model.Message;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper {
    /**
     *映射文件方式实现一对多
     */
    Message queryMessageByComicId(@Param("id") Integer comicId);

    /**
     * 注解方式实现一对多
     */
    @Select("select comic_id,comic_name from all_comic where comic_id=#{id}")
    @Results({
            @Result(column = "comic_id",property = "comicId"),
            @Result(column = "comic_name",property = "comicName"),
            @Result(property = "chapterList",column = "comic_id",
                    many = @Many(select = "com.example.kbcomic.mapper.MessageMapper.queryChapterList"))
    })
    Message queryMessage(@Param("id")Integer comicId);
    //根据漫画id查所有章节
    @Select("select chapter_id,chapter_name from all_chapter where comic_id=#{comic_id}")
    @Results({
            @Result(column = "chapter_id",property = "chapterId"),
            @Result(column = "chapter_name",property = "chapterName"),
            @Result(property = "picList",column = "chapter_id",
                    many = @Many(select = "com.example.kbcomic.mapper.MessageMapper.queryPicList"))
    })
    List<ChapterModel>queryChapterList(@Param("comic_id")Integer id);
    //根据章节id查所有图片
    @Select("select pic_url from all_pic where chapter_id=#{chapter_id}")
    List<String>queryPicList(@Param("chapter_id")Integer id);
}
