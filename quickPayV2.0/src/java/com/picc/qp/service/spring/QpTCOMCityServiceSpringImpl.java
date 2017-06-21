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
import com.picc.common.utils.ToolsUtils;
import com.picc.qp.dao.QpTCOMCityDaoHibernate;
import com.picc.qp.schema.model.QpTCOMCity;
import com.picc.qp.schema.model.QpTCOMCityId;
import com.picc.qp.service.facade.IQpTCOMCityService;


@Service("qpTCOMCityService")
public class QpTCOMCityServiceSpringImpl implements IQpTCOMCityService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private QpTCOMCityDaoHibernate qpTCOMCityDao;

	/**
	 * 根据主键对象QpTCOMCityId获取QpTCOMCity信息
	 * @param QpTCOMCityId
	 * @return QpTCOMCity
	 */
	public QpTCOMCity findQpTCOMCityByPK(QpTCOMCityId id) throws Exception{
			return qpTCOMCityDao.get(QpTCOMCity.class,id);
	}

	/**
	 * 根据qpTCOMCity和页码信息，获取Page对象
	 * @param qpTCOMCity
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTCOMCity的Page对象
	 */
	public Page findByQpTCOMCity(QpTCOMCity qpTCOMCity, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据qpTCOMCity生成queryRule
		if(ToolsUtils.notEmpty(qpTCOMCity.getProvId())) {
			queryRule.addEqual("provId", qpTCOMCity.getProvId());
		}
		queryRule.addAscOrder("cityOrder");
		return qpTCOMCityDao.find(queryRule, pageNo, pageSize);
	}
	
	/**
     * 根据qpTCOMCity获取查询列表
     * @param qpTCOMCity
     * @param pageNo
     * @param pageSize
     * @return 包含QpTCOMCity
     */
    public List<QpTCOMCity> findByQpTCOMCity(QpTCOMCity qpTCOMCity) throws Exception {
        QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
        
        //根据qpTCOMCity生成queryRule
        if(ToolsUtils.notEmpty(qpTCOMCity.getProvId())) {
            queryRule.addEqual("provId", qpTCOMCity.getProvId());
        }
        queryRule.addAscOrder("cityOrder");
        return qpTCOMCityDao.find(queryRule);
    }

	/**
	 * 更新QpTCOMCity信息
	 * @param QpSelectData
	 */
	public void updateQpTCOMCity(QpTCOMCity qpTCOMCity) throws Exception{
			qpTCOMCityDao.update(qpTCOMCity);
	}

	/**
	 * 保存QpTCOMCity信息
	 * @param QpSelectData
	 */
	public void saveQpTCOMCity(QpTCOMCity qpTCOMCity) throws Exception{
			qpTCOMCityDao.save(qpTCOMCity);
	}

	/**
	 * 根据主键对象，删除QpTCOMCity信息
	 * @param QpTCOMCityId
	 */
	public void deleteByPK(QpTCOMCityId id) throws Exception{
			qpTCOMCityDao.deleteByPK(QpTCOMCity.class,id);
	}
	/**
	 * 查询QpTCOMCity所有信息
	 * 
	 */
	@Override
	public List findAllInfo() throws Exception {
		QueryRule queryRule  =  QueryRule.getInstance();
		QpTCOMCity qpTCOMCity = new QpTCOMCity();
		qpTCOMCity.setValidStatus("1");
		QueryRule rule = QueryRuleHelper.generateQueryRule(qpTCOMCity);
		List<QpTCOMCity> configList = qpTCOMCityDao.find(rule);
		return configList;
	
	}


}
