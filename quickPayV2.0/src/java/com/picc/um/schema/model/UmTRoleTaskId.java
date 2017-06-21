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
public class UmTRoleTaskId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTRoleTaskId() {
	}
	
	public UmTRoleTaskId(String roleTaskId) {
		this.roleTaskId = roleTaskId;
	}
	
	 
		private String roleTaskId;
		@Column(name = "RoleTaskId")
		public String getRoleTaskId() {
			return this.roleTaskId;
		}
		
		public void setRoleTaskId(String roleTaskId) {
			this.roleTaskId = roleTaskId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRoleTaskId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTRoleTaskId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTRoleTaskId other = (UmTRoleTaskId)obj;
		return new EqualsBuilder()
			.append(getRoleTaskId(),other.getRoleTaskId())
			.isEquals();
	}
}