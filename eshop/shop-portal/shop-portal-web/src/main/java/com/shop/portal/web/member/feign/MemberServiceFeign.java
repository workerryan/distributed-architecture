package com.shop.portal.web.member.feign;

import com.alibaba.fastjson.JSONObject;
import com.shop.common.core.base.entity.BaseResponse;
import com.shop.member.dto.input.UserInpDTO;
import com.shop.member.dto.input.UserLoginInpDTO;
import com.shop.member.service.MemberLoginService;
import com.shop.member.service.MemberRegisterService;
import com.shop.member.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("app-member")
public interface MemberServiceFeign extends MemberService  {

    @PostMapping("/login")
    BaseResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDto);

    /**
     * 用户注册接口
     *
     * @param userInpDTO
     * @return
     */
    @PostMapping("/register")
    BaseResponse<JSONObject> register(@RequestBody UserInpDTO userInpDTO,
                                      @RequestParam("registCode") String registerCode);


    /**
     * 根据 openid查询是否已经绑定,如果已经绑定，则直接实现自动登陆
     *
     * @param qqOpenId  qq的openId
     */
    @RequestMapping("/findByOpenId")
    BaseResponse<JSONObject> findByOpenId(@RequestParam("qqOpenId") String qqOpenId);
}
