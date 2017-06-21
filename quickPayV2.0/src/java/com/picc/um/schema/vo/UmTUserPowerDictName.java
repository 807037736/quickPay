package com.picc.um.schema.vo;

import java.util.Date;

import com.picc.um.schema.model.UmTUserPowerId;

public class UmTUserPowerDictName {
	
	private UmTUserPowerId id;
	
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
	/**是否可以界面删除标志**/
	private String comId;
	
	private String rolePowerId;
	
	private String dictionaryName;
	
	public void setId(UmTUserPowerId id) {
		this.id = id;
	}
	
	public UmTUserPowerId getId() {
		return id;
	}
	
	public void setDictionaryName(String dictionaryName) {
		this.dictionaryName = dictionaryName;
	}
	
	public String getDictionaryName() {
		return dictionaryName;
	}

	public String getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}

	public String getDictionaryId() {
		return dictionaryId;
	}

	public void setDictionaryId(String dictionaryId) {
		this.dictionaryId = dictionaryId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getOperationSymbol() {
		return operationSymbol;
	}

	public void setOperationSymbol(String operationSymbol) {
		this.operationSymbol = operationSymbol;
	}

	public String getPowerValue() {
		return powerValue;
	}

	public void setPowerValue(String powerValue) {
		this.powerValue = powerValue;
	}

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getCreatorCode() {
		return creatorCode;
	}

	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}

	public Date getInsertTimeForHis() {
		return insertTimeForHis;
	}

	public void setInsertTimeForHis(Date insertTimeForHis) {
		this.insertTimeForHis = insertTimeForHis;
	}

	public String getUpdaterCode() {
		return updaterCode;
	}

	public void setUpdaterCode(String updaterCode) {
		this.updaterCode = updaterCode;
	}

	public Date getOperateTimeForHis() {
		return operateTimeForHis;
	}

	public void setOperateTimeForHis(Date operateTimeForHis) {
		this.operateTimeForHis = operateTimeForHis;
	}

	public String getValidStatus() {
		return validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}


	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public String getRolePowerId() {
		return rolePowerId;
	}

	public void setRolePowerId(String rolePowerId) {
		this.rolePowerId = rolePowerId;
	}

}
