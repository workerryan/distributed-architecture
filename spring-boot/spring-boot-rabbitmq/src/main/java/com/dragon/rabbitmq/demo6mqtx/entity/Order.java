package com.dragon.rabbitmq.demo6mqtx.entity;

import java.util.Date;

/**
 * @author wanglei
 * @since 1.0.0
 */
public class Order {
    private int id;
    private String name;
    private Date createTime;
    /** 订单状态，0-未支付，1-已支付*/
    private int status;
    private int money;
    private int skuId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }
}
