/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.schema.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Embeddable
public class QpTTPPoliceId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public QpTTPPoliceId() {
	}
	
	 
		private String policeId;
		@Column(name = "PoliceId")
		public String getPoliceId() {
			return this.policeId;
		}
		
		public void setPoliceId(String policeId) {
			this.policeId = policeId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPoliceId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QpTTPPoliceId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		QpTTPPoliceId other = (QpTTPPoliceId)obj;
		return new EqualsBuilder()
			.append(getPoliceId(),other.getPoliceId())
			.isEquals();
	}
}