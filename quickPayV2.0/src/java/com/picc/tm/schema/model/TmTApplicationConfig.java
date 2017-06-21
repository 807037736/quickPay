/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2014
 */

package com.picc.tm.schema.model;

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
@Table(name = "TM_T_APPLICATIONCONFIG")
public class TmTApplicationConfig implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_SERVER_CODE = "服务代码";
	public static final String ALIAS_SERVER_NAME = "服务名称";
	public static final String ALIAS_SERVER_CONTEXT = "服务上下文";
	public static final String ALIAS_IMAGE_URL = "应用缩略图";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "更新人代码";
	public static final String ALIAS_UPDATE_TIME_FOR_HIS = "更新时间";
	public static final String ALIAS_VALID_STATUS = "有效标志";
	public static final String ALIAS_APPLY_TYPE = "应用类型-外部还是内部";
	public static final String ALIAS_NEED_COGNOS = "是否需要cognos交互";
	public static final String ALIAS_REMARK = "其他备注";
	
	
	/**	构造函数	**/
	public TmTApplicationConfig() {
	}
	
	/** 主键对象 **/
	private TmTApplicationConfigId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "serverCode", column = @Column(name = "serverCode"))
    })
	public TmTApplicationConfigId getId() {
		return this.id;
	}

	public void setId(TmTApplicationConfigId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 服务名称 **/
	private String serverName;
  	/** 服务上下文 **/
	private String serverContext;
  	/** 应用缩略图 **/
	private String imageUrl;
	/** 系统登录页面 **/
	private String loginPage;
	/** 系统主页面 **/
	private String mainPage;
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
  	/** 应用类型-外部还是内部 **/
	private String userType;
  	/** 是否需要cognos交互 **/
	private String needCognos;
  	/** 其他备注 **/
	private String remark;
	
	/**getter setter方法*/
	@Column(name = "ServerName")
	public String getServerName() {
		return this.serverName;
	}
	
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	@Column(name = "ServerContext")
	public String getServerContext() {
		return this.serverContext;
	}
	
	@Column(name = "loginPage")
	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
	@Column(name = "mainPage")
	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public void setServerContext(String serverContext) {
		this.serverContext = serverContext;
	}
	@Column(name = "ImageUrl")
	public String getImageUrl() {
		return this.imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
	@Column(name = "UserType")
	public String getUserType() {
		return this.userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Column(name = "needCognos")
	public String getNeedCognos() {
		return this.needCognos;
	}
	
	public void setNeedCognos(String needCognos) {
		this.needCognos = needCognos;
	}
	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
		
}