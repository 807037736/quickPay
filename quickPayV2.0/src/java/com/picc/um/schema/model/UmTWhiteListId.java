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
public class UmTWhiteListId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTWhiteListId() {
	}
	
	public UmTWhiteListId(String wlId){
		this.wlId = wlId;
	}
	
	 
		private String wlId;
		@Column(name = "WlId")
		public String getWlId() {
			return this.wlId;
		}
		
		public void setWlId(String wlId) {
			this.wlId = wlId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getWlId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTWhiteListId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTWhiteListId other = (UmTWhiteListId)obj;
		return new EqualsBuilder()
			.append(getWlId(),other.getWlId())
			.isEquals();
	}
}