package com.example.kbcomic.mapper;

import com.example.kbcomic.entity.Chapter;
import com.example.kbcomic.entity.Comic;
import com.example.kbcomic.entity.Pic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PicMapper {
    //查询所有图片
    List<Pic> queryAll();
    //根据id查询图片
    Pic queryById(@Param(value = "id") Integer id);
    //插入图片
    Integer insertPic(Pic pic);
    //根据图片页数和章节id查询图片
    Pic queryPicByPicIdAndChapterId(@Param(value = "picId")Integer picId,@Param(value = "chapterId")Integer chapterId);
    /**
     * 供api使用
     * 根据章节id查当前章节图片列表
     */
    List<Pic>searchPictures(@Param(value = "chapterId")Integer chapterId);
}
