package com.shop.weixin.mp.handler;

import com.shop.common.core.base.entity.BaseResponse;
import com.shop.common.core.constants.Constants;
import com.shop.common.core.utils.RedisUtil;
import com.shop.common.core.utils.RegexUtils;
import com.shop.member.dto.output.UserOutDTO;
import com.shop.member.entity.UserEntity;
import com.shop.weixin.feign.MemberServiceFeign;
import com.shop.weixin.mp.builder.TextBuilder;
import com.shop.weixin.mp.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {

    private static Random random = new Random();

    /**用户发送手机验证码提示 */
    @Value("${reply.weixin.registration.code.message}")
    private String registrationCodeMessage;
    /** 默认用户发送验证码提示*/
    @Value("${reply.weixin.default.registration.code.message}")
    private String defaultRegistrationCodeMessage;

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private MemberServiceFeign memberServiceFeign;


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        try {
            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
                && weixinService.getKefuService().kfOnlineList()
                .getKfOnlineList().size() > 0) {
                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser()).build();
            }
        } catch (WxErrorException e) {
            logger.error("对用户输入的信息处理错误", e);
        }

        //微信公众号上输入的文本
        String inputContent = wxMessage.getContent();
        if(RegexUtils.checkMobile(inputContent)){
            //校验该手机号是否已经注册
            BaseResponse<UserOutDTO> reusltUserInfo = memberServiceFeign.existMobile(inputContent);
            if (reusltUserInfo.getCode().equals(Constants.HTTP_RES_CODE_200)) {
                return new TextBuilder().build("该手机号码" + inputContent + "已经存在!", wxMessage, weixinService);
            }
            if (!reusltUserInfo.getCode().equals(Constants.HTTP_RES_CODE_EXISTMOBILE_203)) {
                return new TextBuilder().build(reusltUserInfo.getMsg(), wxMessage, weixinService);
            }

            //手机号，生产注册码
            int code = registerCode();
            String content = String.format(registrationCodeMessage, code);
            //将注册码存储到Redis中
            redisUtil.setString(Constants.WEIXINCODE_KEY + inputContent, String.valueOf(code), Constants.WEIXINCODE_TIMEOUT);

            return new TextBuilder().build(content, wxMessage, weixinService);
        }
        //返回默认消息
        return new TextBuilder().build(defaultRegistrationCodeMessage, wxMessage, weixinService);
    }

    /** 获取注册码*/
    private int registerCode() {
        return random.nextInt(1000) + 1000;
    }
}
