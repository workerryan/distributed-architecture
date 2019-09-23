package com.dragon.zk.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderNumGenerator {
    /** 生成订单号规则*/
    private static int count = 0;

    public String getNumber() {
        try {
            Thread.sleep(200);
        } catch (Exception e) {

        }
        SimpleDateFormat simpt = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return simpt.format(new Date()) + "-" + ++count;
    }
}
