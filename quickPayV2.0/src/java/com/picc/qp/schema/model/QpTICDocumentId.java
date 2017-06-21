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
public class QpTICDocumentId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public QpTICDocumentId() {
	}
	
	 
		private String batchNo;
		@Column(name = "batchNo")
		public String getBatchNo() {
			return this.batchNo;
		}
		
		public void setBatchNo(String batchNo) {
			this.batchNo = batchNo;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getBatchNo())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QpTICDocumentId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		QpTICDocumentId other = (QpTICDocumentId)obj;
		return new EqualsBuilder()
			.append(getBatchNo(),other.getBatchNo())
			.isEquals();
	}
}