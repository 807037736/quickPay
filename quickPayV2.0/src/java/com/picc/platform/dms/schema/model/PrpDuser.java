package com.picc.platform.dms.schema.model;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * POJO类PrpDuser
 */
@Entity
@Table(name = "PRPDUSER")
public class PrpDuser implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性员工代码 */
	private String userCode;

	/** 属性PrpDcompany--机构代码表 */
	private PrpDcompany prpDcompany;

	/** 属性员工名称 */
	private String userName;

	/** 属性密码 */
	private String password;

	/** 属性密码设置日期 */
	private Date passwdSetDate;

	/** 属性密码过期日期 */
	private Date passwdExpireDate;

	/** 属性最新员工代码 */
	private String newUserCode;

	/** 属性效力状态 */
	private String validStatus;

	/** 属性专项代码(对应会计科目) */
	private String articleCode;

	/** 属性标志 */
	private String flag;

	/** 属性创建时间（自动赋值、无需维护） */
	private Date insertTimeForHis;

	/** 属性操作时间（自动赋值、无需维护） */
	private Date operateTimeForHis;

	/**
	 * 类PrpDuser的默认构造方法
	 */
	public PrpDuser() {
	}

	/**
	 * 属性员工代码的getter方法
	 */
	@Id
	@Column(name = "USERCODE")
	public String getUserCode() {
		return this.userCode;
	}

	/**
	 * 属性员工代码的setter方法
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * 属性PrpDcompany--机构代码表的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMCODE", nullable = false)
	public PrpDcompany getPrpDcompany() {
		return this.prpDcompany;
	}

	/**
	 * 属性PrpDcompany--机构代码表的setter方法
	 */
	public void setPrpDcompany(PrpDcompany prpDcompany) {
		this.prpDcompany = prpDcompany;
	}

	/**
	 * 属性员工名称的getter方法
	 */

	@Column(name = "USERNAME")
	public String getUserName() {
		return this.userName;
	}

	/**
	 * 属性员工名称的setter方法
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 属性密码的getter方法
	 */

	@Column(name = "PASSWORD")
	public String getPassword() {
		return this.password;
	}

	/**
	 * 属性密码的setter方法
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 属性密码设置日期的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PASSWDSETDATE")
	public Date getPasswdSetDate() {
		return this.passwdSetDate;
	}

	/**
	 * 属性密码设置日期的setter方法
	 */
	public void setPasswdSetDate(Date passwdSetDate) {
		this.passwdSetDate = passwdSetDate;
	}

	/**
	 * 属性密码过期日期的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PASSWDEXPIREDATE")
	public Date getPasswdExpireDate() {
		return this.passwdExpireDate;
	}

	/**
	 * 属性密码过期日期的setter方法
	 */
	public void setPasswdExpireDate(Date passwdExpireDate) {
		this.passwdExpireDate = passwdExpireDate;
	}

	/**
	 * 属性最新员工代码的getter方法
	 */

	@Column(name = "NEWUSERCODE")
	public String getNewUserCode() {
		return this.newUserCode;
	}

	/**
	 * 属性最新员工代码的setter方法
	 */
	public void setNewUserCode(String newUserCode) {
		this.newUserCode = newUserCode;
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

}
