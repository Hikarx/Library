package com.hh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hh.pojo.BorrowRecords;
import com.hh.pojo.dto.BorrowResult;

import java.util.List;

/**
 * @Author hh
 * @Date 2024/4/22 18:16
 * @description:
 */
public interface BorrowRecordsService extends IService<BorrowRecords> {

    public List<BorrowResult> all(String username, String title);

    public void borrowBook(Integer userId, String bookTitle);

    public void returnBook(Integer userId, String BookTitle);
}
