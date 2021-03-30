package com.example.kbcomic.service;

import com.example.kbcomic.model.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    //xml
    Message queryMessageByComicId(Integer comicId);
    //注解形式
    Message queryMessage(Integer comicId);
}
