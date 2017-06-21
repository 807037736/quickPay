/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.qp.schema.model.QpTCOMCity;
import com.picc.qp.schema.model.QpTCOMCityId;

public interface IQpTCOMCityService{

	/**
	 * 根据主键对象QpTCOMCityId获取QpTCOMCity信息
	 * @param QpTCOMCityId
	 * @return QpTCOMCity
	 */
	public QpTCOMCity findQpTCOMCityByPK(QpTCOMCityId id) throws Exception;

	/**
	 * 根据qpTCOMCity和页码信息，获取Page对象
	 * @param qpTCOMCity
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTCOMCity的Page对象
	 */
	public Page findByQpTCOMCity(QpTCOMCity qpTCOMCity, int pageNo, int pageSize) throws Exception;
	
	/**
     * 根据qpTCOMCity获取查询列表
     * @param qpTCOMCity
     * @param pageNo
     * @param pageSize
     * @return 包含QpTCOMCity
     */
    public List<QpTCOMCity> findByQpTCOMCity(QpTCOMCity qpTCOMCity) throws Exception;

	/**
	 * 更新QpTCOMCity信息
	 * @param QpSelectData
	 */
	public void updateQpTCOMCity(QpTCOMCity qpTCOMCity) throws Exception;

	/**
	 * 保存QpTCOMCity信息
	 * @param QpSelectData
	 */
	public void saveQpTCOMCity(QpTCOMCity qpTCOMCity) throws Exception;

	/**
	 * 根据主键对象，删除QpTCOMCity信息
	 * @param QpTCOMCityId
	 */
	public void deleteByPK(QpTCOMCityId id) throws Exception;
		
	public List findAllInfo() throws Exception;
	
}
