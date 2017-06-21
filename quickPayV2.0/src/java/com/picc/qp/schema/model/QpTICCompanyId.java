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
public class QpTICCompanyId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public QpTICCompanyId() {
	}
	
	 
		private String coId;
		@Column(name = "CoId")
		public String getCoId() {
			return this.coId;
		}
		
		public void setCoId(String coId) {
			this.coId = coId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCoId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QpTICCompanyId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		QpTICCompanyId other = (QpTICCompanyId)obj;
		return new EqualsBuilder()
			.append(getCoId(),other.getCoId())
			.isEquals();
	}
}