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
@Table(name = "um_t_groupcom")
public class UmTGroupCom implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_GROUP_COM_ID = "ID";
	public static final String ALIAS_GROUP_ID = "用户自定义组ID";
	public static final String ALIAS_COM_CODE = "机构代码";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "更新人代码";
	public static final String ALIAS_UPDATE_TIME_FOR_HIS = "更新时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	
	
	/**	构造函数	**/
	public UmTGroupCom() {
	}
	
	/** 主键对象 **/
	private UmTGroupComId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "groupComId", column = @Column(name = "groupComId"))
    })
	public UmTGroupComId getId() {
		return this.id;
	}

	public void setId(UmTGroupComId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 用户自定义组ID **/
	private String groupId;
  	/** 机构代码 **/
	private String comCode;
  	/** 创建人代码 **/
	private String creatorCode;
  	/** 创建时间 **/
	private Date insertTimeForHis;
  	/** 更新人代码 **/
	private String updaterCode;
  	/** 更新时间 **/
	private Date updateTimeForHis;
  	/** 有效状态 **/
	private String validStatus;
	
	/**getter setter方法*/
	@Column(name = "GroupId")
	public String getGroupId() {
		return this.groupId;
	}
	
	public void setGroupId(String groupId) {
		this.groupId = groupId;
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