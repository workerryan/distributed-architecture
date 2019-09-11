package com.dragon.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenUtils {

    private static Map<String, String> tokenMaps = new ConcurrentHashMap<>();

    /**
     * 单机环境的获取令牌，分布式场景下要使用分布式全局ID实现
     * @return
     */
    public synchronized static String getToken(){
        String token = "token-" + System.currentTimeMillis();
        tokenMaps.put(token, token);
        return token;
    }

    public static boolean findToken(String tokenKey){
        String token = tokenMaps.get(tokenKey);
        if(StringUtils.isBlank(token)){
            return false;
        }
        tokenMaps.remove(tokenKey);
        return true;
    }

}
