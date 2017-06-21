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
public class UmTDictionaryDetailId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTDictionaryDetailId() {
	}
	
	
	public UmTDictionaryDetailId(String dictionaryDetailId){
		this.dictionaryDetailId = dictionaryDetailId;
	}
	
	 
		private String dictionaryDetailId;
		@Column(name = "DictionaryDetailId")
		public String getDictionaryDetailId() {
			return this.dictionaryDetailId;
		}
		
		public void setDictionaryDetailId(String dictionaryDetailId) {
			this.dictionaryDetailId = dictionaryDetailId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDictionaryDetailId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTDictionaryDetailId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTDictionaryDetailId other = (UmTDictionaryDetailId)obj;
		return new EqualsBuilder()
			.append(getDictionaryDetailId(),other.getDictionaryDetailId())
			.isEquals();
	}
}