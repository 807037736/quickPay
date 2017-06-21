/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.log.schema.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 日志信息对象实体
 * @author 杨联
 */
@Entity
@Table(name = "LOG_T_INFO")
public class LoGTINFO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_LOG_ID = "日志ID";
	public static final String ALIAS_OPERATE_TYPE_ID = "操作类型ID";
	public static final String ALIAS_USER_CODE = "用户代码";
	public static final String ALIAS_USER_NAME = "用户名称";
	public static final String ALIAS_OPERATE_TIME = "操作时间";
	public static final String ALIAS_OPERATE_TYPE_NAME = "操作类型名称";
	public static final String ALIAS_OPERATE_METHOD = "操作方法";
	public static final String ALIAS_OPERATE_RESULT = "操作结果";
	public static final String ALIAS_COMID="省机构代码";
	
	/**	构造函数	**/
	public LoGTINFO() {
	}
	
	/** 主键对象 **/
	private LoGTINFOId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "logId", column = @Column(name = "logId"))
    })
	public LoGTINFOId getId() {
    	
		return this.id;
	}

	public void setId(LoGTINFOId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 操作类型ID **/
	private String operateTypeId;
  	/** 用户代码 **/
	private String userCode;
  	/** 用户名称 **/
	private String userName;
  	/** 操作时间 **/
	private Date operateTime;
  	/** 操作类型名称 **/
	private String operateTypeName;
  	/** 操作方法 **/
	private String operateMethod;
  	/** 操作结果 **/
	private String operateResult;
	/**IP**/
	private String ip;
	/**省机构代码**/
	private String comid;
	
	/**getter setter方法*/
	@Column(name = "OperateTypeId")
	public String getOperateTypeId() {
		return this.operateTypeId;
	}
	
	public void setOperateTypeId(String operateTypeId) {
		this.operateTypeId = operateTypeId;
	}
	@Column(name = "UserCode")
	public String getUserCode() {
		return this.userCode;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	@Column(name = "UserName")
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OperateTime")
	public Date getOperateTime() {
		return this.operateTime;
	}
	
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	@Column(name = "OperateTypeName")
	public String getOperateTypeName() {
		return this.operateTypeName;
	}
	
	public void setOperateTypeName(String operateTypeName) {
		this.operateTypeName = operateTypeName;
	}
	@Column(name = "OperateMethod")
	public String getOperateMethod() {
		return this.operateMethod;
	}
	
	public void setOperateMethod(String operateMethod) {
		this.operateMethod = operateMethod;
	}
	@Column(name = "OperateResult")
	public String getOperateResult() {
		return this.operateResult;
	}
	
	public void setOperateResult(String operateResult) {
		this.operateResult = operateResult;
	}
	@Column(name = "IP")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Column(name = "ComId")
	public String getComid() {
		return comid;
	}

	public void setComid(String comid) {
		this.comid = comid;
	}
	
		
}