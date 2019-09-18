package com.dragon.utils;

/**
 * 获取token的来源
 * @author wanglei
 * @since 1.0.0
 */
public interface ConstantUtils {

    /** 从请求头获取token*/
    String EXTAPIHEAD = "head";

    /** 从表单获取token*/
    String EXTAPIFROM = "from";
}
