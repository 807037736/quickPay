/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.facade;

import java.util.List;

import com.picc.qp.schema.model.QpTICAccidentProject;
import com.picc.qp.schema.model.QpTICAccidentProjectId;

public interface IQpTICAccidentProjectService{

	/**
	 * 根据主键对象QpTICAccidentProjectId获取QpTICAccidentProject信息
	 * @param QpTICAccidentProjectId
	 * @return QpTICAccidentProject
	 */
	public QpTICAccidentProject findQpTICAccidentProjectByPK(QpTICAccidentProjectId id) throws Exception;

     
	/**
	 * 根据主键对象QpTICAccidentProjectId获取QpTICAccidentProject信息
	 * @param QpTICAccidentProjectId
	 * @return QpTICAccidentProject
	 */
	public List<QpTICAccidentProject> findQpTICAccidentProjectByPK(QpTICAccidentProject qpTICAccidentProject) throws Exception;

	
	/**
	 * 更新QpTICAccidentProject信息
	 * @param QpTICAccidentProject
	 */
	public void updateQpTICAccidentProject(QpTICAccidentProject qpTICAccidentProject) throws Exception;
	
	/**
	 * 更新QpTICAccidentProject信息
	 * @param QpTICAccidentProject
	 */
	public void saveQpTICAccidentProject(QpTICAccidentProject qpTICAccidentProject) throws Exception;
     
	/**
	 * 根据caseId，删除QpTICAccidentProject信息
	 * @param caseId
	 */
	public void deleteByCaseId(String caseId, String acciId) throws Exception;
}
