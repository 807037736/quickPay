/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.common.utils.ToolsUtils;
import com.picc.qp.dao.QpTICDocumentDetailDaoHibernate;
import com.picc.qp.schema.model.QpTICDocumentDetail;
import com.picc.qp.schema.model.QpTICDocumentDetailId;
import com.picc.qp.service.facade.IQpTICDocumentDetailService;


@Service("qpTICDocumentDetailService")
public class QpTICDocumentDetailServiceSpringImpl implements IQpTICDocumentDetailService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private QpTICDocumentDetailDaoHibernate qpTICDocumentDetailDao;
	
	@Autowired
    private SysCommonDaoHibernate sysCommonDao;

	/**
	 * 根据主键对象QpTICDocumentDetailId获取QpTICDocumentDetail信息
	 * @param QpTICDocumentDetailId
	 * @return QpTICDocumentDetail
	 */
	public QpTICDocumentDetail findQpTICDocumentDetailByPK(QpTICDocumentDetailId id) throws Exception{
			return qpTICDocumentDetailDao.get(QpTICDocumentDetail.class,id);
	}

	/**
	 * 根据qpTICDocumentDetail和页码信息，获取Page对象
	 * @param qpTICDocumentDetail
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTICDocumentDetail的Page对象
	 */
	public Page findByQpTICDocumentDetail(QpTICDocumentDetail qpTICDocumentDetail, int pageNo, int pageSize) throws Exception{
		List<Object> params = new ArrayList<Object>();
		//根据qpTICDocumentDetail生成queryRule
//		if(ToolsUtils.notEmpty(qpTICDocumentDetail.getCenterId())){
//			queryRule.addEqual("centerId", qpTICDocumentDetail.getCenterId());
//		}
//		if(ToolsUtils.notEmpty(qpTICDocumentDetail.getDocumentFlag())){
//			queryRule.addEqual("documentFlag", qpTICDocumentDetail.getDocumentFlag());
//		}
		//return qpTICDocumentDetailDao.find(queryRule, pageNo, pageSize);
		
		String sqlStr = "select a.*,(SELECT t.CENTERNAME FROM qp_t_tp_fast_center t  WHERE t.CENTERID = a.CENTERID) centerName from Qp_T_IC_Document_Detail a where 1=1 ";
		
		if(ToolsUtils.notEmpty(qpTICDocumentDetail.getCenterId())){
			sqlStr += " and a.centerId = ? ";
			params.add(qpTICDocumentDetail.getCenterId());
		}
		if(ToolsUtils.notEmpty(qpTICDocumentDetail.getDocumentFlag())){
			sqlStr += " and a.documentFlag = ? ";
			params.add(qpTICDocumentDetail.getDocumentFlag());
		}
		
		return sysCommonDao.findBySql(QpTICDocumentDetail.class,sqlStr,pageNo,pageSize,params.toArray());
	}

	/**
	 * 更新QpTICDocumentDetail信息
	 * @param QpTICDocumentDetail
	 */
	public void updateQpTICDocumentDetail(QpTICDocumentDetail qpTICDocumentDetail) throws Exception{
			qpTICDocumentDetailDao.update(qpTICDocumentDetail);
	}

	/**
	 * 保存QpTICDocumentDetail信息
	 * @param QpTICDocumentDetail
	 */
	public void saveQpTICDocumentDetail(QpTICDocumentDetail qpTICDocumentDetail) throws Exception{
		/*	String CenterId = sysCommonDao.generateID("TPFC", "QP_SEQ_TP_FASE_CENTER", 16);
		QpTTPFastCenterId qpTTPFastCenterId = new QpTTPFastCenterId();
		qpTTPFastCenterId.setCenterId(CenterId);
		qpTTPFastCenter.setId(qpTTPFastCenterId);
		qpTTPFastCenterDao.save(qpTTPFastCenter);*/
			qpTICDocumentDetailDao.save(qpTICDocumentDetail);
	}

	/**
	 * 根据主键对象，删除QpTICDocumentDetail信息
	 * @param QpTICDocumentDetailId
	 */
	public void deleteByPK(QpTICDocumentDetailId id) throws Exception{
			qpTICDocumentDetailDao.deleteByPK(QpTICDocumentDetail.class,id);
	}
	
	public QpTICDocumentDetail findDetial(String documentNo , String centerId){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("id.documentNo", documentNo);
		queryRule.addEqual("centerId", centerId);
		queryRule.addEqual("documentFlag", "0");
		
		List<QpTICDocumentDetail> list=qpTICDocumentDetailDao.find(queryRule);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
