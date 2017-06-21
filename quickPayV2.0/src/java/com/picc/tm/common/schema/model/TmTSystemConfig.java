/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2014
 */

package com.picc.tm.common.schema.model;

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
@Table(name = "Tm_T_SystemConfig")
public class TmTSystemConfig implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_CONFIG_ID = "配置ID";
	public static final String ALIAS_COM_CODE = "归属机构代码";
	public static final String ALIAS_RISK_CODE = "险种代码";
	public static final String ALIAS_CONFIG_CODE = "配置项代码";
	public static final String ALIAS_CONFIG_NAME = "配置项名称";
	public static final String ALIAS_CONFIG_VALUE = "配置项取值";
	public static final String ALIAS_CONFIG_VALUE_DESC = "配置项描述";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	public static final String ALIAS_COM_ID = "8位机构号";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "插入时间";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "更新时间";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_UPDATER_CODE = "更新人代码";
	public static final String ALIAS_SYSTEM_NATURE = "系统性质";
	public static final String ALIAS_COGNOS_INTERACT = "报表交互";
	
	
	/**	构造函数	**/
	public TmTSystemConfig() {
	}
	
	/** 主键对象 **/
	private TmTSystemConfigId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "configId", column = @Column(name = "configId"))
    })
	public TmTSystemConfigId getId() {
		return this.id;
	}

	public void setId(TmTSystemConfigId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 归属机构代码 **/
	private String comCode;
  	/** 险种代码 **/
	private String riskCode;
  	/** 配置项代码 **/
	private String configCode;
  	/** 配置项名称 **/
	private String configName;
  	/** 配置项取值 **/
	private String configValue;
  	/** 配置项描述 **/
	private String configValueDesc;
  	/** 有效状态 **/
	private String validStatus;
  	/** 8位机构号 **/
	private String comId;
  	/** 插入时间 **/
	private Date insertTimeForHis;
  	/** 更新时间 **/
	private Date operateTimeForHis;
  	/** 创建人代码 **/
	private String creatorCode;
  	/** 更新人代码 **/
	private String updaterCode;
  	/** 系统性质 **/
	private String systemNature;
  	/** 报表交互 **/
	private String cognosInteract;
	
	/**getter setter方法*/
	@Column(name = "comCode")
	public String getComCode() {
		return this.comCode;
	}
	
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	@Column(name = "riskCode")
	public String getRiskCode() {
		return this.riskCode;
	}
	
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	@Column(name = "configCode")
	public String getConfigCode() {
		return this.configCode;
	}
	
	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}
	@Column(name = "configName")
	public String getConfigName() {
		return this.configName;
	}
	
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	@Column(name = "configValue")
	public String getConfigValue() {
		return this.configValue;
	}
	
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
	@Column(name = "configValueDesc")
	public String getConfigValueDesc() {
		return this.configValueDesc;
	}
	
	public void setConfigValueDesc(String configValueDesc) {
		this.configValueDesc = configValueDesc;
	}
	@Column(name = "ValidStatus")
	public String getValidStatus() {
		return this.validStatus;
	}
	
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	@Column(name = "ComId")
	public String getComId() {
		return this.comId;
	}
	
	public void setComId(String comId) {
		this.comId = comId;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "insertTimeForHis" , updatable = false)
	public Date getInsertTimeForHis() {
		return this.insertTimeForHis;
	}
	
	public void setInsertTimeForHis(Date insertTimeForHis) {
		this.insertTimeForHis = insertTimeForHis;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "operateTimeForHis" , insertable = false)
	public Date getOperateTimeForHis() {
		return this.operateTimeForHis;
	}
	
	public void setOperateTimeForHis(Date operateTimeForHis) {
		this.operateTimeForHis = operateTimeForHis;
	}
	@Column(name = "CreatorCode" , updatable = false)
	public String getCreatorCode() {
		return this.creatorCode;
	}
	
	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}
	@Column(name = "UpdaterCode" , insertable = false)
	public String getUpdaterCode() {
		return this.updaterCode;
	}
	
	public void setUpdaterCode(String updaterCode) {
		this.updaterCode = updaterCode;
	}
	@Column(name = "SystemNature")
	public String getSystemNature() {
		return this.systemNature;
	}
	
	public void setSystemNature(String systemNature) {
		this.systemNature = systemNature;
	}
	@Column(name = "CognosInteract")
	public String getCognosInteract() {
		return this.cognosInteract;
	}
	
	public void setCognosInteract(String cognosInteract) {
		this.cognosInteract = cognosInteract;
	}
		
}