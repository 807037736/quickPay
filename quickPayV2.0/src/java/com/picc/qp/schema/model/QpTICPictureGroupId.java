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
public class QpTICPictureGroupId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public QpTICPictureGroupId() {
	}
	
	 
		private String groupId;
		@Column(name = "groupId")
		public String getGroupId() {
			return this.groupId;
		}
		
		public void setGroupId(String groupId) {
			this.groupId = groupId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getGroupId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QpTICPictureGroupId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		QpTICPictureGroupId other = (QpTICPictureGroupId)obj;
		return new EqualsBuilder()
			.append(getGroupId(),other.getGroupId())
			.isEquals();
	}
}