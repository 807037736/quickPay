package com.picc.common.service.spring;

import com.picc.common.service.facade.BaseRemoteService;

/**
 * 与外部系统交互的接口
 */
public class BaseRemoteServiceImpl implements BaseRemoteService {
	
	protected static final String requestCode = "requestCode";
	
	/**
	 * 远程服务地址
	 */
	private String serviceURL = null;
	
	/**
	 * 初始化连接地址
	 */
	public void initServiceURL(String serviceURL) {
		this.serviceURL	 = serviceURL;
	}

	public String getServiceURL() {
		return serviceURL;
	}

	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}
}
