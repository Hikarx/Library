package com.hh.uitl;

import lombok.Data;

import java.util.List;

/**
 * @Author hh
 * @Date 2024/5/7 19:05
 * @description:
 */
@Data
public class MyPage<T> {
    //当前页码
    private int pageNum;
    //总页数
    private int pages;
    //总条数
    private int total;
    //结果集
    private List<T> list;
}
