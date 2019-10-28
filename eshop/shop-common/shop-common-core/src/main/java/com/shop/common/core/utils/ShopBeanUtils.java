package com.shop.common.core.utils;

import org.springframework.beans.BeanUtils;

public class ShopBeanUtils<Dto, Do>  {

    /**
     * dot 转换为 Entity 工具类
     */
    public static <Do> Do dtoToDo(Object dtoEntity, Class<Do> entityClazz) {
        // 判断dto是否为空!
        if (dtoEntity == null) {
            return null;
        }
        // 判断DoClass 是否为空
        if (entityClazz == null) {
            return null;
        }
        try {
            Do newInstance = entityClazz.newInstance();
            BeanUtils.copyProperties(dtoEntity, newInstance);
            // Dto转换Do
            return newInstance;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Entity 转换为Dto 工具类
     */
    public static <Dto> Dto doToDto(Object entity, Class<Dto> dtoClass) {
        // 判断dto是否为空!
        if (entity == null) {
            return null;
        }
        // 判断DoClass 是否为空
        if (dtoClass == null) {
            return null;
        }
        try {
            Dto newInstance = dtoClass.newInstance();
            BeanUtils.copyProperties(entity, newInstance);
            // Dto转换Do
            return newInstance;
        } catch (Exception e) {
            return null;
        }
    }
}
