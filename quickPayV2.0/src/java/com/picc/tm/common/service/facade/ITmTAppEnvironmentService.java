/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.tm.common.service.facade;

import ins.framework.common.Page;

import com.picc.tm.common.schema.model.TmTAppEnvironment;
import com.picc.tm.common.schema.model.TmTAppEnvironmentId;

public interface ITmTAppEnvironmentService{

	/**
	 * 根据主键对象TmTAppEnvironmentId获取TmTAppEnvironment信息
	 * @param TmTAppEnvironmentId
	 * @return TmTAppEnvironment
	 */
	public TmTAppEnvironment findTmTAppEnvironmentByPK(TmTAppEnvironmentId id) throws Exception;

	/**
	 * 根据tmTAppEnvironment和页码信息，获取Page对象
	 * @param tmTAppEnvironment
	 * @param pageNo
	 * @param pageSize
	 * @return 包含TmTAppEnvironment的Page对象
	 */
	public Page findByTmTAppEnvironment(TmTAppEnvironment tmTAppEnvironment, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新TmTAppEnvironment信息
	 * @param TmTAppEnvironment
	 */
	public void updateTmTAppEnvironment(TmTAppEnvironment tmTAppEnvironment) throws Exception;

	/**
	 * 保存TmTAppEnvironment信息
	 * @param TmTAppEnvironment
	 */
	public void saveTmTAppEnvironment(TmTAppEnvironment tmTAppEnvironment) throws Exception;

	/**
	 * 根据主键对象，删除TmTAppEnvironment信息
	 * @param TmTAppEnvironmentId
	 */
	public void deleteByPK(TmTAppEnvironmentId id) throws Exception;
	/**
	 * 根据comId获取环境代码
	 *@author    moxg    2013-9-16
	 * @param comId
	 * @return
	 */
	public String getEnvironmentCode(String comId);
	
}
