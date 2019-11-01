package com.shop.common.core.utils;

import org.springframework.beans.BeanUtils;

public class ShopBeanUtils<Dto, Do>  {

    /**
     * dot 转换为 Entity 工具类
     */
    public static <Do> Do convert(Object source, Class<Do> targetClazz) {
        // 判断dto是否为空!
        if (source == null) {
            return null;
        }
        // 判断DoClass 是否为空
        if (targetClazz == null) {
            return null;
        }
        try {
            Do newInstance = targetClazz.newInstance();
            BeanUtils.copyProperties(source, newInstance);
            // Dto转换Do
            return newInstance;
        } catch (Exception e) {
            return null;
        }
    }
}
