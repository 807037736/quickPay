package com.picc.um.schema.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "um_t_utiiuser")
public class UtiIUser implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String userCode;
	private String userName;
	private String userType;
	private String userSort;
	private String comCode;
	private String newUserCode;
	private String roamingType;
	private String roamingStatus;
	private String validStatus;
	private String auditStatus;
	private Date validEndDate;
	
	private Date createDate;
	private Date updateDate;
	private String creatorCode;
	private String updaterCode;
	private String flag;
	private String roamComCode;
	/** 老用户代码prpduser.usercode*/
	private String oldUserCode;
	/** 是否为交叉销售用户*/
	private String crossUserFlag;
	private String comID;
	

	public UtiIUser() {
	}

	@Id
	@Column(name = "USERCODE")
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "USERNAME")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USERTYPE")
	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Column(name = "USERSORT")
	public String getUserSort() {
		return this.userSort;
	}

	public void setUserSort(String userSort) {
		this.userSort = userSort;
	}

	@Column(name = "COMCODE")
	public String getComCode() {
		return this.comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	@Column(name = "NEWUSERCODE")
	public String getNewUserCode() {
		return this.newUserCode;
	}

	public void setNewUserCode(String newUserCode) {
		this.newUserCode = newUserCode;
	}

	@Column(name = "ROAMINGTYPE")
	public String getRoamingType() {
		return this.roamingType;
	}

	public void setRoamingType(String roamingType) {
		this.roamingType = roamingType;
	}

	@Column(name = "ROAMINGSTATUS")
	public String getRoamingStatus() {
		return this.roamingStatus;
	}

	public void setRoamingStatus(String roamingStatus) {
		this.roamingStatus = roamingStatus;
	}

	@Column(name = "VALIDSTATUS")
	public String getValidStatus() {
		return this.validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	@Column(name = "AUDITSTATUS")
	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "VALIDENDDATE")
	public Date getValidEndDate() {
		return this.validEndDate;
	}

	public void setValidEndDate(Date validEndDate) {
		this.validEndDate = validEndDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATEDATE")
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATEDATE")
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "CREATORCODE")
	public String getCreatorCode() {
		return this.creatorCode;
	}

	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}

	@Column(name = "UPDATERCODE")
	public String getUpdaterCode() {
		return this.updaterCode;
	}

	public void setUpdaterCode(String updaterCode) {
		this.updaterCode = updaterCode;
	}

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Column(name = "ROAMCOMCODE")
	public String getRoamComCode() {
		return this.roamComCode;
	}

	public void setRoamComCode(String roamComCode) {
		this.roamComCode = roamComCode;
	}

	@Column(name = "OLDUSERCODE")
	public String getOldUserCode() {
		return oldUserCode;
	}

	public void setOldUserCode(String oldUserCode) {
		this.oldUserCode = oldUserCode;
	}
	
	@Column(name = "CROSSUSERFLAG")
	public String getCrossUserFlag() {
		return crossUserFlag;
	}
	
	public void setCrossUserFlag(String crossUserFlag) {
		this.crossUserFlag = crossUserFlag;
	}

	public String getComID() {
		return comID;
	}

	public void setComID(String comID) {
		this.comID = comID;
	}

}
