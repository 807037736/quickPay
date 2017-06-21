package com.picc.report.picc.schema.upload.model;

import java.io.Serializable;

import com.ymt.annotation.ClassConvert;
import com.ymt.annotation.FiledConvert;

@ClassConvert(name="PACKET")
public class UploadPacket implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 请求报文头
	 */
	@FiledConvert(name="requesthead")
	private UploadRequestHead requestHead;
	
	/**
	 * 请求报文主题
	 */
	@FiledConvert(name="requestbody")
	private UploadRequestBody requestBody;

	
	public UploadRequestHead getRequestHead() {
		return requestHead;
	}

	public void setRequestHead(UploadRequestHead requestHead) {
		this.requestHead = requestHead;
	}

	public UploadRequestBody getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(UploadRequestBody requestBody) {
		this.requestBody = requestBody;
	}

	@Override
	public String toString() {
		return "packet [requesthead=" + requestHead + ", requestbody=" + requestBody + "]";
	}
	
}
