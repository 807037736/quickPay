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
public class UmTSMSValidId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTSMSValidId() {
	}
	
	 
		private String smsId;
		@Column(name = "SmsId")
		public String getSmsId() {
			return this.smsId;
		}
		
		public void setSmsId(String smsId) {
			this.smsId = smsId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getSmsId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTSMSValidId == false) return false;
		if(this == obj) return true;
		UmTSMSValidId other = (UmTSMSValidId)obj;
		return new EqualsBuilder()
			.append(getSmsId(),other.getSmsId())
			.isEquals();
	}
}