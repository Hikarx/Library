package com.hh.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hh.mapper.BooksMapper;
import com.hh.mapper.BorrowRecordsMapper;
import com.hh.pojo.Books;
import com.hh.pojo.BorrowRecords;
import com.hh.pojo.dto.BorrowResult;
import com.hh.service.BooksService;
import com.hh.service.BorrowRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author hh
 * @Date 2024/4/22 18:16
 * @description:
 */

@Service
public class BorrowRecordsServiceImpl extends ServiceImpl<BorrowRecordsMapper, BorrowRecords>
        implements BorrowRecordsService {

    @Autowired
    private BorrowRecordsMapper borrowRecordsMapper;

    @Autowired
    private BooksMapper booksMapper;

    public List<BorrowResult> all(String username, String title) {
        return borrowRecordsMapper.findAll(username, title);
    }

    @Transactional
    public void borrowBook(Integer userId, String bookTitle) {

        // 根据书名查询借阅记录
        QueryWrapper<Books> query = new QueryWrapper<>();
        query.eq("title", bookTitle);
        Books book = booksMapper.selectOne(query);
        Integer bookId = book.getBookId();

        QueryWrapper<BorrowRecords> q = new QueryWrapper<>();
        q.eq("book_id", bookId);
        BorrowRecords borrowRecord = borrowRecordsMapper.selectOne(q);

        if (borrowRecord != null) {
            // 如果已经有借阅记录，更新借阅记录的借阅时间和重置归还时间
            borrowRecord.setBorrowDate(new Date());
            borrowRecord.setReturnDate(null);
            borrowRecord.setUserId(userId);
            borrowRecordsMapper.update1(borrowRecord.getRecordId(),
                    borrowRecord.getUserId(),
                    borrowRecord.getBookId(),
                    borrowRecord.getBorrowDate(),
                    borrowRecord.getReturnDate());
        } else {
            // 如果没有借阅记录，插入一条借阅记录
            BorrowRecords newBorrowRecord = new BorrowRecords();
            newBorrowRecord.setUserId(userId);
            newBorrowRecord.setBookId(bookId);
            newBorrowRecord.setBorrowDate(new Date());
            borrowRecordsMapper.insert(newBorrowRecord);
        }

        // 更新书本的借阅状态
        booksMapper.update1(1,bookId);
    }

    @Transactional
    public void returnBook(Integer userId, String bookTitle) {
            QueryWrapper<Books> query = new QueryWrapper<>();
            query.eq("title", bookTitle);
            Books book = booksMapper.selectOne(query);
            Integer bookId = book.getBookId();

            QueryWrapper<BorrowRecords> q = new QueryWrapper<>();
            q.eq("book_id", bookId);
            BorrowRecords borrowRecord = borrowRecordsMapper.selectOne(q);

            borrowRecord.setUserId(null);
            borrowRecord.setReturnDate(new Date());
            borrowRecordsMapper.update1(borrowRecord.getRecordId(),
                    borrowRecord.getUserId(),
                    borrowRecord.getBookId(),
                    borrowRecord.getBorrowDate(),
                    borrowRecord.getReturnDate());

            booksMapper.update1(0, borrowRecord.getBookId());


    }


}
