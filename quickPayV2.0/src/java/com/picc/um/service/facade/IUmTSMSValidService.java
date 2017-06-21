/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.um.schema.model.UmTSMSValid;
import com.picc.um.schema.model.UmTSMSValidId;

/**
 * 短信验证码自定义接口
 * @author 李明果
 */
public interface IUmTSMSValidService{
	
	public List<UmTSMSValid> findSMSValidByMobile(String mobile) throws Exception;

	/**
	 * 根据主键对象UmTSMSValidId获取UmTSMSValid信息
	 * @param UmTSMSValidId
	 * @return UmTSMSValid
	 */
	public UmTSMSValid findUmTSMSValidByPK(UmTSMSValidId id) throws Exception;

	/**
	 * 根据umTSMSValid和页码信息，获取Page对象
	 * @param umTSMSValid
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTSMSValid的Page对象
	 */
	public Page findByUmTSMSValid(UmTSMSValid umTSMSValid, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新UmTSMSValid信息
	 * @param UmTSMSValid
	 */
	public void updateUmTSMSValid(UmTSMSValid umTSMSValid) throws Exception;

	/**
	 * 保存UmTSMSValid信息
	 * @param UmTSMSValid
	 */
	public void saveUmTSMSValid(UmTSMSValid umTSMSValid) throws Exception;

	/**
	 * 根据主键对象，删除UmTSMSValid信息
	 * @param UmTSMSValidId
	 */
	public void deleteByPK(UmTSMSValidId id) throws Exception;
	
}
