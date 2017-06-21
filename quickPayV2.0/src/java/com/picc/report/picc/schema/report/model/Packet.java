package com.picc.report.picc.schema.report.model;

import java.io.Serializable;

import com.ymt.annotation.ClassConvert;

@ClassConvert(name="PACKET")
public class Packet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 请求报文头
	 */
	private Requesthead requesthead;
	
	/**
	 * 请求报文主题
	 */
	private Requestbody requestbody;

	public Requesthead getRequesthead() {
		return requesthead;
	}

	public void setRequesthead(Requesthead requesthead) {
		this.requesthead = requesthead;
	}

	public Requestbody getRequestbody() {
		return requestbody;
	}

	public void setRequestbody(Requestbody requestbody) {
		this.requestbody = requestbody;
	}

	@Override
	public String toString() {
		return "packet [requesthead=" + requesthead + ", requestbody=" + requestbody + "]";
	}
	
}
