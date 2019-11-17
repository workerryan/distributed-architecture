package com.shop.auth.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author wanglei
 * @since 1.0.0
 */
public interface AppInfoMapper {
    @Select("SELECT ID AS ID ,APP_NAME AS appName, app_id as appId, app_secret as appSecret ,is_flag as isFlag, access_token as accessToken " +
            "from m_app " +
            "where app_id=#{appId} and app_secret=#{appSecret} ")
    AppInfo findApp(AppInfo appEntity);

    @Select("SELECT ID AS ID ,APP_NAME AS appName, app_id as appId, app_secret as appSecret ,is_flag as isFlag, access_token as accessToken " +
            "from m_app " +
            "where app_id=#{appId}")
    AppInfo findAppId(@Param("appId") String appId);

    @Update("update m_app set access_token =#{accessToken} where app_id=#{appId} ")
    int updateAccessToken(@Param("accessToken") String accessToken, @Param("appId") String appId);

    @Insert("INSERT INTO m_app(`app_name`, `app_id`, `app_secret`, `availability`) VALUES (#{appName}, #{appId}, #{appSecret}, '0')")
    int insertAppInfo(AppInfo meiteAppInfo);

    @Select("SELECT ID AS ID ,app_id as appId, app_name AS appName ,app_secret as appSecret  " +
            "FROM m_app where app_id=#{appId} and app_secret=#{appSecret}; ")
    public AppInfo selectByAppInfo(@Param("appId") String appId, @Param("appSecret") String appSecret);

    @Select("SELECT ID AS ID ,app_id as appId, app_name AS appName ,app_secret as appSecret  " +
            "FROM m_app where app_id=#{appId}  ")
    public AppInfo findByAppInfo(@Param("appId") String appId);
}
