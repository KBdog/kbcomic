package com.example.kbcomic.controller;

import com.example.kbcomic.model.Message;
import com.example.kbcomic.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService service;
    @RequestMapping("/comic1")
    public Message getMessage(@RequestParam(value = "id") Integer comicId){
        System.out.println("查询漫画id:"+comicId);
        return service.queryMessageByComicId(comicId);
    }

    @RequestMapping("/comic2")
    public Message testGetMessage(@RequestParam(value = "id") Integer comicId){
        System.out.println("查询漫画id:"+comicId);
        return service.queryMessage(comicId);
    }
}
