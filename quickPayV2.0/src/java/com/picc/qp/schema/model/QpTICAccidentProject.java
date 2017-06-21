package com.picc.qp.schema.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author ff
 * 2016年10月18日
 */
@Entity
@Table(name = "Qp_T_IC_Accident_project")
public class QpTICAccidentProject implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	
	
	/**	构造函数	**/
	public QpTICAccidentProject() {
	}
	
//	/** 主键对象 **/
//	private QpTICAccidentProjectId id;
//	
//    @EmbeddedId
//    @AttributeOverrides( {
//      @AttributeOverride(name = "id", column = @Column(name = "id"))
//    })
//	public QpTICAccidentProjectId getId() {
//		return this.id;
//	}
//
//	public void setId(QpTICAccidentProjectId id) {
//		this.id = id;
//	}
	private int id;
	
	/** 案件id **/
	private String caseId;
	/**当事人id*/
	private String acciId;
	/**项目名称*/
	private String accidentProject;
	/**核定金额*/
	private Double accidentProjectMoney;
	/**类型 1:更换项目  2:修理项目*/
	private String type;
	/**创建时间**/
	private Date createTime;
	/**备注**/
	private String remark;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
	    return id;
	}

	public void setId(int id) {
	    this.id = id;
	}

	@Column(name = "caseId")
	public String getCaseId() {
	    return caseId;
	}

	public void setCaseId(String caseId) {
	    this.caseId = caseId;
	}

	@Column(name = "acciId")
	public String getAcciId() {
	    return acciId;
	}

	public void setAcciId(String acciId) {
	    this.acciId = acciId;
	}

	@Column(name = "accidentProject")
	public String getAccidentProject() {
	    return accidentProject;
	}

	public void setAccidentProject(String accidentProject) {
	    this.accidentProject = accidentProject;
	}

	@Column(name = "accidentProjectMoney")
	public Double getAccidentProjectMoney() {
	    return accidentProjectMoney;
	}

	public void setAccidentProjectMoney(Double accidentProjectMoney) {
	    this.accidentProjectMoney = accidentProjectMoney;
	}

	@Column(name = "type")
	public String getType() {
	    return type;
	}

	public void setType(String type) {
	    this.type = type;
	}

	@Column(name = "createTime")
	public Date getCreateTime() {
	    return createTime;
	}

	public void setCreateTime(Date createTime) {
	    this.createTime = createTime;
	}

	@Column(name = "remark")
	public String getRemark() {
	    return remark;
	}

	public void setRemark(String remark) {
	    this.remark = remark;
	}
}