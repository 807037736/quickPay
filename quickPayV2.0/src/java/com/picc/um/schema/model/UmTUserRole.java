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
@Table(name = "um_t_userrole")
public class UmTUserRole implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_USER_ROLE_ID = "用户角色ID";
	public static final String ALIAS_ROLE_ID = "角色ID";
	public static final String ALIAS_USER_CODE = "用户代码";
	public static final String ALIAS_ROLE_CODE = "角色代码";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建日期";
	public static final String ALIAS_UPDATER_CODE = "更新人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "更新日期";
	
	
	/**	构造函数	**/
	public UmTUserRole() {
	}
	
	/** 主键对象 **/
	private UmTUserRoleId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "userRoleId", column = @Column(name = "userRoleId"))
    })
	public UmTUserRoleId getId() {
		return this.id;
	}

	public void setId(UmTUserRoleId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 角色ID **/
	private String roleId;
  	/** 用户代码 **/
	private String userCode;
  	/** 角色代码 **/
	private String roleCode;
  	/** 有效状态 **/
	private String validStatus;
  	/** 创建人代码 **/
	private String creatorCode;
  	/** 创建日期 **/
	private Date insertTimeForHis;
  	/** 更新人代码 **/
	private String updaterCode;
  	/** 更新日期 **/
	private Date operateTimeForHis;
	/** 省级机构代码 **/
	private String comId;
	
	
	/**getter setter方法*/
	@Column(name = "RoleId")
	public String getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Column(name = "UserCode")
	public String getUserCode() {
		return this.userCode;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	@Column(name = "RoleCode")
	public String getRoleCode() {
		return this.roleCode;
	}
	
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OperateTimeForHis" , insertable = false)
	public Date getOperateTimeForHis() {
		return this.operateTimeForHis;
	}
	
	public void setOperateTimeForHis(Date operateTimeForHis) {
		this.operateTimeForHis = operateTimeForHis;
	}
	@Column(name = "ComId")
	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}
}