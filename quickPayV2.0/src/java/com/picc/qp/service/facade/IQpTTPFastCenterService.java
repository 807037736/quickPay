/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.qp.schema.model.QpTTPFastCenter;
import com.picc.qp.schema.model.QpTTPFastCenterId;
import com.picc.qp.schema.model.QpTTPFastCentercompare;
import com.picc.um.schema.model.UmTUser;

public interface IQpTTPFastCenterService{

	/**
	 * 根据主键对象QpTTPFastCenterId获取QpTTPFastCenter信息
	 * @param QpTTPFastCenterId
	 * @return QpTTPFastCenter
	 */
	public QpTTPFastCenter findQpTTPFastCenterByPK(QpTTPFastCenterId id) throws Exception;

	/**
	 * 根据qpTTPFastCenter和页码信息，获取Page对象
	 * @param qpTTPFastCenter
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTTPFastCenter的Page对象
	 */
	public Page findByQpTTPFastCenter(QpTTPFastCenter qpTTPFastCenter, int pageNo, int pageSize) throws Exception;
	
	/**
     * 根据qpTTPFastCenter获取列表
     * @param qpTTPFastCenter
     * @param pageNo
     * @param pageSize
     * @return 包含QpTTPFastCenter的Page对象
     */
    public List<QpTTPFastCenter> findByQpTTPFastCenter(QpTTPFastCenter qpTTPFastCenter) throws Exception;

	/**
	 * 更新QpTTPFastCenter信息
	 * @param QpTTPFastCenter
	 */
	public void updateQpTTPFastCenter(QpTTPFastCenter qpTTPFastCenter) throws Exception;

	/**
	 * 保存QpTTPFastCenter信息
	 * @param QpTTPFastCenter
	 */
	public void saveQpTTPFastCenter(QpTTPFastCenter qpTTPFastCenter) throws Exception;

	/**
	 * 根据主键对象，删除QpTTPFastCenter信息
	 * @param QpTTPFastCenterId
	 */
	public void deleteByPK(QpTTPFastCenterId id) throws Exception;
	
	/**
	 * 保存QpTTPFastCenterCompare信息
	 * @param QpTTPFastCenter
	 */
	public void saveQpTTPFastCenterCompare(UmTUser umTUser) throws Exception;
	
	/**
	 * 修改QpTTPFastCenterCompare信息
	 * @param QpTTPFastCenter
	 */
	public void updateQpTTPFastCenterCompare(UmTUser umTUser) throws Exception;
	
	/**
	 * 查询QpTTPFastCenterCompare信息
	 * @param QpTTPFastCenter
	 */
	public List<QpTTPFastCentercompare> findQpTTPFastCenterCompare(UmTUser umTUser) throws Exception;
	
}
