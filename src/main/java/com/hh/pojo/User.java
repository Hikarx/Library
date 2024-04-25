package com.hh.pojo;


import lombok.Data;

/**
 * @Author hh
 * @Date 2024/4/22 15:12
 * @description:
 */
@Data
public class User {
    private Integer userId;
    private String username;
    private String password;
    private Integer role;
}
