package com.shop.pay.callback.template.factory;


import com.shop.common.core.utils.SpringContextUtil;
import com.shop.pay.callback.template.AbstractPayCallbackTemplate;

/**
 * 获取具体实现的模版工厂方案
 *
 */
public class TemplateFactory {

	public static AbstractPayCallbackTemplate getPayCallbackTemplate(String beanId) {
		return (AbstractPayCallbackTemplate) SpringContextUtil.getBean(beanId);
	}

}
