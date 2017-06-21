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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.QueryRuleHelper;
import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.utils.AppConfig;
import com.picc.um.dao.UmTRoleDimeDaoHibernate;
import com.picc.um.schema.model.UmTRoleDime;
import com.picc.um.schema.model.UmTRoleDimeId;
import com.picc.um.schema.model.UmTRoleTask;
import com.picc.um.schema.vo.RoleDimeVo;
import com.picc.um.service.facade.IUmTRoleDimeService;
import com.picc.um.service.facade.IUmTRoleService;

/**
 * 角色功能扩展接口实现类
 * @author 姜卫洋
 */
@Service("umTRoleDimeService")
public class UmTRoleDimeServiceSpringImpl implements IUmTRoleDimeService {
	
	private static CacheService roleDimeCacheService = CacheManager.getInstance("UM_T_ROLEDIME");

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UmTRoleDimeDaoHibernate umTRoleDimeDao;

	@Autowired
	private CommonDaoHibernate commonDao;

	@Autowired
	private IUmTRoleService umTRoleService;

	/**
	 * 根据主键对象UmTRoleDimeId获取UmTRoleDime信息
	 * 
	 * @param UmTRoleDimeId
	 * @return UmTRoleDime
	 */
	public UmTRoleDime findUmTRoleDimeByPK(UmTRoleDimeId id) throws Exception {
		return umTRoleDimeDao.get(UmTRoleDime.class, id);
	}

	/**
	 * 根据umTRoleDime和页码信息，获取Page对象
	 * 
	 * @param umTRoleDime
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTRoleDime的Page对象
	 */
	public Page findByUmTRoleDime(UmTRoleDime umTRoleDime, int pageNo,
			int pageSize) throws Exception {
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		// 根据umTRoleDime生成queryRule
		return umTRoleDimeDao.find(queryRule, pageNo, pageSize);
	}
	
	
	
	/**
	 * 根据查询对象拼接条件查询unique相应结果
	 * @param umTRoleDime
	 * @return
	 * @throws Exception
	 * 2013-9-11下午9:22:00
	 * jiangweiyang
	 */
	public UmTRoleDime findUniqueRoleDime(UmTRoleDime umTRoleDime) throws Exception {
		QueryRule queryRule = QueryRuleHelper.generateQueryRule(umTRoleDime);
		return umTRoleDimeDao.findUnique(queryRule);
	}
	

	/**
	 * 更新UmTRoleDime信息
	 * 
	 * @param UmTRoleDime
	 */
	public void updateUmTRoleDime(UmTRoleDime umTRoleDime) throws Exception {
		umTRoleDimeDao.update(umTRoleDime);
	}

	/**
	 * 保存UmTRoleDime信息
	 * 
	 * @param UmTRoleDime
	 */
	public void saveUmTRoleDime(UmTRoleDime umTRoleDime) throws Exception {
		UmTRoleDimeId id = new UmTRoleDimeId();
		id.setRoleDimeId(commonDao.generateID("UMRD", "UM_SEQ_ROLEDIME", 6));
		umTRoleDime.setId(id);
		umTRoleDimeDao.save(umTRoleDime);
	}

	/**
	 * 根据主键对象，删除UmTRoleDime信息
	 * 
	 * @param UmTRoleDimeId
	 */
	public void deleteByPK(UmTRoleDimeId id) throws Exception {
		umTRoleDimeDao.deleteByPK(UmTRoleDime.class, id);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public synchronized void reBuildRoleDimeCache(String comId)  {
		try {
			roleDimeCacheService.clearCache(comId);						//清除本机构的角色维护扩展信息
			Page page = commonDao.findObjectPageBySql("select a.dimensionvalue,c.rolecode,b.methodcode,a.operatetype from " +
					"um_t_roledime a,um_t_methodtask b,um_t_role c where a.roleid = c.roleid and a.taskid = b.taskid and " +
					"a.dimensioncode = 'comcode' and a.validstatus = '1' and b.validstatus = '1' and c.validstatus = '1'",
					RoleDimeVo.class, 1, Integer.MAX_VALUE);
			List<RoleDimeVo> roleDimeList = (List<RoleDimeVo>)page.getResult();					//获取所有符合条件的角色维护扩展信息
			Map<String,Map<String,String>> urlRoleMap = null;
			Map<String,String> operateMap = null;
			for(RoleDimeVo roleDime : roleDimeList) {
				if((urlRoleMap = (Map<String,Map<String,String>>)roleDimeCacheService.getCache(roleDime.getDimensionValue()))==null){
					//缓存对象中没有数据
					operateMap = new HashMap<String,String>();
					operateMap.put(roleDime.getRoleCode(), roleDime.getOperateType());
					urlRoleMap = new HashMap<String,Map<String,String>>();
					urlRoleMap.put(roleDime.getMethodCode(), operateMap);
					roleDimeCacheService.putCache(roleDime.getDimensionValue(), urlRoleMap);
				}else {
					if(urlRoleMap.keySet().contains(roleDime.getMethodCode())){
						operateMap = urlRoleMap.get(roleDime.getMethodCode());
						operateMap.put(roleDime.getRoleCode(), roleDime.getOperateType());
						urlRoleMap.put(roleDime.getMethodCode(), operateMap);
						roleDimeCacheService.putCache(roleDime.getDimensionValue(), urlRoleMap);
					}else {
						operateMap = new HashMap<String,String>();
						operateMap.put(roleDime.getRoleCode(), roleDime.getOperateType());
						urlRoleMap.put(roleDime.getMethodCode(), operateMap);
						roleDimeCacheService.putCache(roleDime.getDimensionValue(), urlRoleMap);
					}
				}
			}
			logger.info("roleDimeCacheService Size:"+roleDimeCacheService.size());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
	}
	
	
	

	
	public void updateRoleTaskByRoleDimeCom(UmTRoleTask umTRoleTask,
			int operateType, String comId, String operaterCode)
			throws Exception {
		if (umTRoleTask == null
				|| AppConfig.get("um.CITY").equals(umTRoleTask.getScope())) {
			// 各省市自行设计的系统角色,不进行维度表操作
			return;
		} else if (operateType == 1) {
			// 在原有总部的角色模板上新增功能
			UmTRoleDime umTRoleDime = new UmTRoleDime();
			UmTRoleDime tmpUmTRoleDime = null;
			umTRoleDime.setDimensionCode(AppConfig.get("um.DIME_COMCODE"));
			umTRoleDime.setDimensionValue(comId);
			umTRoleDime.setRoleId(umTRoleTask.getRoleId());
			umTRoleDime.setTaskId(umTRoleTask.getTaskID());
			umTRoleDime
					.setOperateType(AppConfig.get("um.DIME_OPERATETYPE_ADD")); // 添加角色与功能的对应关系点
			if((tmpUmTRoleDime = findUniqueRoleDime(umTRoleDime))==null){
				//查询数据库中是否有角色维度信息,如果没有找到,则保存数据
				umTRoleDime.setCreatorCode(operaterCode);
				umTRoleDime.setValidStatus("1");
				saveUmTRoleDime(umTRoleDime);
			}else {
				tmpUmTRoleDime.setValidStatus("1");
				updateUmTRoleDime(tmpUmTRoleDime);
			}
		} else if (operateType == 2) {
			// 在原有总部的角色模板上去除功能
			UmTRoleDime umTRoleDime = new UmTRoleDime();
			UmTRoleDime tmpUmTRoleDime = null;
			umTRoleDime.setDimensionCode(AppConfig.get("um.DIME_COMCODE"));
			umTRoleDime.setDimensionValue(comId);
			umTRoleDime.setRoleId(umTRoleTask.getRoleId());
			umTRoleDime.setTaskId(umTRoleTask.getTaskID());
			umTRoleDime
					.setOperateType(AppConfig.get("um.DIME_OPERATETYPE_SUB")); // 添加角色与功能的对应关系点
			if((tmpUmTRoleDime = findUniqueRoleDime(umTRoleDime))==null){
				//查询数据库中是否有角色维度信息,如果没有找到,则保存数据
				umTRoleDime.setCreatorCode(operaterCode);
				umTRoleDime.setValidStatus("1");
				saveUmTRoleDime(umTRoleDime);
			}else {
				tmpUmTRoleDime.setValidStatus("1");
				updateUmTRoleDime(tmpUmTRoleDime);
			}
		} else if (operateType == 3) {
			//修复角色与功能的关联回复与总部的角色模块保持一致
			UmTRoleDime umTRoleDime = new UmTRoleDime();
			umTRoleDime.setDimensionCode(AppConfig.get("um.DIME_COMCODE"));
			umTRoleDime.setDimensionValue(comId);
			umTRoleDime.setRoleId(umTRoleTask.getRoleId());
			umTRoleDime.setTaskId(umTRoleTask.getTaskID());
			umTRoleDime = findUniqueRoleDime(umTRoleDime);					//查询维护表中该对象
			if(umTRoleDime!=null){
				deleteByPK(umTRoleDime.getId());
			}
		}
	}

}
