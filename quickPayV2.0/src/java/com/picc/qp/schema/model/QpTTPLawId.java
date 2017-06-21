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
public class QpTTPLawId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public QpTTPLawId() {
	}
	
	 
		private String lawId;
		@Column(name = "LawId")
		public String getLawId() {
			return this.lawId;
		}
		
		public void setLawId(String lawId) {
			this.lawId = lawId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getLawId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QpTTPLawId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		QpTTPLawId other = (QpTTPLawId)obj;
		return new EqualsBuilder()
			.append(getLawId(),other.getLawId())
			.isEquals();
	}
}