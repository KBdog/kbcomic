package com.example.kbcomic.utils;

import com.github.stuxuhai.jpinyin.ChineseHelper;

public class LanguageTransferUtil {
    /**
     * 简体转换为繁体
     * @param pinYinStr 要转换的字符串
     * @return
     */
    public static String convertToTraditionalChinese(String pinYinStr) {
        String tempStr = null;
        try {
            tempStr = ChineseHelper.convertToTraditionalChinese(pinYinStr);
        } catch (Exception e) {
            tempStr = pinYinStr;
            e.printStackTrace();
        }
        return tempStr;
    }


    /**
     * 繁体转换为简体
     * @param pinYinSt 要转换的字符串
     * @return
     */
    public static String convertToSimplifiedChinese(String pinYinSt) {
        String tempStr = null;
        try {
            tempStr = ChineseHelper.convertToSimplifiedChinese(pinYinSt);
        } catch (Exception e) {
            tempStr = pinYinSt;
            e.printStackTrace();
        }

        return tempStr;
    }
}
