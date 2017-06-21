package com.picc.um.schema.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Embeddable
public class UmTUserBoundId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	public UmTUserBoundId(){
	}
	
	public UmTUserBoundId(Integer boundId){
		this.boundId = boundId;
	}
	
	private Integer boundId;
	@Column(name = "boundId")
	public Integer getBoundId() {
		return boundId;
	}

	public void setBoundId(Integer boundId) {
		this.boundId = boundId;
	}
	
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getBoundId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTUserBoundId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTUserBoundId other = (UmTUserBoundId)obj;
		return new EqualsBuilder()
			.append(getBoundId(),other.getBoundId())
			.isEquals();
	}
}
