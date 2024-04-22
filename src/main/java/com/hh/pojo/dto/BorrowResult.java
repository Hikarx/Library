package com.hh.pojo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author hh
 * @Date 2024/4/22 18:30
 * @description:
 */
@Data
public class BorrowResult {
    private String title;
    private Date borrowDate;
    private Date returnDate;
    private Boolean isBorrowed;
    private String username;
}
