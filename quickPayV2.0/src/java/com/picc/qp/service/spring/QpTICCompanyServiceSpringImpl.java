/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
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
import com.picc.qp.dao.QpTICCompanyDaoHibernate;
import com.picc.qp.schema.model.QpTICCompany;
import com.picc.qp.schema.model.QpTICCompanyId;
import com.picc.qp.service.facade.IQpTICCompanyService;


@Service("qpTICCompanyService")
public class QpTICCompanyServiceSpringImpl implements IQpTICCompanyService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private QpTICCompanyDaoHibernate qpTICCompanyDao;
	@Autowired
    private SysCommonDaoHibernate sysCommonDao;

	/**
	 * 根据主键对象QpTICCompanyId获取QpTICCompany信息
	 * @param QpTICCompanyId
	 * @return QpTICCompany
	 */
	public QpTICCompany findQpTICCompanyByPK(QpTICCompanyId id) throws Exception{
			return qpTICCompanyDao.get(QpTICCompany.class,id);
	}

	/**
	 * 根据qpTICCompany和页码信息，获取Page对象
	 * @param qpTICCompany
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTICCompany的Page对象
	 */
	public Page findByQpTICCompany(QpTICCompany qpTICCompany, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		//根据qpTICCompany生成queryRule
		if(ToolsUtils.notEmpty(qpTICCompany.getCoName())){
			queryRule.addEqual("coName", qpTICCompany.getCoName());
		}
		if(ToolsUtils.notEmpty(qpTICCompany.getCoPhone())){
			queryRule.addEqual("coPhone", qpTICCompany.getCoPhone());
		}
		if(ToolsUtils.notEmpty(qpTICCompany.getValidStatus())){
			queryRule.addEqual("validStatus", qpTICCompany.getValidStatus());
		}
		queryRule.addAscOrder("coOrder");
		return qpTICCompanyDao.find(queryRule, pageNo, pageSize);
	}
	
	/**
     * 根据qpTICCompany查询列表
     * @param qpTICCompany
     * @param pageNo
     * @param pageSize
     * @return 包含QpTICCompany
     */
    public List<QpTICCompany> findByQpTICCompany(QpTICCompany qpTICCompany) throws Exception {
        QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
        queryRule.addEqual("validStatus", "1");
        queryRule.addAscOrder("coOrder");
        return qpTICCompanyDao.find(queryRule);
    }

	/**
	 * 更新QpTICCompany信息
	 * @param QpTICCompany
	 */
	public void updateQpTICCompany(QpTICCompany qpTICCompany) throws Exception{
			qpTICCompanyDao.update(qpTICCompany);
	}

	/**
	 * 保存QpTICCompany信息
	 * @param QpTICCompany
	 */
	public void saveQpTICCompany(QpTICCompany qpTICCompany) throws Exception{
			String CompanyId = sysCommonDao.generateID("ICCO", "QP_SEQ_IC_COMPANY", 16);
			
			QpTICCompanyId qpTICCompanyId = new QpTICCompanyId();
			qpTICCompanyId.setCoId(CompanyId);
			qpTICCompany.setId(qpTICCompanyId);
			qpTICCompanyDao.save(qpTICCompany);
	}

	/**
	 * 根据主键对象，删除QpTICCompany信息
	 * @param QpTICCompanyId
	 */
	public void deleteByPK(QpTICCompanyId id) throws Exception{
			qpTICCompanyDao.deleteByPK(QpTICCompany.class,id);
	}
}
