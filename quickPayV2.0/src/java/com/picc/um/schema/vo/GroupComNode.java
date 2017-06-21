package com.picc.um.schema.vo;

public class GroupComNode {
	
	/** 用户自定义组ID **/
	private String groupId;
	/** 机构代码 **/
	private String comCode;
	/** 自定义用户组名称 **/
	private String groupName;
	/** 自定义用户组代码 **/
	private String groupCode;
	/** 有效状态 **/
	private String validStatus;
	
	private String groupComId;
	
	
  	public String getGroupComId() {
		return groupComId;
	}
	public void setGroupComId(String groupComId) {
		this.groupComId = groupComId;
	}
	public String getValidStatus() {
		return validStatus;
	}
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getComCode() {
		return comCode;
	}
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	
  	
	
	
}
