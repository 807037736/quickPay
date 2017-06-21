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
import com.picc.qp.dao.QpTCOMRoadDaoHibernate;
import com.picc.qp.schema.model.QpTCOMRoad;
import com.picc.qp.schema.model.QpTCOMRoadId;
import com.picc.qp.service.facade.IQpTCOMRoadService;


@Service("qpTCOMRoadService")
public class QpTCOMRoadServiceSpringImpl implements IQpTCOMRoadService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private QpTCOMRoadDaoHibernate qpTCOMRoadDao;

	/**
	 * 根据主键对象QpTCOMRoadId获取QpTCOMRoad信息
	 * @param QpTCOMRoadId
	 * @return QpTCOMRoad
	 */
	public QpTCOMRoad findQpTCOMRoadByPK(QpTCOMRoadId id) throws Exception{
			return qpTCOMRoadDao.get(QpTCOMRoad.class,id);
	}

	/**
	 * 根据qpTCOMRoad和页码信息，获取Page对象
	 * @param qpTCOMRoad
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTCOMRoad的Page对象
	 */
	public Page findByQpTCOMRoad(QpTCOMRoad qpTCOMRoad, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据qpTCOMRoad生成queryRule
		if(ToolsUtils.notEmpty(qpTCOMRoad.getDistrictId())) {
			queryRule.addEqual("districtId", qpTCOMRoad.getDistrictId());
		}
		queryRule.addAscOrder("roadOrder");
		return qpTCOMRoadDao.find(queryRule, pageNo, pageSize);
	}
	
	/**
     * 根据qpTCOMRoad获取查询列表
     * @param qpTCOMRoad
     * @param pageNo
     * @param pageSize
     * @return 包含QpTCOMRoad
     */
    public List<QpTCOMRoad> findByQpTCOMRoad(QpTCOMRoad qpTCOMRoad) throws Exception {
        QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
        
        //根据qpTCOMRoad生成queryRule
        if(ToolsUtils.notEmpty(qpTCOMRoad.getDistrictId())) {
            queryRule.addEqual("districtId", qpTCOMRoad.getDistrictId());
        }
        queryRule.addAscOrder("roadOrder");
        return qpTCOMRoadDao.find(queryRule);
    }

	/**
	 * 更新QpTCOMRoad信息
	 * @param QpTCOMRoad
	 */
	public void updateQpTCOMRoad(QpTCOMRoad qpTCOMRoad) throws Exception{
			qpTCOMRoadDao.update(qpTCOMRoad);
	}

	/**
	 * 保存QpTCOMRoad信息
	 * @param QpTCOMRoad
	 */
	public void saveQpTCOMRoad(QpTCOMRoad qpTCOMRoad) throws Exception{
			qpTCOMRoadDao.save(qpTCOMRoad);
	}

	/**
	 * 根据主键对象，删除QpTCOMRoad信息
	 * @param QpTCOMRoadId
	 */
	public void deleteByPK(QpTCOMRoadId id) throws Exception{
			qpTCOMRoadDao.deleteByPK(QpTCOMRoad.class,id);
	}
	/**
	 * 查询QpTCOMRoad所有信息
	 *
	 */
	@Override
	public List findAllInfo() throws Exception {
		QueryRule queryRule  =  QueryRule.getInstance();
		QpTCOMRoad qpTCOMRoad = new QpTCOMRoad();
		qpTCOMRoad.setValidStatus("1");
		QueryRule rule = QueryRuleHelper.generateQueryRule(qpTCOMRoad);
		List<QpTCOMRoad> configList = qpTCOMRoadDao.find(rule);
		return configList;
	
	}
	/**
	 * 根据区的id查询QpTCOMRoad信息
	 *
	 */
	@Override
	public List findRodeNumber(String districtId) throws Exception {
		QueryRule queryRule  =  QueryRule.getInstance();
		QpTCOMRoad qpTCOMRoad = new QpTCOMRoad();
		qpTCOMRoad.setDistrictId(districtId);
		QueryRule rule = QueryRuleHelper.generateQueryRule(qpTCOMRoad);
		List<QpTCOMRoad> configList = qpTCOMRoadDao.find(rule);
		return configList;
	}


}
