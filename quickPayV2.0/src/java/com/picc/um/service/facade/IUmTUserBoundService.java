/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.um.schema.model.UmTUserBound;
import com.picc.um.schema.model.UmTUserBoundId;

/** 
* @Description: 微信用户绑定查勘员用户
* @author CKY
* @date 2016-6-28 
*  
*/
public interface IUmTUserBoundService{

	/**
	 * 更新UmTUserBound信息
	 * @param UmTUserBound
	 */
	public void updateUmTUserBound(UmTUserBound umTUserBound) throws Exception;

	/**
	 * 保存UmTUserBound信息
	 * @param UmTUserBound
	 */
	public void saveUmTUserBound(UmTUserBound umTUserBound) throws Exception;
	/**
	 * 根据wxUserCode，删除UmTUserBound信息
	 * @param UmTUserBound
	 */
	public void deleteByPK(int boundId) throws Exception;
	/**
	 * 根据umTUser获取微信userPage
	 * @param UmTUser
	 */	
	public Page findWXUserPageByUmTUser(UmTUserBound umTUserBound, int pageNo,
			int pageSize) throws Exception;
	/**
	 * 根据umTUser获取查勘员userPage
	 * @param UmTUser
	 */	
	public Page findCKUserPageByUmTUser(UmTUserBound umTUserBound, int pageNo,
			int pageSize) throws Exception;
	/**
	 * 根据UmTUserBound判断是否为微信查勘员
	 * @param UmTUserBound
	 */	
	public UmTUserBound isCKUser(UmTUserBound umTUserBound) throws Exception;
	/**
	 * 根据boundId获取UmTUserBound
	 * @param boundId
	 * @return UmTUserBound
	 */	
	public UmTUserBound findUmTUserBoundByPk(UmTUserBoundId umTUserBoundId) throws Exception;
	/**
	 * 根据UmTUserBound获取UmTUserBound
	 * @param umTUserBound
	 * @return List<UmTUserBound>
	 */	
	public List<UmTUserBound> findByUserBound(UmTUserBound umTUserBound)throws Exception;
}
