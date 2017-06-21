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
public class WfTPortletclassfyId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public WfTPortletclassfyId() {
	}
	
	 
		private String portletid;
		@Column(name = "portletid")
		public String getPortletid() {
			return this.portletid;
		}
		
		public void setPortletid(String portletid) {
			this.portletid = portletid;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPortletid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WfTPortletclassfyId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		WfTPortletclassfyId other = (WfTPortletclassfyId)obj;
		return new EqualsBuilder()
			.append(getPortletid(),other.getPortletid())
			.isEquals();
	}
}