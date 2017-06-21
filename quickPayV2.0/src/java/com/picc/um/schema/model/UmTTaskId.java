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
public class UmTTaskId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTTaskId(String taskId){
		this.taskId  = taskId;
	}
	public UmTTaskId() {
	}
	
	 
		private String taskId;
		@Column(name = "TaskId")
		public String getTaskId() {
			return this.taskId;
		}
		
		public void setTaskId(String taskId) {
			this.taskId = taskId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getTaskId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTTaskId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTTaskId other = (UmTTaskId)obj;
		return new EqualsBuilder()
			.append(getTaskId(),other.getTaskId())
			.isEquals();
	}
}