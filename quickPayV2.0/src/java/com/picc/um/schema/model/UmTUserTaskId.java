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
public class UmTUserTaskId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTUserTaskId() {
	}
	
	public UmTUserTaskId(String userTaskId) {
		this.userTaskId = userTaskId;
	}
	
	 
		private String userTaskId;
		@Column(name = "UserTaskId")
		public String getUserTaskId() {
			return this.userTaskId;
		}
		
		public void setUserTaskId(String userTaskId) {
			this.userTaskId = userTaskId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserTaskId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTUserTaskId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTUserTaskId other = (UmTUserTaskId)obj;
		return new EqualsBuilder()
			.append(getUserTaskId(),other.getUserTaskId())
			.isEquals();
	}
}