package com.hh.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author hh
 * @Date 2024/4/22 18:15
 * @description:
 */
@Data
public class BorrowRecords {
    private Integer recordId;
    private Integer UserId;
    private Integer BookId;
    private Date borrowDate;
    private Date returnDate;
}
