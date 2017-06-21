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
public class QpTICDocumentDetailId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public QpTICDocumentDetailId() {
	}
	
	 
		private String documentNo;
		@Column(name = "documentNo")
		public String getDocumentNo() {
			return this.documentNo;
		}
		
		public void setDocumentNo(String documentNo) {
			this.documentNo = documentNo;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDocumentNo())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QpTICDocumentDetailId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		QpTICDocumentDetailId other = (QpTICDocumentDetailId)obj;
		return new EqualsBuilder()
			.append(getDocumentNo(),other.getDocumentNo())
			.isEquals();
	}
}