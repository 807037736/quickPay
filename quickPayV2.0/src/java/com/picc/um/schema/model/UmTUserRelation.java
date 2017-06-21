
package com.picc.um.schema.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "um_t_userrelation")
public class UmTUserRelation implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_USER_CODE = "用户代码";
	public static final String ALIAS_OPENID = "联系工具代码";
	public static final String ALIAS_USERID = "工具对应用户编码";
	public static final String ALIAS_TOOLTYPE = "联系工具类型";
	public static final String ALIAS_INSERTDATE = "创建日期";
	public static final String ALIAS_VALIDFLAG = "有效标志";
	
	/**	构造函数	**/
	public UmTUserRelation() {
	}
	
	/** 主键对象 **/
	private UmTUserRelationId id;
	
    @EmbeddedId
    @AttributeOverrides( { 
      @AttributeOverride(name = "platId", column = @Column(name = "platId")),
      @AttributeOverride(name = "userId", column = @Column(name = "userId"))
    })
    
	public UmTUserRelationId getId() {
		return this.id;
	}

	public void setId(UmTUserRelationId id) {
		this.id = id;
	}
	
	
	private String userCode;
	private String userName;
	private String platId;
	private String userId;
	private String toolType;
	private String  creatorCode;
	private Date insertTimeForHis;
	private String updaterCode;
	private Date operateTimeForHis;
	private String validStatus;
	private String mobileNo;
	private String comCode;
	private String identityNum;
	@Column(name = "comCode")
	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	@Column(name = "userCode")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	@Column(name = "userName")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Transient
	public String getPlatId() {
		return platId;
	}

	public void setPlatId(String platId) {
		this.platId = platId;
	}

	@Transient
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "toolType")
	public String getToolType() {
		return toolType;
	}

	public void setToolType(String toolType) {
		this.toolType = toolType;
	}
	@Column(name = "creatorCode")
	public String getCreatorCode() {
		return creatorCode;
	}

	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}
	@Column(name = "insertTimeForHis")
	public Date getInsertTimeForHis() {
		return insertTimeForHis;
	}

	public void setInsertTimeForHis(Date insertTimeForHis) {
		this.insertTimeForHis = insertTimeForHis;
	}
	@Column(name = "updaterCode")
	public String getUpdaterCode() {
		return updaterCode;
	}

	public void setUpdaterCode(String updaterCode) {
		this.updaterCode = updaterCode;
	}
	@Column(name = "operateTimeForHis")
	public Date getOperateTimeForHis() {
		return operateTimeForHis;
	}

	public void setOperateTimeForHis(Date operateTimeForHis) {
		this.operateTimeForHis = operateTimeForHis;
	}
	@Column(name = "validStatus")
	public String getValidStatus() {
		return validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

	@Column(name = "mobileno")
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name = "identityNum")
	public String getIdentityNum() {
		return identityNum;
	}

	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
	}
	
	 
 
	
}