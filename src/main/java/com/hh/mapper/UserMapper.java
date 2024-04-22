package com.hh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hh.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author hh
 * @Date 2024/4/22 15:12
 * @description:
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
