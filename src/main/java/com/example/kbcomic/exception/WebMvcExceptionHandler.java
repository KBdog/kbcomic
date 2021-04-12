package com.example.kbcomic.exception;

import com.alibaba.fastjson.JSONException;
import com.example.kbcomic.enums.ResultEnum;
import com.example.kbcomic.model.Result;
import com.example.kbcomic.utils.ResultUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

//全局异常处理
@ControllerAdvice
@RestController
public class WebMvcExceptionHandler {
    //整合请求方式、请求方法体、请求参数的异常
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class,
            HttpMessageNotReadableException.class, JSONException.class
            , MissingServletRequestParameterException.class})
    public Result paramError(Exception e){
        System.out.println("请求方式或参数错误:"+e.getMessage());
        return ResultUtils.error("请求方法或参数错误！");
    }


    //资源不存在
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public  Result notFoundHandler(NoHandlerFoundException e){
        System.out.println("资源不存在:"+e.getMessage());
        return ResultUtils.defineError(ResultEnum.NOT_FOUND.getCode(),"资源不存在！");
    }

    //其他情况:服务器内部异常
    @ExceptionHandler(value = Exception.class)
    public Result serverHandler(Exception e){
        System.out.println("服务器异常:"+e.getMessage());
        e.printStackTrace();
        return ResultUtils.defineError(ResultEnum.SERVER_ERROR.getCode(),"服务器出错！");
    }
}
