/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2014
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import java.util.List;
import java.util.Map;

import com.picc.um.schema.model.UmTRegistuser;
import com.picc.um.schema.model.UmTRegistuserId;

public interface IUmTRegistuserService{

	/**
	 * 根据主键对象UmTRegistuserId获取UmTRegistuser信息
	 * @param UmTRegistuserId
	 * @return UmTRegistuser
	 */
	public UmTRegistuser findUmTRegistuserByPK(UmTRegistuserId id) throws Exception;

	/**
	 * 根据umTRegistuser和页码信息，获取Page对象
	 * @param umTRegistuser
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTRegistuser的Page对象
	 */
	public Page findByUmTRegistuser(UmTRegistuser umTRegistuser, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新UmTRegistuser信息
	 * @param UmTRegistuser
	 */
	public void updateUmTRegistuser(UmTRegistuser umTRegistuser) throws Exception;

	/**
	 * 保存UmTRegistuser信息
	 * @param UmTRegistuser
	 */
	public void saveUmTRegistuser(UmTRegistuser umTRegistuser) throws Exception;

	/**
	 * 根据主键对象，删除UmTRegistuser信息
	 * @param UmTRegistuserId
	 */
	public void deleteByPK(UmTRegistuserId id) throws Exception;
	public Map<String, String> checkMobileno(String mobileno);
	/**
	 * 注册新用户
	 * @param umTRegistuser
	 * @author xiehui 20140722
	 * @throws Exception 
	 */
	public String registNewUser(UmTRegistuser umTRegistuser,String param,String identifyno,
			String licenseno,String postAddress) throws Exception;
	
	/**
	 * 根据微信号查询客户姓名手机
	 * @date 2014年12月4日
	 * @user juzongyi
	 * @param platid
	 * @param openid
	 * @return
	 * @throws Exception
	 */
	public List<UmTRegistuser> findCustNameByOpenid(String platid, String openid) throws Exception;
	
	/**
	 * 根据微信号查询将要绑定的客户
	 * @date 2014年12月4日
	 * @user juzongyi
	 * @param platid
	 * @param openid
	 * @return
	 * @throws Exception
	 */
	public List<UmTRegistuser> findRegistByOpenid(String platid, String openid) throws Exception;
	

	/**
	 * /校验,车牌号且电话号，等于Y，表示注册过
	 * @date 
	 * @user wyq
	 * @param 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<UmTRegistuser> check(String licenseno, String phoneNumber) throws Exception;
	/**
	 * 根据手机查询UmTRegistuser，不为空表示注册过
	 * @date 
	 * @user wyq
	 * @param 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<UmTRegistuser> getUmTRegistuserByMobile(String mobile)throws Exception;
	/**
	 * 根据车牌号查询UmTRegistuser，不为空表示注册过
	 * @date 
	 * @user wyq
	 * @param 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<UmTRegistuser> getUmTRegistuserByLicenseNo(String licenseNo)throws Exception;
	/**
	 * 根据车牌号查询UmTRegistuser，不为空表示注册过
	 * @date 
	 * @user wyq
	 * @param umTUserRelation,licenseNo
	 * @return 
	 * @throws Exception
	 */
	public void updateWxUser(String userCode,String openId,String platId,String userName,String identityNumber, String licenseNo,String mobile,String postAddress) throws Exception;
}
