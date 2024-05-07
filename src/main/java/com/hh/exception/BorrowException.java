package com.hh.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author hh
 * @Date 2024/5/3 21:48
 * @description:
 */
@AllArgsConstructor
@Data
public class BorrowException extends RuntimeException{

    private BorrowExceptionEnum e;

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
