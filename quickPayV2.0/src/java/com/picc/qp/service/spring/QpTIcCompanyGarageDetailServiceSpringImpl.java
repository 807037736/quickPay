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
import com.picc.qp.dao.QpTIcCompanyGarageDetailDaoHibernate;
import com.picc.qp.schema.model.QpTIcCompanyGarageDetail;
import com.picc.qp.schema.model.QpTIcCompanyGarageDetailId;
import com.picc.qp.service.facade.IQpTIcCompanyGarageDetailService;


@Service("qpTIcCompanyGarageDetailService")
public class QpTIcCompanyGarageDetailServiceSpringImpl implements IQpTIcCompanyGarageDetailService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	

	@Autowired
	private SysCommonDaoHibernate sysCommonDao;

	@Autowired
	private CommonDaoHibernate commonDao;
	
	@Autowired
	private QpTIcCompanyGarageDetailDaoHibernate qpTIcCompanyGarageDetailDao;
	
	
	@Override
	public QpTIcCompanyGarageDetail findQpTIcCompanyGarageDetailByPK(QpTIcCompanyGarageDetailId id) throws Exception {
	    return qpTIcCompanyGarageDetailDao.get(QpTIcCompanyGarageDetail.class, id);
	}

	@Override
	public List<QpTIcCompanyGarageDetail> findQpTIcCompanyGarageDetailByCompanyGarageId(int companyGarageId) throws Exception {
	    QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
//	    queryRule.addEqual("companyGarageId", companyGarageId);
	    if(companyGarageId > 0){
		queryRule.addIn("companyGarageId", companyGarageId, 999);
		queryRule.addEqual("status", "1");
	    }else{
		queryRule.addIn("companyGarageId", 999);
		queryRule.addEqual("status", "1");
	    }
	    
	    return qpTIcCompanyGarageDetailDao.find(queryRule);
	}
	
	
	
}
