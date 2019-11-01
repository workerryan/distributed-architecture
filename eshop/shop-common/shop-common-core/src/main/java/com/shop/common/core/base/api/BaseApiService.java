package com.shop.common.core.base.api;

import com.shop.common.core.base.entity.BaseResponse;
import com.shop.common.core.constants.Constants;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class BaseApiService<T> {

	public BaseResponse<T> setResultError(Integer code, String msg) {
		return setResult(code, msg, null);
	}

	/** 返回500错误，可以传msg*/
	public BaseResponse<T> setResultError(String msg) {
		return setResult(Constants.HTTP_RES_CODE_500, msg, null);
	}

	/** 返回成功，可以传data值*/
	public BaseResponse<T> setResultSuccess(Object data) {
		return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, data);
	}

	/** 返回成功，沒有data值*/
	public BaseResponse<T> setResultSuccess() {
		return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, null);
	}

	/** 返回成功，沒有data值 */
	public BaseResponse<T> setResultSuccess(String msg) {
		return setResult(Constants.HTTP_RES_CODE_200, msg, null);
	}

	/**  通用封装*/
	public BaseResponse<T> setResult(Integer code, String msg, Object data) {
		return new BaseResponse(code, msg, data);
	}


	public boolean toDaoResult(int result){
		return result > 0;
	}
}
