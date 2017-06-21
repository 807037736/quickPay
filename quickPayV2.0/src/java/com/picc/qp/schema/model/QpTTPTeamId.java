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
public class QpTTPTeamId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public QpTTPTeamId() {
	}
	
	 
		private String teamId;
		@Column(name = "TeamId")
		public String getTeamId() {
			return this.teamId;
		}
		
		public void setTeamId(String teamId) {
			this.teamId = teamId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getTeamId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QpTTPTeamId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		QpTTPTeamId other = (QpTTPTeamId)obj;
		return new EqualsBuilder()
			.append(getTeamId(),other.getTeamId())
			.isEquals();
	}
}