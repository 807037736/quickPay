/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.qp.dao.QpTCOMDriverInfoDaoHibernate;
import com.picc.qp.schema.model.QpTCOMDriverInfo;
import com.picc.qp.schema.model.QpTCOMDriverInfoId;
import com.picc.qp.service.facade.IQpTCOMDriverInfoService;


@Service("qpTCOMDriverInfoService")
public class QpTCOMDriverInfoServiceSpringImpl implements IQpTCOMDriverInfoService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private QpTCOMDriverInfoDaoHibernate qpTCOMDriverInfoDao;

	/**
	 * 根据主键对象QpTCOMDriverInfoId获取QpTCOMDriverInfo信息
	 * @param QpTCOMDriverInfoId
	 * @return QpTCOMDriverInfo
	 */
	public QpTCOMDriverInfo findQpTCOMDriverInfoByPK(QpTCOMDriverInfoId id) throws Exception{
			return qpTCOMDriverInfoDao.get(QpTCOMDriverInfo.class,id);
	}

	/**
	 * 根据qpTCOMDriverInfo和页码信息，获取Page对象
	 * @param qpTCOMDriverInfo
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTCOMDriverInfo的Page对象
	 */
	public Page findByQpTCOMDriverInfo(QpTCOMDriverInfo qpTCOMDriverInfo, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据qpTCOMDriverInfo生成queryRule
		
		return qpTCOMDriverInfoDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新QpTCOMDriverInfo信息
	 * @param QpTCOMDriverInfo
	 */
	public void updateQpTCOMDriverInfo(QpTCOMDriverInfo qpTCOMDriverInfo) throws Exception{
			qpTCOMDriverInfoDao.update(qpTCOMDriverInfo);
	}

	/**
	 * 保存QpTCOMDriverInfo信息
	 * @param QpTCOMDriverInfo
	 */
	public void saveQpTCOMDriverInfo(QpTCOMDriverInfo qpTCOMDriverInfo) throws Exception{
			qpTCOMDriverInfoDao.save(qpTCOMDriverInfo);
	}

	/**
	 * 根据主键对象，删除QpTCOMDriverInfo信息
	 * @param QpTCOMDriverInfoId
	 */
	public void deleteByPK(QpTCOMDriverInfoId id) throws Exception{
			qpTCOMDriverInfoDao.deleteByPK(QpTCOMDriverInfo.class,id);
	}
}
