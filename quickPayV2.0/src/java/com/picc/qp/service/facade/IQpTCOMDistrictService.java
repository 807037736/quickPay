/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.qp.schema.model.QpTCOMDistrict;
import com.picc.qp.schema.model.QpTCOMDistrictId;

public interface IQpTCOMDistrictService{

	/**
	 * 根据主键对象QpTCOMDistrictId获取QpTCOMDistrict信息
	 * @param QpTCOMDistrictId
	 * @return QpTCOMDistrict
	 */
	public QpTCOMDistrict findQpTCOMDistrictByPK(QpTCOMDistrictId id) throws Exception;

	/**
	 * 根据qpTCOMDistrict和页码信息，获取Page对象
	 * @param qpTCOMDistrict
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTCOMDistrict的Page对象
	 */
	public Page findByQpTCOMDistrict(QpTCOMDistrict qpTCOMDistrict, int pageNo, int pageSize) throws Exception;
	
	/**
     * 根据qpTCOMDistrict获取查询列表
     * @param qpTCOMDistrict
     * @param pageNo
     * @param pageSize
     * @return 包含QpTCOMDistrict
     */
    public List<QpTCOMDistrict> findByQpTCOMDistrict(QpTCOMDistrict qpTCOMDistrict) throws Exception;

	/**
	 * 更新QpTCOMDistrict信息
	 * @param QpTCOMDistrict
	 */
	public void updateQpTCOMDistrict(QpTCOMDistrict qpTCOMDistrict) throws Exception;

	/**
	 * 保存QpTCOMDistrict信息
	 * @param QpTCOMDistrict
	 */
	public void saveQpTCOMDistrict(QpTCOMDistrict qpTCOMDistrict) throws Exception;

	/**
	 * 根据主键对象，删除QpTCOMDistrict信息
	 * @param QpTCOMDistrictId
	 */
	public void deleteByPK(QpTCOMDistrictId id) throws Exception;
	
	public List findAllInfo() throws Exception;
	
}
