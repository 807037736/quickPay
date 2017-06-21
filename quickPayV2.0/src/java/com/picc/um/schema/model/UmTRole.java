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
@Table(name = "um_t_role")
public class UmTRole implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_ROLE_ID = "角色ID";
	public static final String ALIAS_ROLE_C_NAME = "角色简体中文名";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	public static final String ALIAS_FLAG = "标识字段";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_ROLE_CODE = "角色代码";
	
	
	/**	构造函数	**/
	public UmTRole() {
	}
	
	/** 主键对象 **/
	private UmTRoleId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "roleId", column = @Column(name = "roleId"))
    })
	public UmTRoleId getId() {
		return this.id;
	}

	public void setId(UmTRoleId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 角色简体中文名 **/
	private String roleCName;
  	/** 创建人代码 **/
	private String creatorCode;
  	/** 创建时间 **/
	private Date insertTimeForHis;
  	/** 修改人代码 **/
	private String updaterCode;
  	/** 修改时间 **/
	private Date operateTimeForHis;
  	/** 有效状态 **/
	private String validStatus = "1";  
  	/** 标识字段 **/
	private String flag;
  	/** 备注 **/
	private String remark;
  	/** 角色代码 **/
	private String roleCode;
	
	private String comName;
	
	/** 标志字段 **/
	private String userType;
	private String serverCode;
	
//	private List<UmTRoleCom> umRoleComs = new ArrayList<UmTRoleCom>();    //角色与与机构的关联
//	
//	private List<UmTRoleTask> umTRoleTasks = new ArrayList<UmTRoleTask>(); //角色下有与功能的关联
//	
//	private List<UmTTask> umTTasks = new ArrayList<UmTTask>();             //角色下有功能
	
	
	/**getter setter方法*/
	@Column(name = "RoleCName")
	public String getRoleCName() {
		return this.roleCName;
	}
	
	public void setRoleCName(String roleCName) {
		this.roleCName = roleCName;
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
	@Column(name = "Flag")
	public String getFlag() {
		return this.flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Column(name = "Remark")
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "RoleCode")
	public String getRoleCode() {
		return this.roleCode;
	}
	
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Transient
	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}
	@Column(name = "UserType")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
//	@OneToMany(cascade =CascadeType.ALL,fetch=FetchType.LAZY)
//	@JoinColumn(name="roleId")
//	public List<UmTRoleCom> getUmRoleComs() {
//		return umRoleComs;
//	}
//
//	public void setUmRoleComs(List<UmTRoleCom> umRoleComs) {
//		this.umRoleComs = umRoleComs;
//	}
//
//	@ManyToMany(fetch=FetchType.LAZY)
//	@JoinTable(name="um_t_roletask",
//			joinColumns={@JoinColumn(name="roleId")},
//			inverseJoinColumns={@JoinColumn(name="taskId")})
//	public List<UmTTask> getUmTTaskList() {
//		return umTTasks;
//	}
//
//	public void setUmTTaskList(List<UmTTask> umTTasks) {
//		this.umTTasks = umTTasks;
//	}
//
//	@OneToMany(cascade =CascadeType.ALL,fetch=FetchType.LAZY)
//	@JoinColumn(name="RoleId")
//	public List<UmTRoleTask> getUmTRoleTasks() {
//		return umTRoleTasks;
//	}
//
//	public void setUmTRoleTasks(List<UmTRoleTask> umTRoleTasks) {
//		this.umTRoleTasks = umTRoleTasks;
//	}

	/**
	 * @param serverCode the serverCode to set
	 */
	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}

	@Column(name = "ServerCode")
	public String getServerCode() {
		return serverCode;
	}
}