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
@Table(name = "um_t_dictionarydetail")
public class UmTDictionaryDetail implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_DICTIONARY_DETAIL_ID = "字典明细ID";
	public static final String ALIAS_DICTIONARY_ID = "字典ID";
	public static final String ALIAS_SERIAL_NO = "序列号";
	public static final String ALIAS_TARGET_NAME = "操作目标";
	public static final String ALIAS_TARGET_FIELD = "操作域";
	public static final String ALIAS_COM_CODE = "机构代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	
	
	/**	构造函数	**/
	public UmTDictionaryDetail() {
	}
	
	/** 主键对象 **/
	private UmTDictionaryDetailId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "dictionaryDetailId", column = @Column(name = "dictionaryDetailId"))
    })
	public UmTDictionaryDetailId getId() {
		return this.id;
	}

	public void setId(UmTDictionaryDetailId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 字典ID **/
	private String dictionaryId;
  	/** 序列号 **/
	private Integer serialNo;
  	/** 操作目标 **/
	private String targetName;
  	/** 操作域 **/
	private String targetField;
  	/** 机构代码 **/
	private String comCode;
  	/** 创建时间 **/
	private Date insertTimeForHis;
  	/** 创建人代码 **/
	private String creatorCode;
  	/** 修改时间 **/
	private Date operateTimeForHis;
  	/** 修改人代码 **/
	private String updaterCode;
  	/** 有效状态 **/
	private String validStatus;
	
	private String comId;
	
	/**getter setter方法*/
	@Column(name = "DictionaryId")
	public String getDictionaryId() {
		return this.dictionaryId;
	}
	
	public void setDictionaryId(String dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
	@Column(name = "SerialNo")
	public Integer getSerialNo() {
		return this.serialNo;
	}
	
	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	@Column(name = "TargetName")
	public String getTargetName() {
		return this.targetName;
	}
	
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	@Column(name = "TargetField")
	public String getTargetField() {
		return this.targetField;
	}
	
	public void setTargetField(String targetField) {
		this.targetField = targetField;
	}
	@Column(name = "ComCode")
	public String getComCode() {
		return this.comCode;
	}
	
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "InsertTimeForHis" , updatable = false)
	public Date getInsertTimeForHis() {
		return this.insertTimeForHis;
	}
	
	public void setInsertTimeForHis(Date insertTimeForHis) {
		this.insertTimeForHis = insertTimeForHis;
	}
	@Column(name = "CreatorCode" , updatable = false)
	public String getCreatorCode() {
		return this.creatorCode;
	}
	
	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OperateTimeForHis" , insertable = false)
	public Date getOperateTimeForHis() {
		return this.operateTimeForHis;
	}
	
	public void setOperateTimeForHis(Date operateTimeForHis) {
		this.operateTimeForHis = operateTimeForHis;
	}
	@Column(name = "UpdaterCode" , insertable = false)
	public String getUpdaterCode() {
		return this.updaterCode;
	}
	
	public void setUpdaterCode(String updaterCode) {
		this.updaterCode = updaterCode;
	}
	@Column(name = "ValidStatus")
	public String getValidStatus() {
		return this.validStatus;
	}
	
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

	@Column(name="COMID")
	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}
		
}