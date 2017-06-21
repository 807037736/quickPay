/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.tm.common.service.facade;

import ins.framework.common.Page;

import com.picc.tm.common.schema.model.TmTSystemConfig;
import com.picc.tm.common.schema.model.TmTSystemConfigId;
import com.picc.um.schema.vo.SessionUser;

public interface ITmTSystemConfigService{

	/**
	 * 根据主键对象TmTSystemConfigId获取TmTSystemConfig信息
	 * @param TmTSystemConfigId
	 * @return TmTSystemConfig
	 */
	public TmTSystemConfig findTmTSystemConfigByPK(TmTSystemConfigId id) throws Exception;

	/**
	 * 根据tmTSystemConfig和页码信息，获取Page对象
	 * @param tmTSystemConfig
	 * @param pageNo
	 * @param pageSize
	 * @return 包含TmTSystemConfig的Page对象
	 */
	public Page findByTmTSystemConfig(TmTSystemConfig tmTSystemConfig, int pageNo, int pageSize,SessionUser sessionUser) throws Exception;

	/**
	 * 根据tmTSystemConfig和页码信息，获取Page对象
	 * @param tmTSystemConfig
	 * @param pageNo
	 * @param pageSize
	 * @return 包含TmTSystemConfig的Page对象
	 */
	public Page findByTmTSystemConfig(TmTSystemConfig tmTSystemConfig, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新TmTSystemConfig信息
	 * @param TmTSystemConfig
	 */
	public void updateTmTSystemConfig(TmTSystemConfig tmTSystemConfig) throws Exception;

	/**
	 * 保存TmTSystemConfig信息
	 * @param TmTSystemConfig
	 */
	public void saveTmTSystemConfig(TmTSystemConfig tmTSystemConfig) throws Exception;

	/**
	 * 根据主键对象，删除TmTSystemConfig信息
	 * @param TmTSystemConfigId
	 */
	public void deleteByPK(TmTSystemConfigId id) throws Exception;
	
	/**
	 * 获取系统参数配置 TmTSystemConfig 对象
	 * @param tmTSystemConfig
	 * @return
	 * @throws Exception
	 */
	public TmTSystemConfig findByTmTSystemConfig(TmTSystemConfig tmTSystemConfig) throws Exception;
	
	/**
	 * 根据归属机构代码以及配置项代码获取配置信息
	 *@author    moxg    2013-8-30
	 *@update    zhb
	 * @param comCode
	 * @param searchUpper
	 * @param configCode
	 * @return
	 */
	public TmTSystemConfig findByConfigCode(String comCode,String configCode,boolean searchUpper);
	/**
	 * 生成更新Id
	 *@author    moxg    2013-8-30
	 * @return
	 */
	public String genId(String comId4);

	/**
	 * 查询归属机构 （返回各个级机构）
	 * @return
	 * @throws Exception 
	 */
	public String[] queryUpperCompany(String comCode,int pageNo,int pageSize) throws Exception;
	
	/**
	 * 根据归属机构代码以及配置项代码获取配置信息
	 *@author    moxg    2013-8-30
	 *@update    zhb
	 * @param comCode
	 * @param searchUpper
	 * @param configCode
	 * @return
	 */
	public String findConfigValueByConfigCode(String comCode,String configCode,boolean searchUpper);
}
