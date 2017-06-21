/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2016
 */

package com.picc.qp.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.common.utils.ToolsUtils;
import com.picc.qp.dao.QpTFLOWDaoHibernate;
import com.picc.qp.schema.model.QpTFLOW;
import com.picc.qp.schema.model.QpTFLOWId;
import com.picc.qp.service.facade.IQpTFLOWService;


@Service("qpTFLOWService")
public class QpTFLOWServiceSpringImpl implements IQpTFLOWService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private SysCommonDaoHibernate sysCommonDao;
	
	
	
	public SysCommonDaoHibernate getSysCommonDao() {
		return sysCommonDao;
	}

	public void setSysCommonDao(SysCommonDaoHibernate sysCommonDao) {
		this.sysCommonDao = sysCommonDao;
	}

	@Autowired
    private QpTFLOWDaoHibernate qpTFLOWDao;

	/**
	 * 根据主键对象QpTFLOWId获取QpTFLOW信息
	 * @param QpTFLOWId
	 * @return QpTFLOW
	 */
	public QpTFLOW findQpTFLOWByPK(QpTFLOWId id) throws Exception{
			return qpTFLOWDao.get(QpTFLOW.class,id);
	}

	/**
	 * 根据qpTFLOW和页码信息，获取Page对象
	 * @param qpTFLOW
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTFLOW的Page对象
	 */
	public Page findByQpTFLOW(QpTFLOW qpTFLOW, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据qpTFLOW生成queryRule
		if(ToolsUtils.notEmpty(qpTFLOW.getProviceId())){
			queryRule.addEqual("proviceId", qpTFLOW.getProviceId());
		}
		if(ToolsUtils.notEmpty(qpTFLOW.getCityId())){
			queryRule.addEqual("cityId", qpTFLOW.getCityId());
		}
		if(ToolsUtils.notEmpty(qpTFLOW.getPolicePro())){
			queryRule.addEqual("policePro", qpTFLOW.getPolicePro());
		}
		if(ToolsUtils.notEmpty(qpTFLOW.getValidStatus())){
			queryRule.addEqual("validStatus", qpTFLOW.getValidStatus());
		}
		
		return qpTFLOWDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新QpTFLOW信息
	 * @param QpTFLOW
	 */
	public void updateQpTFLOW(QpTFLOW qpTFLOW) throws Exception{
			qpTFLOWDao.update(qpTFLOW);
	}

	/**
	 * 保存QpTFLOW信息
	 * @param QpTFLOW
	 */
	public void saveQpTFLOW(QpTFLOW qpTFLOW) throws Exception{
		
		/*String CenterId = sysCommonDao.generateID("TPFC", "QP_SEQ_TP_FASE_CENTER", 16);
		QpTTPFastCenterId qpTTPFastCenterId = new QpTTPFastCenterId();
		qpTTPFastCenterId.setCenterId(CenterId);
		qpTTPFastCenter.setId(qpTTPFastCenterId);
		qpTTPFastCenterDao.save(qpTTPFastCenter);*/
		
		String flowId = sysCommonDao.generateID("FLOW", "QP_T_FLOW", 10);
		QpTFLOWId qpTFLOWId=new QpTFLOWId();
		qpTFLOWId.setFlowId(flowId);
		qpTFLOW.setId(qpTFLOWId);
		qpTFLOWDao.save(qpTFLOW);
	}

	/**
	 * 根据主键对象，删除QpTFLOW信息
	 * @param QpTFLOWId
	 */
	public void deleteByPK(QpTFLOWId id) throws Exception{
			qpTFLOWDao.deleteByPK(QpTFLOW.class,id);
	}
	
	public QpTFLOW getPolicePro(String provice,String city){
		QueryRule queryRule=QueryRule.getInstance();
		queryRule.addEqual("proviceId", provice);
		queryRule.addEqual("cityId", city);
		List<QpTFLOW> list = qpTFLOWDao.find(queryRule);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}
}
