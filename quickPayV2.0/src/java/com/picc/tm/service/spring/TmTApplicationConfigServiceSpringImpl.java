/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2014
 */

package com.picc.tm.service.spring;

import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.utils.SqlUtils;
import com.picc.common.utils.ToolsUtils;
import com.picc.tm.dao.TmTApplicationConfigDaoHibernate;
import com.picc.tm.schema.model.TmTApplicationConfig;
import com.picc.tm.schema.model.TmTApplicationConfigId;
import com.picc.tm.service.facade.ITmTApplicationConfigService;


@Service("tmTApplicationConfigService")
public class TmTApplicationConfigServiceSpringImpl implements ITmTApplicationConfigService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static CacheService applicationCacheService = CacheManager.getInstance("TM_T_ApplicationConfig"); 
	
	@Autowired
    private TmTApplicationConfigDaoHibernate tmTApplicationConfigDao;
	@Autowired
	private CommonDaoHibernate commonDao;

	/**
	 * 根据主键对象TmTApplicationConfigId获取TmTApplicationConfig信息
	 * @param TmTApplicationConfigId
	 * @return TmTApplicationConfig
	 */
	public TmTApplicationConfig findTmTApplicationConfigByPK(TmTApplicationConfigId id) throws Exception{
			logger.debug("正在执行TmTApplicationConfigServiceImpl.findTmTApplicationConfigByPK，获取TmTApplicationConfig对象");
			return tmTApplicationConfigDao.get(TmTApplicationConfig.class,id);
	}

	/**
	 * 根据tmTApplicationConfig和页码信息，获取Page对象
	 * @param tmTApplicationConfig
	 * @param pageNo
	 * @param pageSize
	 * @return 包含TmTApplicationConfig的Page对象
	 */
	public Page findByTmTApplicationConfig(TmTApplicationConfig tmTApplicationConfig, int pageNo, int pageSize) throws Exception{
		logger.debug("正在执行TmTApplicationConfigServiceImpl.findByTmTApplicationConfig，获取包含TmTApplicationConfig的Page对象");
		StringBuffer s = new StringBuffer("select * from TM_T_APPLICATIONCONFIG a where 1=1 ");
		if(tmTApplicationConfig!=null 
				&& ((null!=tmTApplicationConfig.getId()&&ToolsUtils.notEmpty(tmTApplicationConfig.getId().getServerCode()))
				|| ToolsUtils.notEmpty(tmTApplicationConfig.getServerName())
				|| ToolsUtils.notEmpty(tmTApplicationConfig.getServerContext())		
				|| ToolsUtils.notEmpty(tmTApplicationConfig.getValidStatus())		
				)){
						s.append(SqlUtils.addConditions(tmTApplicationConfig, "a"));//查询条件封装
				}
		return commonDao.findObjectPageBySql("select * from ("+s.toString()+")  tt", TmTApplicationConfig.class, pageNo, pageSize, null);

	}
	

	/**
	 * 更新TmTApplicationConfig信息
	 * @param TmTApplicationConfig
	 */
	public void updateTmTApplicationConfig(TmTApplicationConfig tmTApplicationConfig) throws Exception{
			logger.debug("正在执行TmTApplicationConfigServiceImpl.updateTmTApplicationConfig，更新TmTApplicationConfig信息");
			
			tmTApplicationConfigDao.update(tmTApplicationConfig);
	}

	/**
	 * 保存TmTApplicationConfig信息
	 * @param TmTApplicationConfig
	 */
	public void saveTmTApplicationConfig(TmTApplicationConfig tmTApplicationConfig) throws Exception{
			logger.debug("正在执行TmTApplicationConfigServiceImpl.saveTmTApplicationConfig，保存TmTApplicationConfig信息");
			tmTApplicationConfigDao.save(tmTApplicationConfig);
	}

	/**
	 * 根据主键对象，删除TmTApplicationConfig信息
	 * @param TmTApplicationConfigId
	 */
	public void deleteByPK(TmTApplicationConfigId id) throws Exception{
			logger.debug("正在执行TmTApplicationConfigServiceImpl.deleteByPK，删除TmTApplicationConfig信息");
			tmTApplicationConfigDao.deleteByPK(TmTApplicationConfig.class,id);
	}
	
	
	/**
	 * 根据上下文查询服务配置表tmTApplicationConfig，获取登录、登录成功跳转的页面，默认为内部登录页面
	 * UserType:01表示内部。02表示外部
	 * @param context 上下文
	 * @return
	 * @throws Exception
	 */
	public String getPageByContext(String context) throws Exception{
		
		String logonPage = "/login.jsp";
		
		TmTApplicationConfig tmTApplicationConfig = this.getApplicationByContext(context);
		if(tmTApplicationConfig!=null&&tmTApplicationConfig.getLoginPage()!=null&&!"".equals(tmTApplicationConfig.getLoginPage())){
			logonPage = tmTApplicationConfig.getLoginPage();
		}else{
			throw new BadCredentialsException("您访问的系统【"+context+"】不存在，请联系管理员！");
		}
		return logonPage;
	}
	
	/**
	 * 根据上下文查询服务配置表tmTApplicationConfig
	 * @param context 上下文
	 * @return
	 * @throws Exception
	 */
	public TmTApplicationConfig getApplicationByContext(String context) throws Exception{
		TmTApplicationConfig tmTApplicationConfig = (TmTApplicationConfig)applicationCacheService.getCache(context);
		
		if(tmTApplicationConfig==null){
			tmTApplicationConfig = new TmTApplicationConfig();
			tmTApplicationConfig.setServerContext(context);
			Page page = this.findByTmTApplicationConfig(tmTApplicationConfig, 1, 100);
			ArrayList<TmTApplicationConfig> tmTApplicationConfigList = (ArrayList<TmTApplicationConfig> )page.getResult();
			if(tmTApplicationConfigList!=null&&tmTApplicationConfigList.size()>0&&tmTApplicationConfigList.get(0)!=null){
				tmTApplicationConfig = tmTApplicationConfigList.get(0);
				applicationCacheService.putCache(context, tmTApplicationConfig);
			}
		}
		return tmTApplicationConfig;
	}

	public List<TmTApplicationConfig> selectSerCode() throws Exception {
			// TODO Auto-generated method stub
			QueryRule rule = QueryRule.getInstance();
			rule.addEqual("validStatus", "1");  //只查询有效的数据   1-有效；0-无效
			List<TmTApplicationConfig> resultList = tmTApplicationConfigDao.find(rule);
			return resultList;
	}
}
