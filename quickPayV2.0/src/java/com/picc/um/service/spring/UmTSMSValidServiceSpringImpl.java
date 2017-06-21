/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.um.dao.UmTSMSValidDaoHibernate;
import com.picc.um.schema.model.UmTSMSValid;
import com.picc.um.schema.model.UmTSMSValidId;
import com.picc.um.service.facade.IUmTSMSValidService;

/**
 * 短信验证码接口实现类
 * @author 李明果
 */
@Service("umTSMSValidService")
public class UmTSMSValidServiceSpringImpl implements IUmTSMSValidService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private UmTSMSValidDaoHibernate umTSMSValidDao;

	/**
	 * 根据主键对象UmTSMSValidId获取UmTSMSValid信息
	 * @param UmTSMSValidId
	 * @return UmTSMSValid
	 */
	public UmTSMSValid findUmTSMSValidByPK(UmTSMSValidId id) throws Exception{
			logger.debug("正在执行UmTSMSValidServiceImpl.findUmTSMSValidByPK，获取UmTSMSValid对象");
			return umTSMSValidDao.get(UmTSMSValid.class,id);
	}

	/**
	 * 根据umTSMSValid和页码信息，获取Page对象
	 * @param umTSMSValid
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTSMSValid的Page对象
	 */
	public Page findByUmTSMSValid(UmTSMSValid umTSMSValid, int pageNo, int pageSize) throws Exception{
		logger.debug("正在执行UmTSMSValidServiceImpl.findByUmTSMSValid，获取包含UmTSMSValid的Page对象");
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据umTSMSValid生成queryRule
		
		return umTSMSValidDao.find(queryRule, pageNo, pageSize);
	}
	/*test*/
	public List<UmTSMSValid> findSMSValidByMobile(String mobile) throws Exception{
		logger.debug("正在执行UmTSMSValidServiceImpl.findByUmTSMSValid，获取包含UmTSMSValid的Page对象");
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		queryRule.addLike("mobile", mobile);
		queryRule.addDescOrder("insertTimeForHis");	
		
		return umTSMSValidDao.find(queryRule);
	}

	/**
	 * 更新UmTSMSValid信息
	 * @param UmTSMSValid
	 */
	public void updateUmTSMSValid(UmTSMSValid umTSMSValid) throws Exception{
			logger.debug("正在执行UmTSMSValidServiceImpl.updateUmTSMSValid，更新UmTSMSValid信息");
			umTSMSValidDao.update(umTSMSValid);
	}

	/**
	 * 保存UmTSMSValid信息
	 * @param UmTSMSValid
	 */
	public void saveUmTSMSValid(UmTSMSValid umTSMSValid) throws Exception{
			logger.debug("正在执行UmTSMSValidServiceImpl.saveUmTSMSValid，保存UmTSMSValid信息");
			UmTSMSValidId id = new UmTSMSValidId();
			id.setSmsId(umTSMSValidDao.getCommonDao().generateID("UMSV", "UM_SEQ_SMSVALID", 26));
			umTSMSValid.setId(id);
			umTSMSValidDao.save(umTSMSValid);
	}

	/**
	 * 根据主键对象，删除UmTSMSValid信息
	 * @param UmTSMSValidId
	 */
	public void deleteByPK(UmTSMSValidId id) throws Exception{
			logger.debug("正在执行UmTSMSValidServiceImpl.deleteByPK，删除UmTSMSValid信息");
			umTSMSValidDao.deleteByPK(UmTSMSValid.class,id);
	}
}
