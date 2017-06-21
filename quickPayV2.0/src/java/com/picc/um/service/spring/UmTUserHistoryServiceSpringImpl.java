/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2014
 */

package com.picc.um.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.um.dao.UmTUserHistoryDaoHibernate;
import com.picc.um.schema.model.UmTUserHistory;
import com.picc.um.schema.model.UmTUserHistoryId;
import com.picc.um.service.facade.IUmTUserHistoryService;


@Service("umTUserHistoryService")
public class UmTUserHistoryServiceSpringImpl implements IUmTUserHistoryService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private UmTUserHistoryDaoHibernate umTUserHistoryDao;
	
	@Autowired
	private CommonDaoHibernate commonDao;

	/**
	 * 根据主键对象UmTUserHistoryId获取UmTUserHistory信息
	 * @param UmTUserHistoryId
	 * @return UmTUserHistory
	 */
	public UmTUserHistory findUmTUserHistoryByPK(UmTUserHistoryId id) throws Exception{
			logger.debug("正在执行UmTUserHistoryServiceImpl.findUmTUserHistoryByPK，获取UmTUserHistory对象");
			return umTUserHistoryDao.get(UmTUserHistory.class,id);
	}

	/**
	 * 根据umTUserHistory和页码信息，获取Page对象
	 * @param umTUserHistory
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTUserHistory的Page对象
	 */
	public Page findByUmTUserHistory(UmTUserHistory umTUserHistory, int pageNo, int pageSize) throws Exception{
		logger.debug("正在执行UmTUserHistoryServiceImpl.findByUmTUserHistory，获取包含UmTUserHistory的Page对象");
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据umTUserHistory生成queryRule
		
		return umTUserHistoryDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新UmTUserHistory信息
	 * @param UmTUserHistory
	 */
	public void updateUmTUserHistory(UmTUserHistory umTUserHistory) throws Exception{
			logger.debug("正在执行UmTUserHistoryServiceImpl.updateUmTUserHistory，更新UmTUserHistory信息");
			umTUserHistoryDao.update(umTUserHistory);
	}

	/**
	 * 保存UmTUserHistory信息
	 * @param UmTUserHistory
	 */
	public void saveUmTUserHistory(UmTUserHistory umTUserHistory) throws Exception{
			logger.debug("正在执行UmTUserHistoryServiceImpl.saveUmTUserHistory，保存UmTUserHistory信息");
			umTUserHistoryDao.save(umTUserHistory);
	}

	/**
	 * 根据主键对象，删除UmTUserHistory信息
	 * @param UmTUserHistoryId
	 */
	public void deleteByPK(UmTUserHistoryId id) throws Exception{
			logger.debug("正在执行UmTUserHistoryServiceImpl.deleteByPK，删除UmTUserHistory信息");
			umTUserHistoryDao.deleteByPK(UmTUserHistory.class,id);
	}

	public void saveFromUmTUser(String usercode)  throws Exception{
		// TODO Auto-generated method stub
		String sql = "insert into um_t_userhistory select * from um_t_user where usercode=? ";
		commonDao.execute(sql, new Object[]{usercode});
	}
}
