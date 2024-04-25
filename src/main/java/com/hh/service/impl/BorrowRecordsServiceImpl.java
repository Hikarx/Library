package com.hh.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hh.mapper.BorrowRecordsMapper;
import com.hh.pojo.BorrowRecords;
import com.hh.service.BorrowRecordsService;
import org.springframework.stereotype.Service;

/**
 * @Author hh
 * @Date 2024/4/22 18:16
 * @description:
 */

@Service
public class BorrowRecordsServiceImpl extends ServiceImpl<BorrowRecordsMapper, BorrowRecords>
        implements BorrowRecordsService {
}
