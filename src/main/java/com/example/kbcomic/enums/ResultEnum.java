package com.example.kbcomic.enums;

/**
 * result枚举
 */
public enum ResultEnum {
    //成功
    SUCCESS(200),
    //失败
    ERROR(400),
    //接口不存在
    NOT_FOUND(404),
    //服务器错误
    SERVER_ERROR(500);
    private Integer code;

    ResultEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
