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
public class QpTIcCompanyGarageId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public QpTIcCompanyGarageId() {
	}
	
	 
		private int id;
		
		@Column(name = "id")
		public int getId() {
			return this.id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QpTIcCompanyGarageId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		QpTIcCompanyGarageId other = (QpTIcCompanyGarageId)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}