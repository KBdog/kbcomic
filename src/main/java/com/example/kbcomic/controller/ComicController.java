package com.example.kbcomic.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.kbcomic.entity.Comic;
import com.example.kbcomic.enums.ResultEnum;
import com.example.kbcomic.model.Result;
import com.example.kbcomic.service.ComicService;
import com.example.kbcomic.utils.JSONUtils;
import com.example.kbcomic.utils.LanguageTransferUtil;
import com.example.kbcomic.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/comic")
@Slf4j
public class ComicController {
    @Autowired
    private ComicService service;

    @RequestMapping("/all")
    public Result queryAll(){
        Map<String,Object> resultMap=new HashMap<>();
        List<Comic> comics = service.queryAll();
        resultMap.put("comicList",comics);
        resultMap.put("total",comics.size());
        log.info("漫画总数:"+comics.size());
        return ResultUtils.success(resultMap);
    }

    @RequestMapping("/id")
    public Result queryById(@RequestParam(value = "id") Integer id){
        return ResultUtils.success(service.queryById(id));
    }

    @RequestMapping("/keyword")
    public Result queryByKeyword(@RequestParam(value = "keyword")String keyword){
        System.out.println("搜索关键字:"+keyword);
        //简繁转换
        String gbKeyword= LanguageTransferUtil.convertToSimplifiedChinese(keyword);
        String big5Keyword=LanguageTransferUtil.convertToTraditionalChinese(keyword);
        System.out.println("-简体:"+gbKeyword);
        System.out.println("-繁体:"+big5Keyword);
        //简繁各查一次
        List<Comic> gbResults=service.queryByKeyword(gbKeyword);
        List<Comic> big5Results=service.queryByKeyword(big5Keyword);
        if(!gbResults.isEmpty()){
            System.out.println("返回简体结果");
            return ResultUtils.success(gbResults);
        }else if(!big5Results.isEmpty()){
            System.out.println("返回繁体结果");
            return ResultUtils.success(big5Results);
        }else {
            System.out.println("无结果");
            return ResultUtils.success("");
        }
    }

}
