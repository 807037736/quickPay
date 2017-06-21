package com.picc.um.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.common.utils.ToolsUtils;
import com.picc.qp.util.Constants;
import com.picc.qp.util.HttpClientUtils;
import com.picc.um.dao.UmTUserBoundDaoHibernate;
import com.picc.um.schema.model.UmTUserBound;
import com.picc.um.schema.model.UmTUserBoundId;
import com.picc.um.service.facade.IUmTUserBoundService;
@Service("umTUserBoundService")
public class UmTUserBoundServiceSpringImpl implements IUmTUserBoundService {
	@Autowired
	UmTUserBoundDaoHibernate umTUserBoundDao;
	@Autowired
    private SysCommonDaoHibernate sysCommonDao;
	@Override
	public void updateUmTUserBound(UmTUserBound umTUserBound) throws Exception {
		umTUserBoundDao.update(umTUserBound);
	}

	@Override
	public void saveUmTUserBound(UmTUserBound umTUserBound) throws Exception {
		JSONObject sendJsonObject = new JSONObject();
		if(ToolsUtils.notEmpty(umTUserBound.getWxUserCode())){
			sendJsonObject.put("openId", umTUserBound.getWxUserCode());
		}
		//调用接口获得微信用户数据
		Map<String, Object> code = HttpClientUtils.HttpClientJsonPost(Constants.getWX_URL() + "wx/user/finduser", sendJsonObject.toString(),"utf-8");
		JSONObject info = JSONObject.parseObject((String) code.get("info"));
		JSONArray dataArray= (JSONArray) info.get("data");
		JSONObject data= (JSONObject) dataArray.get(0);
		umTUserBound.setWxUserName(data.getString("userName"));
		umTUserBoundDao.save(umTUserBound);
	}
	
	@Override
	public List<UmTUserBound> findByUserBound(UmTUserBound umTUserBound) throws Exception {
		if(ToolsUtils.isEmpty(umTUserBound.getUserName()) && ToolsUtils.isEmpty(umTUserBound.getMobile()) && ToolsUtils.isEmpty(umTUserBound.getIdentityNumber()) && ToolsUtils.isEmpty(umTUserBound.getLicenseNo())&& ToolsUtils.isEmpty(umTUserBound.getWxUserCode())){
			return null;
		}
		QueryRule rule = QueryRule.getInstance();
		if(ToolsUtils.notEmpty(umTUserBound.getWxUserCode())){
			rule.addEqual("wxUserCode", umTUserBound.getWxUserCode());
		}
		if(ToolsUtils.notEmpty(umTUserBound.getWxUserName())){
			rule.addEqual("wxUserName", umTUserBound.getWxUserCode());
		}
		if(ToolsUtils.notEmpty(umTUserBound.getCkUserCode())){
			rule.addEqual("ckUserCode", umTUserBound.getCkUserCode());
		}
		if(ToolsUtils.notEmpty(umTUserBound.getCkUserName())){
			rule.addEqual("ckUserName", umTUserBound.getCkUserName());
		}
		return umTUserBoundDao.find(rule);
	}
	
	public UmTUserBound findUmTUserBoundByPk(UmTUserBoundId umTUserBoundId){
		return umTUserBoundDao.get(UmTUserBound.class, umTUserBoundId);
	}

	@Override
	public void deleteByPK(int boundId) throws Exception {
		String sql = "delete from um_t_userBound where boundId='"+boundId+"'";
		umTUserBoundDao.execute(sql, null);
	}
	
	@Override
	public UmTUserBound isCKUser(UmTUserBound umTUserBound) throws Exception {
		if(ToolsUtils.isEmpty(umTUserBound.getUserName()) && ToolsUtils.isEmpty(umTUserBound.getMobile()) && ToolsUtils.isEmpty(umTUserBound.getIdentityNumber()) && ToolsUtils.isEmpty(umTUserBound.getLicenseNo())&& ToolsUtils.isEmpty(umTUserBound.getWxUserCode())){
			return null;
		}
		QueryRule rule = QueryRule.getInstance();
		if(ToolsUtils.notEmpty(umTUserBound.getWxUserCode())){
			rule.addEqual("wxUserCode", umTUserBound.getWxUserCode());
		}
		if(ToolsUtils.notEmpty(umTUserBound.getWxUserName())){
			rule.addEqual("wxUserName", umTUserBound.getWxUserCode());
		}
		if(ToolsUtils.notEmpty(umTUserBound.getCkUserCode())){
			rule.addEqual("ckUserCode", umTUserBound.getCkUserCode());
		}
		if(ToolsUtils.notEmpty(umTUserBound.getCkUserName())){
			rule.addEqual("ckUserName", umTUserBound.getCkUserName());
		}
		List<UmTUserBound> result = umTUserBoundDao.find(rule);
        	if(!result.isEmpty()){
        		return result.get(0);
        	}else{
        		return null;
        	}
	}
	
	/**
	 * added by CKY 2016-6-24
	 * 
	 * @Title: findUserPageByHql
	 * @Description: 根据umTUser获取userPage
	 * @return Page
	 * @throws
	 */
	public Page findWXUserPageByUmTUser(UmTUserBound umTUserBound, int pageNo,
			int pageSize) {
		JSONObject sendJsonObject = new JSONObject();
		
		/*if(!ToolsUtils.notEmpty(umTUserBound.getUserName()) && !ToolsUtils.notEmpty(umTUserBound.getMobile()) && !ToolsUtils.notEmpty(umTUserBound.getIdentityNumber()) && !ToolsUtils.notEmpty(umTUserBound.getLicenseNo())){
        	return null;
        }*/
		if(ToolsUtils.notEmpty(umTUserBound.getWxUserName())){
			sendJsonObject.put("userName", umTUserBound.getWxUserName());
		}
		if(ToolsUtils.notEmpty(umTUserBound.getMobile())){
			sendJsonObject.put("mobile", umTUserBound.getMobile());
		}
		if(ToolsUtils.notEmpty(umTUserBound.getIdentityNumber())){
			sendJsonObject.put("identityNumber", umTUserBound.getIdentityNumber());
		}
		if(ToolsUtils.notEmpty(umTUserBound.getLicenseNo())){
			sendJsonObject.put("licenseNo", umTUserBound.getLicenseNo());
		}
		//获取所有绑定查勘员数据
		List<UmTUserBound> boundUsers = umTUserBoundDao.find(QueryRule.getInstance());
		//调用接口获得微信用户数据
		List<UmTUserBound> wxUsers = new ArrayList<UmTUserBound>();
		Map<String, Object> code = HttpClientUtils.HttpClientJsonPost(Constants.getWX_URL() + "wx/user/finduser", sendJsonObject.toString(),"utf-8");
		JSONObject info = JSONObject.parseObject((String) code.get("info"));
		JSONArray dataArray= (JSONArray) info.get("data");
		for (int i = 0; i < dataArray.size(); i++) {
			JSONObject data= (JSONObject) dataArray.get(i);
			UmTUserBound umTUserBound2 = new UmTUserBound();
			umTUserBound2.setIdentityNumber(data.getString("identityNumber"));
			umTUserBound2.setLicenseNo(data.getString("licenseNo"));
			umTUserBound2.setMobile(data.getString("mobile"));
			umTUserBound2.setWxUserCode(data.getString("openId"));
			umTUserBound2.setWxUserName(data.getString("userName"));
			wxUsers.add(umTUserBound2);
		}
		//已绑定查勘员的微信用户补全信息。
		for (int i = 0; i < wxUsers.size(); i++) {
			for (int j = 0; j < boundUsers.size(); j++) {
				if(boundUsers.get(j).getWxUserCode().equals(wxUsers.get(i).getWxUserCode())){
					wxUsers.get(i).setBoundId(boundUsers.get(j).getBoundId());
					wxUsers.get(i).setCkUserCode(boundUsers.get(j).getCkUserCode());
					wxUsers.get(i).setCkUserName(boundUsers.get(j).getCkUserName());
					wxUsers.get(i).setIsBound("1");
				}
				
			}
		}
		List<UmTUserBound> data = new ArrayList<UmTUserBound>();
		int no;
		if(pageSize*pageNo<wxUsers.size()){
			no = pageSize*pageNo;
		}else{
			no = wxUsers.size();
		}
		for (int i = (pageNo-1)*pageSize; i < no; i++) {
			data.add(wxUsers.get(i));
		}
		
		return  new Page(pageNo, wxUsers.size(), pageSize, data);
	}
	
	/**
	 * added by CKY 2016-6-24
	 * 
	 * @Title: findCKUserPageByUmTUser
	 * @Description: 根据UmTUserBound获取查勘员列表
	 * @return Page
	 * @throws
	 */
	public Page findCKUserPageByUmTUser(UmTUserBound umTUserBound, int pageNo,
			int pageSize) {
		
		/*if(ToolsUtils.isEmpty(umTUser.getUserName()) && ToolsUtils.isEmpty(umTUser.getMobile()) && ToolsUtils.isEmpty(umTUser.getIdentityNumber()) && ToolsUtils.isEmpty(umTUser.getLicenseNo())){
        	return null;
        }*/
		
		StringBuffer sql = new StringBuffer();
        
        sql.append(" SELECT u.userName, u.licenseNo, u.mobile, ");
        sql.append(" u.identityNumber, u.userCode, u.sex, c.boundCount ");
        sql.append(" FROM um_t_user u  JOIN um_t_userrole r ON ");
        //UMRO00000000000000000000000902 为查勘员roleid
        sql.append(" r.usercode = u.userCode AND r.roleId = 'UMRO00000000000000000000000902' ");
        
        sql.append("left JOIN (SELECT u.ckusercode,count(ckusercode) boundCount from um_t_userbound u group by u.ckusercode) c  ");
        sql.append(" ON   c.ckusercode = u.usercode  WHERE 1=1 ");
        // 用户姓名
        if(ToolsUtils.notEmpty(umTUserBound.getCkUserName())) {
            sql.append(" AND u.userName = '").append(umTUserBound.getCkUserName()).append("' ");
        }
        
        // 手机号
        if(ToolsUtils.notEmpty(umTUserBound.getMobile())) {
            sql.append(" AND u.mobile = '").append(umTUserBound.getMobile()).append("' ");
        }
        
        // 身份证号
        if(ToolsUtils.notEmpty(umTUserBound.getIdentityNumber())) {
            sql.append(" AND u.identitynumber = '").append(umTUserBound.getIdentityNumber()).append("' ");
        }
        //车牌号
        if(ToolsUtils.notEmpty(umTUserBound.getLicenseNo())) {
        	sql.append(" AND u.licenseNo = '").append(umTUserBound.getLicenseNo()).append("' ");
        }
        
        sql.append(" order by c.boundCount DESC ");
        System.out.println(sql.toString());
        try {
			return  sysCommonDao.findObjectPageBySql(sql.toString(), UmTUserBound.class, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
