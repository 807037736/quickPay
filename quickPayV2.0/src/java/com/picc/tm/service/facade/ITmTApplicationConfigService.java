/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2014
 */

package com.picc.tm.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.tm.schema.model.TmTApplicationConfig;
import com.picc.tm.schema.model.TmTApplicationConfigId;

public interface ITmTApplicationConfigService{

	/**
	 * 根据主键对象TmTApplicationConfigId获取TmTApplicationConfig信息
	 * @param TmTApplicationConfigId
	 * @return TmTApplicationConfig
	 */
	public TmTApplicationConfig findTmTApplicationConfigByPK(TmTApplicationConfigId id) throws Exception;

	/**
	 * 根据tmTApplicationConfig和页码信息，获取Page对象
	 * @param tmTApplicationConfig
	 * @param pageNo
	 * @param pageSize
	 * @return 包含TmTApplicationConfig的Page对象
	 */
	public Page findByTmTApplicationConfig(TmTApplicationConfig tmTApplicationConfig, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新TmTApplicationConfig信息
	 * @param TmTApplicationConfig
	 */
	public void updateTmTApplicationConfig(TmTApplicationConfig tmTApplicationConfig) throws Exception;

	/**
	 * 保存TmTApplicationConfig信息
	 * @param TmTApplicationConfig
	 */
	public void saveTmTApplicationConfig(TmTApplicationConfig tmTApplicationConfig) throws Exception;

	/**
	 * 根据主键对象，删除TmTApplicationConfig信息
	 * @param TmTApplicationConfigId
	 */
	public void deleteByPK(TmTApplicationConfigId id) throws Exception;
	
	/**
	 * 根据上下文查询服务配置表tmTApplicationConfig，获取登录跳转的页面，默认为内部登录页面
	 * @param context 上下文
	 * @return
	 * @throws Exception
	 */
	public String getPageByContext(String context) throws Exception;
	
	/**
	 * 根据上下文查询服务配置表tmTApplicationConfig
	 * @param context 上下文
	 * @return
	 * @throws Exception
	 */
	public TmTApplicationConfig getApplicationByContext(String context) throws Exception;

	public List<TmTApplicationConfig> selectSerCode() throws Exception;
	
}
