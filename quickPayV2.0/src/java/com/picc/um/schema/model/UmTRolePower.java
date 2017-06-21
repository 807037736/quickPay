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
@Table(name = "um_t_rolepower")
public class UmTRolePower implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_RP_ID = "主键ID";
	public static final String ALIAS_ROLE_ID = "角色ID";
	public static final String ALIAS_DICTIONARY_ID = "字典ID";
	public static final String ALIAS_OPERATION_SYMBOL = "权限操作符";
	public static final String ALIAS_POWER_VALUE = "权限域值";
	public static final String ALIAS_COM_ID = "8位机构号";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "更新人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "更新时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	
	
	/**	构造函数	**/
	public UmTRolePower() {
	}
	
	public UmTRolePower(UmTRolePowerId id) {
		this.id = id;
	}
	
	/** 主键对象 **/
	private UmTRolePowerId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "rpId", column = @Column(name = "rpId"))
    })
	public UmTRolePowerId getId() {
		return this.id;
	}

	public void setId(UmTRolePowerId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 角色ID **/
	private String roleId;
  	/** 字典ID **/
	private String dictionaryId;
  	/** 权限操作符 **/
	private String operationSymbol;
  	/** 权限域值 **/
	private String powerValue;
  	/** 8位机构号 **/
	private String comId;
  	/** 创建人代码 **/
	private String creatorCode;
  	/** 创建时间 **/
	private Date insertTimeForHis;
  	/** 更新人代码 **/
	private String updaterCode;
  	/** 更新时间 **/
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
	@Column(name = "DictionaryId")
	public String getDictionaryId() {
		return this.dictionaryId;
	}
	
	public void setDictionaryId(String dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
	@Column(name = "OperationSymbol")
	public String getOperationSymbol() {
		return this.operationSymbol;
	}
	
	public void setOperationSymbol(String operationSymbol) {
		this.operationSymbol = operationSymbol;
	}
	@Column(name = "PowerValue")
	public String getPowerValue() {
		return this.powerValue;
	}
	
	public void setPowerValue(String powerValue) {
		this.powerValue = powerValue;
	}
	@Column(name = "ComId")
	public String getComId() {
		return this.comId;
	}
	
	public void setComId(String comId) {
		this.comId = comId;
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