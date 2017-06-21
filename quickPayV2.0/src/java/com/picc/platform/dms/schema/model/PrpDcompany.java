package com.picc.platform.dms.schema.model;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * POJO类PrpDcompany
 */
@Entity
@Table(name = "PRPDCOMPANY")
public class PrpDcompany implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性机构代码 */
	private String comCode;

	/** 属性机构中文名称 */
	private String comCName;

	/** 属性机构英文名称 */
	private String comEName;

	/** 属性地址中文名称 */
	private String addressCName;

	/** 属性地址英文名称 */
	private String addressEName;

	/** 属性邮编 */
	private String postCode;

	/** 属性电话 */
	private String phoneNumber;

	/** 属性传真 */
	private String faxNumber;

	/** 属性机构代码 */
	private String upperComCode;

	/** 属性归属保险公司名称 */
	private String insurerName;

	/** 属性机构类型 */
	private String comType;

	/** 属性经理 */
	private String manager;

	/** 属性会计 */
	private String accountant;

	/** 属性备注 */
	private String remark;

	/** 属性最新机构代码 */
	private String newComCode;

	/** 属性效力状态 */
	private String validStatus;

	/** 属性账户归属机构代码 */
	private String acntUnit;

	/** 属性专项代码(对应会计科目) */
	private String articleCode;

	/** 属性标志 */
	private String flag;

	/** 属性创建时间（自动赋值、无需维护） */
	private Date insertTimeForHis;

	/** 属性操作时间（自动赋值、无需维护） */
	private Date operateTimeForHis;

	/** 属性prpDusers */
	private List<PrpDuser> prpDusers = new ArrayList<PrpDuser>(0);

	/**
	 * 类PrpDcompany的默认构造方法
	 */
	public PrpDcompany() {
	}

	/**
	 * 属性机构代码的getter方法
	 */
	@Id
	@Column(name = "COMCODE")
	public String getComCode() {
		return this.comCode;
	}

	/**
	 * 属性机构代码的setter方法
	 */
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	/**
	 * 属性机构中文名称的getter方法
	 */

	@Column(name = "COMCNAME")
	public String getComCName() {
		return this.comCName;
	}

	/**
	 * 属性机构中文名称的setter方法
	 */
	public void setComCName(String comCName) {
		this.comCName = comCName;
	}

	/**
	 * 属性机构英文名称的getter方法
	 */

	@Column(name = "COMENAME")
	public String getComEName() {
		return this.comEName;
	}

	/**
	 * 属性机构英文名称的setter方法
	 */
	public void setComEName(String comEName) {
		this.comEName = comEName;
	}

	/**
	 * 属性地址中文名称的getter方法
	 */

	@Column(name = "ADDRESSCNAME")
	public String getAddressCName() {
		return this.addressCName;
	}

	/**
	 * 属性地址中文名称的setter方法
	 */
	public void setAddressCName(String addressCName) {
		this.addressCName = addressCName;
	}

	/**
	 * 属性地址英文名称的getter方法
	 */

	@Column(name = "ADDRESSENAME")
	public String getAddressEName() {
		return this.addressEName;
	}

	/**
	 * 属性地址英文名称的setter方法
	 */
	public void setAddressEName(String addressEName) {
		this.addressEName = addressEName;
	}

	/**
	 * 属性邮编的getter方法
	 */

	@Column(name = "POSTCODE")
	public String getPostCode() {
		return this.postCode;
	}

	/**
	 * 属性邮编的setter方法
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * 属性电话的getter方法
	 */

	@Column(name = "PHONENUMBER")
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * 属性电话的setter方法
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 属性传真的getter方法
	 */

	@Column(name = "FAXNUMBER")
	public String getFaxNumber() {
		return this.faxNumber;
	}

	/**
	 * 属性传真的setter方法
	 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	/**
	 * 属性机构代码的getter方法
	 */

	@Column(name = "UPPERCOMCODE")
	public String getUpperComCode() {
		return this.upperComCode;
	}

	/**
	 * 属性机构代码的setter方法
	 */
	public void setUpperComCode(String upperComCode) {
		this.upperComCode = upperComCode;
	}

	/**
	 * 属性归属保险公司名称的getter方法
	 */

	@Column(name = "INSURERNAME")
	public String getInsurerName() {
		return this.insurerName;
	}

	/**
	 * 属性归属保险公司名称的setter方法
	 */
	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}

	/**
	 * 属性机构类型的getter方法
	 */

	@Column(name = "COMTYPE")
	public String getComType() {
		return this.comType;
	}

	/**
	 * 属性机构类型的setter方法
	 */
	public void setComType(String comType) {
		this.comType = comType;
	}

	/**
	 * 属性经理的getter方法
	 */

	@Column(name = "MANAGER")
	public String getManager() {
		return this.manager;
	}

	/**
	 * 属性经理的setter方法
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}

	/**
	 * 属性会计的getter方法
	 */

	@Column(name = "ACCOUNTANT")
	public String getAccountant() {
		return this.accountant;
	}

	/**
	 * 属性会计的setter方法
	 */
	public void setAccountant(String accountant) {
		this.accountant = accountant;
	}

	/**
	 * 属性备注的getter方法
	 */

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 属性备注的setter方法
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 属性最新机构代码的getter方法
	 */

	@Column(name = "NEWCOMCODE")
	public String getNewComCode() {
		return this.newComCode;
	}

	/**
	 * 属性最新机构代码的setter方法
	 */
	public void setNewComCode(String newComCode) {
		this.newComCode = newComCode;
	}

	/**
	 * 属性效力状态的getter方法
	 */

	@Column(name = "VALIDSTATUS")
	public String getValidStatus() {
		return this.validStatus;
	}

	/**
	 * 属性效力状态的setter方法
	 */
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

	/**
	 * 属性账户归属机构代码的getter方法
	 */

	@Column(name = "ACNTUNIT")
	public String getAcntUnit() {
		return this.acntUnit;
	}

	/**
	 * 属性账户归属机构代码的setter方法
	 */
	public void setAcntUnit(String acntUnit) {
		this.acntUnit = acntUnit;
	}

	/**
	 * 属性专项代码(对应会计科目)的getter方法
	 */

	@Column(name = "ARTICLECODE")
	public String getArticleCode() {
		return this.articleCode;
	}

	/**
	 * 属性专项代码(对应会计科目)的setter方法
	 */
	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode;
	}

	/**
	 * 属性标志的getter方法
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * 属性标志的setter方法
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 属性创建时间（自动赋值、无需维护）的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSERTTIMEFORHIS", insertable = false, updatable = false)
	public Date getInsertTimeForHis() {
		return this.insertTimeForHis;
	}

	/**
	 * 属性创建时间（自动赋值、无需维护）的setter方法
	 */
	public void setInsertTimeForHis(Date insertTimeForHis) {
		this.insertTimeForHis = insertTimeForHis;
	}

	/**
	 * 属性操作时间（自动赋值、无需维护）的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPERATETIMEFORHIS", insertable = false)
	public Date getOperateTimeForHis() {
		return this.operateTimeForHis;
	}

	/**
	 * 属性操作时间（自动赋值、无需维护）的setter方法
	 */
	public void setOperateTimeForHis(Date operateTimeForHis) {
		this.operateTimeForHis = operateTimeForHis;
	}

	/**
	 * 属性prpDusers的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "prpDcompany")
	public List<PrpDuser> getPrpDusers() {
		return this.prpDusers;
	}

	/**
	 * 属性prpDusers的setter方法
	 */
	public void setPrpDusers(List<PrpDuser> prpDusers) {
		this.prpDusers = prpDusers;
	}

}
