package com.picc.um.schema.vo;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class UmTWxUser implements java.io.Serializable {
	
	private String headImg;
	
	private BigDecimal msgCount;

	private String nickName;

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

 

	public BigDecimal getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(BigDecimal msgCount) {
		this.msgCount = msgCount;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	 

}
