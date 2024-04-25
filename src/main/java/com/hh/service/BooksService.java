package com.hh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.hh.pojo.Books;

/**
 * @Author hh
 * @Date 2024/4/22 21:12
 * @description:
 */
public interface BooksService extends IService<Books> {

    public PageInfo<Books> all(String username, String title, int currentPage, int pageSize);

    public boolean borrowBook(Books book);

    public boolean returnBook(Books book);
}
