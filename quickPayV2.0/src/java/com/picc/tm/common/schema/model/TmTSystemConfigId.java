/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2014
 */

package com.picc.tm.common.schema.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Embeddable
public class TmTSystemConfigId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public TmTSystemConfigId() {
	}
	
	 
		private String configId;
		@Column(name = "configId")
		public String getConfigId() {
			return this.configId;
		}
		
		public void setConfigId(String configId) {
			this.configId = configId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getConfigId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TmTSystemConfigId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		TmTSystemConfigId other = (TmTSystemConfigId)obj;
		return new EqualsBuilder()
			.append(getConfigId(),other.getConfigId())
			.isEquals();
	}
}