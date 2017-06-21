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
import javax.persistence.Transient;


@Entity
@Table(name = "um_t_methodtask")
public class UmTMethodTask implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_METHOD_ID = "方法ID";
	public static final String ALIAS_TASK_ID = "功能ID";
	public static final String ALIAS_METHOD_CODE = "方法代码";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "更新人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "更新时间";
	
	
	/**	构造函数	**/
	public UmTMethodTask() {
	}
	
	/** 主键对象 **/
	private UmTMethodTaskId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "methodId", column = @Column(name = "methodId"))
    })
	public UmTMethodTaskId getId() {
		return this.id;
	}

	public void setId(UmTMethodTaskId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 功能ID **/
	private String taskId;
  	/** 方法代码 **/
	private String methodCode;
	/** 创建人代码 **/
	private String creatorCode;
  	/** 创建时间 **/
	private Date insertTimeForHis;
  	/** 更新人代码 **/
	private String updaterCode;
  	/** 更新时间 **/
	private Date operateTimeForHis;
  	/** 有效状态 **/
	private String validStatus = "1";
	
	/**getter setter方法*/
	@Column(name = "TaskId")
	public String getTaskId() {
		return this.taskId;
	}
	
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	@Column(name = "MethodCode")
	public String getMethodCode() {
		return this.methodCode;
	}
	
	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
	}

	@Column(name = "CreatorCode",updatable = false)
	public String getCreatorCode() {
		return creatorCode;
	}

	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "InsertTimeForHis",updatable = false)
	public Date getInsertTimeForHis() {
		return insertTimeForHis;
	}

	public void setInsertTimeForHis(Date insertTimeForHis) {
		this.insertTimeForHis = insertTimeForHis;
	}

	@Column(name = "UpdaterCode", insertable = false)
	public String getUpdaterCode() {
		return updaterCode;
	}

	public void setUpdaterCode(String updaterCode) {
		this.updaterCode = updaterCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OperateTimeForHis", insertable = false)
	public Date getOperateTimeForHis() {
		return operateTimeForHis;
	}

	public void setOperateTimeForHis(Date operateTimeForHis) {
		this.operateTimeForHis = operateTimeForHis;
	}

	@Column(name = "ValidStatus")
	public String getValidStatus() {
		return validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	
	
	/**********************************辅助字段start****************************************/
	private String roleId;		
	/** 是否开放 **/
	private String isOpen;
	/** 开放等级：00 **/
	private String openLevel;
	/** 标志字段 **/
	private String userType;

	@Transient
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Transient
	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}
	@Transient
	public String getOpenLevel() {
		return openLevel;
	}

	public void setOpenLevel(String openLevel) {
		this.openLevel = openLevel;
	}
	@Transient
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**********************************辅助字段end****************************************/
}