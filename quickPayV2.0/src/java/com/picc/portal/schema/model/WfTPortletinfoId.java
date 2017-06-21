/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.portal.schema.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Embeddable
public class WfTPortletinfoId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public WfTPortletinfoId() {
	}
	
	 
		private String id;
		@Column(name = "id")
		public String getId() {
			return this.id;
		}
		
		public void setId(String id) {
			this.id = id;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WfTPortletinfoId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		WfTPortletinfoId other = (WfTPortletinfoId)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}