/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import com.picc.um.schema.model.UmTWhiteList;
import com.picc.um.schema.model.UmTWhiteListId;

/**
 * 白名单处理自定义接口
 * @author 姜卫洋
 *
 */
public interface IUmTWhiteListService{

	/**
	 * 根据主键对象UmTWhiteListId获取UmTWhiteList信息
	 * @param UmTWhiteListId
	 * @return UmTWhiteList
	 */
	public UmTWhiteList findUmTWhiteListByPK(UmTWhiteListId id) throws Exception;

	/**
	 * 根据umTWhiteList和页码信息，获取Page对象
	 * @param umTWhiteList
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTWhiteList的Page对象
	 */
	public Page findByUmTWhiteList(UmTWhiteList umTWhiteList, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新UmTWhiteList信息
	 * @param UmTWhiteList
	 */
	public void updateUmTWhiteList(UmTWhiteList umTWhiteList) throws Exception;

	/**
	 * 保存UmTWhiteList信息
	 * @param UmTWhiteList
	 */
	public void saveUmTWhiteList(UmTWhiteList umTWhiteList) throws Exception;

	/**
	 * 根据主键对象，删除UmTWhiteList信息
	 * @param UmTWhiteListId
	 */
	public void deleteByPK(UmTWhiteListId id) throws Exception;
	
}
