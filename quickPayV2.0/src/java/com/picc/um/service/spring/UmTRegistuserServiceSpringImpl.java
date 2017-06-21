/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2014
 */

package com.picc.um.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.utils.CodeUtil;
import com.picc.common.utils.SqlUtils;
import com.picc.common.utils.ToolsUtils;
import com.picc.um.dao.UmTAccountDaoHibernate;
import com.picc.um.dao.UmTRegistuserDaoHibernate;
import com.picc.um.dao.UmTUserDaoHibernate;
import com.picc.um.dao.UmTUserRelationDaoHibernate;
import com.picc.um.schema.model.UmTAccount;
import com.picc.um.schema.model.UmTAccountId;
import com.picc.um.schema.model.UmTRegistuser;
import com.picc.um.schema.model.UmTRegistuserId;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.model.UmTUserId;
import com.picc.um.schema.model.UmTUserRelation;
import com.picc.um.schema.model.UmTUserRelationId;
import com.picc.um.security.DESUtils;
import com.picc.um.service.facade.IUmTRegistuserService;


@Service("umTRegistuserService")
public class UmTRegistuserServiceSpringImpl implements IUmTRegistuserService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private UmTRegistuserDaoHibernate umTRegistuserDao;
	@Autowired
	private UmTUserDaoHibernate umTUserDao;
	@Autowired
	private UmTAccountDaoHibernate umTAccountDao;
	@Autowired
	private UmTUserRelationDaoHibernate umTUserRelationDao;
	@Autowired
	private CommonDaoHibernate commonDao;

	/**
	 * 根据主键对象UmTRegistuserId获取UmTRegistuser信息
	 * @param UmTRegistuserId
	 * @return UmTRegistuser
	 */
	public UmTRegistuser findUmTRegistuserByPK(UmTRegistuserId id) throws Exception{
			return umTRegistuserDao.get(UmTRegistuser.class,id);
	}

	/**
	 * 根据umTRegistuser和页码信息，获取Page对象
	 * @param umTRegistuser
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTRegistuser的Page对象
	 */
	public Page findByUmTRegistuser(UmTRegistuser umTRegistuser, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据umTRegistuser生成queryRule
		
		return umTRegistuserDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新UmTRegistuser信息
	 * @param UmTRegistuser
	 */
	public void updateUmTRegistuser(UmTRegistuser umTRegistuser) throws Exception{
			umTRegistuserDao.update(umTRegistuser);
	}

	/**
	 * 保存UmTRegistuser信息
	 * @param UmTRegistuser
	 */
	public void saveUmTRegistuser(UmTRegistuser umTRegistuser) throws Exception{
			
		UmTRegistuserId id_tmp = new UmTRegistuserId();
		String userCode = "RGUS"+CodeUtil.makeLID();
		id_tmp.setUserCode(userCode);
		umTRegistuser.setId(id_tmp);
		umTRegistuserDao.save(umTRegistuser);
	}

	/**
	 * 根据主键对象，删除UmTRegistuser信息
	 * @param UmTRegistuserId
	 */
	public void deleteByPK(UmTRegistuserId id) throws Exception{
			umTRegistuserDao.deleteByPK(UmTRegistuser.class,id);
	}
	
	/**
	 * 校验手机号是否存在
	 * @return
	 */
	public Map<String, String> checkMobileno(String mobileno) {
		
		Map<String, String> resultMap = new HashMap<String, String>();
		if(ToolsUtils.isEmpty(mobileno) || ToolsUtils.isEmpty(mobileno)) {
			resultMap.put("msg", "empty info");
			return resultMap;
		}
		
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("mobile", mobileno);
		List<UmTRegistuser> list = umTRegistuserDao.find(UmTRegistuser.class, queryRule);
		
		if(ToolsUtils.isEmpty(list)){
			resultMap.put("msg", "false");
		}else{
			resultMap.put("msg", "success");
		}
			
		return resultMap;
	}


	/**
	 * 注册新用户
	 * @param umTRegistuser
	 * @author xiehui 20140722
	 * @throws Exception 
	 */
	public String registNewUser(UmTRegistuser umTRegistuser,String param,String identifyno,
			String licenseno,String postAddress) throws Exception {
		// TODO Auto-generated method stub
		
		//获取注册信息
		String userName = umTRegistuser.getUserName();
		String mobile = umTRegistuser.getMobile();
		String password = "weixin8888";
		
		String registSource = umTRegistuser.getRegistSource(); //注册来源
		String userType = umTRegistuser.getUserType(); //注册用户归类
		String creatorCode = "sys";
		
		//初始化状态，保存注册信息
		umTRegistuser.setValidStatus("1");
		umTRegistuser.setCreatorCode(creatorCode);
		umTRegistuser.setRegistSource(registSource);
		
		UmTRegistuserId registId = new UmTRegistuserId();
		String userCode = "RU"+CodeUtil.makeSID();
		registId.setUserCode(userCode);
		umTRegistuser.setId(registId);
		umTRegistuserDao.save(umTRegistuser);
		
		//注册成功，保存用户信息
		UmTUser newUser = new UmTUser();
		newUser.setBindStatus("0");  //未绑定
		newUser.setComId("44030000");
		newUser.setComCode("44030000");
		newUser.setSourceFlag(userType);  //00-外部客户，01-员工，02-渠道
		newUser.setValidStatus("1");  //有效
		
		newUser.setMobile(mobile);
		newUser.setTelePhone(mobile);
		newUser.setUserName(userName);
		newUser.setIdentityNumber(identifyno);
		newUser.setLicenseNo(licenseno);
		newUser.setPostAddress(postAddress);
		newUser.setCreatorCode(creatorCode);
		
		UmTUserId userId = new UmTUserId();
		userId.setUserCode(userCode);
		newUser.setId(userId);
		umTUserDao.save(newUser);
		//创建关联表
		String platId = "piccwx";
		String openId = "";
		UmTUserRelationId id = new UmTUserRelationId();
		if(ToolsUtils.notEmpty(param)){
			String[] params = param.split("-");
			if(params.length>1){
				platId = params[0];
				openId = params[1];
				//先注释
				id.setPlatId(ToolsUtils.toStringHex(platId));
				id.setUserId(ToolsUtils.toStringHex(openId));
			}else{
				id.setPlatId(platId);
				id.setUserId(userCode);
			}
		}else{
			id.setPlatId(platId);
			id.setUserId(userCode);
		}
		UmTUserRelation userR = new UmTUserRelation();
		userR.setId(id);
		userR.setUserCode(userCode);
		userR.setUserName(userName);
		userR.setMobileNo(mobile);
		userR.setToolType("weixin");
		userR.setComCode("44030000");
		userR.setInsertTimeForHis(new Date());
		userR.setValidStatus("1");
		userR.setIdentityNum(identifyno);
		umTUserRelationDao.save(userR);
		
		//创建登陆账号
		UmTAccount account = new UmTAccount();
		account.setComId("44030000");
		
		account.setPassword(DESUtils.getEncryptStr(password));
		account.setCreatorCode(creatorCode);
		account.setValidStatus("1");
		
		UmTAccountId accountId = new UmTAccountId();
		accountId.setUserCode(userCode);
		account.setId(accountId);
		
		umTAccountDao.save(account);
		return userCode;
	}
	
	/**
	 * 根据微信号查询客户姓名手机
	 * @date 2014年12月4日
	 * @user juzongyi
	 * @param platid
	 * @param openid
	 * @return
	 * @throws Exception
	 */
	public List<UmTRegistuser> findCustNameByOpenid(String platid, String openid)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select a.* from um_t_registuser a,um_t_userrelation b ")
				.append("where a.usercode=b.usercode and a.VALIDSTATUS='1' ")
				.append(SqlUtils.convertString("b.platid", platid))
				.append(SqlUtils.convertString("b.userid", openid));
		
		List<UmTRegistuser> list = (List<UmTRegistuser>) commonDao.findListBySql(
				sql.toString(), UmTRegistuser.class, null);
		return list;
	}
	
	/**
	 * 根据微信号查询需要绑定的客户
	 * @date 2014年12月4日
	 * @user juzongyi
	 * @param platid
	 * @param openid
	 * @return
	 * @throws Exception
	 */
	public List<UmTRegistuser> findRegistByOpenid(String platid, String openid)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select a.* from um_t_registuser a ")
				.append("where 1=1 ")
				.append(SqlUtils.convertString("a.platid", platid))
				.append(SqlUtils.convertString("a.userid", openid));

		List<UmTRegistuser> list = (List<UmTRegistuser>) commonDao.findListBySql(
				sql.toString(), UmTRegistuser.class, null);
		return list;
	}
	
	
	
	public List<UmTRegistuser> check(String licenseNo, String mobile)throws Exception {

//		 List<UmTRegistuser> list = umTRegistuserDao.findBySql("SELECT * FROM um_t_user where licenseno=? and mobile=?",new Object[]{licenseno,mobileno});
		 QueryRule queryRule = QueryRule.getInstance();
		 if(!ToolsUtils.isEmpty(licenseNo)){
			 queryRule.addEqual("licenseNo", licenseNo);
		 }
		 if(!ToolsUtils.isEmpty(mobile)){
			 queryRule.addEqual("mobile", mobile);
		 }
		 return umTRegistuserDao.find(queryRule);
//		 return list;

	}
	
	public List<UmTRegistuser> getUmTRegistuserByMobile(String mobile)throws Exception {
		 QueryRule queryRule = QueryRule.getInstance();
		 if(!ToolsUtils.isEmpty(mobile)){
			 queryRule.addEqual("mobile", mobile);
		 }
		 return umTRegistuserDao.find(queryRule);
	}
	
	public List<UmTRegistuser> getUmTRegistuserByLicenseNo(String licenseNo)throws Exception {
		 QueryRule queryRule = QueryRule.getInstance();
		 if(!ToolsUtils.isEmpty(licenseNo)){
			 queryRule.addEqual("licenseNo", licenseNo);
		 }
		 return umTRegistuserDao.find(queryRule);
	}
	
	
	public void updateWxUser(String userCode,String openId,String platId,String userName,String identityNumber, String licenseNo,String mobile,String postAddress) throws Exception {
		UmTRegistuserId umTRegistuserId = new UmTRegistuserId(userCode);
		UmTRegistuser umTRegistuser = umTRegistuserDao.get(UmTRegistuser.class,umTRegistuserId);
		
		UmTUserRelationId umTUserRelationId = new UmTUserRelationId(platId,openId);  
		UmTUserRelation umTUserRelation = umTUserRelationDao.get(UmTUserRelation.class,umTUserRelationId);
		
		UmTUserId umTUserId =new UmTUserId(userCode);
		UmTUser umTUser = umTUserDao.get(UmTUser.class,umTUserId);
		
		if(!userName.isEmpty()){
			umTRegistuser.setUserName(userName);
			umTUser.setUserName(userName);
			umTUserRelation.setUserName(userName);
		}
		if(!identityNumber.isEmpty()){
			umTRegistuser.setIdentityNumber(identityNumber);
			umTUser.setIdentityNumber(identityNumber);
			umTUserRelation.setIdentityNum(identityNumber);
		}
		if(!licenseNo.isEmpty()){
			umTRegistuser.setLicenseNo(licenseNo);
			umTUser.setLicenseNo(licenseNo);
		}
		umTUser.setPostAddress(postAddress);
		
		umTUserRelationDao.update(umTUserRelation);
	    umTRegistuserDao.update(umTRegistuser);
		umTUserDao.update(umTUser);
	}
	
}
