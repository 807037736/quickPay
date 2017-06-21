package com.picc.um.schema.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "um_t_accident_user")
public class UmTAccidentUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID 主键
	 */
	private Integer userID;
	/**
	 * 用户姓名
	 */
	private String userName;
	/**
	 * 手机号
	 */
	private String userMobile;
	/**
	 * 证件号
	 */
	private String IDNumber;
	/**
	 * 车牌号
	 */
	private String licenseNo;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 保险公司id
	 */
	private String coID;
	/**
	 * 车辆类型
	 */
	private String vehicleCode;
	/**
	 * 准驾类型
	 */
	private String permissionDriveCode;
	/**
	 * 车架号
	 */
	private String chassisNumber;
	/**
	 * 厂牌类型
	 */
	private String lableType;
	
	private Date createTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "userID")
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	
	@Column(name = "userName")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "userMobile")
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	
	@Column(name = "IDNumber")
	public String getIDNumber() {
		return IDNumber;
	}
	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}
	
	@Column(name = "licenseNo")
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "coID")
	public String getCoID() {
		return coID;
	}
	public void setCoID(String coID) {
		this.coID = coID;
	}
	
	@Column(name = "vehicleCode")
	public String getVehicleCode() {
		return vehicleCode;
	}
	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}
	
	@Column(name = "permissionDriveCode")
	public String getPermissionDriveCode() {
		return permissionDriveCode;
	}
	public void setPermissionDriveCode(String permissionDriveCode) {
		this.permissionDriveCode = permissionDriveCode;
	}
	
	@Column(name = "chassisNumber")
	public String getChassisNumber() {
		return chassisNumber;
	}
	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}
	
	@Column(name = "lableType")
	public String getLableType() {
		return lableType;
	}
	public void setLableType(String lableType) {
		this.lableType = lableType;
	}
	
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
