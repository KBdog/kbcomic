package com.example.kbcomic.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.kbcomic.entity.Chapter;
import com.example.kbcomic.entity.Comic;
import com.example.kbcomic.entity.Pic;
import com.example.kbcomic.enums.ResultEnum;
import com.example.kbcomic.model.Result;
import com.example.kbcomic.service.ChapterService;
import com.example.kbcomic.service.ComicService;
import com.example.kbcomic.service.PicService;
import com.example.kbcomic.utils.JSONUtils;
import com.example.kbcomic.utils.ResultUtils;
import com.example.kbcomic.utils.SortManga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

//更新数据库
@RestController
@CrossOrigin
@RequestMapping("/update")
public class UpdateController {
    //更新信号量false表示空闲 true表示占用
    public static boolean flag=false;
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
    //更新数据库
    public void update(){
        //根目录
        File comicRoot=new File("D:\\copymanga");
        //漫画列表
        File[] comicList = comicRoot.listFiles();
        //按创建时间顺序排序
        SortManga.sortByCratedTime(comicList);
        for(File comic:comicList){
            if(comic.isDirectory()){
                System.out.println("正在检查:"+comic.getName());
                Comic tmpComic = comicService.queryByComicName(comic.getName());
                //漫画不存在则录入
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
                        //解析message.json
                        else if(chapter.getName().endsWith("json")){
                            String jsonString= JSONUtils.getJSONString(chapter);
                            String comicCover= JSONUtils.getJSONAttribute("comic_cover",jsonString);
                            String comicDescription=JSONUtils.getJSONAttribute("comic_description",jsonString);
                            entityComic.setComicCover(comicCover);
                            entityComic.setComicDescription(comicDescription);
                            entityComic.setUpdateTime(new Date(System.currentTimeMillis()));
                            Integer count=-1;
                            //插入简介
                            count=comicService.updateComicMessage(entityComic.getComicId(),entityComic);
                            if(count>0){
                                System.out.println("添加简介成功:"+entityComic.getComicName());
                            }else {
                                System.out.println("添加简介失败:"+entityComic.getComicName());
                            }
                        }
                    }
                    System.out.println("-------------------------------------------------" +
                            "-----------------------------------------------------------" +
                            "---------------------------------------------");
                }
                //漫画存在但缺章节
                else if(tmpComic!=null){
                    //章节列表
                    File[] chapterList = comic.listFiles();
                    //按创建时间顺序排序
                    SortManga.sortByCratedTime(chapterList);
                    for(File chapter:chapterList){
                        //章节
                        if(chapter.isDirectory()){
                            //根据章节名和漫画名判断该话是否存在
                            Chapter tmpChapter = chapterService.queryByChapterNameAndComicId(chapter.getName(), tmpComic.getComicId());
                            //章节不存在则录入
                            if(tmpChapter==null){
                                entityChapter=new Chapter();
                                entityChapter.setChapterName(chapter.getName());
                                entityChapter.setComicId(tmpComic.getComicId());
                                //更新漫画更新时间
                                comicService.updateComicUpdateTime(new Date(System.currentTimeMillis()),tmpComic.getComicId());
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
                            //章节存在但缺页
                            else if(tmpChapter!=null){
                                //图片列表
                                File[] picList = chapter.listFiles();
                                //按图片页数数字顺序排序
                                SortManga.sortByFileName(picList);
                                for(File pic:picList){
                                    if(pic.getName().endsWith("jpg")){
                                        //图片页数
                                        String page = pic.getName().split(".jpg")[0];
                                        Pic tmpPic = picService.queryPicByPicIdAndChapterId(Integer.parseInt(page), tmpChapter.getChapterId());
                                        //图片不存在则录入
                                        if(tmpPic==null){
                                            String suffixPath=pic.getAbsolutePath().
                                                    replaceAll("\\\\","/").split("D:/copymanga/")[1];
                                            //最终写入数据库的url路径
                                            String comicPath="http://localhost:8081/comics/"+suffixPath;
                                            entityPic=new Pic();
                                            entityPic.setPicId(Integer.parseInt(page));
                                            entityPic.setChapterId(tmpChapter.getChapterId());
                                            entityPic.setPicUrl(comicPath);
                                            //插入图片
                                            picService.insertPic(entityPic);
                                            System.out.println("--"+comicPath);
                                        }
                                    }
                                }
                            }
                        }
                        //message.json
                        else if(chapter.getName().endsWith("json")){
                            String jsonString=JSONUtils.getJSONString(chapter);
                            String comicCover= JSONUtils.getJSONAttribute("comic_cover",jsonString);
                            String comicDescription=JSONUtils.getJSONAttribute("comic_description",jsonString);
                            //判断简介是否为空或有改动
                            Comic message=comicService.queryById(tmpComic.getComicId());
                            if(!(message.getComicCover().equals(comicCover)&&message.getComicDescription().equals(comicDescription))){
                                //不一致则修改或录入
                                Comic _comic=new Comic();
                                _comic.setComicCover(comicCover);
                                _comic.setComicDescription(comicDescription);
                                _comic.setUpdateTime(new Date(System.currentTimeMillis()));
                                Integer count=-1;
                                count=comicService.updateComicMessage(message.getComicId(),_comic);
                                if(count>0){
                                    System.out.println("简介更新成功:"+message.getComicName());
                                }else {
                                    System.out.println("返回值:"+count+","+"简介更新失败:"+message.getComicName());
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("数据库更新完毕！");
    }

    @RequestMapping(value = "/validate",method = RequestMethod.POST)
    public Result updateMangaDB(@RequestBody String jsonString){
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        String username=jsonObject.getString("username");
        String keyword = jsonObject.getString("keyword");
        if(username.equals("root")&&keyword.equals("root")){
            if(flag==false){
                //开个新线程对数据库进行更新
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //锁定信号量
                        flag=true;
                        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                        Date date1 = new Date(System.currentTimeMillis());
                        System.out.println("更新开始时间:"+formatter.format(date1));
                        update();
                        Date date2=new Date(System.currentTimeMillis());
                        System.out.println("更新结束时间:"+formatter.format(date2));
                        long useTime=date2.getTime()-date1.getTime();
                        System.out.println("用时:"+useTime/1000+"秒");
                        //释放信号量
                        flag=false;
                    }
                }).start();
                return ResultUtils.success("验证成功，更新已经开始！");
            }else {
                return ResultUtils.error("验证成功，目前更新正在执行！请稍后再发送请求！");
            }
        }else {
            return ResultUtils.error("验证失败！");
        }
    }

    @RequestMapping(value = "/remove",method = RequestMethod.DELETE)
    public Result deleteMangaDB(@RequestParam("comic_id")Integer comic_id){
        if(flag==true){
            return ResultUtils.error("删除失败，目前更新正在执行！请稍后再发送请求！");
        }
        //锁定信号量
        flag=true;
        Comic comic = comicService.queryById(comic_id);
        Integer resultNum=picService.deletePic(comic_id);
        if(resultNum>0){
            System.out.println("删除图片成功："+comic_id+"-"+comic.getComicName());
            resultNum=chapterService.deleteChapter(comic_id);
            if(resultNum>0){
                System.out.println("删除章节成功："+comic_id+"-"+comic.getComicName());
                resultNum=comicService.deleteComic(comic_id);
                if(resultNum>0){
                    System.out.println("删除漫画简介成功："+comic_id+"-"+comic.getComicName());
                    //释放信号量
                    flag=false;
                    return ResultUtils.success("删除成功！"+comic_id+"-"+comic.getComicName());
                }
                System.out.println("删除漫画简介失败："+comic_id+"-"+comic.getComicName());
                return ResultUtils.define(204,"仅删除图片和章节成功！"+comic_id+"-"+comic.getComicName(),null);
            }
            System.out.println("删除章节失败："+comic_id+"-"+comic.getComicName());
            return ResultUtils.define(204,"仅删除图片成功！"+comic_id+"-"+comic.getComicName(),null);
        }
        //释放信号量
        flag=false;
        return ResultUtils.error("删除失败！不存在该漫画！");
    }
}
