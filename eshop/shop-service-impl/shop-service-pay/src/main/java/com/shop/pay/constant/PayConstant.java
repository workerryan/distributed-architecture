package com.shop.pay.constant;

/**
 * 支付相关常量数据
 * 
 * 
 * @description:
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
public interface PayConstant {

	String RESULT_NAME = "result";
	String RESULT_PAYCODE_201 = "201";
	String RESULT_PAYCODE_200 = "200";
	/**
	 * 已经支付成功状态
	 */
	Integer PAY_STATUS_SUCCESS = 1;
	/**
	 * 返回银联通知成功
	 */
	String YINLIAN_RESULT_SUCCESS = "ok";
	/**
	 * 返回银联失败通知
	 */
	String YINLIAN_RESULT_FAIL = "fail";
}
