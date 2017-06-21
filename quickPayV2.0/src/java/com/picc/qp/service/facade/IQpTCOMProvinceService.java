/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.qp.schema.model.QpTCOMProvince;
import com.picc.qp.schema.model.QpTCOMProvinceId;

public interface IQpTCOMProvinceService{

	/**
	 * 根据主键对象QpTCOMProvinceId获取QpTCOMProvince信息
	 * @param QpTCOMProvinceId
	 * @return QpTCOMProvince
	 */
	public QpTCOMProvince findQpTCOMProvinceByPK(QpTCOMProvinceId id) throws Exception;

	/**
	 * 根据qpTCOMProvince和页码信息，获取Page对象
	 * @param qpTCOMProvince
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTCOMProvince的Page对象
	 */
	public Page findByQpTCOMProvince(QpTCOMProvince qpTCOMProvince, int pageNo, int pageSize) throws Exception;
	
	/**
     * 根据qpTCOMProvince查询列表
     * @param qpTCOMProvince
     * @param pageNo
     * @param pageSize
     * @return 包含QpTCOMProvince
     */
    public List<QpTCOMProvince> findByQpTCOMProvince(QpTCOMProvince qpTCOMProvince) throws Exception;

	/**
	 * 更新QpTCOMProvince信息
	 * @param QpTCOMProvince
	 */
	public void updateQpTCOMProvince(QpTCOMProvince qpTCOMProvince) throws Exception;

	/**
	 * 保存QpTCOMProvince信息
	 * @param QpTCOMProvince
	 */
	public void saveQpTCOMProvince(QpTCOMProvince qpTCOMProvince) throws Exception;

	/**
	 * 根据主键对象，删除QpTCOMProvince信息
	 * @param QpTCOMProvinceId
	 */
	public void deleteByPK(QpTCOMProvinceId id) throws Exception;
	
	public List findAllInfo() throws Exception;
	
}
