package com.picc.qp.schema.model;

import javax.persistence.Column;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 微信临时案件信息
 * 
 * @author ff 2016年7月4日
 */
public class WxCaseId implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    /*
     * 构造函数
     */
    public WxCaseId() {
    }

    private String id;

    @Column(name = "id")
    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public int hashCode() {
	return new HashCodeBuilder().append(getId()).toHashCode();
    }

    public boolean equals(Object obj) {
	if (obj instanceof WxCaseId == false) {
	    return false;
	}
	if (this == obj) {
	    return true;
	}
	WxCaseId other = (WxCaseId) obj;
	return new EqualsBuilder().append(getId(), other.getId()).isEquals();
    }

}
