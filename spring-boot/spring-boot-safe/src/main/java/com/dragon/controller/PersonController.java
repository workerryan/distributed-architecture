package com.dragon.controller;

import com.dragon.aop.ExtApiIdempotent;
import com.dragon.aop.ExtApiToken;
import com.dragon.entity.Person;
import com.dragon.service.PersonService;
import com.dragon.service.RedisToken;
import com.dragon.utils.ConstantUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wanglei
 * @since 1.0.0
 */
@Controller
public class PersonController {
    @Autowired
    private PersonService personService;

    @Autowired
    private RedisToken redisToken;

    @ResponseBody
    @RequestMapping("/redisToken")
    public String RedisToken() {
        return redisToken.getToken();
    }

    @ResponseBody
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

    @ResponseBody
    @ExtApiIdempotent(type = ConstantUtils.EXTAPIHEAD)
    @RequestMapping("/person/save/v2")
    public String savev2( @RequestBody Person person){
        personService.save(person);
        return "yes";
    }

    @ExtApiToken
    @RequestMapping("/person/index")
    public String index(){
        return "person/index";
    }


    @ResponseBody
    @ExtApiIdempotent(type = ConstantUtils.EXTAPIFROM)
    @RequestMapping("/person/save/v3")
    public String savev3(Person person){
        personService.save(person);
        return "yes-v3";
    }
}
