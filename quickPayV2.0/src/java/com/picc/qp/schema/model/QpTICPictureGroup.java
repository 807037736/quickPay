/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.schema.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
@Table(name = "Qp_T_IC_Picture_Group")
public class QpTICPictureGroup implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_GROUP_ID = "组号ID";
	public static final String ALIAS_UPLOAD_TIME_FOR_HIS = "上传时间";
	public static final String ALIAS_UPLOAD_USER_CODE = "上传用户代码";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	
	
	/**	构造函数	**/
	public QpTICPictureGroup() {
	}
	
	/** 主键对象 **/
	private QpTICPictureGroupId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "groupId", column = @Column(name = "groupId"))
    })
	public QpTICPictureGroupId getId() {
		return this.id;
	}

	public void setId(QpTICPictureGroupId id) {
		this.id = id;
	}
	
	private String groupId;
	/**属性*/
  	/** 上传时间 **/
	private Date uploadTimeForHis;
  	/** 上传用户代码 **/
	private String uploadUserCode;
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
	
	/** 手机号码 **/
	private String mobile;
	/**  车牌号 **/
    private String licenseNo;
    
    /** 图片类型  微信01 PC02**/
	private String pictureType;
	
	/**getter setter方法*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "uploadTimeForHis")
	public Date getUploadTimeForHis() {
		return this.uploadTimeForHis;
	}
	
	public void setUploadTimeForHis(Date uploadTimeForHis) {
		this.uploadTimeForHis = uploadTimeForHis;
	}
	@Column(name = "uploadUserCode")
	public String getUploadUserCode() {
		return this.uploadUserCode;
	}
	
	public void setUploadUserCode(String uploadUserCode) {
		this.uploadUserCode = uploadUserCode;
	}
	@Column(name = "creatorCode" , updatable = false)
	public String getCreatorCode() {
		return this.creatorCode;
	}
	
	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "insertTimeForHis" , updatable = false)
	public Date getInsertTimeForHis() {
		return this.insertTimeForHis;
	}
	
	public void setInsertTimeForHis(Date insertTimeForHis) {
		this.insertTimeForHis = insertTimeForHis;
	}
	@Column(name = "updaterCode" , insertable = false)
	public String getUpdaterCode() {
		return this.updaterCode;
	}
	
	public void setUpdaterCode(String updaterCode) {
		this.updaterCode = updaterCode;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "operateTimeForHis" , insertable = false)
	public Date getOperateTimeForHis() {
		return this.operateTimeForHis;
	}
	
	public void setOperateTimeForHis(Date operateTimeForHis) {
		this.operateTimeForHis = operateTimeForHis;
	}
	@Column(name = "validStatus")
	public String getValidStatus() {
		return this.validStatus;
	}
	
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	@Transient
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Transient
	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	@Transient
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	public String getPictureType() {
		return pictureType;
	}

	public void setPictureType(String pictureType) {
		this.pictureType = pictureType;
	}
}