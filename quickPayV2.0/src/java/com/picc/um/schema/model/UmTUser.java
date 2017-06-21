/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.schema.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
@Table(name = "um_t_user")
public class UmTUser implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_USER_CODE = "用户代码";
	public static final String ALIAS_USER_NAME = "用户名称";
	public static final String ALIAS_COM_CODE = "所属机构";
	public static final String ALIAS_TELE_PHONE = "电话号码";
	public static final String ALIAS_FAX_NUMBER = "传真号码";
	public static final String ALIAS_MOBILE = "手机号码";
	public static final String ALIAS_EMAIL = "邮件地址";
	public static final String ALIAS_POST_ADDRESS = "邮寄地址";
	public static final String ALIAS_UNIT = "工作单位";
	public static final String ALIAS_UNIT_ADDRESS = "单位地址";
	public static final String ALIAS_IMAGE = "用户头像保存路径";
	public static final String ALIAS_INTERESTS = "兴趣爱好";
	public static final String ALIAS_CREATOR_CODE = "创建人代码";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建日期";
	public static final String ALIAS_UPDATER_CODE = "更新人代码";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "更新日期";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_FLAG = "标志字段";
	public static final String ALIAS_USER_TYPE = "用户类型";
	public static final String ALIAS_USER_SORT = "用户分类";
	public static final String ALIAS_IDENTITY_NUMBER = "证件号码";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	public static final String ALIAS_SEX = "性别";
	public static final String ALIAS_BIRTHDAY = "生日";
	public static final String ALIAS_AGE = "年龄";
	public static final String ALIAS_COMID = "省机构代码";
	public static final String ALIAS_BIND_FLAG = " 用户绑定状态";
	public static final String ALIAS_SOURCE_FLAG = " 用户来源代码（区分内外部用户）";
	public static final String ALIAS_CUST_ID = " 绑定客户ID";
	
	/**	构造函数	**/
	public UmTUser() {
	}
	
	/** 主键对象 **/
	private UmTUserId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "userCode", column = @Column(name = "userCode"))
    })
	public UmTUserId getId() {
		return this.id;
	}

	public void setId(UmTUserId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 用户名称 **/
	private String userName;
  	/** 所属机构 **/
	private String comCode;
  	/** 电话号码 **/
	private String telePhone;
  	/** 传真号码 **/
	private String faxNumber;
  	/** 手机号码 **/
	private String mobile;
  	/** 邮件地址 **/
	private String email;
  	/** 邮寄地址 **/
	private String postAddress;
  	/** 工作单位 **/
	private String unit;
  	/** 单位地址 **/
	private String unitAddress;
  	/** 用户头像保存路径 **/
	private String image;
  	/** 兴趣爱好 **/
	private String interests;
  	/** 创建人代码 **/
	private String creatorCode;
  	/** 创建日期 **/
	private Date insertTimeForHis;
  	/** 更新人代码 **/
	private String updaterCode;
  	/** 更新日期 **/
	private Date operateTimeForHis;
  	/** 备注 **/
	private String remark;
  	/** 标志字段 **/
	private String flag;
  	/** 用户类型 _01:内部用户;02:外部用户**/
	private String userType;
  	/** 用户分类 **/
	private String userSort;
  	/** 证件号码 **/
	private String identityNumber;
  	/** 有效状态 **/
	private String validStatus;
  	/** 性别 **/
	private String sex;
  	/** 生日 **/
	private Date birthday;
  	/** 年龄 **/
	private BigDecimal age;	
	/** comId **/
	private String comId;
	/**  用户绑定状态 **/
	private String bindStatus;
  	/**  用户来源代码 **/
	private String sourceFlag;
  	/**  关联客户ID **/
	private String custId;
	
	/**  雇员工号 **/
    private String employeeId;
    /**  所属交警大队 **/
    private String policeTeamId;
    /**  所属受理点 **/
    private String centerId;
    /**  查勘员所属受理点ID **/
    private String surveyCenterId;
    /**  所属保险公司 **/
    private String coId;
    /**  省 **/
    private String province;
    /**  市 **/
    private String city;
    /**  区 **/
    private String district;
    /**  道路 **/
    private String road;
    /**  街道 **/
    private String street;
    /**  车牌号 **/
    private String licenseNo;
    
    /** 翻译字段 **/
    private String policeTeamName;
    private String centerName;
    private String provinceDesc;
    private String cityDesc;
    private String districtDesc;
    private String roadDesc;
    private String coName;
    
	/** 账户**/
	/*private UmTAccount umTAccount;*/
	/**getter setter方法*/
	@Column(name = "userName")
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "comCode")
	public String getComCode() {
		return this.comCode;
	}
	
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	@Column(name = "telePhone")
	public String getTelePhone() {
		return this.telePhone;
	}
	
	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}
	@Column(name = "faxNumber")
	public String getFaxNumber() {
		return this.faxNumber;
	}
	
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	@Column(name = "mobile")
	public String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "postAddress")
	public String getPostAddress() {
		return this.postAddress;
	}
	
	public void setPostAddress(String postAddress) {
		this.postAddress = postAddress;
	}
	@Column(name = "unit")
	public String getUnit() {
		return this.unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name = "unitAddress")
	public String getUnitAddress() {
		return this.unitAddress;
	}
	
	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}
	@Column(name = "image")
	public String getImage() {
		return this.image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	@Column(name = "interests")
	public String getInterests() {
		return this.interests;
	}
	
	public void setInterests(String interests) {
		this.interests = interests;
	}
	@Column(name = "creatorCode" , updatable = false)
	public String getCreatorCode() {
		return this.creatorCode;
	}
	
	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "insertTimeForHis" , updatable = false)
	public Date getInsertTimeForHis() {
		return this.insertTimeForHis;
	}
	
	public void setInsertTimeForHis(Date insertTimeForHis) {
		this.insertTimeForHis = insertTimeForHis;
	}
	@Column(name = "updaterCode" , insertable = false)
	public String getUpdaterCode() {
		return this.updaterCode;
	}
	
	public void setUpdaterCode(String updaterCode) {
		this.updaterCode = updaterCode;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "operateTimeForHis" , insertable = false)
	public Date getOperateTimeForHis() {
		return this.operateTimeForHis;
	}
	
	public void setOperateTimeForHis(Date operateTimeForHis) {
		this.operateTimeForHis = operateTimeForHis;
	}
	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "flag")
	public String getFlag() {
		return this.flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Column(name = "userType")
	public String getUserType() {
		return this.userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Column(name = "userSort")
	public String getUserSort() {
		return this.userSort;
	}
	
	public void setUserSort(String userSort) {
		this.userSort = userSort;
	}
	@Column(name = "identityNumber")
	public String getIdentityNumber() {
		return this.identityNumber;
	}
	
	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}
	@Column(name = "validStatus")
	public String getValidStatus() {
		return this.validStatus;
	}
	
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	@Column(name = "sex")
	public String getSex() {
		return this.sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "birthday")
	public Date getBirthday() {
		return this.birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Column(name = "age")
	public BigDecimal getAge() {
		return this.age;
	}
	
	public void setAge(BigDecimal age) {
		this.age = age;
	}
	@Column(name = "comId")
	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}
	@Column(name = "bindStatus")
	public String getBindStatus() {
		return this.bindStatus;
	}
	
	public void setBindStatus(String bindStatus) {
		this.bindStatus = bindStatus;
	}
	@Column(name = "SourceFlag")
	public String getSourceFlag() {
		return this.sourceFlag;
	}
	
	public void setSourceFlag(String sourceFlag) {
		this.sourceFlag = sourceFlag;
	}
	@Column(name = "CustId")
	public String getCustId() {
		return this.custId;
	}
	
	public void setCustId(String custId) {
		this.custId = custId;
	}
	private String usercode;
	@Transient
	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	@Column(name = "EmployeeId")
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Column(name = "PoliceTeamId")
    public String getPoliceTeamId() {
        return policeTeamId;
    }

    public void setPoliceTeamId(String policeTeamId) {
        this.policeTeamId = policeTeamId;
    }

    @Column(name = "CenterId")
    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "district")
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Column(name = "road")
    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    @Column(name = "street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "licenseno")
    public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	@Transient
    public String getPoliceTeamName() {
        return policeTeamName;
    }

    public void setPoliceTeamName(String policeTeamName) {
        this.policeTeamName = policeTeamName;
    }
    
    @Transient
    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    @Transient
    public String getProvinceDesc() {
        return provinceDesc;
    }

    public void setProvinceDesc(String provinceDesc) {
        this.provinceDesc = provinceDesc;
    }

    @Transient
    public String getCityDesc() {
        return cityDesc;
    }

    public void setCityDesc(String cityDesc) {
        this.cityDesc = cityDesc;
    }

    @Transient
    public String getDistrictDesc() {
        return districtDesc;
    }
    
    public void setDistrictDesc(String districtDesc) {
        this.districtDesc = districtDesc;
    }
    
    @Transient
    public String getRoadDesc() {
        return roadDesc;
    }

    public void setRoadDesc(String roadDesc) {
        this.roadDesc = roadDesc;
    }

	public String getCoId() {
		return coId;
	}

	public void setCoId(String coId) {
		this.coId = coId;
	}

	@Transient
	public String getCoName() {
		return coName;
	}

	public void setCoName(String coName) {
		this.coName = coName;
	}

	@Transient
	public String getSurveyCenterId() {
		return surveyCenterId;
	}

	public void setSurveyCenterId(String surveyCenterId) {
		this.surveyCenterId = surveyCenterId;
	}
	

    /*@OneToOne(cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn(name="userCode")
	public UmTAccount getUmTAccount() {
		return umTAccount;
	}

	public void setUmTAccount(UmTAccount umTAccount) {
		this.umTAccount = umTAccount;
	}	
	*/
}