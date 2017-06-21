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
public class QpTTPFastCenterId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public QpTTPFastCenterId() {
	}
	
	 
		private String centerId;
		@Column(name = "CenterId")
		public String getCenterId() {
			return this.centerId;
		}
		
		public void setCenterId(String centerId) {
			this.centerId = centerId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCenterId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QpTTPFastCenterId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		QpTTPFastCenterId other = (QpTTPFastCenterId)obj;
		return new EqualsBuilder()
			.append(getCenterId(),other.getCenterId())
			.isEquals();
	}
}