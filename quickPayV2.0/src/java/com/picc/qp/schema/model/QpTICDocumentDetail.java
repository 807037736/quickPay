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
@Table(name = "Qp_T_IC_Document_Detail")
public class QpTICDocumentDetail implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_BATCH_NO = "所属批次号";
	public static final String ALIAS_DOCUMENT_NO = "单证号";
	public static final String ALIAS_CENTER_ID = "快处中心号";
	public static final String ALIAS_USED_TIME = "使用时间";
	public static final String ALIAS_USED_PERSON = "使用人";
	public static final String ALIAS_CANCEL_TIME = "销毁时间";
	public static final String ALIAS_CANCEL_PERSON = "销毁人";
	public static final String ALIAS_DOCUMENT_FLAG = "单证状态";
	public static final String ALIAS_VALID_STATUS = "数据有效标志";
	
	
	/**	构造函数	**/
	public QpTICDocumentDetail() {
	}
	
	/** 主键对象 **/
	private QpTICDocumentDetailId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "documentNo", column = @Column(name = "documentNo"))
    })
	public QpTICDocumentDetailId getId() {
		return this.id;
	}

	public void setId(QpTICDocumentDetailId id) {
		this.id = id;
	}
	
	/*   */
	private String  documentNo;
	
	/**属性*/
  	/** 所属批次号 **/
	private String batchNo;
  	/** 快处中心号 **/
	private String centerId;
	private String centerName;
  	/** 使用时间 **/
	private Date usedTime;
  	/** 使用人 **/
	private String usedPerson;
  	/** 销毁时间 **/
	private Date cancelTime;
  	/** 销毁人 **/
	private String cancelPerson;
  	/** 单证状态 **/
	private String documentFlag;
  	/** 数据有效标志 **/
	private String validStatus;
	private String businessNo;
	/**getter setter方法*/
	@Column(name = "batchNo")
	public String getBatchNo() {
		return this.batchNo;
	}
	
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	@Column(name = "centerId")
	public String getCenterId() {
		return this.centerId;
	}
	
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "usedTime")
	public Date getUsedTime() {
		return this.usedTime;
	}
	
	public void setUsedTime(Date usedTime) {
		this.usedTime = usedTime;
	}
	@Column(name = "usedPerson")
	public String getUsedPerson() {
		return this.usedPerson;
	}
	
	public void setUsedPerson(String usedPerson) {
		this.usedPerson = usedPerson;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cancelTime")
	public Date getCancelTime() {
		return this.cancelTime;
	}
	
	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}
	@Column(name = "cancelPerson")
	public String getCancelPerson() {
		return this.cancelPerson;
	}
	
	public void setCancelPerson(String cancelPerson) {
		this.cancelPerson = cancelPerson;
	}
	@Column(name = "documentFlag")
	public String getDocumentFlag() {
		return this.documentFlag;
	}
	
	public void setDocumentFlag(String documentFlag) {
		this.documentFlag = documentFlag;
	}
	@Column(name = "validStatus")
	public String getValidStatus() {
		return this.validStatus;
	}
	
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	@Column(name = "businessNo")
	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}
	//配置不映射到数据库
	@Transient
	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	@Transient
	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}


}