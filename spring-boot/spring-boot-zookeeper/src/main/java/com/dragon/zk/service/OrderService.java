package com.dragon.zk.service;

import com.dragon.zk.lock.ZkLock;
import com.dragon.zk.lock.ZookeeperDistrbuteLock;
import com.dragon.zk.utils.OrderNumGenerator;

public class OrderService implements Runnable{
    private OrderNumGenerator orderNumGenerator = new OrderNumGenerator();
    private ZkLock zkLock = new ZookeeperDistrbuteLock();


    @Override
    public void run() {
        getNumber();
    }

    public void getNumber(){
        try {
            zkLock.getLock();
            String number = orderNumGenerator.getNumber();
            System.out.println(Thread.currentThread().getName() + ", sn = " + number);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            zkLock.unLock();
        }
    }

    public static void main(String[] args) {
        //如果强制关了程序，那么会对锁的获取产生延迟，因为强制关闭了程序，断掉链接，zk会对临时节点一会再删除
        for (int i = 0; i < 100; i++) {
            new Thread(new OrderService()).start();
        }
    }
}
