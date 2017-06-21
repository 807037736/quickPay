/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2014
 */

package com.picc.um.schema.model;

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
@Table(name = "um_t_userbind")
public class UmTUserBind implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_USER_CODE = "用户代码";
	public static final String ALIAS_CUSTOMER_NAME = "客户名称";
	public static final String ALIAS_BIND_TYPE = "绑定方式";
	public static final String ALIAS_BIND_VALUE = "绑定值";
	public static final String ALIAS_BIND_TIME = "绑定时间";
	public static final String ALIAS_BIND_RESULT = "绑定结果";
	public static final String ALIAS_CUST_ID = "绑定客户编号";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "更新人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "更新时间";
	public static final String ALIAS_BIND_ID = "绑定ID";
	public static final String ALIAS_LICENSE_NO = "车牌";
	
	
	/**	构造函数	**/
	public UmTUserBind() {
	}
	
	/** 主键对象 **/
	private UmTUserBindId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "bindId", column = @Column(name = "bindId"))
    })
	public UmTUserBindId getId() {
		return this.id;
	}

	public void setId(UmTUserBindId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 用户代码 **/
	private String userCode;
  	/** 客户名称 **/
	private String customerName;
  	/** 绑定方式 **/
	private String bindType;
  	/** 绑定值 **/
	private String bindValue;
  	/** 绑定时间 **/
	private Date bindTime;
  	/** 绑定结果 **/
	private String bindResult;
  	/** 绑定客户编号 **/
	private String custId;
  	/** 有效状态 **/
	private String validStatus;
  	/** 创建人代码 **/
	private String creatorCode;
  	/** 创建时间 **/
	private Date insertTimeForHis;
  	/** 更新人代码 **/
	private String updaterCode;
  	/** 更新时间 **/
	private Date operateTimeForHis;
	/** 车牌 **/
	private String licenseNo;
	
	/**getter setter方法*/
	@Column(name = "UserCode")
	public String getUserCode() {
		return this.userCode;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	@Column(name = "CustomerName")
	public String getCustomerName() {
		return this.customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	@Column(name = "BindType")
	public String getBindType() {
		return this.bindType;
	}
	
	public void setBindType(String bindType) {
		this.bindType = bindType;
	}
	@Column(name = "BindValue")
	public String getBindValue() {
		return this.bindValue;
	}
	
	public void setBindValue(String bindValue) {
		this.bindValue = bindValue;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BindTime")
	public Date getBindTime() {
		return this.bindTime;
	}
	
	public void setBindTime(Date bindTime) {
		this.bindTime = bindTime;
	}
	@Column(name = "BindResult")
	public String getBindResult() {
		return this.bindResult;
	}
	
	public void setBindResult(String bindResult) {
		this.bindResult = bindResult;
	}
	@Column(name = "CustId")
	public String getCustId() {
		return this.custId;
	}
	
	public void setCustId(String custId) {
		this.custId = custId;
	}
	@Column(name = "ValidStatus")
	public String getValidStatus() {
		return this.validStatus;
	}
	
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	@Column(name = "CreatorCode" , updatable = false)
	public String getCreatorCode() {
		return this.creatorCode;
	}
	
	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "InsertTimeForHis" , updatable = false)
	public Date getInsertTimeForHis() {
		return this.insertTimeForHis;
	}
	
	public void setInsertTimeForHis(Date insertTimeForHis) {
		this.insertTimeForHis = insertTimeForHis;
	}
	@Column(name = "UpdaterCode" , insertable = false)
	public String getUpdaterCode() {
		return this.updaterCode;
	}
	
	public void setUpdaterCode(String updaterCode) {
		this.updaterCode = updaterCode;
	}
	
	@Column(name = "LicenseNo"  )
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public void setOperateTimeForHis(Date operateTimeForHis) {
		this.operateTimeForHis = operateTimeForHis;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OperateTimeForHis" , insertable = false)
	public Date getOperateTimeForHis() {
		return this.operateTimeForHis;
	}
	


		
	/**
	 * 辅助字段：返回到页面的绑定结果
	 */
	private String returnPageBindResult;

	@Transient
	public String getReturnPageBindResult() {
		return returnPageBindResult;
	}

	public void setReturnPageBindResult(String returnPageBindResult) {
		this.returnPageBindResult = returnPageBindResult;
	}
}