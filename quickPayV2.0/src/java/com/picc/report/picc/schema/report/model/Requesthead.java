package com.picc.report.picc.schema.report.model;

import java.io.Serializable;
import java.util.Date;

import com.ymt.annotation.ClassConvert;
import com.ymt.annotation.FiledConvert;

@ClassConvert(name="requesthead")
public class Requesthead implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	private String user;
	@FiledConvert(name="request_type")
	private String requestType;
	private String password;
	@FiledConvert(name="server_version")
	private String serverVersion;
	private String sender;
	private String uuid;
	private Date timestamp;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getServerVersion() {
		return serverVersion;
	}
	public void setServerVersion(String serverVersion) {
		this.serverVersion = serverVersion;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return "Requesthead [user=" + user + ", requestType=" + requestType + ", password=" + password + ", serverVersion=" + serverVersion + ", sender=" + sender + ", uuid=" + uuid + ", timestamp=" + timestamp + "]";
	}
	
	
	
}
