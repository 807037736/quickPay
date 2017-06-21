package com.picc.qp.schema.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Embeddable
public class QpTAsyncTaskDataId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public QpTAsyncTaskDataId() {
	}
	
	 
		private String taskDataId;
		@Column(name = "taskDataId")
		public String getTaskDataId() {
			return this.taskDataId;
		}
		
		public void setTaskDataId(String taskDataId) {
			this.taskDataId = taskDataId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getTaskDataId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QpTAsyncTaskDataId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		QpTAsyncTaskDataId other = (QpTAsyncTaskDataId)obj;
		return new EqualsBuilder()
			.append(getTaskDataId(),other.getTaskDataId())
			.isEquals();
	}
}