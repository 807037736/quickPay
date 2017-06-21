/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2016
 */

package com.picc.qp.service.facade;

import ins.framework.common.Page;

import com.picc.qp.schema.model.QpTFLOW;
import com.picc.qp.schema.model.QpTFLOWId;

public interface IQpTFLOWService{

	/**
	 * 根据主键对象QpTFLOWId获取QpTFLOW信息
	 * @param QpTFLOWId
	 * @return QpTFLOW
	 */
	public QpTFLOW findQpTFLOWByPK(QpTFLOWId id) throws Exception;

	/**
	 * 根据qpTFLOW和页码信息，获取Page对象
	 * @param qpTFLOW
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTFLOW的Page对象
	 */
	public Page findByQpTFLOW(QpTFLOW qpTFLOW, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新QpTFLOW信息
	 * @param QpTFLOW
	 */
	public void updateQpTFLOW(QpTFLOW qpTFLOW) throws Exception;

	/**
	 * 保存QpTFLOW信息
	 * @param QpTFLOW
	 */
	public void saveQpTFLOW(QpTFLOW qpTFLOW) throws Exception;

	/**
	 * 根据主键对象，删除QpTFLOW信息
	 * @param QpTFLOWId
	 */
	public void deleteByPK(QpTFLOWId id) throws Exception;
	
	public QpTFLOW getPolicePro(String provice,String city) throws Exception;
}
