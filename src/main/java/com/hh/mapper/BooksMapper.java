package com.hh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hh.pojo.Books;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @Author hh
 * @Date 2024/4/22 21:11
 * @description:
 */
@Mapper
public interface BooksMapper extends BaseMapper<Books> {

    @Update("UPDATE books SET is_borrowed = #{flag} WHERE book_id = #{id}")
    void update1(@Param("flag")Integer flag, @Param("id") Integer id);

}
