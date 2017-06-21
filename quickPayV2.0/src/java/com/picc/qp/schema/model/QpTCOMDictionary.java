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

@Entity
@Table(name = "Qp_T_COM_Dictionary")
public class QpTCOMDictionary implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	

	private QpTCOMDictionaryId id;
	private String value;
	private Date createtime;
	private String creator;
	private int valid;
	private String remark;
	
	/**
	 * 属性ID的getter方法
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "configType", column = @Column(name = "configType")),
			@AttributeOverride(name = "code", column = @Column(name = "code")) })
	public QpTCOMDictionaryId getId() {
		return this.id;
	}

	/**
	 * 属性ID的setter方法
	 */
	public void setId(QpTCOMDictionaryId id) {
		this.id = id;
	}
	
	@Column(name = "value")
	public String getValue() {
	    return value;
	}
	public void setValue(String value) {
	    this.value = value;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime")
	public Date getCreatetime() {
	    return createtime;
	}
	public void setCreatetime(Date createtime) {
	    this.createtime = createtime;
	}
	@Column(name = "creator")
	public String getCreator() {
	    return creator;
	}
	public void setCreator(String creator) {
	    this.creator = creator;
	}
	@Column(name = "valid")
	public int getValid() {
	    return valid;
	}
	public void setValid(int valid) {
	    this.valid = valid;
	}
	@Column(name = "remark")
	public String getRemark() {
	    return remark;
	}
	public void setRemark(String remark) {
	    this.remark = remark;
	}
	
	
}
