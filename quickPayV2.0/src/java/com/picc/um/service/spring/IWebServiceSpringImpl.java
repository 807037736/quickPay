package com.picc.um.service.spring;

import com.picc.um.service.facade.IAwsService;
import com.picc.um.service.facade.IWebService;

/**
 * 炎黄WebService实现类
 * @author 沈一婵
 */
public class IWebServiceSpringImpl implements IWebService {
	
	private IAwsService awsService = null;

	public void setAwsService(IAwsService awsService) {
		this.awsService = awsService;
	}

	public String getCompanyJsonByUpperCode(String comCode, String queryType) {
		return awsService.getCompanyJsonByUpperCode(comCode, queryType);
	}

	public String getUserListByComCode(String comCode, String queryType,
			int start, int limit) {
		return awsService.getUserListByComCode(comCode, queryType, start, limit);
	}

	public String findGradeListById(String gradeCode, String queryType) {
		// TODO Auto-generated method stub
		return null;
	}

	public String findAwsUserByUserCode(String userCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean checkLogin(String userCode, String password)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean checkUserByACCode(String userCode, String acCode)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public String getMenuStrByUserCode(String userCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String findCodeNameByCodeId(String codeId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String findOptionsByQueryType(String queryType) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String findGradeJsonListByUpperCode(String gradeCode,
			String queryType) {
		// TODO Auto-generated method stub
		return null;
	}

}
