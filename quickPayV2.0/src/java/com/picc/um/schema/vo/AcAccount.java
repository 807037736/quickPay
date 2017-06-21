package com.picc.um.schema.vo;

import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

import com.sinosoft.bpsdriver.domain.getUserMsg.UserMsgResInfo;
import com.sinosoft.bpsdriver.service.spring.UserMgrAPIServiceImpl;
import com.sinosoft.bpsdriver.util.SystemCode;
import com.sinosoft.dmsdriver.model.PrpDcompany;
import com.sinosoft.dmsdriver.service.server.PageService;

public class AcAccount {
	
	protected final Logger logger = (Logger) LoggerFactory.getLogger(this
			.getClass());
	
	private List<String> comCodeList;							//允许机构
	
	private List<String> exceptComCodeList;					//除外机构
	
	private List<String> gradeCodeList;							//岗位代码
	
	private List<String>  userList;									//用户ID
	
	private List<String>  groupList;									//自定义分组
	
	private String userCode;											//授权用户登录代码
	
	public AcAccount(String userCode) {
		this.userCode = userCode;
	}
	
	public AcAccount(){}

	public List<String> getComCodeList() {
		return comCodeList;
	}
	
	public List<String> getExceptComCodeList() {
		return exceptComCodeList;
	}

	public void setExceptComCodeList(List<String> exceptComCodeList) {
		this.exceptComCodeList = exceptComCodeList;
	}

	public void setComCodeList(List<String> comCodeList) {
		this.comCodeList = comCodeList;
	}

	public List<String> getGradeCodeList() {
		return gradeCodeList;
	}

	public void setGradeCodeList(List<String> gradeCodeList) {
		this.gradeCodeList = gradeCodeList;
	}

	public List<String> getUserList() {
		return userList;
	}

	public void setUserList(List<String> userList) {
		this.userList = userList;
	}

	public List<String> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<String> groupList) {
		this.groupList = groupList;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@SuppressWarnings("unchecked")
	public boolean checkUser() {
		
		UserMgrAPIServiceImpl _userMgrAPIService = new UserMgrAPIServiceImpl();
		try {
			UserMsgResInfo _userMsgResInfo = null;
			String comCode = null;					//获取机构代码
			boolean permitFlag = false;//允许机构标志
			//判断组织机构
			if(this.comCodeList!=null&&this.comCodeList.size()>0){
				//允许机构中包含数据
				if(comCode==null){
					_userMsgResInfo = _userMgrAPIService.getUserMsg(this.userCode);
					comCode = _userMsgResInfo.getCOMCODE();
				}
				List<PrpDcompany> _companyList = null;
				for(String permitCode : this.comCodeList) {
					if(permitCode.equals(comCode)){
						permitFlag = true;
						break;
					}
					_companyList = PageService.getCompanys(SystemCode.DMS, permitCode, "", "", "1", "ALLSUB", 1, Integer.MAX_VALUE).getData();
//					_companyList = DictAPIService.getAllSubCompany(SystemCode.DMS, permitCode);
					for(PrpDcompany company : _companyList) {
						if(company.getComCode().equals(comCode)){
							permitFlag = true;
							break;
						}
					}
					if(permitFlag)
						break;
				}
			}
			//判断除外机构
			boolean exceptFlag = true;
			if(this.exceptComCodeList!=null&&this.exceptComCodeList.size()>0){
				//除外机构中包含数据
				if(comCode==null){
					_userMsgResInfo = _userMgrAPIService.getUserMsg(this.userCode);
					comCode = _userMsgResInfo.getCOMCODE();
				}
				List<PrpDcompany> _companyList = null;
				for(String exceptCode : this.exceptComCodeList) {
					if(exceptCode.equals(comCode)){
						exceptFlag = false;
						break;
					}
					_companyList = PageService.getCompanys(SystemCode.DMS, exceptCode, "", "", "1", "ALLSUB", 1, Integer.MAX_VALUE).getData();
//					_companyList = DictAPIService.getAllSubCompany(SystemCode.DMS, exceptCode);
					for(PrpDcompany company : _companyList) {
						if(company.getComCode().equals(comCode)){
							exceptFlag = false;
							break;
						}
					}
					if(!exceptFlag)
						break;
				}
			}
			//判断人员信息
			boolean userFlag = false;
			if(this.userList!=null&&this.userList.size()>0){
				for(String userCode : this.userList) {
					if(this.userCode.equals(userCode)){
						//如果用户代码一致
						userFlag = true;
						break;
					}
				}
			}
			if((this.userList==null||this.userList.size()<1)&&
					(this.comCodeList==null||this.comCodeList.size()<1)&&
					(this.exceptComCodeList==null||this.comCodeList.size()<1)){
				return false;
			}else {
				boolean result = true;
				if(this.comCodeList!=null&&this.comCodeList.size()>0){
					result &= permitFlag;
				}
				if(this.exceptComCodeList!=null&&this.exceptComCodeList.size()>0){
					result &= exceptFlag;
				}
				if(this.userList!=null&&this.userList.size()>0){
					result &= userFlag;
				}
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return false;
	}
	
	
	
}
