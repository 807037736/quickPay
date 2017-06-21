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
@Table(name = "Qp_T_IC_Picture")
public class QpTICPicture implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_PIC_ID = "图片ID";
	public static final String ALIAS_PIC_DESC = "图片描述";
	public static final String ALIAS_PIC_ORDER = "序号";
	public static final String ALIAS_PIC_SOURCE = "图片来源";
	public static final String ALIAS_FILE_NAME = "文件路径";
	public static final String ALIAS_ORIGINAL_FILE_NAME = "文件上传原名称";
	public static final String ALIAS_SERVER_ID = "服务ID";
	public static final String ALIAS_UPLOAD_TIME = "上传时间";
	public static final String ALIAS_ACCI_ID = "所属事故ID";
	public static final String ALIAS_CHECK_STATUS = "确认状态";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	
	
	/**	构造函数	**/
	public QpTICPicture() {
	}
	
	/** 主键对象 **/
	private QpTICPictureId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "picId", column = @Column(name = "picId"))
    })
	public QpTICPictureId getId() {
		return this.id;
	}

	public void setId(QpTICPictureId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 图片描述 **/
	private String picDesc;
  	/** 序号 **/
	private String picOrder;
  	/** 图片来源 **/
	private String picSource;
  	/** 文件路径 **/
	private String fileName;
	/** 文件上传原名称 **/
	private String originalFileName;
  	/** 服务ID **/
	private String serverId;
  	/** 上传时间 **/
	private Date uploadTime;
  	/** 所属事故ID **/
	private String acciId;
  	/** 确认状态 **/
	private String checkStatus;
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
	/** 组号ID **/
	private String groupId;
	
	/** 图片宽度**/
	private int width;
	/** 图片高度**/
	private int height;
	
	/**getter setter方法*/
	@Column(name = "PicDesc")
	public String getPicDesc() {
		return this.picDesc;
	}
	
	public void setPicDesc(String picDesc) {
		this.picDesc = picDesc;
	}
	@Column(name = "PicOrder")
	public String getPicOrder() {
		return this.picOrder;
	}
	
	public void setPicOrder(String picOrder) {
		this.picOrder = picOrder;
	}
	@Column(name = "PicSource")
	public String getPicSource() {
		return this.picSource;
	}
	
	public void setPicSource(String picSource) {
		this.picSource = picSource;
	}
	@Column(name = "FileName")
	public String getFileName() {
		return this.fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name = "originalFileName")
	public String getOriginalFileName() {
	    return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
	    this.originalFileName = originalFileName;
	}

	@Column(name = "ServerId")
	public String getServerId() {
		return this.serverId;
	}
	
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UploadTime")
	public Date getUploadTime() {
		return this.uploadTime;
	}
	
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	@Column(name = "AcciId")
	public String getAcciId() {
		return this.acciId;
	}
	
	public void setAcciId(String acciId) {
		this.acciId = acciId;
	}
	@Column(name = "CheckStatus")
	public String getCheckStatus() {
		return this.checkStatus;
	}
	
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
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

	@Column(name = "groupId")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	@Transient
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	@Transient
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
		
}