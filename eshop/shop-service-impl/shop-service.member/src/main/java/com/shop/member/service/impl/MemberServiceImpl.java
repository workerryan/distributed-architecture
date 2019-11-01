package com.shop.member.service.impl;

import com.shop.common.core.base.api.BaseApiService;
import com.shop.common.core.base.entity.BaseResponse;
import com.shop.common.core.constants.Constants;
import com.shop.common.core.token.GenerateToken;
import com.shop.common.core.type.TypeCastHelper;
import com.shop.common.core.utils.ShopBeanUtils;
import com.shop.member.dto.output.UserOutDTO;
import com.shop.member.entity.UserEntity;
import com.shop.member.mapper.UserMapper;
import com.shop.member.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


/**
 * 会员实现
 */
@RestController
public class MemberServiceImpl extends BaseApiService<UserOutDTO> implements MemberService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GenerateToken generateToken;

    @Override
    public BaseResponse<UserOutDTO> existMobile(String mobile) {
        // 1.验证参数
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        // 2.根据手机号码查询用户信息 单独定义code 表示是用户信息不存在把
        UserEntity userEntity = userMapper.existMobile(mobile);
        if (userEntity == null) {
            return setResultError(Constants.HTTP_RES_CODE_EXISTMOBILE_203, "用户信息不存在!");
        }
        UserOutDTO userOutDTO = ShopBeanUtils.convert(userEntity, UserOutDTO.class);
        return setResultSuccess(userOutDTO);
    }

    @Override
    public BaseResponse<UserOutDTO> getInfo(String token) {
        // 1.验证token参数
        if (StringUtils.isEmpty(token)) {
            return setResultError("token不能为空!");
        }
        // 2.使用token查询redis 中的userId
        String redisUserId = generateToken.getToken(token);
        if (StringUtils.isEmpty(redisUserId)) {
            return setResultError("token已经失效或者token错误!");
        }
        // 3.使用userID查询 数据库用户信息
        Long userId = TypeCastHelper.toLong(redisUserId);
        UserEntity userDo = userMapper.findByUserId(userId);
        if (userDo == null) {
            return setResultError("用户不存在!");
        }
        // 下节课将 转换代码放入在BaseApiService
        return setResultSuccess(ShopBeanUtils.convert(userDo, UserOutDTO.class));
    }
}
