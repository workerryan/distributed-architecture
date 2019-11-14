package com.shop.xxl.sso.feign;

import com.shop.member.service.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-member")
public interface MemberServiceFeign extends MemberService {

}
