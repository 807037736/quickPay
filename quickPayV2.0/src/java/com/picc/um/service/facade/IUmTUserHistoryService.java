/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2014
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import com.picc.um.schema.model.UmTUserHistory;
import com.picc.um.schema.model.UmTUserHistoryId;

public interface IUmTUserHistoryService{

	/**
	 * 根据主键对象UmTUserHistoryId获取UmTUserHistory信息
	 * @param UmTUserHistoryId
	 * @return UmTUserHistory
	 */
	public UmTUserHistory findUmTUserHistoryByPK(UmTUserHistoryId id) throws Exception;

	/**
	 * 根据umTUserHistory和页码信息，获取Page对象
	 * @param umTUserHistory
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTUserHistory的Page对象
	 */
	public Page findByUmTUserHistory(UmTUserHistory umTUserHistory, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新UmTUserHistory信息
	 * @param UmTUserHistory
	 */
	public void updateUmTUserHistory(UmTUserHistory umTUserHistory) throws Exception;

	/**
	 * 保存UmTUserHistory信息
	 * @param UmTUserHistory
	 */
	public void saveUmTUserHistory(UmTUserHistory umTUserHistory) throws Exception;

	/**
	 * 根据主键对象，删除UmTUserHistory信息
	 * @param UmTUserHistoryId
	 */
	public void deleteByPK(UmTUserHistoryId id) throws Exception;

	public void saveFromUmTUser(String usercode) throws Exception;
	
}
