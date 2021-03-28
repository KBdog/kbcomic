package com.example.kbcomic;

import com.example.kbcomic.entity.Chapter;
import com.example.kbcomic.entity.Comic;
import com.example.kbcomic.entity.Pic;
import com.example.kbcomic.service.ChapterService;
import com.example.kbcomic.service.ComicService;
import com.example.kbcomic.service.PicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.kbcomic.utils.SortManga;

import java.io.File;
import java.sql.Date;

@SpringBootTest
public class TestManga {
    @Autowired
    private ComicService comicService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private PicService picService;
    //漫画简介实体
    private Comic entityComic;
    //章节实体
    private Chapter entityChapter;
    //图片实体
    private Pic entityPic;

    @Test
    @Deprecated
    public void all(){
        //根目录
        File comicRoot=new File("D:\\copymanga");
        //漫画列表
        File[] comicList = comicRoot.listFiles();
        //按创建时间顺序排序
        SortManga.sortByCratedTime(comicList);
        for(File comic:comicList){
            if(comic.isDirectory()){
                //数据库中要是已存在该漫画就跳过不录入
                Comic tmpComic = comicService.queryByComicName(comic.getName());
                if(tmpComic==null){
                    System.out.println("未存档:"+comic.getName());
                    entityComic=new Comic();
                    entityComic.setComicName(comic.getName());
                    entityComic.setComicDescription("");
                    entityComic.setCreateTime(new Date(System.currentTimeMillis()));
                    entityComic.setUpdateTime(new Date(System.currentTimeMillis()));
                    //插入漫画简介
                    comicService.insertComic(entityComic);
                    System.out.println("comic_id:"+entityComic.getComicId());
                    System.out.println("comic_name:"+entityComic.getComicName());
                    //章节列表
                    File[] chapterList = comic.listFiles();
                    //按创建时间顺序排序
                    SortManga.sortByCratedTime(chapterList);
                    for(File chapter:chapterList){
                        if(chapter.isDirectory()){
                            entityChapter=new Chapter();
                            entityChapter.setChapterName(chapter.getName());
                            entityChapter.setComicId(entityComic.getComicId());
                            //插入章节
                            chapterService.insertChapter(entityChapter);
                            System.out.println("-chapter_id:"+entityChapter.getChapterId());
                            System.out.println("-chapter_name:"+entityChapter.getChapterName());
                            //图片列表
                            File[] picList = chapter.listFiles();
                            //按图片页数数字顺序排序
                            SortManga.sortByFileName(picList);
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            for(File pic:picList){
                                if(pic.getName().endsWith("jpg")){
                                    //图片页数
                                    String page = pic.getName().split(".jpg")[0];
                                    String suffixPath=pic.getAbsolutePath().
                                            replaceAll("\\\\","/").split("D:/copymanga/")[1];
                                    //最终写入数据库的url路径
                                    String comicPath="http://localhost:8081/comics/"+suffixPath;
                                    entityPic=new Pic();
                                    entityPic.setPicId(Integer.parseInt(page));
                                    entityPic.setChapterId(entityChapter.getChapterId());
                                    entityPic.setPicUrl(comicPath);
                                    //插入图片
                                    picService.insertPic(entityPic);
                                    System.out.println("--"+comicPath);
                                }
                            }
                        }
                    }
                    System.out.println("-------------------------------------------------" +
                            "-----------------------------------------------------------" +
                            "---------------------------------------------");
                }else {
                    System.out.println("已存档:"+tmpComic.getComicName()+"-"+tmpComic.getComicId());
                }
            }
        }

    }
}
