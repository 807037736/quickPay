/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.spring;

import ins.framework.common.QueryRule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.qp.dao.QpTIcCompanyGarageDaoHibernate;
import com.picc.qp.schema.model.QpTIcCompanyGarage;
import com.picc.qp.schema.model.QpTIcCompanyGarageId;
import com.picc.qp.service.facade.IQpTIcCompanyGarageService;


@Service("qpTIcCompanyGarageService")
public class QpTIcCompanyGarageServiceSpringImpl implements IQpTIcCompanyGarageService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysCommonDaoHibernate sysCommonDao;

	@Autowired
	private CommonDaoHibernate commonDao;
	
	@Autowired
	private QpTIcCompanyGarageDaoHibernate qpTIcCompanyGarageDao;
		
	@Override
	public QpTIcCompanyGarage findQpTIcCompanyGarageByPK(QpTIcCompanyGarageId id) throws Exception {
	    return qpTIcCompanyGarageDao.get(QpTIcCompanyGarage.class, id);
	}

	@Override
	public QpTIcCompanyGarage findQpTIcCompanyGarageByCoId(String coId) throws Exception {
	    QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	    queryRule.addEqual("coId", coId);
	    queryRule.addEqual("status", "1");
	    List<QpTIcCompanyGarage> lists = qpTIcCompanyGarageDao.find(queryRule);
	    if(lists != null && lists.size() > 0){
		return lists.get(0);
	    }else{
		return null;
	    }
	}
	
	
	
}
