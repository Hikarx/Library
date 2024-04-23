package com.hh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hh.pojo.dto.BorrowResult;
import com.hh.pojo.dto.PageResult;
import com.hh.service.BorrowRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author hh
 * @Date 2024/4/22 18:16
 * @description:
 */
@RestController
@RequestMapping("/borrow")
public class BorrowRecordsController {

    @Autowired
    private BorrowRecordsService borrowRecordsService;

    @GetMapping("/all")
    public ResponseEntity<PageResult> getAll(@RequestParam String username,
                                                     @RequestParam String title,
                                                     @RequestParam(defaultValue = "1") int currentPage,
                                                     @RequestParam(defaultValue = "5") int recordsPerPage){
        System.out.println("username: " + username + ", title: " + title
                + ", currentPage: " + currentPage + ", recordsPerPage: " + recordsPerPage);
        PageResult all = borrowRecordsService.all(username, title, currentPage, recordsPerPage);

        System.out.println(all);
        return ResponseEntity.ok(all);
    }

    @PostMapping("/{userId}/{bookTitle}")
    public ResponseEntity<?> borrowBook(@PathVariable Integer userId,
                                        @PathVariable String bookTitle) {
        System.out.println("userId: " + userId + ", bookTitle: " + bookTitle);
        try {
            borrowRecordsService.borrowBook(userId, bookTitle);
            return ResponseEntity.ok("借阅成功");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body("借阅失败：" + e.getMessage());
        }
    }

    @PostMapping("/return/{userId}/{bookTitle}")
    public ResponseEntity<?> returnBook(@PathVariable Integer userId, @PathVariable String bookTitle) {
        System.out.println("归还  userId: " + userId + ", bookTitle: " + bookTitle);
        try {
            borrowRecordsService.returnBook(userId, bookTitle);

            return ResponseEntity.ok().body("归还成功");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("归还失败");
        }
    }
}
