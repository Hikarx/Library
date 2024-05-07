package com.hh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hh.pojo.Books;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author hh
 * @Date 2024/4/22 21:11
 * @description:
 */
@Mapper
public interface BooksMapper extends BaseMapper<Books> {

//    List<Books> selectBooks(@Param("username")String username, @Param("title")String title);
    List<Books> selectBooks(@Param("username")String username, @Param("title")String title,
                            @Param("offset") int offset, @Param("limit") int limit);

    Integer countBy(@Param("username")String username, @Param("title")String title);
}
