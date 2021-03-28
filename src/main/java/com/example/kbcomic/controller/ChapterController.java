package com.example.kbcomic.controller;

import com.example.kbcomic.entity.Chapter;
import com.example.kbcomic.model.Result;
import com.example.kbcomic.service.ChapterService;
import com.example.kbcomic.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/chapter")
public class ChapterController {
    @Autowired
    private ChapterService service;

    //根据漫画id查询章节列表
    @RequestMapping("/{comicId}")
    public Result searchChapters(@PathVariable("comicId")Integer comicId){
        return ResultUtils.success(service.searchChapters(comicId));
    }
}
