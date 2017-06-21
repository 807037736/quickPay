package com.picc.report.picc.schema.upload.model;

import java.io.Serializable;

import com.ymt.annotation.ClassConvert;
import com.ymt.annotation.FiledConvert;

@ClassConvert(name="requesthead")
public class RequestHead implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String uuid;
	@FiledConvert(name="request_type")
	private String requestType;
	private String sender;
	@FiledConvert(name="server_version")
	private String serverVersion;
	private String user;
	private String password;
	private String flowintime;
	
	
	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getRequestType() {
		return requestType;
	}


	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}


	public String getSender() {
		return sender;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}


	public String getServerVersion() {
		return serverVersion;
	}


	public void setServerVersion(String serverVersion) {
		this.serverVersion = serverVersion;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFlowintime() {
		return flowintime;
	}


	public void setFlowintime(String flowintime) {
		this.flowintime = flowintime;
	}


	@Override
	public String toString() {
		return "Requesthead [user=" + user + ", requestType=" + requestType + ", password=" + password + ", serverVersion=" + serverVersion + ", sender=" + sender + ", uuid=" + uuid + ", flowintime=" + flowintime + "]";
	}
}
