/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.spring;

import ins.framework.common.Page;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.common.utils.ToolsUtils;
import com.picc.qp.dao.QpTICDocumentDaoHibernate;
import com.picc.qp.schema.model.QpTICDocument;
import com.picc.qp.schema.model.QpTICDocumentId;
import com.picc.qp.service.facade.IQpTICDocumentService;


@Service("qpTICDocumentService")
public class QpTICDocumentServiceSpringImpl implements IQpTICDocumentService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private SysCommonDaoHibernate sysCommonDao;
	
	@Autowired
    private QpTICDocumentDaoHibernate qpTICDocumentDao;

	/**
	 * 根据主键对象QpTICDocumentId获取QpTICDocument信息
	 * @param QpTICDocumentId
	 * @return QpTICDocument
	 */
	public QpTICDocument findQpTICDocumentByPK(QpTICDocumentId id) throws Exception{
			return qpTICDocumentDao.get(QpTICDocument.class,id);
	}

	/**
	 * 根据qpTICDocument和页码信息，获取Page对象
	 * @param qpTICDocument
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTICDocument的Page对象
	 */
	public Page findByQpTICDocument(QpTICDocument qpTICDocument, int pageNo, int pageSize) throws Exception{
		List<Object> params = new ArrayList<Object>();
		
		String sqlStr = "select a.*,(SELECT t.CENTERNAME FROM qp_t_tp_fast_center t  WHERE t.CENTERID = a.CENTERID) centerName from Qp_T_IC_Document a where 1=1 ";
		
		if(ToolsUtils.notEmpty(qpTICDocument.getCenterId())){
			sqlStr += " and a.centerId = ? ";
			params.add(qpTICDocument.getCenterId());
		}
		
		if(qpTICDocument.getGrantTimeStar()!=null){
			sqlStr += " and a.GrantTime > ? ";
			params.add(qpTICDocument.getGrantTimeStar());
		}
		
		if(qpTICDocument.getGrantTimeEnd()!=null){
			sqlStr += " and a.GrantTime < date_add( ? , INTERVAL 1 day)  ";
			params.add(qpTICDocument.getGrantTimeEnd());
		}
		return sysCommonDao.findBySql(QpTICDocument.class,sqlStr,pageNo,pageSize,params.toArray());
	}

	/**
	 * 更新QpTICDocument信息
	 * @param QpTICDocument
	 */
	public void updateQpTICDocument(QpTICDocument qpTICDocument) throws Exception{
			qpTICDocumentDao.update(qpTICDocument);
	}

	/**
	 * 保存QpTICDocument信息
	 * @param QpTICDocument
	 */
	public void saveQpTICDocument(QpTICDocument qpTICDocument) throws Exception{
	/*	String CenterId = sysCommonDao.generateID("TPFC", "QP_SEQ_TP_FASE_CENTER", 16);
		QpTTPFastCenterId qpTTPFastCenterId = new QpTTPFastCenterId();
		qpTTPFastCenterId.setCenterId(CenterId);
		qpTTPFastCenter.setId(qpTTPFastCenterId);
		qpTTPFastCenterDao.save(qpTTPFastCenter);*/
	
		String documentId=sysCommonDao.generateID("DOCUMENT", "QP_SEQ_TP_DOCUMENT", 12);
		QpTICDocumentId qpTICDocumentId=new QpTICDocumentId();
		qpTICDocumentId.setBatchNo(documentId);
		qpTICDocument.setId(qpTICDocumentId);
		qpTICDocumentDao.save(qpTICDocument);
	}

	/**
	 * 根据主键对象，删除QpTICDocument信息
	 * @param QpTICDocumentId
	 */
	public void deleteByPK(QpTICDocumentId id) throws Exception{
			qpTICDocumentDao.deleteByPK(QpTICDocument.class,id);
	}
	
	public String findEndNo(){
		List EndNo = qpTICDocumentDao.findBySql("SELECT MAX(endNO) FROM qp_t_ic_document", new Object[]{});
		if(EndNo!= null && EndNo.size() > 0) {
			if(EndNo.get(0)!= null && !("".equals(EndNo.get(0)))) {
				return (String) EndNo.get(0);
			}
			return "00000001";
		}
		return "00000001";
	}
	
	public String toNumber(Integer num){
		//0000000 12
		//0000000 123
		String str="0000000"+num.toString();
		str=str.substring(str.length()-8, str.length());
		return str;
	}
}
