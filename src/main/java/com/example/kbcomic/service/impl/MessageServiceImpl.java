package com.example.kbcomic.service.impl;

import com.example.kbcomic.mapper.MessageMapper;
import com.example.kbcomic.model.Message;
import com.example.kbcomic.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper mapper;
    @Override
    public Message queryMessageByComicId(Integer comicId) {
        return mapper.queryMessageByComicId(comicId);
    }

    @Override
    public Message queryMessage(Integer comicId) {
        return mapper.queryMessage(comicId);
    }
}
