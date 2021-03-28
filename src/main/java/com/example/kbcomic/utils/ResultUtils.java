package com.example.kbcomic.utils;

import com.example.kbcomic.enums.ResultEnum;
import com.example.kbcomic.model.Result;

/**
 * 请求成功则带数据
 * 请求失败则不带数据
 */
public class ResultUtils {
    //定义success
    public static <T> Result<T> defineSuccess(Integer code, T data) {
        Result result = new Result<>();
        result.setCode(code);
        result.setMessage("请求成功");
        result.setData(data);
        return result;
    }
    //默认success
    public static <T> Result<T> success(T data) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage("请求成功");
        result.setData(data);
        return result;
    }
    //默认error
    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMessage(msg);
        return result;
    }
    //定义error
    public static <T> Result<T> defineError(Integer code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }
    //默认
    public static <T> Result<T> define(Integer code, String msg, T data){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        result.setData(data);
        return result;
    }
}
