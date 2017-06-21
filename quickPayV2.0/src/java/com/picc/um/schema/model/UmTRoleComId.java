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
public class UmTRoleComId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTRoleComId() {
	}
	public UmTRoleComId(String roleComId) {
		this.roleComId = roleComId;
	}
	
	 
		private String roleComId;
		@Column(name = "RoleComId")
		public String getRoleComId() {
			return this.roleComId;
		}
		
		public void setRoleComId(String roleComId) {
			this.roleComId = roleComId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRoleComId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTRoleComId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTRoleComId other = (UmTRoleComId)obj;
		return new EqualsBuilder()
			.append(getRoleComId(),other.getRoleComId())
			.isEquals();
	}
}