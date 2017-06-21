/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.facade;

import ins.framework.common.Page;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.picc.qp.schema.model.QpTTPCase;
import com.picc.qp.schema.model.QpTTPCaseId;
import com.picc.qp.schema.vo.QpTTPCaseStatVO;

public interface IQpTTPCaseService{

	/**
	 * 根据主键对象QpTTPCaseId获取QpTTPCase信息
	 * @param QpTTPCaseId
	 * @return QpTTPCase
	 */
	public QpTTPCase findQpTTPCaseByPK(QpTTPCaseId id) throws Exception;

	/**
	 * 根据qpTTPCase和页码信息，获取Page对象
	 * @param qpTTPCase
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTTPCase的Page对象
	 */
	public Page findByQpTTPCase(QpTTPCase qpTTPCase, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新QpTTPCase信息
	 * @param QpTTPCase
	 */
	public void updateQpTTPCase(QpTTPCase qpTTPCase) throws Exception;
	
	/**
	 * 更新QpTTPCase信息的groupId
	 * @param QpTTPCase
	 */
	public void updateQpTTPCasePicGroupId(QpTTPCase qpTTPCase) throws Exception;

	/**
	 * 保存QpTTPCase信息
	 * @param QpTTPCase
	 */
	public void saveQpTTPCase(QpTTPCase qpTTPCase) throws Exception;

	/**
	 * 根据主键对象，删除QpTTPCase信息
	 * @param QpTTPCaseId
	 */
	public void deleteByPK(QpTTPCaseId id) throws Exception;
	
	
	/**
     * 根据主键对象QpTTPCaseId获取QpTTPCase信息
     * @param QpTTPCaseId
     * @return QpTTPCase
     */
    public QpTTPCase findQpTTPCaseById(QpTTPCase qpTTPCase) throws Exception ;
    
    /**
     * 案件列表查询
     * @param qpTTPCase
     * @return
     * @throws Exception
     */
    public Page findQpTTPCasePageBySql(QpTTPCase qpTTPCase, int pageNo, int pageSize) throws Exception ;

    /**
     * 当事人信息导出
     * @param qpTTPCase
     * @param filePath excel模版路径
     * @return
     * @throws Exception
     */
	public HSSFWorkbook getDownLoadWB(QpTTPCase qpTTPCase, String filePath)  throws Exception ;

	/**
	 * 台帐信息导出
	 * @param qpTTPCaseStatVO
	 * @param filePath	excel模版路径
	 * @return
	 */
	public HSSFWorkbook getAccountDownLoadWB(QpTTPCaseStatVO qpTTPCaseStatVO,
			String filePath)  throws Exception;
	
	public String generateSerinalNo(String readNum) throws Exception;
	
	/**
	 * 级联删除案件(事务)
	 * @param id
	 * @throws Exception
	 * @author obba
	 */
	public void deleteCaseAndAccidentByPK(QpTTPCaseId id) throws Exception;
	
	/**
	 * 封装报表datasource数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public JRDataSource getDataSourcePrint(QpTTPCase qpTTPCase) throws Exception;
	
	public QpTTPCase  saveOrupdateQpTTPCase(QpTTPCase qpTTPCase) throws Exception;
	
	public List<QpTTPCase> findCaseByWxUser(String openId) throws Exception;
	
	public List<QpTTPCase> findCaseByOpenID(String openId) throws Exception;
}
