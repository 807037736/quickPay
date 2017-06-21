/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.qp.schema.model.QpTICCompany;
import com.picc.qp.schema.model.QpTICCompanyId;

public interface IQpTICCompanyService{

	/**
	 * 根据主键对象QpTICCompanyId获取QpTICCompany信息
	 * @param QpTICCompanyId
	 * @return QpTICCompany
	 */
	public QpTICCompany findQpTICCompanyByPK(QpTICCompanyId id) throws Exception;

	/**
	 * 根据qpTICCompany和页码信息，获取Page对象
	 * @param qpTICCompany
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTICCompany的Page对象
	 */
	public Page findByQpTICCompany(QpTICCompany qpTICCompany, int pageNo, int pageSize) throws Exception;
	
	/**
     * 根据qpTICCompany查询列表
     * @param qpTICCompany
     * @param pageNo
     * @param pageSize
     * @return 包含QpTICCompany
     */
    public List<QpTICCompany> findByQpTICCompany(QpTICCompany qpTICCompany) throws Exception;

	/**
	 * 更新QpTICCompany信息
	 * @param QpTICCompany
	 */
	public void updateQpTICCompany(QpTICCompany qpTICCompany) throws Exception;

	/**
	 * 保存QpTICCompany信息
	 * @param QpTICCompany
	 */
	public void saveQpTICCompany(QpTICCompany qpTICCompany) throws Exception;

	/**
	 * 根据主键对象，删除QpTICCompany信息
	 * @param QpTICCompanyId
	 */
	public void deleteByPK(QpTICCompanyId id) throws Exception;
	
}
