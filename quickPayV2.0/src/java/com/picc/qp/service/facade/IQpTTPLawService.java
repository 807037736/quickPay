/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.qp.schema.model.QpTTPLaw;
import com.picc.qp.schema.model.QpTTPLawId;

public interface IQpTTPLawService{

	/**
	 * 根据主键对象QpTTPLawId获取QpTTPLaw信息
	 * @param QpTTPLawId
	 * @return QpTTPLaw
	 */
	public QpTTPLaw findQpTTPLawByPK(QpTTPLawId id) throws Exception;

	/**
	 * 根据qpTTPLaw和页码信息，获取Page对象
	 * @param qpTTPLaw
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTTPLaw的Page对象
	 */
	public Page findByQpTTPLaw(QpTTPLaw qpTTPLaw, int pageNo, int pageSize) throws Exception;
	
	/**
     * 根据qpTTPLaw和页码信息列表
     * @param qpTTPLaw
     * @param pageNo
     * @param pageSize
     * @return 包含QpTTPLaw
     */
    public List<QpTTPLaw> findByQpTTPLaw(QpTTPLaw qpTTPLaw) throws Exception;

	/**
	 * 更新QpTTPLaw信息
	 * @param QpTTPLaw
	 */
	public void updateQpTTPLaw(QpTTPLaw qpTTPLaw) throws Exception;

	/**
	 * 保存QpTTPLaw信息
	 * @param QpTTPLaw
	 */
	public void saveQpTTPLaw(QpTTPLaw qpTTPLaw) throws Exception;

	/**
	 * 根据主键对象，删除QpTTPLaw信息
	 * @param QpTTPLawId
	 */
	public void deleteByPK(QpTTPLawId id) throws Exception;
	
}
