package com.picc.qp.schema.model;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO类PrpDcodeId
 */
@Embeddable
public class QpTCOMDictionaryId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性代码类型 */
	private String configType;

	/** 属性业务代码 */
	private String code;

	/**
	 * 类PrpDcodeId的默认构造方法
	 */
	public QpTCOMDictionaryId() {
	}



	@Column(name = "configType")
	public String getConfigType() {
	    return configType;
	}

	public void setConfigType(String configType) {
	    this.configType = configType;
	}

	@Column(name = "code")
	public String getCode() {
	    return code;
	}

	public void setCode(String code) {
	    this.code = code;
	}
	
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if ((other == null)) {
			return false;
		}
		if (!(other instanceof QpTCOMDictionaryId)) {
			return false;
		}
		QpTCOMDictionaryId castOther = (QpTCOMDictionaryId) other;

		return ((this.getConfigType() == castOther.getConfigType()) || (this
				.getConfigType() != null && castOther.getConfigType() != null && this
				.getConfigType().equals(castOther.getConfigType())))
				&& ((this.getCode() == castOther.getCode()) || (this
						.getCode() != null
						&& castOther.getCode() != null && this
						.getCode().equals(castOther.getCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getConfigType() == null ? 0 : this.getConfigType().hashCode());
		result = 37 * result
				+ (getCode() == null ? 0 : this.getCode().hashCode());
		return result;
	}

}
