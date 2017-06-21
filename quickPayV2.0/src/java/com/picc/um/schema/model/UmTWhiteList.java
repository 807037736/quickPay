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
@Table(name = "um_t_whitelist")
public class UmTWhiteList implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_WL_ID = "白名单ID";
	public static final String ALIAS_VISIT_URL = "访问URL";
	public static final String ALIAS_VISIT_DESC = "访问地址描述";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	public static final String ALIAS_CREATOR_CODE = "创建者代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "修改者代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	
	
	/**	构造函数	**/
	public UmTWhiteList() {
	}
	
	/** 主键对象 **/
	private UmTWhiteListId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "wlId", column = @Column(name = "wlId"))
    })
	public UmTWhiteListId getId() {
		return this.id;
	}

	public void setId(UmTWhiteListId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 访问URL **/
	private String visitUrl;
  	/** 访问地址描述 **/
	private String visitDesc;
  	/** 有效状态 **/
	private String validStatus;
  	/** 创建者代码 **/
	private String creatorCode;
  	/** 创建时间 **/
	private Date insertTimeForHis;
  	/** 修改者代码 **/
	private String updaterCode;
  	/** 修改时间 **/
	private Date operateTimeForHis;
	
	
	
	/**getter setter方法*/
	@Column(name = "VisitUrl")
	public String getVisitUrl() {
		return this.visitUrl;
	}
	
	public void setVisitUrl(String visitUrl) {
		this.visitUrl = visitUrl;
	}
	@Column(name = "VisitDesc")
	public String getVisitDesc() {
		return this.visitDesc;
	}
	
	public void setVisitDesc(String visitDesc) {
		this.visitDesc = visitDesc;
	}
	@Column(name = "ValidStatus")
	public String getValidStatus() {
		return this.validStatus;
	}
	
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
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
		
}