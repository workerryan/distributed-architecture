package com.shop.portal.web.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.common.core.base.entity.BaseResponse;
import com.shop.common.web.base.BaseWebController;
import com.shop.common.web.utils.CookieUtils;
import com.shop.member.dto.output.UserOutDTO;
import com.shop.portal.web.member.feign.MemberServiceFeign;
import com.shop.portal.web.web.constants.WebConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController extends BaseWebController {
	@Autowired
	private MemberServiceFeign memberServiceFeign;
	/**
	 * 跳转到index页面
	 */
	private static final String INDEX_FTL = "index";

	@RequestMapping(value = {"/","/index.html"})
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 1.从cookie 中 获取 会员token
		String token = CookieUtils.getCookieValue(request, WebConstants.LOGIN_TOKEN_COOKIENAME, true);
		if (!StringUtils.isEmpty(token)) {
			// 2.调用会员服务接口,查询会员用户信息
			BaseResponse<UserOutDTO> userInfo = memberServiceFeign.getInfo(token);
			if (isSuccess(userInfo)) {
				UserOutDTO data = userInfo.getData();
				if (data != null) {
					String mobile = data.getMobile();
					// 对手机号码实现脱敏
					String desensMobile = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
					model.addAttribute("desensMobile", desensMobile);
				}
			}
		}
		return INDEX_FTL;
	}
	// 作业题：完成退出功能 实现唯一登陆心跳检测 前端定时器 定时 使用js 读取本地cookie 信息 使用token 查询当前状态 如果token状态为1的话，页面直接刷新下。

	@RequestMapping(value = "/exit")
	public String exit(HttpServletRequest request, HttpServletResponse response, Model model){
		CookieUtils.deleteCookie(request, response, WebConstants.LOGIN_TOKEN_COOKIENAME);
		return INDEX_FTL;
	}
}
