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
public class UmTSauuserId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTSauuserId() {
	}
	
	 
		private String saleUserId;
		@Column(name = "saleUserId")
		public String getSaleUserId() {
			return this.saleUserId;
		}
		
		public void setSaleUserId(String saleUserId) {
			this.saleUserId = saleUserId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getSaleUserId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTSauuserId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTSauuserId other = (UmTSauuserId)obj;
		return new EqualsBuilder()
			.append(getSaleUserId(),other.getSaleUserId())
			.isEquals();
	}
}