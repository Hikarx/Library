package com.hh.exception;

import lombok.Data;

/**
 * @Author hh
 * @Date 2024/5/3 14:21
 * @description:
 */
public enum BorrowExceptionEnum {

    BOOK_ALREADY_BORROWED("书籍已借阅", 321),
    BOOK_NOT_BORROWED("该书籍暂未借阅", 322),
    NO_PERMISSION_TO_RETURN("无权限归还", 323),
    USER_NOT_FOUND("该用户不存在", 324),
    INVALID_USERNAME_OR_PASSWORD("用户名或密码错误", 325),
    ;

    private String desc;
    private Integer code;

    BorrowExceptionEnum(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "BorrowExceptionEnum{" +
                "desc='" + desc + '\'' +
                ", code=" + code +
                "} " + super.toString();
    }
}
