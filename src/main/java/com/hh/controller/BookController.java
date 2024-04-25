package com.hh.controller;

import com.github.pagehelper.PageInfo;
import com.hh.pojo.Books;
import com.hh.service.BooksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/all")
    public ResponseEntity<PageInfo<Books>> getAll(@RequestParam String username,
                                                     @RequestParam String title,
                                                     @RequestParam(defaultValue = "1") Integer currentPage,
                                                     @RequestParam(defaultValue = "5") Integer pageSize){
        log.info("username: {}, title: {}, cur: {}, size:{} ", username, title, currentPage, pageSize);
        PageInfo<Books> page = booksService.all(username, title, currentPage<=0 ? 1 : currentPage, pageSize);

        if (currentPage > page.getPages()) {
            page = booksService.all(username, title,page.getPages(), pageSize);
        }
        log.info("pageInfo: {}", page);
        return ResponseEntity.ok(page);
    }

    //借书
    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestBody Books book) {

        log.info("book: {}", book);
        if (booksService.borrowBook(book)) {
            return ResponseEntity.ok("借阅成功");
        }
        return ResponseEntity.badRequest().body("归还失败");
    }

    //还书
    @PostMapping("/return")
    public ResponseEntity<?> returnBook(@RequestBody Books book) {

        log.info("book: {}", book);
        if (booksService.returnBook(book)) {
            return ResponseEntity.ok().body("归还成功");
        }
        return ResponseEntity.badRequest().body("归还失败");
    }
}
