package com.shop.seckill.mq.transaction;

import com.shop.seckill.mq.transaction.mapper.IntegralMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntegralService {
    @Autowired
    private IntegralMapper integralMapper;


    public boolean incrIntegral(Integral integral){
        Integer id = integralMapper.getIdByPhone(integral.getPhone());
        if(id == null){
            return integralMapper.insert(integral) == 1;
        }
        return integralMapper.updateIntegral(id, integral.getIntegral()) == 1;
    }
}
