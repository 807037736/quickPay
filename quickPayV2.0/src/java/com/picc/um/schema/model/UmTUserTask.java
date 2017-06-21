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
@Table(name = "um_t_usertask")
public class UmTUserTask implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_USER_TASK_ID = "ID";
	public static final String ALIAS_TASK_ID = "功能ID";
	public static final String ALIAS_USER_CODE = "用户代码";
	public static final String ALIAS_SCOPE = "适用范围";
	public static final String ALIAS_INVALID_DATE = "失效日期";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建日期";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改日期";
	public static final String ALIAS_FLAG = "标识";
	
	
	/**	构造函数	**/
	public UmTUserTask() {
	}
	
	/** 主键对象 **/
	private UmTUserTaskId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "userTaskId", column = @Column(name = "userTaskId"))
    })
	public UmTUserTaskId getId() {
		return this.id;
	}

	public void setId(UmTUserTaskId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 功能ID **/
	private String taskId;
  	/** 用户代码 **/
	private String userCode;
  	/** 适用范围 **/
	private String scope;
  	/** 失效日期 **/
	private Date invalidDate;
  	/** 有效状态 **/
	private String validStatus;
  	/** 创建人代码 **/
	private String creatorCode;
  	/** 创建日期 **/
	private Date insertTimeForHis;
  	/** 修改人代码 **/
	private String updaterCode;
  	/** 修改日期 **/
	private Date operateTimeForHis;
  	/** 标识 **/
	private String flag;
	
	/**getter setter方法*/
	@Column(name = "TaskId")
	public String getTaskId() {
		return this.taskId;
	}
	
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	@Column(name = "UserCode")
	public String getUserCode() {
		return this.userCode;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	@Column(name = "Scope")
	public String getScope() {
		return this.scope;
	}
	
	public void setScope(String scope) {
		this.scope = scope;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "InvalidDate")
	public Date getInvalidDate() {
		return this.invalidDate;
	}
	
	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
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