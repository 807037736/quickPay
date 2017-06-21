/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import com.picc.um.schema.model.UmTDictionary;
import com.picc.um.schema.model.UmTDictionaryId;

/**
 * 用户数据字典自定义接口
 * @author 姜卫洋
 *
 */
public interface IUmTDictionaryService{

	/**
	 * 根据主键对象UmTDictionaryId获取UmTDictionary信息
	 * @param UmTDictionaryId
	 * @return UmTDictionary
	 */
	public UmTDictionary findUmTDictionaryByPK(UmTDictionaryId id) throws Exception;

	/**
	 * 根据umTDictionary和页码信息，获取Page对象
	 * @param umTDictionary
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTDictionary的Page对象
	 */
	public Page findByUmTDictionary(UmTDictionary umTDictionary, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新UmTDictionary信息
	 * @param UmTDictionary
	 */
	public void updateUmTDictionary(UmTDictionary umTDictionary) throws Exception;

	/**
	 * 保存UmTDictionary信息
	 * @param UmTDictionary
	 */
	public void saveUmTDictionary(UmTDictionary umTDictionary) throws Exception;

	/**
	 * 根据主键对象，删除UmTDictionary信息
	 * @param UmTDictionaryId
	 */
	public void deleteByPK(UmTDictionaryId id) throws Exception;
	
	
	
	/**
	 * 获取所有有效的数据权限字典集合
	 * @return									数据权限字典集合
	 * @throws Exception
	 * 2013-8-30下午3:49:13
	 * jiangweiyang
	 */
	public Page findValidDictionary(String comId,int pageNo,int pageSize) throws Exception;
	
	
}
