package com.hh.pojo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author hh
 * @Date 2024/4/22 18:15
 * @description:
 */
@Data
public class Books {
    @TableId
    private Integer bookId;
    private String title;
    private String author;
    private Integer isBorrowed;

    @JsonFormat(pattern = "yyyy/MM/dd hh:ss",timezone = "GMT+8")
    private Date borrowDate;
    @JsonFormat(pattern = "yyyy/MM/dd hh:ss",timezone = "GMT+8")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Date returnDate;
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String username;

}
