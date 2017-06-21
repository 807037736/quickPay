/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.facade;

import java.util.List;

import com.picc.qp.schema.model.QpTCOMHandlePolice;

import ins.framework.common.Page;

public interface IQpTComHandlePoliceService{


	/**
	 * 根据QpTComHandlePolice和页码信息，获取Page对象
	 * @param QpTComHandlePolice
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTComHandlePolice的Page对象
	 */
	public Page getPageByHandlePolice(QpTCOMHandlePolice handlePolice, int pageNo, int pageSize) throws Exception;
	
	/**
     * 根据QpTComHandlePolice查询列表
     * @param QpTComHandlePolice
     * @param pageNo
     * @param pageSize
     * @return 包含QpTComHandlePolice
     */
    public List<QpTCOMHandlePolice> findByHandlePolice(QpTCOMHandlePolice handlePolice) throws Exception;
    /**
     * 根据Id查询
     * @param policeId
     * @return QpTComHandlePolice
     */
    public QpTCOMHandlePolice findById(Integer policeId) throws Exception;

	/**
	 * 更新QpTComHandlePolice信息
	 * @param QpTComHandlePolice
	 */
	public void updateHandlePolice(QpTCOMHandlePolice handlePolice) throws Exception;

	/**
	 * 保存QpTComHandlePolice信息
	 * @param QpTComHandlePolice
	 */
	public void saveHandlePolice(QpTCOMHandlePolice handlePolice) throws Exception;

	/**
	 * 根据主键对象，删除QpTComHandlePolice信息
	 * @param QpTComHandlePoliceId
	 */
	public void deleteByPK(Integer policeId) throws Exception;
	
}
