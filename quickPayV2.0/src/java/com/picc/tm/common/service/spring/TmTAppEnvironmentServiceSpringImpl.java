/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.tm.common.service.spring;

import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.tm.common.dao.TmTAppEnvironmentDaoHibernate;
import com.picc.tm.common.schema.model.TmTAppEnvironment;
import com.picc.tm.common.schema.model.TmTAppEnvironmentId;
import com.picc.tm.common.service.facade.ITmTAppEnvironmentService;

/**
 * 环境变量接口实现类
 * @author moxiaoguang 更新 2013-12-12
 *
 */
@Service("tmTAppEnvironmentService")
public class TmTAppEnvironmentServiceSpringImpl implements ITmTAppEnvironmentService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static CacheService cacheService = CacheManager.getInstance("TM_T_APPENVIRONMENT");
	
	@Autowired
    private TmTAppEnvironmentDaoHibernate tmTAppEnvironmentDao;

	@Autowired
	private CommonDaoHibernate commonDao;
	/**
	 * 根据主键对象TmTAppEnvironmentId获取TmTAppEnvironment信息
	 * @param TmTAppEnvironmentId
	 * @return TmTAppEnvironment
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public TmTAppEnvironment findTmTAppEnvironmentByPK(TmTAppEnvironmentId id) throws Exception{
			return tmTAppEnvironmentDao.get(TmTAppEnvironment.class,id);
	}

	/**
	 * 根据tmTAppEnvironment和页码信息，获取Page对象
	 * @param tmTAppEnvironment
	 * @param pageNo
	 * @param pageSize
	 * @return 包含TmTAppEnvironment的Page对象
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public Page findByTmTAppEnvironment(TmTAppEnvironment tmTAppEnvironment, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据tmTAppEnvironment生成queryRule
		
		return tmTAppEnvironmentDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新TmTAppEnvironment信息
	 * @param TmTAppEnvironment
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public void updateTmTAppEnvironment(TmTAppEnvironment tmTAppEnvironment) throws Exception{
			tmTAppEnvironmentDao.update(tmTAppEnvironment);
	}

	/**
	 * 保存TmTAppEnvironment信息
	 * @param TmTAppEnvironment
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public void saveTmTAppEnvironment(TmTAppEnvironment tmTAppEnvironment) throws Exception{
			tmTAppEnvironmentDao.save(tmTAppEnvironment);
	}

	/**
	 * 根据主键对象，删除TmTAppEnvironment信息
	 * @param TmTAppEnvironmentId
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public void deleteByPK(TmTAppEnvironmentId id) throws Exception{
			tmTAppEnvironmentDao.deleteByPK(TmTAppEnvironment.class,id);
	}

	/**
	 * 根据comID获取省级环境代码
	 * @author moxiaoguang 更新 2013-12-12
	 */
	@SuppressWarnings("unchecked")
	public String getEnvironmentCode(String comId) {
		if(null==comId||"".equals(comId))
		{
			return null;
		}
		else
		{
			String key = cacheService.generateCacheKey("TM_T_APPENVIRONMENT",comId);
			TmTAppEnvironment tm_ae = (TmTAppEnvironment)cacheService.getCache(key);
			if(null==tm_ae)
			{
				QueryRule qr = QueryRule.getInstance();
				qr.addEqual("comId", comId);
				qr.addEqual("validStatus", "1");
				List<TmTAppEnvironment> list = (List<TmTAppEnvironment>)this.commonDao.find(TmTAppEnvironment.class, qr);
				if(null==list||list.size()<=0)
				{
					return null;
				}
				else
				{
					tm_ae = list.get(0);
					cacheService.putCache(key,tm_ae);
					return tm_ae.getId().getEnvironmentCode();
				}
			}
			else
			{
				return tm_ae.getId().getEnvironmentCode();
			}
		}
	}
}
