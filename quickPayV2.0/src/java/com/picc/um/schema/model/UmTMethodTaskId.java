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
public class UmTMethodTaskId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTMethodTaskId() {
	}
	
	public UmTMethodTaskId(String methodId){
		this.methodId = methodId;
	}
	 
		private String methodId;
		@Column(name = "MethodId")
		public String getMethodId() {
			return this.methodId;
		}
		
		public void setMethodId(String methodId) {
			this.methodId = methodId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getMethodId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTMethodTaskId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTMethodTaskId other = (UmTMethodTaskId)obj;
		return new EqualsBuilder()
			.append(getMethodId(),other.getMethodId())
			.isEquals();
	}
}