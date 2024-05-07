package com.hh.controller;

import com.hh.pojo.Books;
import com.hh.pojo.CommonResp;
import com.hh.service.BooksService;
import com.hh.uitl.MyPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author hh
 * @Date 2024/4/22 18:16
 * @description:
 */
@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {


    @Autowired
    private BooksService booksService;

    /**
     *
     * @param username 用户名
     * @param title 书名
     * @param currentPage 当前页
     * @param pageSize 每页显示条数
     * @return
     */
    @GetMapping("/search")
    public CommonResp<MyPage<Books>> getAll(@RequestParam String username,
                                              @RequestParam String title,
                                              @RequestParam(defaultValue = "1") Integer currentPage,
                                              @RequestParam(defaultValue = "5") Integer pageSize){

        MyPage<Books> page = booksService.search(username, title, currentPage<=0 ? 1 : currentPage, pageSize);

        return new CommonResp<>(page);
    }

    //借书
    @PostMapping("/borrow")
    public CommonResp<Object> borrowBook(@RequestBody Books book) {

        log.info("book: {}", book);

        booksService.borrowBook(book);

        return new CommonResp<>();
    }

    //还书
    @PostMapping("/return")
    public CommonResp<Object> returnBook(@RequestBody Books book) {

        log.info("book: {}", book);
        booksService.returnBook(book);
        return new CommonResp<>();
    }
}
