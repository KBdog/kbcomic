package com.example.kbcomic.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class JSONUtils {
    //根据json文件解析json字符串
    public static String getJSONString(File jsonFile){
        InputStreamReader isr=null;
        BufferedReader br=null;
        StringBuffer sb=null;
        try {
            isr = new InputStreamReader(new FileInputStream(jsonFile),"gbk");
            br=new BufferedReader(isr);
            sb=new StringBuffer();
            int length=-1;
            char []cache=new char[1024];
            while((length=br.read(cache,0,cache.length))!=-1){
                sb.append(cache,0,length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return sb.toString();
        }
    }
    //根据key值获取json属性
    public static String getJSONAttribute(String key, String jsonString){
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        return jsonObject.getString(key);
    }

}
