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
public class QpTTPFastCentercompareId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public QpTTPFastCentercompareId() {
	}
	
	 
		private String compareid;
		@Column(name = "compareid")
		public String getCompareid() {
			return compareid;
		}

		public void setCompareid(String compareid) {
			this.compareid = compareid;
		}
		
		
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCompareid())
			.toHashCode();
	}
	


	public boolean equals(Object obj) {
		if(obj instanceof QpTTPFastCentercompareId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		QpTTPFastCentercompareId other = (QpTTPFastCentercompareId)obj;
		return new EqualsBuilder()
			.append(getCompareid(),other.getCompareid())
			.isEquals();
	}
}