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
public class UmTMENUId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTMENUId() {
	}
	
	public UmTMENUId(String menuId){
		this.menuId = menuId;
	}
	
	 
		private String menuId;
		@Column(name = "MenuId")
		public String getMenuId() {
			return this.menuId;
		}
		
		public void setMenuId(String menuId) {
			this.menuId = menuId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getMenuId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTMENUId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTMENUId other = (UmTMENUId)obj;
		return new EqualsBuilder()
			.append(getMenuId(),other.getMenuId())
			.isEquals();
	}
}