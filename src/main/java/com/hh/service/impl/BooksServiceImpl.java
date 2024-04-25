package com.hh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hh.mapper.BooksMapper;
import com.hh.pojo.Books;
import com.hh.pojo.BorrowRecords;
import com.hh.pojo.User;
import com.hh.service.BooksService;
import com.hh.service.BorrowRecordsService;
import com.hh.service.UserService;
import com.hh.uitl.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author hh
 * @Date 2024/4/22 21:12
 * @description:
 */

@Service
@Slf4j
public class BooksServiceImpl extends ServiceImpl<BooksMapper, Books> implements BooksService {

    @Autowired
    private BooksMapper booksMapper;

    @Autowired
    private BorrowRecordsService borrowRecordsService;

    @Autowired
    private UserService userService;

    @Override
    public PageInfo<Books> all(String username, String title, int currentPage, int pageSize) {
        log.info("cur: {}, pageSize: {}",currentPage, pageSize);

        if (username != null && !username.trim().isEmpty()) {
            username = "%" + username.trim() + "%";
        }
        if (title != null && !title.trim().isEmpty()) {
            title = "%" + title.trim() + "%";
        }

        PageHelper.startPage(currentPage, pageSize);
        List<Books> books = booksMapper.selectBooks(username, title);

        PageInfo<Books> pageInfo = new PageInfo<>(books);
        return pageInfo;

    }

    @Transactional
    @Override
    public boolean borrowBook(Books book) {
        User user = UserThreadLocal.getUser();

        //数据库中重新查询
        book = getById(book.getBookId());
        if (book.getIsBorrowed() == 1){
            log.warn("图书已借阅");
            return false;
        }

        book.setUsername(user.getUsername());
        book.setIsBorrowed(1);
        book.setBorrowDate(new Date());
        book.setReturnDate(null);
        updateById(book);

        return true;
    }

    @Transactional
    @Override
    public boolean returnBook(Books book) {
        User user = UserThreadLocal.getUser();

        user = userService.getById(user.getUserId());

        book = getById(book.getBookId());
        if (book.getUsername() == null || book.getIsBorrowed() == 0){
            log.warn("图书未借阅");
            return false;
        }
        //非管理员还书
        if (!book.getUsername().equals(user.getUsername()) && user.getRole() != 0) {
            log.warn("非同一个人借书，不能归还");
            return false;
        }
        book.setReturnDate(new Date());

        BorrowRecords borrowRecords = new BorrowRecords();
        //保存记录
        borrowRecords.setBookId(book.getBookId());
        borrowRecords.setUserId(user.getUserId());
        borrowRecords.setBorrowDate(book.getBorrowDate());
        borrowRecords.setReturnDate(book.getReturnDate());
        borrowRecords.setRole(user.getRole());
        borrowRecordsService.save(borrowRecords);

        //恢复图书记录

        book.setUsername(null);
        book.setIsBorrowed(0);
        updateById(book);

        return true;
    }

}
