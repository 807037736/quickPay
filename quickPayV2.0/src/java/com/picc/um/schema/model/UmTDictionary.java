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
@Table(name = "um_t_dictionary")
public class UmTDictionary implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_DICTIONARY_ID = "字典ID";
	public static final String ALIAS_DICTIONARY_CODE = "字典代码";
	public static final String ALIAS_DITIONARY_NAME = "字典名称";
	public static final String ALIAS_COM_CODE = "机构代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	
	
	/**	构造函数	**/
	public UmTDictionary() {
	}
	
	/** 主键对象 **/
	private UmTDictionaryId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "dictionaryId", column = @Column(name = "dictionaryId"))
    })
	public UmTDictionaryId getId() {
		return this.id;
	}

	public void setId(UmTDictionaryId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 字典代码 **/
	private String dictionaryCode;
  	/** 字典名称 **/
	private String ditionaryName;
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
	@Column(name = "DictionaryCode")
	public String getDictionaryCode() {
		return this.dictionaryCode;
	}
	
	public void setDictionaryCode(String dictionaryCode) {
		this.dictionaryCode = dictionaryCode;
	}
	@Column(name = "DictionaryName")
	public String getDitionaryName() {
		return this.ditionaryName;
	}
	
	public void setDitionaryName(String ditionaryName) {
		this.ditionaryName = ditionaryName;
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