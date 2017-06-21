/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.log.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.um.log.schema.model.LoGTTYPE;
import com.picc.um.log.schema.model.LoGTTYPEId;


/**
 * 日志类型自定义接口
 * @author 杨联
 *
 */
public interface ILoGTTYPEService{

	/**
	 * 根据主键对象LoGTTYPEId获取LoGTTYPE信息
	 * @param LoGTTYPEId
	 * @return LoGTTYPE
	 */
	public LoGTTYPE findLoGTTYPEByPK(LoGTTYPEId id) throws Exception;

	/**
	 * 根据loGTTYPE和页码信息，获取Page对象
	 * @param loGTTYPE
	 * @param pageNo
	 * @param pageSize
	 * @return 包含LoGTTYPE的Page对象
	 */
	public Page findByLoGTTYPE(LoGTTYPE loGTTYPE, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新LoGTTYPE信息
	 * @param LoGTTYPE
	 */
	public void updateLoGTTYPE(LoGTTYPE loGTTYPE) throws Exception;

	/**
	 * 保存LoGTTYPE信息
	 * @param LoGTTYPE
	 */
	public void saveLoGTTYPE(LoGTTYPE loGTTYPE) throws Exception;

	/**
	 * 根据主键对象，删除LoGTTYPE信息
	 * @param LoGTTYPEId
	 */
	public void deleteByPK(LoGTTYPEId id) throws Exception;
	
	public List<LoGTTYPE> findAllLogTypeList() throws Exception;
	
}
