package com.hh.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author hh
 * @Date 2024/4/23 17:37
 * @description:
 */

@Data
public class PageResult {

    private List<BorrowResult> data;
    private float totalRecords;
    private int totalPages;
}
