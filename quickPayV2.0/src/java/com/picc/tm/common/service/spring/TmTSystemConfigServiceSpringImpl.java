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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.Constants;
import com.picc.common.QueryRuleHelper;
import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.utils.ToolsUtils;
import com.picc.tm.common.dao.TmTSystemConfigDaoHibernate;
import com.picc.tm.common.schema.model.TmTSystemConfig;
import com.picc.tm.common.schema.model.TmTSystemConfigId;
import com.picc.tm.common.service.facade.ITmTSystemConfigService;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.service.spring.UmTSaugroupServiceSpringImpl;
import com.picc.um.service.spring.UmTSauuserServiceSpringImpl;
import com.picc.um.service.spring.UmTUserPowerServiceSpringImpl;
import com.sinosoft.bpsdriver.util.SystemCode;
import com.sinosoft.dmsdriver.model.PrpDcompany;
import com.sinosoft.dmsdriver.service.common.DictPage;
import com.sinosoft.dmsdriver.service.server.PageService;

/**
 * 系统参数配置接口实体类
 * @author moxiaoguang 更新 2013-12-12
 *
 */
@Service("tmTSystemConfigService")
public class TmTSystemConfigServiceSpringImpl implements ITmTSystemConfigService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static CacheService cacheService = CacheManager.getInstance("TM_T_SYSTEMCONFIG");
	
	@Autowired
    private   TmTSystemConfigDaoHibernate tmTSystemConfigDao;
	
	@Autowired
	private CommonDaoHibernate commonDao;

	//用户权限控制service
	private UmTSaugroupServiceSpringImpl umTSaugroupService;
	private UmTSauuserServiceSpringImpl umTSauuserService;
	private UmTUserPowerServiceSpringImpl umTUserPowerService;
	public UmTSaugroupServiceSpringImpl getUmTSaugroupService() {
		return umTSaugroupService;
	}

	public void setUmTSaugroupService(
			UmTSaugroupServiceSpringImpl umTSaugroupService) {
		this.umTSaugroupService = umTSaugroupService;
	}

	public UmTSauuserServiceSpringImpl getUmTSauuserService() {
		return umTSauuserService;
	}

	public void setUmTSauuserService(UmTSauuserServiceSpringImpl umTSauuserService) {
		this.umTSauuserService = umTSauuserService;
	}

	public UmTUserPowerServiceSpringImpl getUmTUserPowerService() {
		return umTUserPowerService;
	}

	public void setUmTUserPowerService(
			UmTUserPowerServiceSpringImpl umTUserPowerService) {
		this.umTUserPowerService = umTUserPowerService;
	}

	/**
	 * 根据主键对象TmTSystemConfigId获取TmTSystemConfig信息
	 * @param TmTSystemConfigId
	 * @return TmTSystemConfig
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public TmTSystemConfig findTmTSystemConfigByPK(TmTSystemConfigId id) throws Exception{
			return tmTSystemConfigDao.get(TmTSystemConfig.class,id);
	}
	/**
	 * 根据tmTSystemConfig和页码信息，获取Page对象
	 * 限于外部页面调用，需要添加权限校验
	 * @param tmTSystemConfig
	 * @param pageNo
	 * @param pageSize
	 * @return 包含TmTSystemConfig的Page对象
	 */
	public Page findByTmTSystemConfig(TmTSystemConfig tmTSystemConfig, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		queryRule = QueryRuleHelper.generateQueryRule(tmTSystemConfig);
	
		return tmTSystemConfigDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 根据tmTSystemConfig和页码信息，获取Page对象
	 * 限于外部页面调用，需要添加权限校验
	 * @param tmTSystemConfig
	 * @param pageNo
	 * @param pageSize
	 * @return 包含TmTSystemConfig的Page对象
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public Page findByTmTSystemConfig(TmTSystemConfig tmTSystemConfig, int pageNo, int pageSize,SessionUser sessionUser) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		queryRule = QueryRuleHelper.generateQueryRule(tmTSystemConfig);
		if(!"00000000".equals(sessionUser.getComCode()))
		{
			
			//数据权限控制开始
			Map<String,String> targetObjectSelf = new HashMap<String,String>();
			targetObjectSelf.put("Tm_T_SystemConfig", "this_");
			String powerSql = umTUserPowerService.getCondSQLByUserTable(sessionUser.getUserCode(), targetObjectSelf);
			queryRule.addSql(powerSql);
		}
		return tmTSystemConfigDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新TmTSystemConfig信息
	 * @param TmTSystemConfig
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public void updateTmTSystemConfig(TmTSystemConfig tmTSystemConfig) throws Exception{
			tmTSystemConfigDao.update(tmTSystemConfig);
			//更新缓存
			StringBuffer sb = new StringBuffer();
			sb.append(tmTSystemConfig.getComCode());
			sb.append("_");
			sb.append(tmTSystemConfig.getConfigCode());
			String key = sb.toString();
			TmTSystemConfig configDto = null;
			if(cacheService.containsKey(key))
			{
				cacheService.remove(key);
				QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
				queryRule.addEqual("comCode", tmTSystemConfig.getComCode());
				queryRule.addEqual("configCode", tmTSystemConfig.getConfigCode());
				List<TmTSystemConfig> list = tmTSystemConfigDao.find(queryRule);
				if(list.size() >0) {
					configDto = list.get(0);
					cacheService.putCache(key, configDto);
				}
			}
			else
			{
				QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
				queryRule.addEqual("comCode", tmTSystemConfig.getComCode());
				queryRule.addEqual("configCode", tmTSystemConfig.getConfigCode());
				List<TmTSystemConfig> list = tmTSystemConfigDao.find(queryRule);
				if(list.size() >0) {
					configDto = list.get(0);
					cacheService.putCache(key, configDto);
				}
			}
	}

	/**
	 * 保存TmTSystemConfig信息
	 * @param TmTSystemConfig
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public void saveTmTSystemConfig(TmTSystemConfig tmTSystemConfig) throws Exception{
			tmTSystemConfigDao.save(tmTSystemConfig);
			//更新缓存
			StringBuffer sb = new StringBuffer();
			sb.append(tmTSystemConfig.getComCode());
			sb.append("_");
			sb.append(tmTSystemConfig.getConfigCode());
			String key = sb.toString();
			TmTSystemConfig configDto = null;
			if(cacheService.containsKey(key))
			{
				cacheService.remove(key);
				QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
				queryRule.addEqual("comCode", tmTSystemConfig.getComCode());
				queryRule.addEqual("configCode", tmTSystemConfig.getConfigCode());
				List<TmTSystemConfig> list = tmTSystemConfigDao.find(queryRule);
				if(list.size() >0) {
					configDto = list.get(0);
					cacheService.putCache(key, configDto);
				}
			}
			else
			{
				QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
				queryRule.addEqual("comCode", tmTSystemConfig.getComCode());
				queryRule.addEqual("configCode", tmTSystemConfig.getConfigCode());
				List<TmTSystemConfig> list = tmTSystemConfigDao.find(queryRule);
				if(list.size() >0) {
					configDto = list.get(0);
					cacheService.putCache(key, configDto);
				}
			}
	}

	/**
	 * 根据主键对象，删除TmTSystemConfig信息
	 * @param TmTSystemConfigId
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public void deleteByPK(TmTSystemConfigId id) throws Exception{
			TmTSystemConfig tmTSystemConfig = this.findTmTSystemConfigByPK(id);
			if(null==tmTSystemConfig)
			{
				return;
			}
			else
			{
				StringBuffer sb = new StringBuffer();
				sb.append(tmTSystemConfig.getComCode());
				sb.append("_");
				sb.append(tmTSystemConfig.getConfigCode());
				String key = sb.toString();
				if(cacheService.containsKey(key))
				{
					cacheService.remove(key);
				}
			}
			tmTSystemConfigDao.deleteByPK(TmTSystemConfig.class,id);
	}
	
	/**
	 * 获取系统参数配置 TmTSystemConfig 对象
	 * @param tmTSystemConfig
	 * @return
	 * @throws Exception
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public TmTSystemConfig findByTmTSystemConfig(TmTSystemConfig tmTSystemConfig) throws Exception {
		
		TmTSystemConfig configDto = null;
		StringBuffer sb = new StringBuffer();
		sb.append(tmTSystemConfig.getComCode());
		sb.append("_");
		sb.append(tmTSystemConfig.getConfigCode());
		String key = sb.toString();
		configDto = (TmTSystemConfig) cacheService.getCache(key);
		if(null==configDto)
		{
			QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
			queryRule.addEqual("comCode", tmTSystemConfig.getComCode());
			queryRule.addEqual("configCode", tmTSystemConfig.getConfigCode());
			queryRule.addEqual("validStatus", tmTSystemConfig.getValidStatus());
			List<TmTSystemConfig> list = tmTSystemConfigDao.find(queryRule);
			if(list.size() >0) {
				configDto = list.get(0);
				cacheService.putCache(key, configDto);
			}
			
			return configDto;
			
		}
		else
		{
			return configDto;
		}
		
		
	}

	/**
	 * 根据归属机构代码，配置代码，以及是否查询上级标识查找指定配置的值
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public TmTSystemConfig findByConfigCode(String comCode, String configCode,boolean searchUpper) {
		
		TmTSystemConfig configDto = null;
		StringBuffer sb = new StringBuffer();
		sb.append(comCode);
		sb.append("_");
		sb.append(configCode);
		String key = sb.toString();
		configDto = (TmTSystemConfig) cacheService.getCache(key);
		if(null==configDto)
		{
			if(searchUpper){
				String[] s;
				try {
					s = queryUpperCompany(comCode,1,Constants.MaxPageSize);
					if(s!=null&&s.length>0){
						//根据各级的comcode的岗位一次性查询配置
						QueryRule queryRule = QueryRule.getInstance();
						queryRule.addEqual("configCode", configCode);
						queryRule.addEqual("validStatus","1");	
						queryRule.addIn("comCode", (Object[])s);
						List<TmTSystemConfig> list = tmTSystemConfigDao.find(queryRule);
						if(list!=null && list.size()>0){
							//然后再寻找和本级最接近那个级别的配置
							for(int i=s.length-1;i>=0;i--){
								  for(TmTSystemConfig t:list){
										if(t.getComCode().equals(s[i])){
											configDto = list.get(0);
											cacheService.putCache(key, configDto);
											return (configDto);
										}
									}
							}	
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("", e);
				}

			}else{
				QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
				queryRule.addEqual("comCode", comCode);
				queryRule.addEqual("configCode", configCode);
				queryRule.addEqual("validStatus", "1");//必须为有效的才使用
				List<TmTSystemConfig> list = tmTSystemConfigDao.find(queryRule);
				if(list.size() >0) {
					configDto = list.get(0);
					cacheService.putCache(key, configDto);
				}
				
			 return configDto;
			}
		}
		else
		{
				return configDto;
		}
		if(configDto==null){
			return new TmTSystemConfig();
		}
		else{
			return (configDto);
		}
	}

	
	/**
	 * 生成ID
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public String genId(String comId4) {
		
		return this.commonDao.generateID("TMSC"+ToolsUtils.getCurrentDate("yyyy")+comId4, "tm_seq_systemconfig",10);
	}

	/**
	 * 查询归属机构 （包含 上级机构）
	 * @
	 * @return
	 * @throws Exception 
	 * @author moxiaoguang 更新 2013-12-12
	 */
	@SuppressWarnings("unchecked")
	public String[] queryUpperCompany(String comCode,int pageNo,int pageSize) throws Exception {
		//HttpServletRequest request = getRequest();
		//登陆用户信息
		//SessionUser sessionUser = getUserFromSession();
		//String comCode = sessionUser.getComCode();
        
		if (pageNo == 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 10;
		}
		try {
			DictPage dictPage = PageService.getCompanys(SystemCode.DMS, comCode, "", "", "1", "", pageNo, pageSize);
			List<PrpDcompany> list=dictPage.getData();
			if(list!=null && list.size()>0){
				//00000000,44030000
				String s=list.get(0).getUpperPath();
				if(s!=null){
					return(s.split(","));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			//如果数据字典有问题则
			return new String[]{comCode};
			
		}
		return null;
	}
	/**
	 * 根据归属机构代码，配置代码，以及是否查询上级标识查找指定配置的值
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public String findConfigValueByConfigCode(String comCode,
			String configCode, boolean searchUpper) {
		
		TmTSystemConfig configDto = new TmTSystemConfig();
		StringBuffer sb = new StringBuffer();
		sb.append(comCode);
		sb.append("_");
		sb.append(configCode);
		String key = sb.toString();
		
		configDto = (TmTSystemConfig) cacheService.getCache(key);
		if(null==configDto)
		{
			if(searchUpper){
				String[] s;
				try {
					s = queryUpperCompany(comCode,1,Constants.MaxPageSize);
					if(s!=null&&s.length>0){
						//根据各级的comcode的岗位一次性查询配置
						QueryRule queryRule = QueryRule.getInstance();
						queryRule.addEqual("configCode", configCode);
						queryRule.addEqual("validStatus","1");	
						queryRule.addIn("comCode", (Object[])s);
						queryRule.addDescOrder("comCode");
						List<TmTSystemConfig> list = tmTSystemConfigDao.find(queryRule);
						if(list!=null && list.size()>0){
							//然后再寻找和本级最接近那个级别的配置
							for(int i=s.length-1;i>=0;i--){
								  for(TmTSystemConfig t:list){
										if(t.getComCode().equals(s[i])){
											configDto = t;
											break;
											
										}
								}
								if(null!=configDto)
								{
									break;
								}
							}
							cacheService.putCache(key, configDto);
							return (configDto).getConfigValue();
						}
						else{
							return "";
						}
					}
					else
					{
						//没有机构代码返回空值
						return "";
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("", e);
					return "";
				}
			}else{
				QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
				queryRule.addEqual("comCode", comCode);
				queryRule.addEqual("configCode", configCode);
				queryRule.addEqual("validStatus", "1");//必须为有效的才使用
				List<TmTSystemConfig> list = tmTSystemConfigDao.find(queryRule);
				if(list.size() >0) {
					configDto = list.get(0);
					cacheService.putCache(key, configDto);
				}
				if(null==configDto||"".equals(configDto.getConfigValue()))
				{
					return "";
				}
				else
				{
					return configDto.getConfigValue();//新值不用判断有效性
				}
			}
		}
		else
		{
			if("1".equals(configDto.getValidStatus()))
			{
				return configDto.getConfigValue();
			}
			else
			{
				return "";
			}
		}
	}

 	
}
