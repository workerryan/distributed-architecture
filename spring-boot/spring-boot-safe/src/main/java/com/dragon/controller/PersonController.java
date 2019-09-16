package com.dragon.controller;

import com.dragon.entity.Person;
import com.dragon.service.PersonService;
import com.dragon.service.RedisToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wanglei
 * @since 1.0.0
 */
@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @Autowired
    private RedisToken redisToken;

    @RequestMapping("/redisToken")
    public String RedisToken() {
        return redisToken.getToken();
    }

    @RequestMapping("/person/save")
    public String save(
            HttpServletRequest request,
            @RequestBody Person person){
        String token = request.getHeader("token");
        if(StringUtils.isBlank(token)){
            return "参数错误";
        }
        boolean hasToken = redisToken.findToken(token);
        if(!hasToken){
            return "请勿重复提交";
        }

        personService.save(person);
        return "yes";
    }
}
