package com.dragon.xxlclient.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author wanglei
 * @since 1.0.0
 */
@JobHandler(value="myJobHandler")
@Component
public class MyJobHandler extends IJobHandler {
    private static Logger logger = LoggerFactory.getLogger(MyJobHandler.class);
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        logger.info("执行定时任务，入参为 " + param);
        return ReturnT.SUCCESS;
    }
}
