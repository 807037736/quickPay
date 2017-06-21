/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.log.schema.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 日志类型对象
 * @author 杨联
 */
@Entity
@Table(name = "LOG_T_TYPE")
public class LoGTTYPE implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_OPERATE_TYPE_ID = "操作类型ID";
	public static final String ALIAS_OPERATE_TYPE_E_NAME = "操作类型英文名";
	public static final String ALIAS_OPERATE_TYPE_C_NAME = "操作类型中文名";
	public static final String ALIAS_OPERATE_METHOD = "操作方法";
	public static final String ALIAS_COMID="省机构代码";
	
	/**	构造函数	**/
	public LoGTTYPE() {
	}
	
	/** 主键对象 **/
	private LoGTTYPEId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "operateTypeId", column = @Column(name = "operateTypeId"))
    })
	public LoGTTYPEId getId() {
		return this.id;
	}

	public void setId(LoGTTYPEId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 操作类型英文名 **/
	private String operateTypeEName;
  	/** 操作类型中文名 **/
	private String operateTypeCName;
  	/** 操作方法 **/
	private String operateMethod;
	/**省机构代码**/
	private String comid;
	
	/**getter setter方法*/
	@Column(name = "OperateTypeEName")
	public String getOperateTypeEName() {
		return this.operateTypeEName;
	}
	
	public void setOperateTypeEName(String operateTypeEName) {
		this.operateTypeEName = operateTypeEName;
	}
	@Column(name = "OperateTypeCName")
	public String getOperateTypeCName() {
		return this.operateTypeCName;
	}
	
	public void setOperateTypeCName(String operateTypeCName) {
		this.operateTypeCName = operateTypeCName;
	}
	@Column(name = "OperateMethod")
	public String getOperateMethod() {
		return this.operateMethod;
	}
	
	public void setOperateMethod(String operateMethod) {
		this.operateMethod = operateMethod;
	}
	@Column(name = "ComId")
	public String getComid() {
		return comid;
	}

	public void setComid(String comid) {
		this.comid = comid;
	}
		
}