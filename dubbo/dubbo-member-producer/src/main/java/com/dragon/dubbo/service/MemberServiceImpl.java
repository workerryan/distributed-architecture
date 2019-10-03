package com.dragon.dubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author wanglei
 * @since 1.0.0
 */
@Service
public class MemberServiceImpl implements IMemberService {

    @Value("${dubbo.protocol.port}")
    private String port;

    @Override
    public String getUser(String id) {
        System.out.println(id);
        return "Member - producer, id is " + id + " ，dubbo port is" + port;

    }
}
