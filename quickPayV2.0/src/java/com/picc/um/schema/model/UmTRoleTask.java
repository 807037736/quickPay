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
@Table(name = "um_t_roletask")
public class UmTRoleTask implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_ROLE_TASK_ID = "ID";
	public static final String ALIAS_ROLE_ID = "角色ID";
	public static final String ALIAS_TASK_I_D = "功能ID";
	public static final String ALIAS_SCOPE = "适用范围";
	public static final String ALIAS_EXPIRE_DATE = "失效日期";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建日期";
	public static final String ALIAS_UPDATER_CODE = "更新人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改日期";
	public static final String ALIAS_FLAG = "标志字段";
	
	
	/**	构造函数	**/
	public UmTRoleTask() {
	}
	
	/** 主键对象 **/
	private UmTRoleTaskId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "roleTaskId", column = @Column(name = "roleTaskId"))
    })
	public UmTRoleTaskId getId() {
		return this.id;
	}

	public void setId(UmTRoleTaskId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 角色ID **/
	private String roleId;
  	/** 功能ID **/
	private String taskID;
  	/** 适用范围 **/
	private String scope;
  	/** 失效日期 **/
	private Date expireDate;
  	/** 有效状态 **/
	private String validStatus = "1";
  	/** 创建人代码 **/
	private String creatorCode;
  	/** 创建日期 **/
	private Date insertTimeForHis;
  	/** 更新人代码 **/
	private String updaterCode;
  	/** 修改日期 **/
	private Date operateTimeForHis;
  	/** 标志字段 **/
	private String flag;
	
	/**getter setter方法*/
	@Column(name = "RoleId")
	public String getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Column(name = "TaskID")
	public String getTaskID() {
		return this.taskID;
	}
	
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}
	@Column(name = "Scope")
	public String getScope() {
		return this.scope;
	}
	
	public void setScope(String scope) {
		this.scope = scope;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ExpireDate")
	public Date getExpireDate() {
		return this.expireDate;
	}
	
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
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
	@Column(name = "Flag")
	public String getFlag() {
		return this.flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}
}