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


@Entity
@Table(name = "um_t_registuser")
public class UmTRegistuser implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_USER_CODE = "用户注册编号";
	public static final String ALIAS_USER_NAME = "用户姓名";
	public static final String ALIAS_RELATED_CODE = "关联代码";
	public static final String ALIAS_COM_CODE = "机构代码";
	public static final String ALIAS_PASSWORD = "注册密码";
	public static final String ALIAS_NICK_NAME = "昵称";
	public static final String ALIAS_MOBILE = "手机号";
	public static final String ALIAS_EMAIL = "安全邮箱";
	public static final String ALIAS_REGIST_SOURCE = "注册来源";
	public static final String ALIAS_PWD_MODIFY_TIME = "密码最近修改时间";
	public static final String ALIAS_PWD_MODIFY_TIMES = "密码修改次数";
	public static final String ALIAS_USER_TYPE = "用户类型（00-外部客户，01-员工，02-渠道）";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "注册时间";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_UPDATER_CODE = "更新人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "最近操作时间";
	public static final String ALIAS_VALID_STATUS = "有效性";
	
	
	/**	构造函数	**/
	public UmTRegistuser() {
	}
	
	/** 主键对象 **/
	private UmTRegistuserId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "userCode", column = @Column(name = "userCode"))
    })
	public UmTRegistuserId getId() {
		return this.id;
	}

	public void setId(UmTRegistuserId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 用户姓名 **/
	private String userName;
  	/** 关联代码 **/
	private String relatedCode;
  	/** 机构代码 **/
	private String comCode;
  	/** 注册密码 **/
	private String password;
  	/** 昵称 **/
	private String nickName;
  	/** 手机号 **/
	private String mobile;
  	/** 安全邮箱 **/
	private String email;
  	/** 注册来源 **/
	private String registSource;
  	/** 密码最近修改时间 **/
	private Date pwdModifyTime;
  	/** 密码修改次数 **/
	private Integer pwdModifyTimes;
  	/** 用户类型（00-外部客户，01-员工，02-渠道） **/
	private String userType;
  	/** 注册时间 **/
	private Date insertTimeForHis;
  	/** 创建人代码 **/
	private String creatorCode;
  	/** 更新人代码 **/
	private String updaterCode;
  	/** 最近操作时间 **/
	private Date operateTimeForHis;
  	/** 有效性 **/
	private String validStatus;
	/** 身份证 **/
	private String identityNumber;
	/** 车牌号 **/
	private String licenseNo;
	
	/**getter setter方法*/
	@Column(name = "UserName")
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "RelatedCode")
	public String getRelatedCode() {
		return this.relatedCode;
	}
	
	public void setRelatedCode(String relatedCode) {
		this.relatedCode = relatedCode;
	}
	@Column(name = "ComCode")
	public String getComCode() {
		return this.comCode;
	}
	
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	@Column(name = "Password")
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "NickName")
	public String getNickName() {
		return this.nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@Column(name = "Mobile")
	public String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name = "Email")
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "RegistSource")
	public String getRegistSource() {
		return this.registSource;
	}
	
	public void setRegistSource(String registSource) {
		this.registSource = registSource;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PwdModifyTime")
	public Date getPwdModifyTime() {
		return this.pwdModifyTime;
	}
	
	public void setPwdModifyTime(Date pwdModifyTime) {
		this.pwdModifyTime = pwdModifyTime;
	}
	@Column(name = "PwdModifyTimes")
	public Integer getPwdModifyTimes() {
		return this.pwdModifyTimes;
	}
	
	public void setPwdModifyTimes(Integer pwdModifyTimes) {
		this.pwdModifyTimes = pwdModifyTimes;
	}
	@Column(name = "UserType")
	public String getUserType() {
		return this.userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "InsertTimeForHis" , updatable = false)
	public Date getInsertTimeForHis() {
		return this.insertTimeForHis;
	}
	
	public void setInsertTimeForHis(Date insertTimeForHis) {
		this.insertTimeForHis = insertTimeForHis;
	}
	@Column(name = "CreatorCode" , updatable = false)
	public String getCreatorCode() {
		return this.creatorCode;
	}
	
	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
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
	@Column(name = "ValidStatus")
	public String getValidStatus() {
		return this.validStatus;
	}
	
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	@Column(name = "identityNumber")
	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}
	@Column(name = "licenseNo")
	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	@Override
	public String toString() {
		return "UmTRegistuser [id=" + id + ", userName=" + userName
				+ ", relatedCode=" + relatedCode + ", comCode=" + comCode
				+ ", password=" + password + ", nickName=" + nickName
				+ ", mobile=" + mobile + ", email=" + email + ", registSource="
				+ registSource + ", pwdModifyTime=" + pwdModifyTime
				+ ", pwdModifyTimes=" + pwdModifyTimes + ", userType="
				+ userType + ", insertTimeForHis=" + insertTimeForHis
				+ ", creatorCode=" + creatorCode + ", updaterCode="
				+ updaterCode + ", operateTimeForHis=" + operateTimeForHis
				+ ", validStatus=" + validStatus + ", identityNumber="
				+ identityNumber + ", licenseNo=" + licenseNo + "]";
	}
	
		
}