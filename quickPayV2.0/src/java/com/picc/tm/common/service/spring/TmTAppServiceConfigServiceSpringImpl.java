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
import com.picc.common.utils.ToolsUtils;
import com.picc.tm.common.dao.TmTAppServiceConfigDaoHibernate;
import com.picc.tm.common.schema.model.TmTAppServiceConfig;
import com.picc.tm.common.schema.model.TmTAppServiceConfigId;
import com.picc.tm.common.service.facade.ITmTAppServiceConfigService;

/**
 * 服务环境变量接口实现类，用于获取周边环境的地址IP信息
 * @author moxiaoguang 更新 2013-12-12
 *
 */
@Service("tmTAppServiceConfigService")
public class TmTAppServiceConfigServiceSpringImpl implements ITmTAppServiceConfigService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static CacheService cacheService = CacheManager.getInstance("TM_T_APPSERVICECONFIG");
	@Autowired
    private TmTAppServiceConfigDaoHibernate tmTAppServiceConfigDao;

	@Autowired
	private CommonDaoHibernate commonDao;
	
	/**
	 * 根据主键对象TmTAppServiceConfigId获取TmTAppServiceConfig信息
	 * @param TmTAppServiceConfigId
	 * @return TmTAppServiceConfig
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public TmTAppServiceConfig findTmTAppServiceConfigByPK(TmTAppServiceConfigId id) throws Exception{
			return tmTAppServiceConfigDao.get(TmTAppServiceConfig.class,id);
	}

	/**
	 * 根据tmTAppServiceConfig和页码信息，获取Page对象
	 * @param tmTAppServiceConfig
	 * @param pageNo
	 * @param pageSize
	 * @return 包含TmTAppServiceConfig的Page对象
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public Page findByTmTAppServiceConfig(TmTAppServiceConfig tmTAppServiceConfig, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据tmTAppServiceConfig生成queryRule
		
		return tmTAppServiceConfigDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新TmTAppServiceConfig信息
	 * @param TmTAppServiceConfig
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public void updateTmTAppServiceConfig(TmTAppServiceConfig tmTAppServiceConfig) throws Exception{
			tmTAppServiceConfigDao.update(tmTAppServiceConfig);
	}

	/**
	 * 保存TmTAppServiceConfig信息
	 * @param TmTAppServiceConfig
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public void saveTmTAppServiceConfig(TmTAppServiceConfig tmTAppServiceConfig) throws Exception{
			tmTAppServiceConfigDao.save(tmTAppServiceConfig);
	}

	/**
	 * 根据主键对象，删除TmTAppServiceConfig信息
	 * @param TmTAppServiceConfigId
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public void deleteByPK(TmTAppServiceConfigId id) throws Exception{
			tmTAppServiceConfigDao.deleteByPK(TmTAppServiceConfig.class,id);
	}

	/**
	 * 根据环境变量的代码获取ip地址
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public String getServiceUrl(String serverCode, String environmentCode) {
		if((null==serverCode||"".equals(serverCode))||(null==environmentCode)||"".equals(environmentCode))
		{
			return null;
		}
		else
		{
			String key =  cacheService.generateCacheKey(serverCode,environmentCode);
			TmTAppServiceConfig tm_asc = (TmTAppServiceConfig)cacheService.getCache(key);
			if(null == tm_asc)
			{
				QueryRule queryRule = QueryRule.getInstance();
				queryRule.addEqual("id.serverCode", serverCode);
				queryRule.addEqual("id.environmentCode", environmentCode);
				queryRule.addEqual("validStatus", "1");
				List<TmTAppServiceConfig> list = this.commonDao.find(TmTAppServiceConfig.class,queryRule);
				if(null==list||list.size()<=0)
				{
					return null;
				}
				else
				{
					tm_asc = list.get(0);
					cacheService.putCache(key, tm_asc);
					return getUrl(tm_asc);
				}
			}
			else
			{
				return getUrl(tm_asc);
			}
		}
	}

	/**
	 * 根据环境配置实体获取URL
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public  String getUrl(TmTAppServiceConfig tmTAppServiceConfig) {
		StringBuffer sbUrl =  new StringBuffer();
		String protocolType = tmTAppServiceConfig.getProtocolType();
		String serverIp = tmTAppServiceConfig.getServerIp();
		String serverPort = tmTAppServiceConfig.getServerPort();
		String serverContext = tmTAppServiceConfig.getServerContext();
		if(ToolsUtils.notEmpty(protocolType)) {
			sbUrl.append(protocolType.trim());
			sbUrl.append("://");
		}
		if(ToolsUtils.notEmpty(serverIp)) {
			sbUrl.append(serverIp.trim());
		}
		if(ToolsUtils.notEmpty(serverPort)) {
			sbUrl.append(":");
			sbUrl.append(serverPort.trim());
		}
		if(ToolsUtils.notEmpty(serverContext)) {
			sbUrl.append("/");
			sbUrl.append(serverContext.trim());
		}
		return sbUrl.toString();
	}
	public List<TmTAppServiceConfig> findAll() throws Exception {
		String executeSQL = "select * from tm_t_appserviceconfig where validstatus='1' and isshow='0'";
		
		Page page = commonDao.findObjectPageBySql(executeSQL,
				TmTAppServiceConfig.class, 1, Integer.MAX_VALUE, new Object[] {});
	
		return page.getResult();
	}
	
	
	public List<TmTAppServiceConfig> findListByQueryRule(QueryRule rule)
			throws Exception {
		return this.tmTAppServiceConfigDao.find(rule);
	}
	
	
}
