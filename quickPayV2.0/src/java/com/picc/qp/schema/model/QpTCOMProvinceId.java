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
public class QpTCOMProvinceId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public QpTCOMProvinceId() {
	}
	
	 
		private String provId;
		@Column(name = "ProvId")
		public String getProvId() {
			return this.provId;
		}
		
		public void setProvId(String provId) {
			this.provId = provId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getProvId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QpTCOMProvinceId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		QpTCOMProvinceId other = (QpTCOMProvinceId)obj;
		return new EqualsBuilder()
			.append(getProvId(),other.getProvId())
			.isEquals();
	}
}