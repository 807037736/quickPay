package com.picc.platform.dms.schema.model;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。
/**
 * @author liuyatao 2013-8-9
 */
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * POJO类mc_d_newcode
 */
@Entity
@Table(name = "mc_d_type")
public class MCDType implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性ID */
	private String codeType;

	/** 属性业务代码中文含义 */
	private String codeTypeDesc;

	/** 属性业务代码英文含义 */
	private String newCodeType;

	/** 属性效力状态 */
	private String validStatus;

	/** 属性标志 */
	private String flag;

	/** 有效日期 */
	private Date validDate;

	/** 失效日期 */
	private Date invalidDate;
	
	/** 数据来源 */
	private String dataSource;
	
	/**
	 * 类的默认构造方法
	 */
	public MCDType() {
	}
	
	@Id
	@Column(name = "CODETYPE")
	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	@Column(name = "CODETYPEDESC")
	public String getCodeTypeDesc() {
		return codeTypeDesc;
	}

	public void setCodeTypeDesc(String codeTypeDesc) {
		this.codeTypeDesc = codeTypeDesc;
	}
	@Column(name = "NEWCODETYPE")
	public String getNewCodeType() {
		return newCodeType;
	}

	public void setNewCodeType(String newCodeType) {
		this.newCodeType = newCodeType;
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
	 * 属性标志的getter方法
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	@Column(name = "DATASOURCE")
	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	/**
	 * 属性标志的setter方法
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 属性有效时间（自动赋值、无需维护）的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VALIDDATE")
	public Date getValidDate() {
		return this.validDate;
	}

	/**
	 * 属性操作时间（自动赋值、无需维护）的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INVALIDDATE")
	public Date getInvalidDate() {
		return this.invalidDate;
	}
}
