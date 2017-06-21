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
@Table(name = "Qp_T_TP_Law")
public class QpTTPLaw implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_LAW_ID = "法律法规ID";
	public static final String ALIAS_LAW_TYPE = "法律法规类型";
	public static final String ALIAS_LAW_NAME = "法律法规内容";
	public static final String ALIAS_LAW_WORDS = "关键字描述";
	public static final String ALIAS_LAW_ITEM = "条";
	public static final String ALIAS_LAW_SEGMENT = "款";
	public static final String ALIAS_LAW_SECTION = "项";
	public static final String ALIAS_LAW_ORDER = "序号";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	
	
	/**	构造函数	**/
	public QpTTPLaw() {
	}
	
	/** 主键对象 **/
	private QpTTPLawId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "lawId", column = @Column(name = "lawId"))
    })
	public QpTTPLawId getId() {
		return this.id;
	}

	public void setId(QpTTPLawId id) {
	    this.lawId = id.getLawId();
		this.id = id;
	}
	
	private String lawId;
	/**属性*/
	/** 法律法规内容 **/
	private String lawType;
  	/** 法律法规内容 **/
	private String lawName;
  	/** 关键字描述 **/
	private String lawWords;
  	/** 条 **/
	private String lawItem;
  	/** 款 **/
	private String lawSegment;
  	/** 项 **/
	private String lawSection;
  	/** 序号 **/
	private Long lawOrder;
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
	
	public String getLawType() {
		return lawType;
	}

	public void setLawType(String lawType) {
		this.lawType = lawType;
	}

	/**getter setter方法*/
	@Column(name = "LawName")
	public String getLawName() {
		return this.lawName;
	}
	
	public void setLawName(String lawName) {
		this.lawName = lawName;
	}
	@Column(name = "LawWords")
	public String getLawWords() {
		return this.lawWords;
	}
	
	public void setLawWords(String lawWords) {
		this.lawWords = lawWords;
	}
	@Column(name = "LawItem")
	public String getLawItem() {
		return this.lawItem;
	}
	
	public void setLawItem(String lawItem) {
		this.lawItem = lawItem;
	}
	@Column(name = "LawSegment")
	public String getLawSegment() {
		return this.lawSegment;
	}
	
	public void setLawSegment(String lawSegment) {
		this.lawSegment = lawSegment;
	}
	@Column(name = "LawSection")
	public String getLawSection() {
		return this.lawSection;
	}
	
	public void setLawSection(String lawSection) {
		this.lawSection = lawSection;
	}
	@Column(name = "LawOrder")
	public Long getLawOrder() {
		return this.lawOrder;
	}
	
	public void setLawOrder(Long lawOrder) {
		this.lawOrder = lawOrder;
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
	
	@Transient
	public String getLawId() {
		return lawId;
	}

	public void setLawId(String lawId) {
		this.lawId = lawId;
	}
		
}