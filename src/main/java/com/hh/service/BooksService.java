package com.hh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hh.pojo.Books;
import com.hh.uitl.MyPage;

/**
 * @Author hh
 * @Date 2024/4/22 21:12
 * @description:
 */
public interface BooksService extends IService<Books> {

    public MyPage<Books> search(String username, String title, int currentPage, int pageSize);

    public void borrowBook(Books book);

    public void returnBook(Books book);
}
