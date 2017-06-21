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
@Table(name = "Qp_T_IC_Company")
public class QpTICCompany implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_CO_ID = "保险公司ID";
	public static final String ALIAS_CO_NAME = "保险公司名称";
	public static final String ALIAS_CO_ORDER = "序号";
	public static final String ALIAS_CO_PHONE = "公司电话";
	public static final String ALIAS_CO_NOTES = "备注信息";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	
	
	/**	构造函数	**/
	public QpTICCompany() {
	}
	
	/** 主键对象 **/
	private QpTICCompanyId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "coId", column = @Column(name = "coId"))
    })
	public QpTICCompanyId getId() {
		return this.id;
	}

	public void setId(QpTICCompanyId id) {
	    this.coId = id.getCoId();
		this.id = id;
	}
	
	private String coId;
	/**属性*/
  	/** 保险公司名称 **/
	private String coName;
  	/** 序号 **/
	private Integer coOrder;
  	/** 公司电话 **/
	private String coPhone;
  	/** 备注信息 **/
	private String coNotes;
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
	
	/**getter setter方法*/
	@Column(name = "CoName")
	public String getCoName() {
		return this.coName;
	}
	
	public void setCoName(String coName) {
		this.coName = coName;
	}
	@Column(name = "CoOrder")
	public Integer getCoOrder() {
		return this.coOrder;
	}
	
	public void setCoOrder(Integer coOrder) {
		this.coOrder = coOrder;
	}
	@Column(name = "CoPhone")
	public String getCoPhone() {
		return this.coPhone;
	}
	
	public void setCoPhone(String coPhone) {
		this.coPhone = coPhone;
	}
	@Column(name = "CoNotes")
	public String getCoNotes() {
		return this.coNotes;
	}
	
	public void setCoNotes(String coNotes) {
		this.coNotes = coNotes;
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
	public String getCoId() {
		return coId;
	}

	public void setCoId(String coId) {
		this.coId = coId;
	}

}