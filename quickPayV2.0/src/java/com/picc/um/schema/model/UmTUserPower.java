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
@Table(name = "um_t_userpower")
public class UmTUserPower implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_USER_POWER_ID = "ID";
	public static final String ALIAS_OPERATOR_TYPE = "操作者类型";
	public static final String ALIAS_DICTIONARY_ID = "字典ID";
	public static final String ALIAS_USER_CODE = "用户代码";
	public static final String ALIAS_OPERATION_SYMBOL = "操作符";
	public static final String ALIAS_POWER_VALUE = "限制域值";
	public static final String ALIAS_COM_CODE = "机构代码";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	
	
	/**	构造函数	**/
	public UmTUserPower() {
	}
	
	public UmTUserPower(UmTUserPowerId id) {
		this.id = id;
	}
	
	/** 主键对象 **/
	private UmTUserPowerId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "userPowerId", column = @Column(name = "userPowerId"))
    })
	public UmTUserPowerId getId() {
		return this.id;
	}

	public void setId(UmTUserPowerId id) {
		this.id = id;
	}
	
	/**属性*/
  	/** 操作者类型 **/
	private String operatorType;
  	/** 字典ID **/
	private String dictionaryId;
  	/** 用户代码 **/
	private String userCode;
  	/** 操作符 **/
	private String operationSymbol;
  	/** 限制域值 **/
	private String powerValue;
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
	private String validStatus;
	/**角色权限对应ID**/
	private String rolePowerId;
	
	private String comId;
	
	/**getter setter方法*/
	@Column(name = "OperatorType")
	public String getOperatorType() {
		return this.operatorType;
	}
	
	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}
	@Column(name = "DictionaryId")
	public String getDictionaryId() {
		return this.dictionaryId;
	}
	
	public void setDictionaryId(String dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
	@Column(name = "UserCode")
	public String getUserCode() {
		return this.userCode;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
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
	
	@Column(name="ROLEPOWERID")
	public String getRolePowerId() {
		return rolePowerId;
	}

	public void setRolePowerId(String rolePowerId) {
		this.rolePowerId = rolePowerId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}
	
	@Column(name="COMID")
	public String getComId() {
		return comId;
	}
	
	
	
	
	
		
}