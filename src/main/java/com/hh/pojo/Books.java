package com.hh.pojo;

import lombok.Data;

/**
 * @Author hh
 * @Date 2024/4/22 18:15
 * @description:
 */
@Data
public class Books {
    private Integer bookId;
    private String title;
    private String author;
    private Integer isBorrowed;
}
