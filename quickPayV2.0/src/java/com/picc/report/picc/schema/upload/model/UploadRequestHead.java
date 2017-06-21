package com.picc.report.picc.schema.upload.model;

import java.io.Serializable;

import com.ymt.annotation.ClassConvert;
import com.ymt.annotation.FiledConvert;

@ClassConvert(name="requesthead")
public class UploadRequestHead implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 请求唯一标识
	 */
	private String uuid;
	/**
	 * 请求类型
	 */
	@FiledConvert(name="request_type")
	private String requestType;
	/**
	 * 发送者代码
	 */
	private String sender;
	@FiledConvert(name="server_version")
	/**
	 * 服务器版本
	 */
	private String serverVersion;
	/**
	 * 用户
	 */
	private String user;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 请求服务时间
	 */
	private String flowintime;
	
	/**
	 * 请求唯一标识
	 */
	public String getUuid() {
		return uuid;
	}
	
	/**
	 * 请求唯一标识
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * 请求类型
	 */
	public String getRequestType() {
		return requestType;
	}

	/**
	 * 请求类型
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	/**
	 * 发送者代码
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * 发送者代码
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * 服务器版本
	 */
	public String getServerVersion() {
		return serverVersion;
	}

	/**
	 * 服务器版本
	 */
	public void setServerVersion(String serverVersion) {
		this.serverVersion = serverVersion;
	}

	/**
	 * 用户
	 */
	public String getUser() {
		return user;
	}

	/**
	 * 用户
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * 密码
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 请求服务时间
	 */
	public String getFlowintime() {
		return flowintime;
	}

	/**
	 * 请求服务时间
	 */
	public void setFlowintime(String flowintime) {
		this.flowintime = flowintime;
	}


	@Override
	public String toString() {
		return "Requesthead [user=" + user + ", requestType=" + requestType + ", password=" + password + ", serverVersion=" + serverVersion + ", sender=" + sender + ", uuid=" + uuid + ", flowintime=" + flowintime + "]";
	}
}
