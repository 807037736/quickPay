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
import com.picc.qp.dao.QpTCOMDistrictDaoHibernate;
import com.picc.qp.schema.model.QpTCOMDistrict;
import com.picc.qp.schema.model.QpTCOMDistrictId;
import com.picc.qp.service.facade.IQpTCOMDistrictService;


@Service("qpTCOMDistrictService")
public class QpTCOMDistrictServiceSpringImpl implements IQpTCOMDistrictService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private QpTCOMDistrictDaoHibernate qpTCOMDistrictDao;

	/**
	 * 根据主键对象QpTCOMDistrictId获取QpTCOMDistrict信息
	 * @param QpTCOMDistrictId
	 * @return QpTCOMDistrict
	 */
	public QpTCOMDistrict findQpTCOMDistrictByPK(QpTCOMDistrictId id) throws Exception{
			return qpTCOMDistrictDao.get(QpTCOMDistrict.class,id);
	}

	/**
	 * 根据qpTCOMDistrict和页码信息，获取Page对象
	 * @param qpTCOMDistrict
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTCOMDistrict的Page对象
	 */
	public Page findByQpTCOMDistrict(QpTCOMDistrict qpTCOMDistrict, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据qpTCOMDistrict生成queryRule
		if(ToolsUtils.notEmpty(qpTCOMDistrict.getCityId())) {
			queryRule.addEqual("cityId", qpTCOMDistrict.getCityId());
		}
		queryRule.addAscOrder("districtOrder");
		return qpTCOMDistrictDao.find(queryRule, pageNo, pageSize);
	}
	
	/**
     * 根据qpTCOMDistrict获取查询列表
     * @param qpTCOMDistrict
     * @param pageNo
     * @param pageSize
     * @return 包含QpTCOMDistrict
     */
    public List<QpTCOMDistrict> findByQpTCOMDistrict(QpTCOMDistrict qpTCOMDistrict) throws Exception {
        QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
        
        //根据qpTCOMDistrict生成queryRule
        if(ToolsUtils.notEmpty(qpTCOMDistrict.getCityId())) {
            queryRule.addEqual("cityId", qpTCOMDistrict.getCityId());
        }
        if(ToolsUtils.notEmpty(qpTCOMDistrict.getType())) {
            queryRule.addEqual("type", qpTCOMDistrict.getType());
        }
        queryRule.addAscOrder("districtOrder");
        return qpTCOMDistrictDao.find(queryRule);
    }

	/**
	 * 更新QpTCOMDistrict信息
	 * @param QpTCOMDistrict
	 */
	public void updateQpTCOMDistrict(QpTCOMDistrict qpTCOMDistrict) throws Exception{
			qpTCOMDistrictDao.update(qpTCOMDistrict);
	}

	/**
	 * 保存QpTCOMDistrict信息
	 * @param QpTCOMDistrict
	 */
	public void saveQpTCOMDistrict(QpTCOMDistrict qpTCOMDistrict) throws Exception{
			qpTCOMDistrictDao.save(qpTCOMDistrict);
	}

	/**
	 * 根据主键对象，删除QpTCOMDistrict信息
	 * @param QpTCOMDistrictId
	 */
	public void deleteByPK(QpTCOMDistrictId id) throws Exception{
			qpTCOMDistrictDao.deleteByPK(QpTCOMDistrict.class,id);
	}
	/**
	 * 查询QpTCOMDistrict信息
	 * 
	 */
	@Override
	public List findAllInfo() throws Exception {
		QueryRule queryRule  =  QueryRule.getInstance();
		QpTCOMDistrict qpTCOMDistrict = new QpTCOMDistrict();
		qpTCOMDistrict.setValidStatus("1");
		qpTCOMDistrict.setType("0");
		QueryRule rule = QueryRuleHelper.generateQueryRule(qpTCOMDistrict);
		List<QpTCOMDistrict> configList = qpTCOMDistrictDao.find(rule);
		return configList;
	
	}
}
