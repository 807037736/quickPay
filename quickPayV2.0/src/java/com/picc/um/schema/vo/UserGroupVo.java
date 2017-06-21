package com.picc.um.schema.vo;

@SuppressWarnings("serial")
public class UserGroupVo implements java.io.Serializable {
	
	private String userCode;
	
	private String userName;
	
	private String comCode;
	
	private String groupId;
	
	private String groupCode;
	
	private String groupType;
	
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public UserGroupVo(String userCode,String userName,String comCode) {
		this.userCode = userCode;
		this.userName = userName;
		this.comCode = comCode;
	}
	
	public UserGroupVo(){}

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

}
