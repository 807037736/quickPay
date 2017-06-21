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
@Table(name = "Qp_T_IC_Document")
public class QpTICDocument implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_BATCH_NO = "发放批次号";
	public static final String ALIAS_GRANTOR = "发放人";
	public static final String ALIAS_CENTER_ID = "快出中心id";
	public static final String ALIAS_GRANT_TIME = "发放时间";
	public static final String ALIAS_GRANT_COUNT = "发放数量";
	public static final String ALIAS_START_NO = "单证起始号";
	public static final String ALIAS_END_NO = "单证结束号";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	
	
	/**	构造函数	**/
	public QpTICDocument() {
	}
	
	/** 主键对象 **/
	private QpTICDocumentId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "batchNo", column = @Column(name = "batchNo"))
    })
	public QpTICDocumentId getId() {
		return this.id;
	}

	public void setId(QpTICDocumentId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 发放人 **/
	private String grantor;
  	/** 快出中心id **/
	private String centerId;
	
	private String centerName;
  	/** 发放时间 **/
	private Date grantTime;
	
	
	private Date grantTimeStar;
	
	private Date grantTimeEnd;
	
  	/** 发放数量 **/
	private java.lang.Integer grantCount;
  	/** 单证起始号 **/
	private String startNo;
  	/** 单证结束号 **/
	private String endNo;
  	/** 有效状态 **/
	private String validStatus;
	
	private String batchNo;
	/**getter setter方法*/
	@Column(name = "grantor")
	public String getGrantor() {
		return this.grantor;
	}
	
	public void setGrantor(String grantor) {
		this.grantor = grantor;
	}
	@Column(name = "centerId")
	public String getCenterId() {
		return this.centerId;
	}
	
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "grantTime")
	public Date getGrantTime() {
		return this.grantTime;
	}
	
	public void setGrantTime(Date grantTime) {
		this.grantTime = grantTime;
	}
	@Column(name = "grantCount")
	public java.lang.Integer getGrantCount() {
		return this.grantCount;
	}
	
	public void setGrantCount(java.lang.Integer grantCount) {
		this.grantCount = grantCount;
	}
	@Column(name = "startNo")
	public String getStartNo() {
		return this.startNo;
	}
	
	public void setStartNo(String startNo) {
		this.startNo = startNo;
	}
	@Column(name = "endNo")
	public String getEndNo() {
		return this.endNo;
	}
	
	public void setEndNo(String endNo) {
		this.endNo = endNo;
	}
	@Column(name = "validStatus")
	public String getValidStatus() {
		return this.validStatus;
	}
	
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	@Transient
	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	@Transient
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	@Transient
	public Date getGrantTimeStar() {
		return grantTimeStar;
	}

	public void setGrantTimeStar(Date grantTimeStar) {
		this.grantTimeStar = grantTimeStar;
	}
	@Transient
	public Date getGrantTimeEnd() {
		return grantTimeEnd;
	}

	public void setGrantTimeEnd(Date grantTimeEnd) {
		this.grantTimeEnd = grantTimeEnd;
	}
		
}