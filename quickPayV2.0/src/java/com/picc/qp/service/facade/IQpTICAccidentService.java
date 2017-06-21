/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.facade;

import ins.framework.common.Page;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.picc.qp.schema.model.QpTICAccident;
import com.picc.qp.schema.model.QpTICAccidentId;
import com.picc.qp.schema.model.QpTTPCase;
import com.picc.qp.schema.vo.QpTTPCaseStatVO;
import com.picc.um.schema.model.UmTUser;

public interface IQpTICAccidentService{

	/**
	 * 根据主键对象QpTICAccidentId获取QpTICAccident信息
	 * @param QpTICAccidentId
	 * @return QpTICAccident
	 */
	public QpTICAccident findQpTICAccidentByPK(QpTICAccidentId id) throws Exception;

	/**
	 * 根据qpTICAccident和页码信息，获取Page对象
	 * @param qpTICAccident
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTICAccident的Page对象
	 */
	public Page findByQpTICAccident(QpTICAccident qpTICAccident, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新QpTICAccident信息
	 * @param QpTICAccident
	 */
	public void updateQpTICAccident(QpTICAccident qpTICAccident) throws Exception;

	/**
	 * 保存QpTICAccident信息
	 * @param QpTICAccident
	 */
	public Map<String,String> saveQpTICAccident(QpTTPCase qpTTPCase, QpTICAccident qpTICAccident) throws Exception;

	/**
	 * 根据主键对象，删除QpTICAccident信息
	 * @param QpTICAccidentId
	 */
	public void deleteByPK(QpTICAccidentId id) throws Exception;
	
	/**
	 * 查询当事人信息
	 * @param qpTICAccident
	 * @return
	 * @throws Exception
	 */
	public List<QpTICAccident> findQpTICAccidentInfo(QpTICAccident qpTICAccident) throws Exception;
	
    /**
     * 查询当事人列表(精简)
     * 
     * @param caseId
     * @return
     * @throws Exception
     */
    public List<QpTICAccident> findQpTICAccidentInfoOnly(String caseId) throws Exception;
	
	/**
     * 当事人列表查询
     * @param qpTICAccident
     * @return
     * @throws Exception
     */
    public Page findQpTICAccidentPageBySql(QpTICAccident qpTICAccident, int pageNo, int pageSize) throws Exception ;
    
    /**
     * 个人台账查询
     * @param qpTICAccident
     * @return
     * @throws Exception
     */
    public Page findQpTICAccidentPageBySql(QpTTPCaseStatVO qpTTPCaseStatVO, int pageNo, int pageSize) throws Exception;
    
    /**
     * 查询历史当事人信息
     * @param qpTICAccident
     * @return
     * @throws Exception
     */
    public List<QpTICAccident> queryAcciDriverInfo(QpTICAccident qpTICAccident) throws Exception;
    
    public Integer queryAcciDriverInfoCount(String number) throws Exception;
    
    public List<QpTICAccident> findAccidentByUserCode(UmTUser user) throws Exception;
    
    public boolean sendSmsToClient(List<QpTICAccident> qpTICAccidents);
    
    public boolean sendSmsToClient2(List<QpTICAccident> qpTICAccidents, JSONObject array, String serverCode);
    
    public List<QpTICAccident> findAccidentListByOpenID(String openID) throws Exception;
     
}
