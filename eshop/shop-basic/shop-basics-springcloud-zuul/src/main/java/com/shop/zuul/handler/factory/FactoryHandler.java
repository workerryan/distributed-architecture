package com.shop.zuul.handler.factory;

import com.shop.common.core.utils.SpringContextUtil;
import com.shop.zuul.handler.GatewayHandler;
import com.shop.zuul.handler.impl.ApiAuthorityHandler;
import com.shop.zuul.handler.impl.BlacklistHandler;
import com.shop.zuul.handler.impl.CurrentLimitHandler;
import com.shop.zuul.handler.impl.ToVerifyMapHandler;

import java.util.Arrays;
import java.util.List;

/**
 * 使用工厂去获取Hander
 */
public class FactoryHandler {

    /**
     * 封装所有的责任链启动方式，将每个Handler存放到集合中执行便利
     * 这里后续需要放入数据库或者配置中心里面，以便能动态的调整执行顺序
     */
    public List<GatewayHandler> getAllHandler(){
        return Arrays.asList(SpringContextUtil.getBean(CurrentLimitHandler.class),
                SpringContextUtil.getBean(BlacklistHandler.class),
                SpringContextUtil.getBean(ToVerifyMapHandler.class),
                SpringContextUtil.getBean(ApiAuthorityHandler.class));
    }
}
