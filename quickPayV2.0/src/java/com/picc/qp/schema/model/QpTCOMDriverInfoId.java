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
public class QpTCOMDriverInfoId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public QpTCOMDriverInfoId() {
	}
	
	 
		private String driverInfoId;
		@Column(name = "DriverInfoId")
		public String getDriverInfoId() {
			return this.driverInfoId;
		}
		
		public void setDriverInfoId(String driverInfoId) {
			this.driverInfoId = driverInfoId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDriverInfoId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QpTCOMDriverInfoId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		QpTCOMDriverInfoId other = (QpTCOMDriverInfoId)obj;
		return new EqualsBuilder()
			.append(getDriverInfoId(),other.getDriverInfoId())
			.isEquals();
	}
}