package com.picc.report.picc.schema.upload.model;

import com.ymt.annotation.ClassConvert;

@ClassConvert(name="response")
public class Response {
	
	ResponseRequestBody requestBody;
	
	ResponseRequestHead requestHead;

	public ResponseRequestBody getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(ResponseRequestBody requestBody) {
		this.requestBody = requestBody;
	}

	public ResponseRequestHead getRequestHead() {
		return requestHead;
	}

	public void setRequestHead(ResponseRequestHead requestHead) {
		this.requestHead = requestHead;
	}
	
}
