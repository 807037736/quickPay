/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.tm.common.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import com.picc.tm.common.schema.model.TmTAppServiceConfig;
import com.picc.tm.common.schema.model.TmTAppServiceConfigId;

public interface ITmTAppServiceConfigService{

	/**
	 * 根据主键对象TmTAppServiceConfigId获取TmTAppServiceConfig信息
	 * @param TmTAppServiceConfigId
	 * @return TmTAppServiceConfig
	 */
	public TmTAppServiceConfig findTmTAppServiceConfigByPK(TmTAppServiceConfigId id) throws Exception;

	/**
	 * 根据tmTAppServiceConfig和页码信息，获取Page对象
	 * @param tmTAppServiceConfig
	 * @param pageNo
	 * @param pageSize
	 * @return 包含TmTAppServiceConfig的Page对象
	 */
	public Page findByTmTAppServiceConfig(TmTAppServiceConfig tmTAppServiceConfig, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新TmTAppServiceConfig信息
	 * @param TmTAppServiceConfig
	 */
	public void updateTmTAppServiceConfig(TmTAppServiceConfig tmTAppServiceConfig) throws Exception;

	/**
	 * 保存TmTAppServiceConfig信息
	 * @param TmTAppServiceConfig
	 */
	public void saveTmTAppServiceConfig(TmTAppServiceConfig tmTAppServiceConfig) throws Exception;

	/**
	 * 根据主键对象，删除TmTAppServiceConfig信息
	 * @param TmTAppServiceConfigId
	 */
	public void deleteByPK(TmTAppServiceConfigId id) throws Exception;
	/**
	 * 根据serviceCode以及environmentCode获取服务的地址
	 *@author    moxg    2013-9-16
	 * @param serverCode
	 * @param environmentCode
	 * @return
	 */
	public String getServiceUrl(String serverCode,String environmentCode);
	
	public  String getUrl(TmTAppServiceConfig tmTAppServiceConfig);
	
	/**
	 * 
	* @Title: findListByQueryRule
	* @Description:	根据QueryRule查询满足条件数据List
	* @param 查询条件QueryRule
	* @return List<TmTAppServiceConfig>   数据List集合
	* @throws					程序运行中抛出的异常信息
	 */
	public List<TmTAppServiceConfig> findListByQueryRule(QueryRule rule) throws Exception;
	
	public List<TmTAppServiceConfig> findAll()throws Exception;
	
}
