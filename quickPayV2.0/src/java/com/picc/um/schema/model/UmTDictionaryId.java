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
public class UmTDictionaryId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTDictionaryId() {
	}
	
	public UmTDictionaryId(String dictionaryId){
		this.dictionaryId = dictionaryId;
	}
	
	 
		private String dictionaryId;
		@Column(name = "DictionaryId")
		public String getDictionaryId() {
			return this.dictionaryId;
		}
		
		public void setDictionaryId(String dictionaryId) {
			this.dictionaryId = dictionaryId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDictionaryId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTDictionaryId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTDictionaryId other = (UmTDictionaryId)obj;
		return new EqualsBuilder()
			.append(getDictionaryId(),other.getDictionaryId())
			.isEquals();
	}
}