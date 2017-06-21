package com.picc.um.schema.vo;

import java.util.Date;

public class GroupComColumn {
	
	private String groupComId;
	/** 用户自定义组ID **/
	private String groupId;
	/** 自定义用户组名称 **/
	private String groupName;
	/** 自定义用户组代码 **/
	private String groupCode;
	/** 机构代码 **/
	private String comCode;
	
  	/** 创建人代码 **/
	private String creatorCode;
	/** 创建时间 **/
	private Date insertTimeForHis;
  	/** 更新人代码 **/
	private String updaterCode;
  	/** 更新时间 **/
	private Date updateTimeForHis;
  	public Date getInsertTimeForHis() {
		return insertTimeForHis;
	}
	public void setInsertTimeForHis(Date insertTimeForHis) {
		this.insertTimeForHis = insertTimeForHis;
	}
	public Date getUpdateTimeForHis() {
		return updateTimeForHis;
	}
	public void setUpdateTimeForHis(Date updateTimeForHis) {
		this.updateTimeForHis = updateTimeForHis;
	}
	/** 有效状态 **/
	private String validStatus;
	
  	public String getCreatorCode() {
		return creatorCode;
	}
	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}

	public String getUpdaterCode() {
		return updaterCode;
	}
	public void setUpdaterCode(String updaterCode) {
		this.updaterCode = updaterCode;
	}
	
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
