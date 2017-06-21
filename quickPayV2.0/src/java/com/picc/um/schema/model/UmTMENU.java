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
@Table(name = "um_t_menu")
public class UmTMENU implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_MENU_ID = "菜单ID";
	public static final String ALIAS_UPPER_MENU_ID = "上级菜单ID";
	public static final String ALIAS_TASK_ID = "功能ID";
	public static final String ALIAS_LEVEL = "菜单层级";
	public static final String ALIAS_SCOPE = "适用范围";
	public static final String ALIAS_SYSTEM_CODE = "系统代码";
	public static final String ALIAS_MENU_NAME = "菜单名称";
	public static final String ALIAS_ACTION_URL = "菜单URL";
	public static final String ALIAS_DISPLAY_NO = "显示序号";
	public static final String ALIAS_TASK_CODE = "功能代码";
	public static final String ALIAS_VALID_STAUTS = "有效状态";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "更新人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "更新时间";
	public static final String ALIAS_FLAG = "标志字段";
	
	
	
	/**	构造函数	**/
	public UmTMENU() {
	}
	
	/** 主键对象 **/
	private UmTMENUId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "menuId", column = @Column(name = "menuId"))
    })
	public UmTMENUId getId() {
		return this.id;
	}

	public void setId(UmTMENUId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 上级菜单ID **/
	private String upperMenuId;
  	/** 功能ID **/
	private String taskId;
  	/** 菜单层级 **/
	private Integer level;
  	/** 适用范围 **/
	private String scope;
  	/** 系统代码 **/
	private String systemCode;
  	/** 菜单名称 **/
	private String menuName;
  	/** 菜单URL **/
	private String actionUrl;
  	/** 显示序号 **/
	private Integer displayNo;
  	/** 功能代码 **/
	private String taskCode;
  	/** 有效状态 **/
	private String validStauts;
  	/** 创建人代码 **/
	private String creatorCode;
  	/** 创建时间 **/
	private Date insertTimeForHis;
  	/** 更新人代码 **/
	private String updaterCode;
  	/** 更新时间 **/
	private Date operateTimeForHis;
  	/** 标志字段 **/
	private String flag;
	/** 标志字段 **/
	private String isOpen;
	/** 标志字段 **/
	private String openLevel;
	/** 标志字段 **/
	private String userType;
	
	
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
	
	
	/**getter setter方法*/
	@Column(name = "UpperMenuId")
	public String getUpperMenuId() {
		return this.upperMenuId;
	}
	
	public void setUpperMenuId(String upperMenuId) {
		this.upperMenuId = upperMenuId;
	}
	@Column(name = "TaskId")
	public String getTaskId() {
		return this.taskId;
	}
	
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	@Column(name = "MENULEVEL")
	public Integer getLevel() {
		return this.level;
	}
	
	public void setLevel(Integer level) {
		this.level = level;
	}
	@Column(name = "APPLYSCOPE")
	public String getScope() {
		return this.scope;
	}
	
	public void setScope(String scope) {
		this.scope = scope;
	}
	@Column(name = "SystemCode")
	public String getSystemCode() {
		return this.systemCode;
	}
	
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	@Column(name = "MenuName")
	public String getMenuName() {
		return this.menuName;
	}
	
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	@Column(name = "ActionUrl")
	public String getActionUrl() {
		return this.actionUrl;
	}
	
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
	@Column(name = "DisplayNo")
	public Integer getDisplayNo() {
		return this.displayNo;
	}
	
	public void setDisplayNo(Integer displayNo) {
		this.displayNo = displayNo;
	}
	@Column(name = "TaskCode")
	public String getTaskCode() {
		return this.taskCode;
	}
	
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	@Column(name = "ValidStauts")
	public String getValidStauts() {
		return this.validStauts;
	}
	
	public void setValidStauts(String validStauts) {
		this.validStauts = validStauts;
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