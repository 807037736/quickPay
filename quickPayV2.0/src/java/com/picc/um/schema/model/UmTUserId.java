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
public class UmTUserId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTUserId() {
	}
	
	public UmTUserId(String userCode) {
		this.userCode= userCode;
	} 
		private String userCode;
		@Column(name = "userCode")
		public String getUserCode() {
			return this.userCode;
		}
		
		public void setUserCode(String userCode) {
			this.userCode = userCode;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserCode())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTUserId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTUserId other = (UmTUserId)obj;
		return new EqualsBuilder()
			.append(getUserCode(),other.getUserCode())
			.isEquals();
	}
}