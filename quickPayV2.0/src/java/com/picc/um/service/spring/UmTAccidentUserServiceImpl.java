package com.picc.um.service.spring;

import ins.framework.common.QueryRule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.utils.StringUtils;
import com.picc.qp.service.facade.IQpTTPFastCenterService;
import com.picc.um.dao.UmTAccidentUserDaoHibernate;
import com.picc.um.schema.model.UmTAccidentUser;
import com.picc.um.service.facade.IUmTAccidentUserService;

@Service("umTAccidentUserService")
public class UmTAccidentUserServiceImpl implements IUmTAccidentUserService {

	@Autowired
	private UmTAccidentUserDaoHibernate umTAccidentUserDaoHibernate;
	@Autowired
	private IQpTTPFastCenterService qpTTPFastCenterService;
	
	
	@Override
	public List<UmTAccidentUser> findByUmTAccidentUser(UmTAccidentUser umTAccidentUser) throws Exception {
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		if(!StringUtils.isEmptyStr(umTAccidentUser.getUserID()) && umTAccidentUser.getUserID() > 0){
			queryRule.addEqual("userID", umTAccidentUser.getUserID());
		}
		if(StringUtils.isNotEmpty(umTAccidentUser.getIDNumber())){
			queryRule.addEqual("IDNumber", umTAccidentUser.getIDNumber());
		}
		if(StringUtils.isNotEmpty(umTAccidentUser.getUserMobile())){
			queryRule.addEqual("userMobile", umTAccidentUser.getUserMobile());
		}
		if(StringUtils.isNotEmpty(umTAccidentUser.getLicenseNo())){
			queryRule.addEqual("licenseNo", umTAccidentUser.getLicenseNo());
		}
		// TODO Auto-generated method stub
		return umTAccidentUserDaoHibernate.find(queryRule);
	}

	@Override
	public void updateUmTAccidentUser(UmTAccidentUser umTAccidentUser) throws Exception {
		// TODO Auto-generated method stub
		umTAccidentUserDaoHibernate.update(umTAccidentUser);
	}

	@Override
	public void saveUmTAccidentUser(UmTAccidentUser umTAccidentUser) throws Exception {
		// TODO Auto-generated method stub
		umTAccidentUserDaoHibernate.save(umTAccidentUser);
	}

	@Override
	public void deleteByPK(Integer userID) throws Exception {
		// TODO Auto-generated method stub
		umTAccidentUserDaoHibernate.deleteByPK(UmTAccidentUser.class, userID);
	}

}
