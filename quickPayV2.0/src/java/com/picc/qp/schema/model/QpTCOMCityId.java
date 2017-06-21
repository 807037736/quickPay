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
public class QpTCOMCityId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public QpTCOMCityId() {
	}
	
	 
		private String cityId;
		@Column(name = "CityId")
		public String getCityId() {
			return this.cityId;
		}
		
		public void setCityId(String cityId) {
			this.cityId = cityId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCityId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QpTCOMCityId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		QpTCOMCityId other = (QpTCOMCityId)obj;
		return new EqualsBuilder()
			.append(getCityId(),other.getCityId())
			.isEquals();
	}
}