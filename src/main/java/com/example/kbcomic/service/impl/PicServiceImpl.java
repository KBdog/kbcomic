package com.example.kbcomic.service.impl;

import com.example.kbcomic.entity.Comic;
import com.example.kbcomic.entity.Pic;
import com.example.kbcomic.mapper.PicMapper;
import com.example.kbcomic.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class PicServiceImpl implements PicService {
    @Autowired
    private PicMapper mapper;
    @Override
    public List<Pic> queryAll() {
        return mapper.queryAll();
    }

    @Override
    public Pic queryById(Integer id) {
        return mapper.queryById(id);
    }

    @Override
    public Integer insertPic(Pic pic) {
        return mapper.insertPic(pic);
    }

    @Override
    public Pic queryPicByPicIdAndChapterId(Integer picId, Integer chapterId) {
        return mapper.queryPicByPicIdAndChapterId(picId,chapterId);
    }

    @Override
    public List<Pic> searchPictures(Integer chapterId) {
        return mapper.searchPictures(chapterId);
    }

    @Override
    public Integer deletePic(Integer comicId) {
        return mapper.deletePic(comicId);
    }
}
