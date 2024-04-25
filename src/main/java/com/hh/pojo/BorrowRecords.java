package com.hh.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date borrowDate;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date returnDate;
    private Integer role;
}
