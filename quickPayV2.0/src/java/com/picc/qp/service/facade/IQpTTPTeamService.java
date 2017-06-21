/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.qp.schema.model.QpTTPTeam;
import com.picc.qp.schema.model.QpTTPTeamId;

public interface IQpTTPTeamService{

	/**
	 * 根据主键对象QpTTPTeamId获取QpTTPTeam信息
	 * @param QpTTPTeamId
	 * @return QpTTPTeam
	 */
	public QpTTPTeam findQpTTPTeamByPK(QpTTPTeamId id) throws Exception;

	/**
	 * 根据qpTTPTeam和页码信息，获取Page对象
	 * @param qpTTPTeam
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTTPTeam的Page对象
	 */
	public Page findByQpTTPTeam(QpTTPTeam qpTTPTeam, int pageNo, int pageSize) throws Exception;
	
	/**
     * 根据qpTTPTeam查询列表
     * @param qpTTPTeam
     * @param pageNo
     * @param pageSize
     * @return 包含QpTTPTeam
     */
    public List<QpTTPTeam> findByQpTTPTeam(QpTTPTeam qpTTPTeam) throws Exception;


	/**
	 * 更新QpTTPTeam信息
	 * @param QpTTPTeam
	 */
	public void updateQpTTPTeam(QpTTPTeam qpTTPTeam) throws Exception;

	/**
	 * 保存QpTTPTeam信息
	 * @param QpTTPTeam
	 */
	public void saveQpTTPTeam(QpTTPTeam qpTTPTeam) throws Exception;

	/**
	 * 根据主键对象，删除QpTTPTeam信息
	 * @param QpTTPTeamId
	 */
	public void deleteByPK(QpTTPTeamId id) throws Exception;
	
}
