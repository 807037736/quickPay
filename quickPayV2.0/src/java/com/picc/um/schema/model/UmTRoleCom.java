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
@Table(name = "um_t_rolecom")
public class UmTRoleCom implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_ROLE_COM_ID = "ID";
	public static final String ALIAS_ROLE_ID = "角色ID";
	public static final String ALIAS_ROLE_CODE = "角色代码";
	public static final String ALIAS_COM_CODE = "机构代码";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	public static final String ALIAS_INCLUDE_TYPE = "包含类型";
	public static final String ALIAS_COM_NAME = "机构名称";
	
	
	/**	构造函数	**/
	public UmTRoleCom() {
	}
	
	/** 主键对象 **/
	private UmTRoleComId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "roleComId", column = @Column(name = "roleComId"))
    })
	public UmTRoleComId getId() {
		return this.id;
	}

	public void setId(UmTRoleComId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 角色ID **/
	private String roleId;
  	/** 角色代码 **/
	private String roleCode;
  	/** 机构代码 **/
	private String comCode;
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
  	/** 包含类型 **/
	private String includeType = "0";
  	/** 机构名称 **/
	private String comName;
	
//	private UmTRole umTRole;
	
	/**getter setter方法*/
	@Column(name = "RoleId")
	public String getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Column(name = "RoleCode")
	public String getRoleCode() {
		return this.roleCode;
	}
	
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	@Column(name = "ComCode")
	public String getComCode() {
		return this.comCode;
	}
	
	public void setComCode(String comCode) {
		this.comCode = comCode;
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
	@Column(name = "IncludeType")
	public String getIncludeType() {
		return this.includeType;
	}
	
	public void setIncludeType(String includeType) {
		this.includeType = includeType;
	}
	@Column(name = "ComName")
	public String getComName() {
		return this.comName;
	}
	
	public void setComName(String comName) {
		this.comName = comName;
	}


//	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
//	@JoinColumn(name = "RoleId")
//	public UmTRole getUmTRole() {
//		return umTRole;
//	}
//	
//	public void setUmTRole(UmTRole umTRole) {
//		this.umTRole = umTRole;
//	}
}