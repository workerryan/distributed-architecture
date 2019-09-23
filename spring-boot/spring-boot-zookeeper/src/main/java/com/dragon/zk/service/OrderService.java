package com.dragon.zk.service;

import com.dragon.zk.lock.ZkLock;
import com.dragon.zk.lock.ZookeeperDistrbuteLock;
import com.dragon.zk.utils.OrderNumGenerator;

public class OrderService implements Runnable{
    private OrderNumGenerator orderNumGenerator = new OrderNumGenerator();
    private ZkLock zkLock = new ZookeeperDistrbuteLock();


    @Override
    public void run() {

    }

    public void getNumber(){
        String number = orderNumGenerator.getNumber();
        System.out.println(Thread.currentThread().getName() + ", sn = " + number);
    }

    public static void main(String[] args) {
        OrderService service = new OrderService();
        for (int i = 0; i < 100; i++) {
            new Thread(service).start();
        }
    }
}
