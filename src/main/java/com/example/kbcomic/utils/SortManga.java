package com.example.kbcomic.utils;

import java.io.File;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;

public class SortManga {
    //按时间排序
    public static void sortByCratedTime(File[] list){
        Arrays.sort(list, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                long flag=o1.lastModified()-o2.lastModified();
                if(flag>0){
                    return 1;
                }else if(flag==0){
                    return 0;
                }else {
                    return -1;
                }
            }
        });
    }
    //按文件名排序
    public static void sortByFileName(File[] list){
        NumberFormat f = NumberFormat.getInstance();
        Arrays.sort(list, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                try {
                    return Double.compare(f.parse(o1.getName()).longValue(), f.parse(o2.getName()).longValue());
                } catch (ParseException e) {
                    e.printStackTrace();
                    return -1;
                }
            }
        });
    }
}
