package com.dragon;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dragon.dubbo.service.IMemberService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanglei
 * @since 1.0.0
 */
@RestController
public class MemberController {
    @Reference
    private IMemberService memberServiceImpl;

    @RequestMapping("/user/{id}")
    public String getUser(@PathVariable String id){
        return memberServiceImpl.getUser(id);
    }
}
