package com.mysterybox.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysterybox.entity.PaymentRecord;
import com.mysterybox.service.PaymentRecordService;
import com.mysterybox.mapper.PaymentRecordMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class PaymentRecordServiceImpl extends ServiceImpl<PaymentRecordMapper, PaymentRecord>
    implements PaymentRecordService{

}




