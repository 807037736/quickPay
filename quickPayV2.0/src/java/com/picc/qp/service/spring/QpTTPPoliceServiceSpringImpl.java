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

import com.picc.qp.dao.QpTTPPoliceDaoHibernate;
import com.picc.qp.schema.model.QpTTPPolice;
import com.picc.qp.schema.model.QpTTPPoliceId;
import com.picc.qp.service.facade.IQpTTPPoliceService;


@Service("qpTTPPoliceService")
public class QpTTPPoliceServiceSpringImpl implements IQpTTPPoliceService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private QpTTPPoliceDaoHibernate qpTTPPoliceDao;

	/**
	 * 根据主键对象QpTTPPoliceId获取QpTTPPolice信息
	 * @param QpTTPPoliceId
	 * @return QpTTPPolice
	 */
	public QpTTPPolice findQpTTPPoliceByPK(QpTTPPoliceId id) throws Exception{
			return qpTTPPoliceDao.get(QpTTPPolice.class,id);
	}

	/**
	 * 根据qpTTPPolice和页码信息，获取Page对象
	 * @param qpTTPPolice
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTTPPolice的Page对象
	 */
	public Page findByQpTTPPolice(QpTTPPolice qpTTPPolice, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据qpTTPPolice生成queryRule
		
		return qpTTPPoliceDao.find(queryRule, pageNo, pageSize);
	}
	
	/**
     * 根据qpTTPPolice和页码信息
     * @param qpTTPPolice
     * @param pageNo
     * @param pageSize
     * @return 包含QpTTPPolice
     */
    public List<QpTTPPolice> findByQpTTPPolice(QpTTPPolice qpTTPPolice) throws Exception {
        QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
        
        //根据qpTTPPolice生成queryRule
        queryRule.addAscOrder("policeOrder");
        return qpTTPPoliceDao.find(queryRule);
    }

	/**
	 * 更新QpTTPPolice信息
	 * @param QpTTPPolice
	 */
	public void updateQpTTPPolice(QpTTPPolice qpTTPPolice) throws Exception{
			qpTTPPoliceDao.update(qpTTPPolice);
	}

	/**
	 * 保存QpTTPPolice信息
	 * @param QpTTPPolice
	 */
	public void saveQpTTPPolice(QpTTPPolice qpTTPPolice) throws Exception{
			qpTTPPoliceDao.save(qpTTPPolice);
	}

	/**
	 * 根据主键对象，删除QpTTPPolice信息
	 * @param QpTTPPoliceId
	 */
	public void deleteByPK(QpTTPPoliceId id) throws Exception{
			qpTTPPoliceDao.deleteByPK(QpTTPPolice.class,id);
	}
}
