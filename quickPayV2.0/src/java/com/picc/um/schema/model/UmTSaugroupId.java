/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.schema.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Embeddable
public class UmTSaugroupId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTSaugroupId() {
	}
	public UmTSaugroupId(String teamId) {
		this.teamID= teamId;
	} 
	 
		private String teamID;
		@Column(name = "teamID")
		public String getTeamID() {
			return this.teamID;
		}
		
		public void setTeamID(String teamID) {
			this.teamID = teamID;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getTeamID())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UmTSaugroupId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		UmTSaugroupId other = (UmTSaugroupId)obj;
		return new EqualsBuilder()
			.append(getTeamID(),other.getTeamID())
			.isEquals();
	}
}