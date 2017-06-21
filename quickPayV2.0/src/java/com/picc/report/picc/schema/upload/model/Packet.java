package com.picc.report.picc.schema.upload.model;

import java.io.Serializable;

import com.ymt.annotation.ClassConvert;
import com.ymt.annotation.FiledConvert;

@ClassConvert(name="PACKET")
public class Packet implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 请求报文头
	 */
	@FiledConvert(name="requesthead")
	private RequestHead requestHead;
	
	/**
	 * 请求报文主题
	 */
	@FiledConvert(name="requestbody")
	private RequestBody requestBody;

	
	public RequestHead getRequestHead() {
		return requestHead;
	}

	public void setRequestHead(RequestHead requestHead) {
		this.requestHead = requestHead;
	}

	public RequestBody getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(RequestBody requestBody) {
		this.requestBody = requestBody;
	}

	@Override
	public String toString() {
		return "packet [requesthead=" + requestHead + ", requestbody=" + requestBody + "]";
	}
	
}
