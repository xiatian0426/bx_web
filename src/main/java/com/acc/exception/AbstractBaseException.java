/**
 * 
 */
package com.acc.exception;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

abstract class AbstractBaseException extends RuntimeException {


	
	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = 7157459396839212223L;

	private ErrorCodeInter errorCode;

	private Map<String, Object> params = new HashMap<String, Object>();

	/**
	 * @param errorCode
	 */
	protected AbstractBaseException(ErrorCodeInter errorCode) {
		super(JSON.toJSONString(errorCode));
		this.errorCode = errorCode;
	}

	/**
	 * @param cause
	 * @param errorCode
	 */
	protected AbstractBaseException(Throwable cause, ErrorCodeInter errorCode) {
		super(JSON.toJSONString(errorCode), cause);
		this.errorCode = errorCode;
	}

	/**
	 * @param message
	 * @param cause
	 * @param errorCode
	 */
	protected AbstractBaseException(String message, Throwable cause, ErrorCodeInter errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	/**
	 * @return
	 * @author liujun
	 * @Date 2015-8-14
	 */
	public ErrorCodeInter getErrorCode() {
		return errorCode;
	}

	/**
	 * setParams: 记录参数到快照
	 * 
	 * @author liujun
	 * @param key  参数名
	 * @param value 参数值
	 * @return
	 * @since JDK 1.6
	 */
	public AbstractBaseException setParams(final String key, final Object value) {
		params.put(key, value);
		return this;
	}

	/**
	 * setParamsAll:记录一组参数到快照
	 * @author liujun
	 * @param params
	 * @return
	 * @since JDK 1.6
	 */
	public AbstractBaseException setParamsAll(Map<String, Object> params) {
		params.putAll(params);
		return this;
	}

	/**
	 * getSystemLogDesc: 获取异常快照信息
	 * @author liujun
	 * @return
	 * @since JDK 1.6
	 */
	public String getExceptionSnapshotInfo() {
		Map<String, Object> errorInfo = new LinkedHashMap<String, Object>();
		errorInfo.put("errorType", errorCode.getClass().getName());
		errorInfo.put("errorCode", errorCode.getCode());
		errorInfo.put("message", errorCode.getDesc());
		errorInfo.put("snapshot", params.toString());
		return errorInfo.toString();
	}
}
