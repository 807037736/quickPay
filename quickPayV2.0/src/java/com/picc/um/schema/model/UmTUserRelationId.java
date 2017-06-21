package com.picc.um.schema.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UmTUserRelationId implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/*
	*	构造函数
	*/
	public UmTUserRelationId() {
	}
	
	public UmTUserRelationId(String openid,String userid) {
		this.platId= openid;
		this.userId= userid;
	} 
		private String platId;
		private String userId;
		
		@Column(name = "platId")
		public String getPlatId() {
			return platId;
		}

		public void setPlatId(String platId) {
			this.platId = platId;
		}
		@Column(name = "userId")
		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((platId == null) ? 0 : platId.hashCode());
			result = prime * result
					+ ((userId == null) ? 0 : userId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			UmTUserRelationId other = (UmTUserRelationId) obj;
			if (platId == null) {
				if (other.platId != null)
					return false;
			} else if (!platId.equals(other.platId))
				return false;
			if (userId == null) {
				if (other.userId != null)
					return false;
			} else if (!userId.equals(other.userId))
				return false;
			return true;
		}
	 
 
 
}