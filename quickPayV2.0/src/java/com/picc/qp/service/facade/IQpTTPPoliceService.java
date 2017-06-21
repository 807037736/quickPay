/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.qp.schema.model.QpTTPPolice;
import com.picc.qp.schema.model.QpTTPPoliceId;

public interface IQpTTPPoliceService{

	/**
	 * 根据主键对象QpTTPPoliceId获取QpTTPPolice信息
	 * @param QpTTPPoliceId
	 * @return QpTTPPolice
	 */
	public QpTTPPolice findQpTTPPoliceByPK(QpTTPPoliceId id) throws Exception;

	/**
	 * 根据qpTTPPolice和页码信息，获取Page对象
	 * @param qpTTPPolice
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTTPPolice的Page对象
	 */
	public Page findByQpTTPPolice(QpTTPPolice qpTTPPolice, int pageNo, int pageSize) throws Exception;
	
	/**
     * 根据qpTTPPolice
     * @param qpTTPPolice
     * @param pageNo
     * @param pageSize
     * @return 包含QpTTPPolice
     */
    public List<QpTTPPolice> findByQpTTPPolice(QpTTPPolice qpTTPPolice) throws Exception;

	/**
	 * 更新QpTTPPolice信息
	 * @param QpTTPPolice
	 */
	public void updateQpTTPPolice(QpTTPPolice qpTTPPolice) throws Exception;

	/**
	 * 保存QpTTPPolice信息
	 * @param QpTTPPolice
	 */
	public void saveQpTTPPolice(QpTTPPolice qpTTPPolice) throws Exception;

	/**
	 * 根据主键对象，删除QpTTPPolice信息
	 * @param QpTTPPoliceId
	 */
	public void deleteByPK(QpTTPPoliceId id) throws Exception;
	
}
