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
public class UmTGroupComId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTGroupComId() {
	}
	
	 
		private String groupComId;
		@Column(name = "GroupComId")
		public String getGroupComId() {
			return this.groupComId;
		}
		
		public void setGroupComId(String groupComId) {
			this.groupComId = groupComId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getGroupComId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTGroupComId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTGroupComId other = (UmTGroupComId)obj;
		return new EqualsBuilder()
			.append(getGroupComId(),other.getGroupComId())
			.isEquals();
	}
}