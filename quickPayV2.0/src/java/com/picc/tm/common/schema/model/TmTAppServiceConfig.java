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
@Table(name = "TM_T_APPSERVICECONFIG")
public class TmTAppServiceConfig implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_SERVER_CODE = "服务代码";
	public static final String ALIAS_SERVER_NAME = "服务名称";
	public static final String ALIAS_ENVIRONMENT_CODE = "环境代码";
	public static final String ALIAS_PROTOCOL_TYPE = "通道类型";
	public static final String ALIAS_SERVER_IP = "服务地址";
	public static final String ALIAS_SERVER_PORT = "服务端口";
	public static final String ALIAS_SERVER_CONTEXT = "服务上下文";
	public static final String ALIAS_METHODS = "方法路径";
	public static final String ALIAS_AREA_CODE = "区域代码";
	public static final String ALIAS_APP_USER_NAME = "应用用户名";
	public static final String ALIAS_APP_PASSWORD = "应用密码";
	public static final String ALIAS_COM_ID = "8位省级归属机构";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "更新人代码";
	public static final String ALIAS_UPDATE_TIME_FOR_HIS = "更新时间";
	public static final String ALIAS_VALID_STATUS = "有效标志";
	
	
	/**	构造函数	**/
	public TmTAppServiceConfig() {
	}
	
	/** 主键对象 **/
	private TmTAppServiceConfigId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "serverCode", column = @Column(name = "serverCode")),
      @AttributeOverride(name = "environmentCode", column = @Column(name = "environmentCode"))
    })
	public TmTAppServiceConfigId getId() {
		return this.id;
	}

	public void setId(TmTAppServiceConfigId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 服务名称 **/
	private String serverName;
  	/** 通道类型 **/
	private String protocolType;
  	/** 服务地址 **/
	private String serverIp;
  	/** 服务端口 **/
	private String serverPort;
  	/** 服务上下文 **/
	private String serverContext;
  	/** 方法路径 **/
	private String methods;
  	/** 区域代码 **/
	private String areaCode;
  	/** 应用用户名 **/
	private String appUserName;
  	/** 应用密码 **/
	private String appPassword;
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
	/** 是否显示系统 **/
	private String isShow;
	
	/**当前用户是否有访问系统的权限,0为没有，1为有**/
	private Integer isAllow ;
	
	
	@Column(name = "IsAllow")
	public Integer getIsAllow() {
		return isAllow;
	}

	public void setIsAllow(Integer isAllow) {
		this.isAllow = isAllow;
	}

	/**getter setter方法*/
	@Column(name = "ServerName")
	public String getServerName() {
		return this.serverName;
	}
	
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	@Column(name = "ProtocolType")
	public String getProtocolType() {
		return this.protocolType;
	}
	
	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}
	@Column(name = "ServerIp")
	public String getServerIp() {
		return this.serverIp;
	}
	
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	@Column(name = "ServerPort")
	public String getServerPort() {
		return this.serverPort;
	}
	
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}
	@Column(name = "ServerContext")
	public String getServerContext() {
		return this.serverContext;
	}
	
	public void setServerContext(String serverContext) {
		this.serverContext = serverContext;
	}
	@Column(name = "Methods")
	public String getMethods() {
		return this.methods;
	}
	
	public void setMethods(String methods) {
		this.methods = methods;
	}
	@Column(name = "AreaCode")
	public String getAreaCode() {
		return this.areaCode;
	}
	
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	@Column(name = "AppUserName")
	public String getAppUserName() {
		return this.appUserName;
	}
	
	public void setAppUserName(String appUserName) {
		this.appUserName = appUserName;
	}
	@Column(name = "AppPassword")
	public String getAppPassword() {
		return this.appPassword;
	}
	
	public void setAppPassword(String appPassword) {
		this.appPassword = appPassword;
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
	@Column(name = "IsShow")
	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
}