/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.log.schema.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 日志类型ID
 * @author 杨联
 */
@Embeddable
public class LoGTTYPEId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public LoGTTYPEId() {
	}
	
	 
		private String operateTypeId;
		@Column(name = "OperateTypeId")
		public String getOperateTypeId() {
			return this.operateTypeId;
		}
		
		public void setOperateTypeId(String operateTypeId) {
			this.operateTypeId = operateTypeId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getOperateTypeId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LoGTTYPEId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		LoGTTYPEId other = (LoGTTYPEId)obj;
		return new EqualsBuilder()
			.append(getOperateTypeId(),other.getOperateTypeId())
			.isEquals();
	}
}