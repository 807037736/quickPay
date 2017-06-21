/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.qp.schema.model.QpTCOMRoad;
import com.picc.qp.schema.model.QpTCOMRoadId;

public interface IQpTCOMRoadService{

	/**
	 * 根据主键对象QpTCOMRoadId获取QpTCOMRoad信息
	 * @param QpTCOMRoadId
	 * @return QpTCOMRoad
	 */
	public QpTCOMRoad findQpTCOMRoadByPK(QpTCOMRoadId id) throws Exception;

	/**
	 * 根据qpTCOMRoad和页码信息，获取Page对象
	 * @param qpTCOMRoad
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTCOMRoad的Page对象
	 */
	public Page findByQpTCOMRoad(QpTCOMRoad qpTCOMRoad, int pageNo, int pageSize) throws Exception;
	
	/**
     * 根据qpTCOMRoad获取查询列表
     * @param qpTCOMRoad
     * @param pageNo
     * @param pageSize
     * @return 包含QpTCOMRoad
     */
    public List<QpTCOMRoad> findByQpTCOMRoad(QpTCOMRoad qpTCOMRoad) throws Exception;

	/**
	 * 更新QpTCOMRoad信息
	 * @param QpTCOMRoad
	 */
	public void updateQpTCOMRoad(QpTCOMRoad qpTCOMRoad) throws Exception;

	/**
	 * 保存QpTCOMRoad信息
	 * @param QpTCOMRoad
	 */
	public void saveQpTCOMRoad(QpTCOMRoad qpTCOMRoad) throws Exception;

	/**
	 * 根据主键对象，删除QpTCOMRoad信息
	 * @param QpTCOMRoadId
	 */
	public void deleteByPK(QpTCOMRoadId id) throws Exception;
	
	public List findAllInfo() throws Exception;
	
	public List findRodeNumber(String districtId) throws Exception;
	
}
