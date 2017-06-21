package com.picc.um.schema.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
@Entity
@Table(name="um_t_userbound")
public class UmTUserBound implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public UmTUserBound(){
	};
	
	private UmTUserBoundId id;
	@EmbeddedId
	@AttributeOverrides( {
		@AttributeOverride(name = "boundId", column = @Column(name = "boundId"))
	})
	public UmTUserBoundId getId() {
		return id;
	}
	public void setId(UmTUserBoundId id) {
		this.boundId = id.getBoundId();
		this.id = id;
	}
	@Transient
	public Integer getBoundId() {
		return boundId;
	}
	public void setBoundId(Integer boundId) {
		this.boundId = boundId;
	}
	
	
	private Integer boundId;
	private Date createTime;
	private Date updateTime;
	private String creater;
	private String updateCode;
	private String wxUserCode;
	private String ckUserCode;
	private String isBound;
	private String wxUserName;
	private String ckUserName;
	
	//user表数据
	/** 用户代码 **/
    private String userCode;
  	/** 手机号码 **/
	private String mobile;
  	/** 证件号码 **/
	private String identityNumber;
  	/** 性别 **/
	private String sex;
    /** 车牌号 **/
    private String licenseNo;
    /** 绑定统计 **/
    private BigInteger boundCount;
    /** 用户名 **/
    private String userName;
    
	
	
	@Column(name="wxUserCode")
	public String getWxUserCode() {
		return wxUserCode;
	}
	public void setWxUserCode(String wxUserCode) {
		this.wxUserCode = wxUserCode;
	}
	@Column(name="ckUserCode")
	public String getCkUserCode() {
		return ckUserCode;
	}
	public void setCkUserCode(String ckUserCode) {
		this.ckUserCode = ckUserCode;
	}
	@Column(name="isBound")
	public String getIsBound() {
		return isBound;
	}
	public void setIsBound(String isBound) {
		this.isBound = isBound;
	}
	@Column(name="wxUserName")
	public String getWxUserName() {
		return wxUserName;
	}
	public void setWxUserName(String wxUserName) {
		this.wxUserName = wxUserName;
	}
	@Column(name="ckUserName")
	public String getCkUserName() {
		return ckUserName;
	}
	public void setCkUserName(String ckUserName) {
		this.ckUserName = ckUserName;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="createTime")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updateTime")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name="creater")
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	@Column(name="updateCode")
	public String getUpdateCode() {
		return updateCode;
	}
	public void setUpdateCode(String updateCode) {
		this.updateCode = updateCode;
	}
	
	@Transient
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Transient
	public String getIdentityNumber() {
		return identityNumber;
	}
	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}
	@Transient
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Transient
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	@Transient
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	@Transient
	public BigInteger getBoundCount() {
		return boundCount;
	}
	public void setBoundCount(BigInteger boundCount) {
		this.boundCount = boundCount;
	}
	@Transient
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "UmTUserBound [id=" + id + ", boundId=" + boundId
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", creater=" + creater + ", updateCode=" + updateCode
				+ ", wxUserCode=" + wxUserCode + ", ckUserCode=" + ckUserCode
				+ ", isBound=" + isBound + ", wxUserName=" + wxUserName
				+ ", ckUserName=" + ckUserName + ", userCode=" + userCode
				+ ", mobile=" + mobile + ", identityNumber=" + identityNumber
				+ ", sex=" + sex + ", licenseNo=" + licenseNo + ", boundCount="
				+ boundCount + ", userName=" + userName + "]";
	}
	
}
