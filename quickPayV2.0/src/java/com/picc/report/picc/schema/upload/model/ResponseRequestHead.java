package com.picc.report.picc.schema.upload.model;

import com.ymt.annotation.ClassConvert;

@ClassConvert(name="requesthead")
public class ResponseRequestHead {
	String uuid;
	
	String sender;
	
	String requestType;
	
	String serverVersion;
	
	String responseCode;
	
	String errorMessage;
	
	String flowintime;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getServerVersion() {
		return serverVersion;
	}

	public void setServerVersion(String serverVersion) {
		this.serverVersion = serverVersion;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getFlowintime() {
		return flowintime;
	}

	public void setFlowintime(String flowintime) {
		this.flowintime = flowintime;
	}
	
}
