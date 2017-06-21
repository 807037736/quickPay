package com.picc.report.picc.schema.upload.model;

import com.ymt.annotation.ClassConvert;

@ClassConvert(name="requestbody")
public class ResponseRequestBody {
	String uuid;
	/**
	 * 返回标识
	 * 300:成功 201:失败
	 */
	String result;
	
	String errMessage;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * 返回标识
	 * 300:成功 201:失败
	 */
	public String getResult() {
		return result;
	}
	/**
	 * 返回标识
	 * 300:成功 201:失败
	 */
	public void setResult(String result) {
		this.result = result;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	
}
