package com.shop.member.mapper;

import com.shop.member.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
	@Insert("INSERT INTO user VALUES (null,#{mobile}, #{email}, #{password}, #{userName}, null, null, null, '1', null, null, null);")
	int register(UserEntity userEntity);

	@Select("SELECT * FROM user WHERE MOBILE=#{mobile};")
	UserEntity existMobile(@Param("mobile") String mobile);

	@Select("SELECT USER_ID AS USERID ,MOBILE AS MOBILE,EMAIL AS EMAIL,PASSWORD AS PASSWORD, USER_NAME AS USER_NAME , " +
			"	SEX AS SEX ,AGE AS AGE ,CREATE_TIME AS CREATETIME,IS_AVALIBLE AS ISAVALIBLE,PIC_IMG AS PICIMG, " +
			"	QQ_OPENID AS QQOPENID,WX_OPENID AS WXOPENID "
			+ "  FROM user  WHERE MOBILE=#{mobile} and password=#{password};")
	UserEntity login(@Param("mobile") String mobile, @Param("password") String password);

	@Select("SELECT USER_ID AS userId ,MOBILE AS MOBILE,EMAIL AS EMAIL,PASSWORD AS PASSWORD, USER_NAME AS userName , " +
			"	SEX AS SEX ,AGE AS AGE ,CREATE_TIME AS createTime,IS_AVALIBLE AS isAvalible,PIC_IMG AS picImg, " +
			"	QQ_OPENID AS qqOpenid,WX_OPENID AS wxOpenid "
			+ " FROM user WHERE user_Id=#{userId}")
	UserEntity findByUserId(@Param("userId") Long userId);


	@Select("SELECT USER_ID AS USERID ,MOBILE AS MOBILE,EMAIL AS EMAIL,PASSWORD AS PASSWORD, USER_NAME AS USER_NAME , " +
			"SEX AS SEX ,AGE AS AGE ,CREATE_TIME AS CREATETIME,IS_AVALIBLE AS ISAVALIBLE,PIC_IMG AS PICIMG, " +
			"QQ_OPENID AS QQOPENID,WX_OPENID AS WXOPENID " +
			"FROM user WHERE qq_openid=#{qqOpenId}")
	UserEntity findByOpenId(@Param("qqOpenId") String qqOpenId);

	@Update("update user set QQ_OPENID =#{qqOpenId} WHERE USER_ID=#{userId}")
	int updateUserOpenId(@Param("qqOpenId") String qqOpenId, @Param("userId") Long userId);
}
