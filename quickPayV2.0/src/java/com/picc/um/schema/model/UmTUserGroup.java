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
@Table(name = "um_t_usergroup")
public class UmTUserGroup implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_USER_GROUP_ID = "ID";
	public static final String ALIAS_GROUP_ID = "用户自定义组ID";
	public static final String ALIAS_USER_CODE = "用户代码";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_H_IS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	public static final String ALIAS_COM_ID = "8位机构号";
	public static final String ALIAS_ISLEADER = "isleader";
	
	
	/**	构造函数	**/
	public UmTUserGroup() {
	}
	
	/** 主键对象 **/
	private UmTUserGroupId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "userGroupId", column = @Column(name = "userGroupId"))
    })
	public UmTUserGroupId getId() {
		return this.id;
	}

	public void setId(UmTUserGroupId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 用户自定义组ID **/
	private String groupId;
  	/** 用户代码 **/
	private String userCode;
  	/** 创建人代码 **/
	private String creatorCode;
  	/** 创建时间 **/
	private Date insertTimeForHIs;
  	/** 修改人代码 **/
	private String updaterCode;
  	/** 修改时间 **/
	private Date operateTimeForHis;
  	/** 有效状态 **/
	private String validStatus;
	/** 8位机构号 **/
	private String comId;
	/** isleader **/
	private String isleader;
	
	/**getter setter方法*/
	@Column(name = "GroupId")
	public String getGroupId() {
		return this.groupId;
	}
	
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	@Column(name = "UserCode")
	public String getUserCode() {
		return this.userCode;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	@Column(name = "CreatorCode" , updatable = false)
	public String getCreatorCode() {
		return this.creatorCode;
	}
	
	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "InsertTimeForHIs" , updatable = false)
	public Date getInsertTimeForHIs() {
		return this.insertTimeForHIs;
	}
	
	public void setInsertTimeForHIs(Date insertTimeForHIs) {
		this.insertTimeForHIs = insertTimeForHIs;
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
	@Column(name = "ComId")
	public String getComId() {
		return this.comId;
	}
	
	public void setComId(String comId) {
		this.comId = comId;
	}
	@Column(name = "ISLEADER")
	public String getIsleader() {
		return this.isleader;
	}
	
	public void setIsleader(String isleader) {
		this.isleader = isleader;
	}
		
}