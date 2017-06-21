/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.spring;

import java.util.Date;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.utils.ToolsUtils;
import com.picc.qp.dao.QpTComHandlePoliceDaoHibernate;
import com.picc.qp.schema.model.QpTCOMHandlePolice;
import com.picc.qp.schema.model.QpTTPFastCenter;
import com.picc.qp.schema.model.QpTTPFastCenterId;
import com.picc.qp.service.facade.IQpTComHandlePoliceService;
import com.picc.qp.service.facade.IQpTTPFastCenterService;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;


@Service("qpTComHandlePoliceService")
public class QpTComHandlePoliceServiceSpringImpl implements IQpTComHandlePoliceService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private QpTComHandlePoliceDaoHibernate qpTComHandlePoliceDao;
	@Autowired
	private IQpTTPFastCenterService qpTTPFastCenterService;


	/**
	 * 根据QpTComPolice和页码信息，获取Page对象
	 * @param QpTComPolice
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTComPolice的Page对象
	 */
	public Page getPageByHandlePolice(QpTCOMHandlePolice qpTComPolice, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		//根据QpTComPolice生成queryRule
		if(ToolsUtils.notEmpty(qpTComPolice.getHandlePoliceName())){
			queryRule.addEqual("handlePoliceName", qpTComPolice.getHandlePoliceName());
		}
		if(ToolsUtils.notEmpty(qpTComPolice.getCenterId())){
			queryRule.addEqual("centerId", qpTComPolice.getCenterId());
		}
		if(ToolsUtils.notEmpty(qpTComPolice.getHandlePoliceNO())){
			queryRule.addEqual("handlePoliceNO", qpTComPolice.getHandlePoliceNO());
		}
		if(ToolsUtils.notEmpty(qpTComPolice.getHandlePolicePhone())){
			queryRule.addEqual("handlePolicePhone", qpTComPolice.getHandlePolicePhone());
		}
		queryRule.addAscOrder("createTime");
		return qpTComHandlePoliceDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 根据QpTComPolice查询列表
	 * @param QpTComPolice
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTComPolice
	 */
	public List<QpTCOMHandlePolice> findByHandlePolice(QpTCOMHandlePolice qpTComPolice) throws Exception {
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		
		if(!StringUtils.isEmpty(qpTComPolice.getCenterId())){
			queryRule.addEqual("centerId",qpTComPolice.getCenterId());
		}
		if(!StringUtils.isEmpty(qpTComPolice.getCenterName())){
			queryRule.addEqual("centerName",qpTComPolice.getCenterName());
		}
		if(!StringUtils.isEmpty(qpTComPolice.getCreateCode())){
			queryRule.addEqual("creatorCode",qpTComPolice.getCreateCode());
		}
		if(!StringUtils.isEmpty(qpTComPolice.getFlag())){
			queryRule.addEqual("flag",qpTComPolice.getFlag());
		}
		if(!StringUtils.isEmpty(qpTComPolice.getOrganization())){
			queryRule.addEqual("organization",qpTComPolice.getOrganization());
		}
		if(!StringUtils.isEmpty(qpTComPolice.getHandlePoliceName())){
			queryRule.addEqual("handlePoliceName",qpTComPolice.getHandlePoliceName());
		}
		if(!StringUtils.isEmpty(qpTComPolice.getHandlePoliceNO())){
			queryRule.addEqual("handlePoliceNO",qpTComPolice.getHandlePoliceNO());
		}
		if(!StringUtils.isEmpty(qpTComPolice.getHandlePolicePhone())){
			queryRule.addEqual("handlePolicePhone",qpTComPolice.getHandlePolicePhone());
		}
		if(!StringUtils.isEmpty(qpTComPolice.getUpdaterCode())){
			queryRule.addEqual("updaterCode",qpTComPolice.getUpdaterCode());
		}
		if(!StringUtils.isEmpty(qpTComPolice.getValidStatus())){
			queryRule.addEqual("validStatus",qpTComPolice.getValidStatus());
		}
		if(qpTComPolice.getHandlePoliceId() != null && qpTComPolice.getHandlePoliceId() != 0){
			queryRule.addEqual("handlePoliceId",qpTComPolice.getHandlePoliceId());
		}
		queryRule.addAscOrder("createTime");
		return qpTComHandlePoliceDao.find(queryRule);
	}

	/**
	 * 更新QpTComPolice信息
	 * @param QpTComPolice
	 */
	public void updateHandlePolice(QpTCOMHandlePolice qpTComPolice) throws Exception{
		qpTComPolice.setUpdaterTime(new Date());
		qpTComHandlePoliceDao.update(qpTComPolice);
	}

	/**
	 * 保存QpTComPolice信息
	 * @param QpTComPolice
	 */
	public void saveHandlePolice(QpTCOMHandlePolice qpTComPolice) throws Exception{
		qpTComPolice.setCreateTime(new Date());
		qpTComHandlePoliceDao.save(qpTComPolice);	
	}
	
	
	/**
	 * 根据主键对象，删除QpTComPolice信息
	 * @param QpTCOMPoliceId
	 */
	public void deleteByPK(Integer handlePoliceId) throws Exception{
		qpTComHandlePoliceDao.deleteByPK(QpTCOMHandlePolice.class,handlePoliceId);
	}
	
	public QpTCOMHandlePolice findById(Integer handlePoliceId) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		if(handlePoliceId != null && handlePoliceId != 0){
			queryRule.addEqual("handlePoliceId",handlePoliceId);
			return qpTComHandlePoliceDao.findUnique(queryRule);
		}else{
			return null;
		}
	}
	
	
}
