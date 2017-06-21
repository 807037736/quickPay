package com.picc.um.schema.vo;

@SuppressWarnings("serial")
public class UmTUserVo implements java.io.Serializable {
	
	private String userCode;
	
	private String userName;
	
	private String comCode;
	
	private String comCName;
	
	public UmTUserVo(String userCode,String userName,String comCode,String comCName) {
		this.userCode = userCode;
		this.userName = userName;
		this.comCode = comCode;
		this.comCName = comCName;
	}
	
	public UmTUserVo(){}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getComCName() {
		return comCName;
	}

	public void setComCName(String comCName) {
		this.comCName = comCName;
	}

}
