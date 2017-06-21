/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.um.schema.model.UmTDictionaryDetail;
import com.picc.um.schema.model.UmTDictionaryDetailId;

/**
 * 用户权限字典明细自定义接口
 * @author 姜卫洋
 */
public interface IUmTDictionaryDetailService{

	/**
	 * 根据主键对象UmTDictionaryDetailId获取UmTDictionaryDetail信息
	 * @param UmTDictionaryDetailId
	 * @return UmTDictionaryDetail
	 */
	public UmTDictionaryDetail findUmTDictionaryDetailByPK(UmTDictionaryDetailId id) throws Exception;

	/**
	 * 根据umTDictionaryDetail和页码信息，获取Page对象
	 * @param umTDictionaryDetail
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTDictionaryDetail的Page对象
	 */
	public Page findByUmTDictionaryDetail(UmTDictionaryDetail umTDictionaryDetail, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新UmTDictionaryDetail信息
	 * @param UmTDictionaryDetail
	 */
	public void updateUmTDictionaryDetail(UmTDictionaryDetail umTDictionaryDetail) throws Exception;

	/**
	 * 保存UmTDictionaryDetail信息
	 * @param UmTDictionaryDetail
	 */
	public void saveUmTDictionaryDetail(UmTDictionaryDetail umTDictionaryDetail) throws Exception;

	/**
	 * 根据主键对象，删除UmTDictionaryDetail信息
	 * @param UmTDictionaryDetailId
	 */
	public void deleteByPK(UmTDictionaryDetailId id) throws Exception;
	
	
	
	/**
	 * 往数据库中批量插入数据权限明细数据
	 * @param list						数据权限明细数据List
	 * @throws Exception
	 * 2013-8-6下午3:20:07
	 * jiangweiyang
	 */
	public void insertUmTDictionaryDetailList(List<UmTDictionaryDetail> list) throws Exception;
	
	
	/**
	 * 通过传入对象List更新数据明细信息
	 * @param list
	 * @param userCode
	 * @throws Exception
	 * 2013-8-6下午5:17:30
	 * jiangweiyang
	 */
	public void updateUmTDictionaryDetailList(List<UmTDictionaryDetail> list) throws Exception;
	
	
	
	
	/**
	 * 根据传入UmTDictDetail列表删除数据权限明细数据
	 * @param list
	 * @throws Exception
	 * 2013-8-6下午6:26:24
	 * jiangweiyang
	 */
	public void deleteUmTDictionaryList(List<UmTDictionaryDetailId> list) throws Exception;
	
	
	
	
	/**
	 * 根据字典代码返回数据权限字典明细清单
	 * @param dictionaryId					字典代码
	 * @return
	 * @throws Exception
	 * 2013-8-22下午9:40:14
	 * jiangweiyang
	 */
	public List<UmTDictionaryDetail> findUmTDictionaryDetailListByDictCode(String comId,String dictionaryId) throws Exception;
	
	
	
	/**
	 * 根据字典名称返回对应字典下所有配置的字段名称
	 * @param dictName				字典名称
	 * @param userCode				用户代码
	 * @return								字段名称List
	 * @throws Exception
	 * 2013-10-18上午10:26:10
	 * jiangweiyang
	 */
	public List<String> findTargetFieldByDictName(String dictName,String userCode) throws Exception;
	
	
	
	
}
