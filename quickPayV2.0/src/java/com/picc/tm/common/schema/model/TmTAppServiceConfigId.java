/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.tm.common.schema.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Embeddable
public class TmTAppServiceConfigId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public TmTAppServiceConfigId() {
	}
	
	 
		private String serverCode;
		@Column(name = "ServerCode")
		public String getServerCode() {
			return this.serverCode;
		}
		
		public void setServerCode(String serverCode) {
			this.serverCode = serverCode;
		}
	 
		private String environmentCode;
		@Column(name = "EnvironmentCode")
		public String getEnvironmentCode() {
			return this.environmentCode;
		}
		
		public void setEnvironmentCode(String environmentCode) {
			this.environmentCode = environmentCode;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getServerCode())
			.append(getEnvironmentCode())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmTAppServiceConfigId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		TmTAppServiceConfigId other = (TmTAppServiceConfigId)obj;
		return new EqualsBuilder()
			.append(getServerCode(),other.getServerCode())
			.append(getEnvironmentCode(),other.getEnvironmentCode())
			.isEquals();
	}
}