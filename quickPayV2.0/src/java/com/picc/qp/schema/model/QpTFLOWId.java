/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2016
 */

package com.picc.qp.schema.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Embeddable
public class QpTFLOWId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public QpTFLOWId() {
	}
	
	 
		private String flowId;
		@Column(name = "flowId")
		public String getFlowId() {
			return this.flowId;
		}
		
		public void setFlowId(String flowId) {
			this.flowId = flowId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getFlowId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QpTFLOWId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		QpTFLOWId other = (QpTFLOWId)obj;
		return new EqualsBuilder()
			.append(getFlowId(),other.getFlowId())
			.isEquals();
	}
}