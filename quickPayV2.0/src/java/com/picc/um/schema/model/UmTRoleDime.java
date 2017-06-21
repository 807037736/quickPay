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
@Table(name = "um_t_roledime")
public class UmTRoleDime implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_ROLE_DIME_ID = "主键ID";
	public static final String ALIAS_ROLE_ID = "角色ID";
	public static final String ALIAS_DIMENSION_CODE = "维度代码";
	public static final String ALIAS_DIMENSION_VALUE = "维度值";
	public static final String ALIAS_TASK_ID = "功能ID";
	public static final String ALIAS_OPERATE_TYPE = "操作类型";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	
	
	/**	构造函数	**/
	public UmTRoleDime() {
	}
	
	/** 主键对象 **/
	private UmTRoleDimeId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "roleDimeId", column = @Column(name = "roleDimeId"))
    })
	public UmTRoleDimeId getId() {
		return this.id;
	}

	public void setId(UmTRoleDimeId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 角色ID **/
	private String roleId;
  	/** 维度代码 **/
	private String dimensionCode;
  	/** 维度值 **/
	private String dimensionValue;
  	/** 功能ID **/
	private String taskId;
  	/** 操作类型 **/
	private String operateType;
  	/** 创建人代码 **/
	private String creatorCode;
  	/** 创建时间 **/
	private Date insertTimeForHis;
  	/** 修改人代码 **/
	private String updaterCode;
  	/** 修改时间 **/
	private Date operateTimeForHis;
  	/** 有效状态 **/
	private String validStatus;
	
	/**getter setter方法*/
	@Column(name = "RoleId")
	public String getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Column(name = "DimensionCode")
	public String getDimensionCode() {
		return this.dimensionCode;
	}
	
	public void setDimensionCode(String dimensionCode) {
		this.dimensionCode = dimensionCode;
	}
	@Column(name = "DimensionValue")
	public String getDimensionValue() {
		return this.dimensionValue;
	}
	
	public void setDimensionValue(String dimensionValue) {
		this.dimensionValue = dimensionValue;
	}
	@Column(name = "TaskId")
	public String getTaskId() {
		return this.taskId;
	}
	
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	@Column(name = "OperateType")
	public String getOperateType() {
		return this.operateType;
	}
	
	public void setOperateType(String operateType) {
		this.operateType = operateType;
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
	@Column(name = "ValidStatus")
	public String getValidStatus() {
		return this.validStatus;
	}
	
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
		
}