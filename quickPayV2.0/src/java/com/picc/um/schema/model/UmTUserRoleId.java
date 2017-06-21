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
public class UmTUserRoleId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTUserRoleId() {
	}
	
	public UmTUserRoleId(String userRoleId){
		this.userRoleId = userRoleId;
	}

	private String userRoleId;
		@Column(name = "UserRoleId")
		public String getUserRoleId() {
			return this.userRoleId;
		}
		
		public void setUserRoleId(String userRoleId) {
			this.userRoleId = userRoleId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserRoleId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTUserRoleId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTUserRoleId other = (UmTUserRoleId)obj;
		return new EqualsBuilder()
			.append(getUserRoleId(),other.getUserRoleId())
			.isEquals();
	}
}