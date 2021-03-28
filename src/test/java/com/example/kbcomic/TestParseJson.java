package com.example.kbcomic;

import com.example.kbcomic.utils.JSONUtils;

import java.io.File;

/**
 * 解析json
 */
public class TestParseJson {
    public static void main(String[] args) {
        File file=new File("D:\\copymanga\\無職轉生\\message.json");
        String jsonString = JSONUtils.getJSONString(file);
        System.out.println(JSONUtils.getJSONAttribute("comic_description",jsonString));
    }
}
