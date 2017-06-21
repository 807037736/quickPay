/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
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


@Entity
@Table(name = "Um_T_SMSValid")
public class UmTSMSValid implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_SMS_ID = "短信验证码ID";
	public static final String ALIAS_VERIFICATION = "验证码";
	public static final String ALIAS_MOBILE = "手机号";
	public static final String ALIAS_EXPIRE_TIME = "失效时间";
	public static final String ALIAS_USER_CODE = "用户代码";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	public static final String ALIAS_FLAG = "标识位";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "更新人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "更新时间";
	
	
	/**	构造函数	**/
	public UmTSMSValid() {
	}
	
	/** 主键对象 **/
	private UmTSMSValidId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "smsId", column = @Column(name = "smsId"))
    })
	public UmTSMSValidId getId() {
		return this.id;
	}

	public void setId(UmTSMSValidId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 验证码 **/
	private String verification;
  	/** 手机号 **/
	private String mobile;
  	/** 失效时间 **/
	private Date expireTime;
  	/** 用户代码 **/
	private String userCode;
  	/** 有效状态 **/
	private String validStatus;
  	/** 标识位 **/
	private String flag;
  	/** 创建人代码 **/
	private String creatorCode;
  	/** 创建时间 **/
	private Date insertTimeForHis;
  	/** 更新人代码 **/
	private String updaterCode;
  	/** 更新时间 **/
	private Date operateTimeForHis;
	
	/**getter setter方法*/
	@Column(name = "Verification")
	public String getVerification() {
		return this.verification;
	}
	
	public void setVerification(String verification) {
		this.verification = verification;
	}
	@Column(name = "Mobile")
	public String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ExpireTime")
	public Date getExpireTime() {
		return this.expireTime;
	}
	
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	@Column(name = "UserCode")
	public String getUserCode() {
		return this.userCode;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	@Column(name = "ValidStatus")
	public String getValidStatus() {
		return this.validStatus;
	}
	
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	@Column(name = "Flag")
	public String getFlag() {
		return this.flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OperateTimeForHis" , insertable = false)
	public Date getOperateTimeForHis() {
		return this.operateTimeForHis;
	}
	
	public void setOperateTimeForHis(Date operateTimeForHis) {
		this.operateTimeForHis = operateTimeForHis;
	}
		
}