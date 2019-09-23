package com.dragon.zk.lock;

import org.I0Itec.zkclient.IZkDataListener;
import java.util.concurrent.CountDownLatch;

public class ZookeeperDistrbuteLock extends ZookeeperAbstractLock {
    private CountDownLatch countDownLatch = null;

    @Override
    boolean tryLock() {
        try {
            zkClient.createEphemeral(PATH);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    void waitLock() {
        IZkDataListener izkDataListener = new IZkDataListener() {
            @Override
            public void handleDataDeleted(String path) throws Exception {
                // 节点被删除的情况，需要唤醒被等待的线程
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
            }
            @Override
            public void handleDataChange(String path, Object data) throws Exception {
                //节点被修改
            }
        };
        // 注册事件
        zkClient.subscribeDataChanges(PATH, izkDataListener);
        if (zkClient.exists(PATH)) {
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 删除监听，waitLock()会重复多次，所以这里要删除监听，为了避免监听多次
        zkClient.unsubscribeDataChanges(PATH, izkDataListener);
    }
}
