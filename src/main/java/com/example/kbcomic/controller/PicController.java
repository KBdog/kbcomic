package com.example.kbcomic.controller;

import com.example.kbcomic.entity.Pic;
import com.example.kbcomic.model.Result;
import com.example.kbcomic.service.PicService;
import com.example.kbcomic.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/pic")
public class PicController {
    @Autowired
    private PicService service;
    @RequestMapping("/{chapterId}")
    public Result searchPictures(@PathVariable("chapterId")Integer chapterId){
        return ResultUtils.success(service.searchPictures(chapterId));
    }
}
