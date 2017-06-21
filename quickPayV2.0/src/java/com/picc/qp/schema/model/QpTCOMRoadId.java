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
public class QpTCOMRoadId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public QpTCOMRoadId() {
	}
	
	 
		private String roadId;
		@Column(name = "RoadId")
		public String getRoadId() {
			return this.roadId;
		}
		
		public void setRoadId(String roadId) {
			this.roadId = roadId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRoadId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QpTCOMRoadId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		QpTCOMRoadId other = (QpTCOMRoadId)obj;
		return new EqualsBuilder()
			.append(getRoadId(),other.getRoadId())
			.isEquals();
	}
}