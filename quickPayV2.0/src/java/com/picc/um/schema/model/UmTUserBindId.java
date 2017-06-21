/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2014
 */

package com.picc.um.schema.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Embeddable
public class UmTUserBindId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTUserBindId() {
	}
	
	 
		private String bindId;
		@Column(name = "BindId")
		public String getBindId() {
			return this.bindId;
		}
		
		public void setBindId(String bindId) {
			this.bindId = bindId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getBindId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTUserBindId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTUserBindId other = (UmTUserBindId)obj;
		return new EqualsBuilder()
			.append(getBindId(),other.getBindId())
			.isEquals();
	}
}