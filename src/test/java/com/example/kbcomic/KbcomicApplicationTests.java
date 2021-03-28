package com.example.kbcomic;

import com.example.kbcomic.entity.Comic;
import com.example.kbcomic.service.ComicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class KbcomicApplicationTests {

    @Autowired
    private ComicService comicService;

    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
        Comic comic = comicService.queryByComicName("回復術");
        if(comic==null){
            System.out.println("comic is null!");
        }else {
            System.out.println(comic);
        }
    }

}
