/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.facade;

import ins.framework.common.Page;

import com.picc.qp.schema.model.QpTICDocument;
import com.picc.qp.schema.model.QpTICDocumentId;

public interface IQpTICDocumentService{

	/**
	 * 根据主键对象QpTICDocumentId获取QpTICDocument信息
	 * @param QpTICDocumentId
	 * @return QpTICDocument
	 */
	public QpTICDocument findQpTICDocumentByPK(QpTICDocumentId id) throws Exception;

	/**
	 * 根据qpTICDocument和页码信息，获取Page对象
	 * @param qpTICDocument
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTICDocument的Page对象
	 */
	public Page findByQpTICDocument(QpTICDocument qpTICDocument, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新QpTICDocument信息
	 * @param QpTICDocument
	 */
	public void updateQpTICDocument(QpTICDocument qpTICDocument) throws Exception;

	/**
	 * 保存QpTICDocument信息
	 * @param QpTICDocument
	 */
	public void saveQpTICDocument(QpTICDocument qpTICDocument) throws Exception;

	/**
	 * 根据主键对象，删除QpTICDocument信息
	 * @param QpTICDocumentId
	 */
	public void deleteByPK(QpTICDocumentId id) throws Exception;

	public String findEndNo();
	
	public String toNumber(Integer num);
	
}
