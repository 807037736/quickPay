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
public class UmTRoleDimeId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTRoleDimeId() {
	}
	
	 
		private String roleDimeId;
		@Column(name = "RoleDimeId")
		public String getRoleDimeId() {
			return this.roleDimeId;
		}
		
		public void setRoleDimeId(String roleDimeId) {
			this.roleDimeId = roleDimeId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRoleDimeId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTRoleDimeId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTRoleDimeId other = (UmTRoleDimeId)obj;
		return new EqualsBuilder()
			.append(getRoleDimeId(),other.getRoleDimeId())
			.isEquals();
	}
}