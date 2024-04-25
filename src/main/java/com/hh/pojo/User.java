package com.hh.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author hh
 * @Date 2024/4/22 15:12
 * @description:
 */
@Data
public class User {
    @TableId
    private Integer userId;
    private String username;
    private String password;
    private Integer role;
}
