package com.picc.platform.dms.schema.model;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。
/**
 * @author liuyatao 2013-8-9
 */
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * POJO类mc_d_newcode
 */
@Entity
@Table(name = "mc_d_newcode")
public class MCDNewCode implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性ID */
	private MCDNewCodeId id;

	/** 属性业务代码中文含义 */
	private String codeCName;

	/** 属性业务代码英文含义 */
	private String codeEName;

	/** 上层业务代码 */
	private String upperCode;
	
	/** 旧的业务代码类型 */
	private String oldCodeType;
	
	/** 旧的业务代码 */
	private String oldCodeCode;
	
	/** 属性新的业务代码 */
	private String newCodeCode;
	
	/** 是否公开 */
	private String commonFlag;

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
	
	private Integer sort;
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 类的默认构造方法
	 */
	public MCDNewCode() {
	}

	/**
	 * 属性ID的getter方法
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codeType", column = @Column(name = "CODETYPE")),
			@AttributeOverride(name = "codeCode", column = @Column(name = "CODECODE")) })
	public MCDNewCodeId getId() {
		return this.id;
	}

	/**
	 * 属性ID的setter方法
	 */
	public void setId(MCDNewCodeId id) {
		this.id = id;
	}

	/**
	 * 属性业务代码中文含义的getter方法
	 */

	@Column(name = "CODECNAME")
	public String getCodeCName() {
		return this.codeCName;
	}

	/**
	 * 属性业务代码中文含义的setter方法
	 */
	public void setCodeCName(String codeCName) {
		this.codeCName = codeCName;
	}

	/**
	 * 属性业务代码英文含义的getter方法
	 */

	@Column(name = "CODEENAME")
	public String getCodeEName() {
		return this.codeEName;
	}

	/**
	 * 属性业务代码英文含义的setter方法
	 */
	public void setCodeEName(String codeEName) {
		this.codeEName = codeEName;
	}

	/**
	 * 属性新的业务代码的getter方法
	 */

	@Column(name = "NEWCODECODE")
	public String getNewCodeCode() {
		return this.newCodeCode;
	}

	/**
	 * 属性新的业务代码的setter方法
	 */
	public void setNewCodeCode(String newCodeCode) {
		this.newCodeCode = newCodeCode;
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
	@Column(name = "UPPERCODE")
	public String getUpperCode() {
		return upperCode;
	}

	public void setUpperCode(String upperCode) {
		this.upperCode = upperCode;
	}
	@Column(name = "OLDCODETYPE")
	public String getOldCodeType() {
		return oldCodeType;
	}

	public void setOldCodeType(String oldCodeType) {
		this.oldCodeType = oldCodeType;
	}
	@Column(name = "OLDCODECODE")
	public String getOldCodeCode() {
		return oldCodeCode;
	}

	public void setOldCodeCode(String oldCodeCode) {
		this.oldCodeCode = oldCodeCode;
	}
	@Column(name = "COMMONFLAG")
	public String getCommonFlag() {
		return commonFlag;
	}

	public void setCommonFlag(String commonFlag) {
		this.commonFlag = commonFlag;
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
