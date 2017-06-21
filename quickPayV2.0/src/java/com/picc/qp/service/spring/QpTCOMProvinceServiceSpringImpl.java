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

import com.picc.common.QueryRuleHelper;
import com.picc.qp.dao.QpTCOMProvinceDaoHibernate;
import com.picc.qp.schema.model.QpTCOMProvince;
import com.picc.qp.schema.model.QpTCOMProvinceId;
import com.picc.qp.service.facade.IQpTCOMProvinceService;


@Service("qpTCOMProvinceService")
public class QpTCOMProvinceServiceSpringImpl implements IQpTCOMProvinceService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private QpTCOMProvinceDaoHibernate qpTCOMProvinceDao;

	/**
	 * 根据主键对象QpTCOMProvinceId获取QpTCOMProvince信息
	 * @param QpTCOMProvinceId
	 * @return QpTCOMProvince
	 */
	public QpTCOMProvince findQpTCOMProvinceByPK(QpTCOMProvinceId id) throws Exception{
			return qpTCOMProvinceDao.get(QpTCOMProvince.class,id);
	}

	/**
	 * 根据qpTCOMProvince和页码信息，获取Page对象
	 * @param qpTCOMProvince
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTCOMProvince的Page对象
	 */
	public Page findByQpTCOMProvince(QpTCOMProvince qpTCOMProvince, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据qpTCOMProvince生成queryRule
		queryRule.addAscOrder("provOrder");
		return qpTCOMProvinceDao.find(queryRule, pageNo, pageSize);
	}
	
	/**
     * 根据qpTCOMProvince查询列表
     * @param qpTCOMProvince
     * @param pageNo
     * @param pageSize
     * @return 包含QpTCOMProvince
     */
    public List<QpTCOMProvince> findByQpTCOMProvince(QpTCOMProvince qpTCOMProvince) throws Exception {
        QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
        
        //根据qpTCOMProvince生成queryRule
        queryRule.addAscOrder("provOrder");
        return qpTCOMProvinceDao.find(queryRule);
    }

	/**
	 * 更新QpTCOMProvince信息
	 * @param QpTCOMProvince
	 */
	public void updateQpTCOMProvince(QpTCOMProvince qpTCOMProvince) throws Exception{
			qpTCOMProvinceDao.update(qpTCOMProvince);
	}

	/**
	 * 保存QpTCOMProvince信息
	 * @param QpTCOMProvince
	 */
	public void saveQpTCOMProvince(QpTCOMProvince qpTCOMProvince) throws Exception{
			qpTCOMProvinceDao.save(qpTCOMProvince);
	}

	/**
	 * 根据主键对象，删除QpTCOMProvince信息
	 * @param QpTCOMProvinceId
	 */
	public void deleteByPK(QpTCOMProvinceId id) throws Exception{
			qpTCOMProvinceDao.deleteByPK(QpTCOMProvince.class,id);
	}
	/**
	 * 查询QpTCOMProvince所有信息
	 *
	 */
	@Override
	public List findAllInfo() throws Exception {
		
		QueryRule queryRule  =  QueryRule.getInstance();
		QpTCOMProvince qpTCOMProvince = new QpTCOMProvince();
		qpTCOMProvince.setValidStatus("1");
		QueryRule rule = QueryRuleHelper.generateQueryRule(qpTCOMProvince);
		List<QpTCOMProvince> configList = qpTCOMProvinceDao.find(rule);
		return configList;
	
	}
}
