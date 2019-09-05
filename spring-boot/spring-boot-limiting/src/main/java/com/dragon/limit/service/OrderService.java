package com.dragon.limit.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public boolean addOrder() {
        System.out.println("db....正在操作订单表数据库...");
        return true;
    }
}
