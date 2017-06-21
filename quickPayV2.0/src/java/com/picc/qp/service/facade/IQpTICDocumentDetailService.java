/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.facade;

import ins.framework.common.Page;

import com.picc.qp.schema.model.QpTICDocumentDetail;
import com.picc.qp.schema.model.QpTICDocumentDetailId;

public interface IQpTICDocumentDetailService{

	/**
	 * 根据主键对象QpTICDocumentDetailId获取QpTICDocumentDetail信息
	 * @param QpTICDocumentDetailId
	 * @return QpTICDocumentDetail
	 */
	public QpTICDocumentDetail findQpTICDocumentDetailByPK(QpTICDocumentDetailId id) throws Exception;

	/**
	 * 根据qpTICDocumentDetail和页码信息，获取Page对象
	 * @param qpTICDocumentDetail
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTICDocumentDetail的Page对象
	 */
	public Page findByQpTICDocumentDetail(QpTICDocumentDetail qpTICDocumentDetail, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新QpTICDocumentDetail信息
	 * @param QpTICDocumentDetail
	 */
	public void updateQpTICDocumentDetail(QpTICDocumentDetail qpTICDocumentDetail) throws Exception;

	/**
	 * 保存QpTICDocumentDetail信息
	 * @param QpTICDocumentDetail
	 */
	public void saveQpTICDocumentDetail(QpTICDocumentDetail qpTICDocumentDetail) throws Exception;

	/**
	 * 根据主键对象，删除QpTICDocumentDetail信息
	 * @param QpTICDocumentDetailId
	 */
	public void deleteByPK(QpTICDocumentDetailId id) throws Exception;
	
	
	public QpTICDocumentDetail findDetial(String documentNo , String centerId) throws Exception;
	
}
