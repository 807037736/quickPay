/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.um.schema.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Embeddable
public class UmTUserHistoryId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTUserHistoryId() {
	}
	
	 
		private String usercode;
		@Column(name = "USERCODE")
		public String getUsercode() {
			return this.usercode;
		}
		
		public void setUsercode(String usercode) {
			this.usercode = usercode;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUsercode())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTUserHistoryId == false) return false;
		if(this == obj) return true;
		UmTUserHistoryId other = (UmTUserHistoryId)obj;
		return new EqualsBuilder()
			.append(getUsercode(),other.getUsercode())
			.isEquals();
	}
}