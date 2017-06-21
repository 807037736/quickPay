/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2014
 */

package com.picc.tm.schema.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Embeddable
public class TmTApplicationConfigId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public TmTApplicationConfigId() {
	}
	
	 
		private String serverCode;
		@Column(name = "ServerCode")
		public String getServerCode() {
			return this.serverCode;
		}
		
		public void setServerCode(String serverCode) {
			this.serverCode = serverCode;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getServerCode())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmTApplicationConfigId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		TmTApplicationConfigId other = (TmTApplicationConfigId)obj;
		return new EqualsBuilder()
			.append(getServerCode(),other.getServerCode())
			.isEquals();
	}
}