/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.spring;

import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.QueryRuleHelper;
import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.um.dao.UmTWhiteListDaoHibernate;
import com.picc.um.schema.model.UmTWhiteList;
import com.picc.um.schema.model.UmTWhiteListId;
import com.picc.um.service.facade.IUmTWhiteListService;

/**
 * 白名单接口实现类
 * @author 姜卫洋
 */
@Service("umTWhiteListService")
public class UmTWhiteListServiceSpringImpl implements IUmTWhiteListService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static CacheService whiteListCacheService  = CacheManager.getInstance("UM_T_WHITELIST");

	@Autowired
	private UmTWhiteListDaoHibernate umTWhiteListDao;
	
	@Autowired
	private CommonDaoHibernate commonDao;
	
	@Autowired
    private SysCommonDaoHibernate sysCommonDao;
	

	/**
	 * 根据主键对象UmTWhiteListId获取UmTWhiteList信息
	 * 
	 * @param UmTWhiteListId
	 * @return UmTWhiteList
	 */
	public UmTWhiteList findUmTWhiteListByPK(UmTWhiteListId id)
			throws Exception {
		return umTWhiteListDao.get(UmTWhiteList.class, id);
	}

	/**
	 * 根据umTWhiteList和页码信息，获取Page对象
	 * 
	 * @param umTWhiteList
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTWhiteList的Page对象
	 */
	public Page findByUmTWhiteList(UmTWhiteList umTWhiteList, int pageNo,
			int pageSize) throws Exception {
		QueryRule queryRule = QueryRuleHelper.generateQueryRule(umTWhiteList);
		return umTWhiteListDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新UmTWhiteList信息
	 * 
	 * @param UmTWhiteList
	 */
	public void updateUmTWhiteList(UmTWhiteList umTWhiteList) throws Exception {
		if("0".equals(umTWhiteList.getValidStatus())){
			//置为无效
			if(whiteListCacheService.containsKey(umTWhiteList.getVisitUrl())){
				whiteListCacheService.remove(umTWhiteList.getVisitUrl());
			}
		}else if("1".equals(umTWhiteList.getValidStatus())){
			if(!whiteListCacheService.containsKey(umTWhiteList.getVisitUrl())){
				whiteListCacheService.putCache(umTWhiteList.getVisitUrl(), umTWhiteList.getVisitUrl());
			}
		}
		umTWhiteListDao.update(umTWhiteList);
		whiteListCacheService.clearCacheManager("UM_T_WHITELIST");
	}

	/**
	 * 保存UmTWhiteList信息
	 * 
	 * @param UmTWhiteList
	 */
	public void saveUmTWhiteList(UmTWhiteList umTWhiteList) throws Exception {
		String wlId = sysCommonDao.generateID("UMWL", "UM_SEQ_WHITELIST", 16);
		umTWhiteList.setId(new UmTWhiteListId(wlId));						//设置ID主键
		umTWhiteListDao.save(umTWhiteList);
		if("1".equals(umTWhiteList.getValidStatus()))
			whiteListCacheService.putCache(umTWhiteList.getVisitUrl(), umTWhiteList.getVisitUrl());
		whiteListCacheService.clearCacheManager("UM_T_WHITELIST");
	}

	/**
	 * 根据主键对象，删除UmTWhiteList信息
	 * 
	 * @param UmTWhiteListId
	 */
	public void deleteByPK(UmTWhiteListId id) throws Exception {
		UmTWhiteList wl = findUmTWhiteListByPK(id);					//查询白名单对象
		if(whiteListCacheService.containsKey(wl.getVisitUrl())){
			whiteListCacheService.remove(wl.getVisitUrl());
		}
		umTWhiteListDao.delete(wl);
		whiteListCacheService.clearCacheManager("UM_T_WHITELIST");
//		umTWhiteListDao.deleteByPK(UmTWhiteList.class, id);
	}
}
