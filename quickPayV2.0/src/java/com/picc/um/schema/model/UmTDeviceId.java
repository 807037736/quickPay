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
public class UmTDeviceId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTDeviceId() {
	}
	
	 
		private String deviceId;
		@Column(name = "deviceId")
		public String getDeviceId() {
			return this.deviceId;
		}
		
		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDeviceId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTDeviceId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTDeviceId other = (UmTDeviceId)obj;
		return new EqualsBuilder()
			.append(getDeviceId(),other.getDeviceId())
			.isEquals();
	}
}