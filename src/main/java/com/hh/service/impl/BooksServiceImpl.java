package com.hh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hh.exception.BorrowException;
import com.hh.exception.BorrowExceptionEnum;
import com.hh.mapper.BooksMapper;
import com.hh.pojo.Books;
import com.hh.pojo.BorrowRecords;
import com.hh.pojo.User;
import com.hh.service.BooksService;
import com.hh.service.BorrowRecordsService;
import com.hh.service.UserService;
import com.hh.uitl.MyPage;
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
    public MyPage<Books> search(String username, String title, int currentPage, int pageSize) {

        int offset = (currentPage - 1) * pageSize;
        log.info("username: {}, title: {}, cur: {}, size:{} , offset: {}", username, title, currentPage, pageSize, offset);
        List<Books> books = booksMapper.selectBooks(username, title, offset, pageSize);

        int total = booksMapper.countBy(username, title);
        // 返回自定义封装类
        MyPage<Books> page = new MyPage<>();
        int pages = (total + pageSize - 1) / pageSize;
        page.setList(books);
        page.setTotal(total);
        page.setPageNum(pages == 0 ? 0 : currentPage);
        page.setPages(pages);
        //如果查询页数大于总页数
        if (page.getPageNum() > pages) {
            offset = (pages - 1) * pageSize;
            log.info("username: {}, title: {}, cur: {}, size:{} , offset: {}", username, title, page.getPageNum(), pageSize, offset);
            books = booksMapper.selectBooks(username, title, offset, pageSize);
            page.setList(books);
            page.setPageNum(pages);
        }
        log.info("page: {}", page);
        return page;
    }

    @Transactional
    @Override
    public void borrowBook(Books book) {
        User user = UserThreadLocal.getUser();

        book = getById(book.getBookId());

        if (book.getIsBorrowed() == 1){
            log.warn("图书已借阅");
            throw new BorrowException(BorrowExceptionEnum.BOOK_ALREADY_BORROWED);
        }

        book.setUsername(user.getUsername());
        book.setIsBorrowed(1);
        book.setBorrowDate(new Date());
        book.setReturnDate(null);
        updateById(book);

    }

    @Transactional
    @Override
    public void returnBook(Books book) {
        User user = UserThreadLocal.getUser();

        user = userService.getById(user.getUserId());

        book = getById(book.getBookId());
        if (book.getUsername() == null || book.getIsBorrowed() == 0){
            throw new BorrowException(BorrowExceptionEnum.BOOK_NOT_BORROWED);
        }
        //非管理员还书
        if (!book.getUsername().equals(user.getUsername()) && user.getRole() != 0) {
            throw new BorrowException(BorrowExceptionEnum.NO_PERMISSION_TO_RETURN);
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

    }

}
