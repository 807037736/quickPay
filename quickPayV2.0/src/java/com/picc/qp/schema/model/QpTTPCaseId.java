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
public class QpTTPCaseId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public QpTTPCaseId() {
	}
	
	 
		private String caseId;
		@Column(name = "CaseId")
		public String getCaseId() {
			return this.caseId;
		}
		
		public void setCaseId(String caseId) {
			this.caseId = caseId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCaseId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QpTTPCaseId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		QpTTPCaseId other = (QpTTPCaseId)obj;
		return new EqualsBuilder()
			.append(getCaseId(),other.getCaseId())
			.isEquals();
	}
}