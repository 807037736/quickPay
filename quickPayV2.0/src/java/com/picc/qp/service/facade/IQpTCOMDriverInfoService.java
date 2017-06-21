/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.facade;

import ins.framework.common.Page;

import com.picc.qp.schema.model.QpTCOMDriverInfo;
import com.picc.qp.schema.model.QpTCOMDriverInfoId;

public interface IQpTCOMDriverInfoService{

	/**
	 * 根据主键对象QpTCOMDriverInfoId获取QpTCOMDriverInfo信息
	 * @param QpTCOMDriverInfoId
	 * @return QpTCOMDriverInfo
	 */
	public QpTCOMDriverInfo findQpTCOMDriverInfoByPK(QpTCOMDriverInfoId id) throws Exception;

	/**
	 * 根据qpTCOMDriverInfo和页码信息，获取Page对象
	 * @param qpTCOMDriverInfo
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTCOMDriverInfo的Page对象
	 */
	public Page findByQpTCOMDriverInfo(QpTCOMDriverInfo qpTCOMDriverInfo, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新QpTCOMDriverInfo信息
	 * @param QpTCOMDriverInfo
	 */
	public void updateQpTCOMDriverInfo(QpTCOMDriverInfo qpTCOMDriverInfo) throws Exception;

	/**
	 * 保存QpTCOMDriverInfo信息
	 * @param QpTCOMDriverInfo
	 */
	public void saveQpTCOMDriverInfo(QpTCOMDriverInfo qpTCOMDriverInfo) throws Exception;

	/**
	 * 根据主键对象，删除QpTCOMDriverInfo信息
	 * @param QpTCOMDriverInfoId
	 */
	public void deleteByPK(QpTCOMDriverInfoId id) throws Exception;
	
}
