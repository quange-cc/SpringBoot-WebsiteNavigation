package com.quange.controller;

import lombok.Data;


/**
 * 返回数据格式
 */
@Data
public class Result {

    // 状态码
    private Integer code;

    // 信息
    private String msg;

    // 数据的数量
    private long count;

    //数据
    private Object data;


    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg, long count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

}
