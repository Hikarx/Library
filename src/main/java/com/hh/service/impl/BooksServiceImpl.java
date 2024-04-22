package com.hh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hh.mapper.BooksMapper;
import com.hh.pojo.Books;
import com.hh.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author hh
 * @Date 2024/4/22 21:12
 * @description:
 */

@Service
public class BooksServiceImpl extends ServiceImpl<BooksMapper, Books> implements BooksService {

    @Autowired
    private BooksMapper booksMapper;

}
