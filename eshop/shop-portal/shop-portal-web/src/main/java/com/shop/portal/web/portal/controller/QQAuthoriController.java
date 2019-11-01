package com.shop.portal.web.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.common.core.base.entity.BaseResponse;
import com.shop.common.core.constants.Constants;
import com.shop.common.core.utils.ShopBeanUtils;
import com.shop.common.web.base.BaseWebController;
import com.shop.common.web.utils.CookieUtils;
import com.shop.member.dto.input.UserLoginInpDTO;
import com.shop.portal.web.member.controller.req.vo.LoginVo;
import com.shop.portal.web.member.feign.MemberServiceFeign;
import com.shop.portal.web.web.constants.WebConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: QQ授权
 */
@Controller
@Slf4j
public class QQAuthoriController extends BaseWebController {
	/*@Autowired
	private QQAuthoriFeign qqAuthoriFeign;*/

	private static final String MB_QQ_QQLOGIN = "member/qqlogin";
	@Autowired
	private MemberServiceFeign memberServiceFeign;
	/**
	 * 重定向到首页
	 */
	private static final String REDIRECT_INDEX = "redirect:/";

	/**
	 * 生成授权链接
	 */
	@RequestMapping("/qqAuth")
	public String qqAuth(HttpServletRequest request) {
		try {
			return "redirect:" + new Oauth().getAuthorizeURL(request);
		} catch (Exception e) {
			return ERROR_500_FTL;
		}
	}

	/**
	 * qq 授权回调
	 */
	@RequestMapping("/qq/fallback")
	public String qqLoginBack(String code, HttpServletRequest request, HttpServletResponse response,HttpSession httpSession) {
		try {
			// 使用授权码获取accessToken
			AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
			if (accessTokenObj == null) {
				return ERROR_500_FTL;
			}
			String accessToken = accessTokenObj.getAccessToken();
			if (StringUtils.isEmpty(accessToken)) {
				return ERROR_500_FTL;
			}
			// 使用accessToken获取用户openid
			OpenID openIdObj = new OpenID(accessToken);
			String openId = openIdObj.getUserOpenID();
			if (StringUtils.isEmpty(openId)) {
				return ERROR_500_FTL;
			}

			// 使用openid 查询数据库是否已经关联账号信息
			BaseResponse<JSONObject> findByOpenId = memberServiceFeign.findByOpenId(openId);
			if (!isSuccess(findByOpenId)) {
				return ERROR_500_FTL;
			}

			// 如果调用接口返回203 ,跳转到关联账号页面
			if (findByOpenId.getCode().equals(Constants.HTTP_RES_CODE_EXISTMOBILE_203)) {
				// 页面需要展示 QQ头像
				UserInfo qzoneUserInfo = new UserInfo(accessToken, openId);
				UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
				if (userInfoBean == null) {
					return ERROR_500_FTL;
				}
				// 用户的QQ头像
				String avatarURL100 = userInfoBean.getAvatar().getAvatarURL100();
				request.setAttribute("avatarURL100", avatarURL100);

				// 需要将openid存入在session中
				httpSession.setAttribute(WebConstants.LOGIN_QQ_OPENID, openId);
				return MB_QQ_QQLOGIN;
			}
			// 如果能够查询到用户信息的话,直接自动登陆
			// 自动实现登陆
			JSONObject data = findByOpenId.getData();
			String token = data.getString("token");
			CookieUtils.setCookie(request, response, WebConstants.LOGIN_TOKEN_COOKIENAME, token);
			return REDIRECT_INDEX;

		} catch (Exception e) {
			return ERROR_500_FTL;
		}
	}

	/**
	 * 绑定qqOpenId
	 */
	@RequestMapping("/qq/qqJointLogin")
	public String qqJointLogin(@ModelAttribute("loginVo") LoginVo loginVo, Model model, HttpServletRequest request,
							   HttpServletResponse response, HttpSession httpSession) {
		// 1.获取用户openid
		String qqOpenId = (String) httpSession.getAttribute(WebConstants.LOGIN_QQ_OPENID);
		if (StringUtils.isEmpty(qqOpenId)) {
			return ERROR_500_FTL;
		}

		// 2.将vo转换dto调用会员登陆接口
		UserLoginInpDTO userLoginInpDTO = ShopBeanUtils.convert(loginVo, UserLoginInpDTO.class);
		userLoginInpDTO.setQqOpenId(qqOpenId);
		userLoginInpDTO.setLoginType(Constants.MEMBER_LOGIN_TYPE_PC);
		String info = webBrowserInfo(request);
		userLoginInpDTO.setDeviceInfor(info);
		BaseResponse<JSONObject> login = memberServiceFeign.login(userLoginInpDTO);
		if (!isSuccess(login)) {
			setErrorMsg(model, login.getMsg());
			return MB_QQ_QQLOGIN;
		}
		// 3.登陆成功之后如何处理 保持会话信息 将token存入到cookie 里面 首页读取cookietoken 查询用户信息返回到页面展示
		JSONObject data = login.getData();
		String token = data.getString("token");
		CookieUtils.setCookie(request, response, WebConstants.LOGIN_TOKEN_COOKIENAME, token);
		return REDIRECT_INDEX;
	}

}
