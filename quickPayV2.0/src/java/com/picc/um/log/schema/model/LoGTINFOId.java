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
 * 日志信息对象ID
 * @author 杨联
 */
@Embeddable
public class LoGTINFOId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public LoGTINFOId() {
	}
	
	 
		private String logId;
		@Column(name = "LogId")
		public String getLogId() {
			if(logId!=null)
			{return this.logId;}
			long l=System.currentTimeMillis();
			String s=Long.toString(l); 
			s=s.substring(2, 12);
			return s;
			
		}
		
		public void setLogId(String logId) {
			this.logId = logId;
		}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getLogId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LoGTINFOId == false){
			return false;
		}
		if(this == obj){
			return true;
		}
		LoGTINFOId other = (LoGTINFOId)obj;
		return new EqualsBuilder()
			.append(getLogId(),other.getLogId())
			.isEquals();
	}
}