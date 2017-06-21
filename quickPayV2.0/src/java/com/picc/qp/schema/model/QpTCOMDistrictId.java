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
public class QpTCOMDistrictId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public QpTCOMDistrictId() {
	}
	
	 
		private String districtId;
		@Column(name = "DistrictId")
		public String getDistrictId() {
			return this.districtId;
		}
		
		public void setDistrictId(String districtId) {
			this.districtId = districtId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDistrictId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QpTCOMDistrictId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		QpTCOMDistrictId other = (QpTCOMDistrictId)obj;
		return new EqualsBuilder()
			.append(getDistrictId(),other.getDistrictId())
			.isEquals();
	}
}