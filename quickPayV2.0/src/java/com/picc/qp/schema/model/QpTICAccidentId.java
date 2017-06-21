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
public class QpTICAccidentId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public QpTICAccidentId() {
	}
	
	 
		private String acciId;
		@Column(name = "AcciId")
		public String getAcciId() {
			return this.acciId;
		}
		
		public void setAcciId(String acciId) {
			this.acciId = acciId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getAcciId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QpTICAccidentId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		QpTICAccidentId other = (QpTICAccidentId)obj;
		return new EqualsBuilder()
			.append(getAcciId(),other.getAcciId())
			.isEquals();
	}
}