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
public class UmTRoleId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTRoleId() {
	}
	
	 
	public UmTRoleId(String roleId) {
		this.roleId = roleId;
	}


		private String roleId;
		@Column(name = "RoleId")
		public String getRoleId() {
			return this.roleId;
		}
		
		public void setRoleId(String roleId) {
			this.roleId = roleId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRoleId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTRoleId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTRoleId other = (UmTRoleId)obj;
		return new EqualsBuilder()
			.append(getRoleId(),other.getRoleId())
			.isEquals();
	}
}