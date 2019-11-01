package com.shop.member.mapper;

import com.shop.member.entity.UserToken;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserTokenMapper {
    @Select("SELECT id as id ,token as token ,login_type as LoginType, device_infor as deviceInfor ,is_availability as isAvailability,user_id as userId  , " +
            " create_time as createTime,update_time as updateTime   " +
            "FROM user_token WHERE user_id=#{userId} AND login_type=#{loginType} and is_availability ='0' ")
    UserToken selectByUserIdAndLoginType(@Param("userId") Long userId, @Param("loginType") String loginType);

    @Update(" update user_token set is_availability  ='1', update_time=now() where token=#{token}")
    int updateTokenAvailability(@Param("token") String token);

    @Insert("INSERT INTO `user_token` VALUES (null, #{token},#{loginType}, #{deviceInfor}, 0, #{userId} ,now(),null ); ")
    int insertUserToken(UserToken userTokenDo);
}
