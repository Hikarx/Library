package com.hh.pojo;

import lombok.Data;

/**
 * @Author hh
 * @Date 2024/5/6 16:42
 * @description:
 */
@Data
public class CommonResp<T> {
    //业务上的成功或失败
    private boolean success = true;
    //返回信息
    private String message;
    //返回泛型数据
    private T content;
    //自定义状态码
    private String code;

    public CommonResp(boolean success, String message, T content) {
        this.success = success;
        this.message = message;
        this.content = content;
    }

    public CommonResp(T content) {
        this.content = content;
    }
    public CommonResp(){}
}
