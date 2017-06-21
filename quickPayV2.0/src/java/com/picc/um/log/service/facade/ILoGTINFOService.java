/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.log.service.facade;

import ins.framework.common.Page;

import java.util.Date;

import com.picc.um.log.schema.model.LoGTINFO;
import com.picc.um.log.schema.model.LoGTINFOId;

/**
 * 日志信息自定义接口
 * @author 杨联
 */
public interface ILoGTINFOService{

	/**
	 * 根据主键对象LoGTINFOId获取LoGTINFO信息
	 * @param LoGTINFOId
	 * @return LoGTINFO
	 */
	public LoGTINFO findLoGTINFOByPK(LoGTINFOId id) throws Exception;

	/**
	 * 根据loGTINFO和页码信息，获取Page对象
	 * @param loGTINFO
	 * @param pageNo
	 * @param pageSize
	 * @return 包含LoGTINFO的Page对象
	 */
	public Page findByLoGTINFO(LoGTINFO loGTINFO, int pageNo, int pageSize) throws Exception;

	public Page findByLoGTINFO(LoGTINFO loGTINFO,Date date,int pageNo, int pageSize) throws Exception;
	/**
	 * 更新LoGTINFO信息
	 * @param LoGTINFO
	 */
	public void updateLoGTINFO(LoGTINFO loGTINFO) throws Exception;

	/**
	 * 保存LoGTINFO信息
	 * @param LoGTINFO
	 */
	public void saveLoGTINFO(LoGTINFO loGTINFO) throws Exception;

	/**
	 * 根据主键对象，删除LoGTINFO信息
	 * @param LoGTINFOId
	 */
	public void deleteByPK(LoGTINFOId id) throws Exception;
	
}
