package com.picc.qp.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.qp.schema.model.QpTAsyncTaskSucc;
import com.picc.qp.schema.model.QpTAsyncTaskSuccId;

public interface IQpTAsyncTaskSuccService{

	/**
	 * 根据主键对象QpTAsyncTaskSuccId获取QpTAsyncTaskSucc信息
	 * @param QpTAsyncTaskSuccId
	 * @return QpTAsyncTaskSucc
	 */
	public QpTAsyncTaskSucc findQpTAsyncTaskSuccByPK(QpTAsyncTaskSuccId id) throws Exception;
	/**
	 * 根据QpTAsyncTaskSucc和页码信息，获取Page对象
	 * @param QpTAsyncTaskSucc
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTAsyncTaskSucc的Page对象
	 */
	public Page findByQpTAsyncTaskSucc(QpTAsyncTaskSucc qpTAsyncTaskSucc, int pageNo, int pageSize) throws Exception;
	
	/**
     * 根据QpTAsyncTaskSucc获取查询列表
     * @param QpTAsyncTaskSucc
     * @return 包含QpTAsyncTaskSucc
     */
    public List<QpTAsyncTaskSucc> findByQpTAsyncTaskSucc(QpTAsyncTaskSucc qpTAsyncTaskSucc) throws Exception;

	/**
	 * 更新QpTAsyncTaskSucc信息
	 * @param QpTAsyncTaskSucc
	 */
	public void updateQpTAsyncTaskSucc(QpTAsyncTaskSucc qpTAsyncTaskSucc) throws Exception;

	/**
	 * 保存QpTAsyncTaskSucc信息
	 * @param QpTAsyncTaskSucc
	 */
	public void saveQpTAsyncTaskSucc(QpTAsyncTaskSucc qpTAsyncTaskSucc) throws Exception;

	/**
	 * 根据主键对象，删除QpTAsyncTaskSucc信息
	 * @param QpTAsyncTaskSuccId
	 */
	public void deleteByPK(QpTAsyncTaskSuccId id) throws Exception;
		
	public List<QpTAsyncTaskSucc> findAllInfo() throws Exception;
	
}
