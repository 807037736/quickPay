/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
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
@Table(name = "Tm_T_AppEnvironment")
public class TmTAppEnvironment implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_ENVIRONMENT_CODE = "环境代码";
	public static final String ALIAS_ENVIRONMENT_NAME = "环境名称";
	public static final String ALIAS_NET_TYEP = "网络类型";
	public static final String ALIAS_COM_ID = "8位省级归属机构";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "更新人代码";
	public static final String ALIAS_UPDATE_TIME_FOR_HIS = "更新时间";
	public static final String ALIAS_VALID_STATUS = "有效标志";
	
	
	/**	构造函数	**/
	public TmTAppEnvironment() {
	}
	
	/** 主键对象 **/
	private TmTAppEnvironmentId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "environmentCode", column = @Column(name = "environmentCode"))
    })
	public TmTAppEnvironmentId getId() {
		return this.id;
	}

	public void setId(TmTAppEnvironmentId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 环境名称 **/
	private String environmentName;
  	/** 网络类型 **/
	private String netTyep;
  	/** 8位省级归属机构 **/
	private String comId;
  	/** 创建人代码 **/
	private String creatorCode;
  	/** 创建时间 **/
	private Date insertTimeForHis;
  	/** 更新人代码 **/
	private String updaterCode;
  	/** 更新时间 **/
	private Date updateTimeForHis;
  	/** 有效标志 **/
	private String validStatus;
	
	/**getter setter方法*/
	@Column(name = "EnvironmentName")
	public String getEnvironmentName() {
		return this.environmentName;
	}
	
	public void setEnvironmentName(String environmentName) {
		this.environmentName = environmentName;
	}
	@Column(name = "NetTyep")
	public String getNetTyep() {
		return this.netTyep;
	}
	
	public void setNetTyep(String netTyep) {
		this.netTyep = netTyep;
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
	@Column(name = "UpdateTimeForHis")
	public Date getUpdateTimeForHis() {
		return this.updateTimeForHis;
	}
	
	public void setUpdateTimeForHis(Date updateTimeForHis) {
		this.updateTimeForHis = updateTimeForHis;
	}
	@Column(name = "ValidStatus")
	public String getValidStatus() {
		return this.validStatus;
	}
	
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
		
}