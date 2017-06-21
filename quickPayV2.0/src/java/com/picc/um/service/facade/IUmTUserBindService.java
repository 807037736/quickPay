/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2014
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import java.util.List;
import java.util.Map;

import com.picc.um.schema.model.UmTUserBind;
import com.picc.um.schema.model.UmTUserBindId;
import com.picc.um.schema.model.UmTUserRelation;
import com.picc.um.schema.vo.UmTUserBindVo;
import com.picc.um.schema.vo.UmTWxUser;

public interface IUmTUserBindService {

	/**
	 * 根据主键对象UmTUserBindId获取UmTUserBind信息
	 * 
	 * @param UmTUserBindId
	 * @return UmTUserBind
	 */
	public UmTUserBind findUmTUserBindByPK(UmTUserBindId id) throws Exception;

	/**
	 * 根据umTUserBind和页码信息，获取Page对象
	 * 
	 * @param umTUserBind
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTUserBind的Page对象
	 */
	public Page findByUmTUserBind(UmTUserBind umTUserBind, int pageNo,
			int pageSize) throws Exception;
	/**
	 * 根据usercode查询bind
	 * 
	 * @param umTUserBind
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTUserBind的Page对象
	 */
	public UmTUserBind findListByUserCode(String usercode) throws Exception;

	/**
	 * 更新UmTUserBind信息
	 * 
	 * @param UmTUserBind
	 */
	public void updateUmTUserBind(UmTUserBind umTUserBind) throws Exception;

	/**
	 * 保存UmTUserBind信息
	 * 
	 * @param UmTUserBind
	 */
	public void saveUmTUserBind(UmTUserBind umTUserBind) throws Exception;

	/**
	 * 根据主键对象，删除UmTUserBind信息
	 * 
	 * @param UmTUserBindId
	 */
	public void deleteByPK(UmTUserBindId id) throws Exception;
	/**
	 * 绑定
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public UmTUserBind fdBindCust(Map paramMap) throws Exception;
	/**
	 * 修改绑定信息
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public UmTUserBind savBindCust(UmTUserRelation userRelation, UmTUserBind userBind) throws Exception;
	public void deleteByParam(String platid, String openid) throws Exception;
	public List<UmTUserBindVo> findCustIdByOpenid(String platid, String openid)
			throws Exception;
	public UmTWxUser  findCustInfoByOpenid(String platid, String openid)
			throws Exception;
	/**
	 * 根据微信号查询客户id
	 * @date 2015年12月10日
	 * @param platid
	 * @param openid
	 * @return
	 * @throws Exception
	 */
	public List<UmTUserBindVo> findRegistUserByOpenid(String platid, String openid) throws Exception;
	
	/**
	 * 根据手机号码查询个人信息
	 * @date 2016年7月10日
	 * @param mobile
	 * @return List<UmTUserBindVo>
	 * @throws Exception
	 */
	public List<UmTUserBindVo> findRegistUserByMobile(String mobile) throws Exception;
}
