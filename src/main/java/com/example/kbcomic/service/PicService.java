package com.example.kbcomic.service;

import com.example.kbcomic.entity.Comic;
import com.example.kbcomic.entity.Pic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PicService {
    List<Pic> queryAll();
    Pic queryById(Integer id);
    Integer insertPic(Pic pic);
    Pic queryPicByPicIdAndChapterId(Integer picId,Integer chapterId);
    /**
     * 供api使用
     */
    //根据章节id查询图片列表
    List<Pic>searchPictures(Integer chapterId);
    //根据漫画id删除其所有图片
    Integer deletePic(Integer comicId);
}
