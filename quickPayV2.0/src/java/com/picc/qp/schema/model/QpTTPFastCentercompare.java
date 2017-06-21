/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.schema.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "qp_t_tp_fast_centercompare")
public class QpTTPFastCentercompare implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_CENTER_ID = "中心ID";
	public static final String ALIAS_CENTER_NAME = "名称";
	public static final String ALIAS_CENTER_NUMBER = "序号";
	public static final String ALIAS_CENTER_PHONE = "电话";
	public static final String ALIAS_CENTER_NOTES = "备注";
	public static final String ALIAS_CITY_ID = "所属城市";
	public static final String ALIAS_CUR_YEAR = "年份";
	public static final String ALIAS_CUR_SERIAL_NO = "编码";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_UPDATER_CODE = "修改人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	
	
	/**	构造函数	**/
	public QpTTPFastCentercompare() {
	}
	
	/** 主键对象 **/
	private QpTTPFastCentercompareId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "compareid", column = @Column(name = "compareid"))
    })
	public QpTTPFastCentercompareId getId() {
		return this.id;
	}

	public void setId(QpTTPFastCentercompareId id) {
		this.compareid = id.getCompareid();
		this.id = id;
	}
	
	private String compareid;
	
	@Transient
  	public String getCompareid() {
		return compareid;
	}

	public void setCompareid(String compareid) {
		this.compareid = compareid;
	}

	/**属性*/
  	/** 用户名 **/
	private String userCode;
  	/** 快处中心ID **/
	private String centerId;
	/** 快处中心名称 **/
	private String centerName; 
  
	@Column(name = "usercode")
	public String getUserCode() {
		return userCode;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	@Column(name = "centerId")
	public String getCenterId() {
		return centerId;
	}
	
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	@Column(name = "centerName")
	public String getCenterName() {
		return this.centerName;
	}
	
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

}