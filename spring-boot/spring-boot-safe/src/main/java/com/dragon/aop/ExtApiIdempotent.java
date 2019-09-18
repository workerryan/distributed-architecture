package com.dragon.aop;

import com.dragon.utils.ConstantUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 解决接口幂等性 支持网络延迟和表单重复提交
 * @author wanglei
 * @since 1.0.0
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtApiIdempotent {
    /** 默认从头里面获取token*/
    String type() default ConstantUtils.EXTAPIHEAD;
}
