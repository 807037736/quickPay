package com.picc.qp.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.qp.schema.model.QpTAsyncTaskErr;
import com.picc.qp.schema.model.QpTAsyncTaskErrId;

public interface IQpTAsyncTaskErrService{

	/**
	 * 根据主键对象QpTAsyncTaskErrId获取QpTAsyncTaskErr信息
	 * @param QpTAsyncTaskErrId
	 * @return QpTAsyncTaskErr
	 */
	public QpTAsyncTaskErr findQpTAsyncTaskErrByPK(QpTAsyncTaskErrId id) throws Exception;
	/**
	 * 根据QpTAsyncTaskErr和页码信息，获取Page对象
	 * @param QpTAsyncTaskErr
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTAsyncTaskErr的Page对象
	 */
	public Page findByQpTAsyncTaskErr(QpTAsyncTaskErr qpTAsyncTaskErr, int pageNo, int pageSize) throws Exception;
	
	/**
     * 根据QpTAsyncTaskErr获取查询列表
     * @param QpTAsyncTaskErr
     * @return 包含QpTAsyncTaskErr
     */
    public List<QpTAsyncTaskErr> findByQpTAsyncTaskErr(QpTAsyncTaskErr qpTAsyncTaskErr) throws Exception;

	/**
	 * 更新QpTAsyncTaskErr信息
	 * @param QpTAsyncTaskErr
	 */
	public void updateQpTAsyncTaskErr(QpTAsyncTaskErr qpTAsyncTaskErr) throws Exception;

	/**
	 * 保存QpTAsyncTaskErr信息
	 * @param QpTAsyncTaskErr
	 */
	public void saveQpTAsyncTaskErr(QpTAsyncTaskErr qpTAsyncTaskErr) throws Exception;

	/**
	 * 根据主键对象，删除QpTAsyncTaskErr信息
	 * @param QpTAsyncTaskErrId
	 */
	public void deleteByPK(QpTAsyncTaskErrId id) throws Exception;
		
	public List<QpTAsyncTaskErr> findAllInfo() throws Exception;
	
	public List<QpTAsyncTaskErr> getFailTaskListByType(String type,Integer pageSize) throws Exception;
	
}
