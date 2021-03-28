package com.example.kbcomic.mapper;

import com.example.kbcomic.model.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageMapper {
    Message queryMessageByComicId(@Param("id") Integer comicId);
}
