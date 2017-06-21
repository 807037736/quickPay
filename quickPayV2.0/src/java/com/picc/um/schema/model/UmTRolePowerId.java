/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.schema.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Embeddable
public class UmTRolePowerId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTRolePowerId() {
	}
	
	public UmTRolePowerId(String rpId){
		this.rpId = rpId;
	}
	
	 
		private String rpId;
		@Column(name = "RpId")
		public String getRpId() {
			return this.rpId;
		}
		
		public void setRpId(String rpId) {
			this.rpId = rpId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRpId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTRolePowerId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTRolePowerId other = (UmTRolePowerId)obj;
		return new EqualsBuilder()
			.append(getRpId(),other.getRpId())
			.isEquals();
	}
}