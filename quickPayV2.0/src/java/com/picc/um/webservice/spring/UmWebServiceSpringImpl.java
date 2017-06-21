package com.picc.um.webservice.spring;

import javax.jws.WebService;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.model.UmTUserId;
import com.picc.um.schema.model.UserModel;
import com.picc.um.service.facade.IUmTUserService;
import com.picc.um.webservice.facade.IUmWebService;

@WebService(endpointInterface = "com.picc.um.webservice.facade.IUmWebService")
public class UmWebServiceSpringImpl implements IUmWebService {
	
	@Autowired
	private IUmTUserService umTUserService;
	
	public String findAwsUserModel(String userCode) throws Exception {
		UmTUser user = umTUserService.findUmTUserByPK(new UmTUserId(userCode));
		UserModel userModel = new UserModel();
		userModel.setUID(user.getId().getUserCode());
		userModel.setUserName(user.getUserName());
		JSONObject userJson = JSONObject.fromObject(userModel);
		return userJson.toString();
	}

	public String findCodeNameByCodeId(String codeId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean checkUserByACCode(String userCode, String acCode)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public String findRootCompany(String userCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String findSubCompany(String upperComCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String findUserByComCode(String comCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String parseParticipants(String chooseStr) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean checkLogin(String userCode, String password)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public String getMenuStrByUserCode(String userCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
