package com.hh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hh.pojo.BorrowRecords;
import com.hh.pojo.dto.BorrowResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * @Author hh
 * @Date 2024/4/22 18:34
 * @description:
 */
@Mapper
public interface BorrowRecordsMapper extends BaseMapper<BorrowRecords> {

    List<BorrowResult> findAll(@Param("username") String username, @Param("title") String title);


    @Update("UPDATE borrow_records SET book_id = #{bookId}, user_id = #{userId}, borrow_date = #{bor}, return_date = #{ret} WHERE record_id = #{recordId}")
    void update1(@Param("recordId") Integer recordId,
                                @Param("userId") Integer userId,
                                @Param("bookId") Integer bookId,
                                @Param("bor") Date bor,
                                @Param("ret") Date ret);

}
