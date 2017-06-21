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
public class UmTUserPowerId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTUserPowerId() {
	}
	
	 
		private String userPowerId;
		@Column(name = "UserPowerId")
		public String getUserPowerId() {
			return this.userPowerId;
		}
		
		public void setUserPowerId(String userPowerId) {
			this.userPowerId = userPowerId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserPowerId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTUserPowerId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTUserPowerId other = (UmTUserPowerId)obj;
		return new EqualsBuilder()
			.append(getUserPowerId(),other.getUserPowerId())
			.isEquals();
	}
}