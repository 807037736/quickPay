/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2014
 */

package com.picc.um.schema.model;

import java.math.BigDecimal;
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
@Table(name = "um_t_task")
public class UmTTask implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_TASK_ID = "功能ID";
	public static final String ALIAS_UPPER_TASK_ID = "上级功能ID";
	public static final String ALIAS_TASK_CODE = "功能代码";
	public static final String ALIAS_TASK_NAME = "功能名称";
	public static final String ALIAS_SVR_CODE = "服务代码";
	public static final String ALIAS_TASK_TYPE = "功能类型";
	public static final String ALIAS_EXPIRE_DATE = "失效日期";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "更新人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "更新时间";
	public static final String ALIAS_UPPER_TASK_CODE = "上级功能代码";
	public static final String ALIAS_UPPER_TASK_NAME = "上级功能名称";
	public static final String ALIAS_TASK_LEVEL = "菜单层级";
	public static final String ALIAS_APPLY_SCOPE = "适用范围";
	public static final String ALIAS_SYSTEM_CODE = "系统代码";
	public static final String ALIAS_ACTION_URL = "菜单URL";
	public static final String ALIAS_DISPLAY_NO = "显示序号";
	public static final String ALIAS_FLAG = "标志字段";
	
	
	/**	构造函数	**/
	public UmTTask() {
	}
	
	/** 主键对象 **/
	private UmTTaskId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "taskId", column = @Column(name = "taskId"))
    })
	public UmTTaskId getId() {
		return this.id;
	}

	public void setId(UmTTaskId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 上级功能ID **/
	private String upperTaskId;
  	/** 功能代码 **/
	private String taskCode;
  	/** 功能名称 **/
	private String taskName;
  	/** 服务代码 **/
	private String svrCode;
  	/** 功能类型 **/
	private String taskType;
  	/** 失效日期 **/
	private Date expireDate;
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
  	/** 上级功能代码 **/
	private String upperTaskCode;
  	/** 上级功能名称 **/
	private String upperTaskName;
  	/** 菜单层级 **/
	private BigDecimal taskLevel;
  	/** 适用范围 **/
	private String applyScope;
  	/** 菜单URL **/
	private String actionUrl;
  	/** 显示序号 **/
	private BigDecimal displayNo;
  	/** 标志字段 **/
	private String flag;
	/** 标志字段 **/
	private String isOpen;
	/** 标志字段 **/
	private String openLevel;
	/** 标志字段 **/
	private String userType;
	
	
	@Column(name = "IsOpen")
	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}
	@Column(name = "OpenLevel")
	public String getOpenLevel() {
		return openLevel;
	}

	public void setOpenLevel(String openLevel) {
		this.openLevel = openLevel;
	}
	@Column(name = "UserType")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**getter setter方法*/
	@Column(name = "UpperTaskId")
	public String getUpperTaskId() {
		return this.upperTaskId;
	}
	
	public void setUpperTaskId(String upperTaskId) {
		this.upperTaskId = upperTaskId;
	}
	@Column(name = "TaskCode")
	public String getTaskCode() {
		return this.taskCode;
	}
	
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	@Column(name = "TaskName")
	public String getTaskName() {
		return this.taskName;
	}
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	@Column(name = "SvrCode")
	public String getSvrCode() {
		return this.svrCode;
	}
	
	public void setSvrCode(String svrCode) {
		this.svrCode = svrCode;
	}
	@Column(name = "TaskType")
	public String getTaskType() {
		return this.taskType;
	}
	
	public void setTaskType(String taskType) {
		this.taskType = taskType;
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
	@Column(name = "UpperTaskCode")
	public String getUpperTaskCode() {
		return this.upperTaskCode;
	}
	
	public void setUpperTaskCode(String upperTaskCode) {
		this.upperTaskCode = upperTaskCode;
	}
	@Column(name = "UpperTaskName")
	public String getUpperTaskName() {
		return this.upperTaskName;
	}
	
	public void setUpperTaskName(String upperTaskName) {
		this.upperTaskName = upperTaskName;
	}
	@Column(name = "TaskLevel")
	public BigDecimal getTaskLevel() {
		return this.taskLevel;
	}
	
	public void setTaskLevel(BigDecimal taskLevel) {
		this.taskLevel = taskLevel;
	}
	@Column(name = "ApplyScope")
	public String getApplyScope() {
		return this.applyScope;
	}
	
	public void setApplyScope(String applyScope) {
		this.applyScope = applyScope;
	}
	
	@Column(name = "ActionUrl")
	public String getActionUrl() {
		return this.actionUrl;
	}
	
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
	@Column(name = "DisplayNo")
	public BigDecimal getDisplayNo() {
		return this.displayNo;
	}
	
	public void setDisplayNo(BigDecimal displayNo) {
		this.displayNo = displayNo;
	}
	@Column(name = "Flag")
	public String getFlag() {
		return this.flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	/** 功能方法代码  非数据库字段，只用于传值  **/
	private String methodCode;
	@Transient
	public String getMethodCode() {
		return methodCode;
	}
	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
	}

}