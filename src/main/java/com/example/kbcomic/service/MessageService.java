package com.example.kbcomic.service;

import com.example.kbcomic.model.Message;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    Message queryMessageByComicId(Integer comicId);
}
