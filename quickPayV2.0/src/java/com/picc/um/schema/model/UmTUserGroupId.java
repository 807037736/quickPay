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
public class UmTUserGroupId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTUserGroupId() {
	}
	
	 
		private String userGroupId;
		@Column(name = "UserGroupId")
		public String getUserGroupId() {
			return this.userGroupId;
		}
		
		public void setUserGroupId(String userGroupId) {
			this.userGroupId = userGroupId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserGroupId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTUserGroupId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTUserGroupId other = (UmTUserGroupId)obj;
		return new EqualsBuilder()
			.append(getUserGroupId(),other.getUserGroupId())
			.isEquals();
	}
}