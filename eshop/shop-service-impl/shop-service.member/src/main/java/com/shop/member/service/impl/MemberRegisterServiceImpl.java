package com.shop.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.shop.common.core.base.api.BaseApiService;
import com.shop.common.core.base.entity.BaseResponse;
import com.shop.common.core.constants.Constants;
import com.shop.common.core.utils.MD5Util;
import com.shop.common.core.utils.ShopBeanUtils;
import com.shop.member.dto.input.UserInpDTO;
import com.shop.member.entity.UserEntity;
import com.shop.member.feign.VerifiedCodeServiceFeign;
import com.shop.member.mapper.UserMapper;
import com.shop.member.service.MemberRegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRegisterServiceImpl extends BaseApiService<JSONObject> implements MemberRegisterService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VerifiedCodeServiceFeign verificaCodeServiceFeign;

    /**
     * 这里需要注意，接口层拆分了，在userEntity前面要加上@RequestBody注解，否则会存在映射不了的情况
     */
    @Override
    @Transactional
    public BaseResponse<JSONObject> register(@RequestBody UserInpDTO userInpDTO, String registCode) {
        // 1.参数验证
        /*String userName = userInpDTO.getUserName();
        if (StringUtils.isEmpty(userName)) {
            return setResultError("用户名称不能为空!");
        }*/
        String mobile = userInpDTO.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        String password = userInpDTO.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空!");
        }
        // 2.验证码注册码是否正确 暂时省略 会员调用微信接口实现注册码验证
         BaseResponse<JSONObject> verificaWeixinCode = verificaCodeServiceFeign.verifiedCode(mobile, registCode);
         if(!verificaWeixinCode.getCode().equals(Constants.HTTP_RES_CODE_200)) {
            return setResultError(verificaWeixinCode.getMsg());
         }
        // 3.对用户的密码进行加密 MD5 可以解密 暴力破解
        userInpDTO.setPassword(MD5Util.MD5(password));
        // 4.调用数据库插入数据 将请求的dto参数转换Entity
        UserEntity entity = ShopBeanUtils.convert(userInpDTO, UserEntity.class);

        return userMapper.register(entity) > 0 ? setResultSuccess("注册成功") : setResultError("注册失败!");

    }
}
