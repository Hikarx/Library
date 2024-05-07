package com.hh.controller;

import com.hh.exception.BorrowException;
import com.hh.pojo.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author hh
 * @Date 2024/5/6 16:38
 * @description:
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(value = BorrowException.class)
    @ResponseBody
    public CommonResp exceptionHandler(BorrowException e) {
        CommonResp commonResp = new CommonResp();
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getE().getDesc());
        log.error("业务异常: {}", e.getE().getDesc());
        return commonResp;
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResp exceptionHandler(Exception e) {

        CommonResp commonResp = new CommonResp();
        log.error("系统异常: {}", e);
        commonResp.setSuccess(false);
        commonResp.setMessage("系统内部异常");
        return commonResp;
    }
}
