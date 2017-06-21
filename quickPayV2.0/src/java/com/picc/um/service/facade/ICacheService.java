package com.picc.um.service.facade;

import ins.framework.cache.info.CacheManagerInfo;
import ins.framework.common.Page;

import java.util.List;
import java.util.Map;

/**
 * 缓存处理自定义接口
 * @author 杨联
 * 修改 姜卫洋 2013-09-10
 */
public interface ICacheService {
	
	/**
	 * 根据缓存名称查找出缓存管理对象
	 * @param cacheName
	 * @return
	 * yanglian
	 */
	public CacheManagerInfo findCacheManagerInfoByCacheName(String cacheName);
	
	/**
	 * 查询所有缓存清单
	 * @return
	 * yanglian
	 */
	public List<CacheManagerInfo> findAllCacheManager(); 
	
	/**
	 * 分页查询缓存清单
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page findCacheManager(CacheManagerInfo cacheManagerInfo,int pageNo, int pageSize)throws Exception;;
	/**
	 * 删除对应缓存管理对象
	 * @param cacheManagerInfo
	 */
	public void deleteCacheManager(CacheManagerInfo cacheManagerInfo);
	
	/**
	 * 重新加载用户与角色的对应关系(根据用户代码重建用户角色缓存)
	 * @param userCode					用户代码
	 * 2013-9-6下午8:30:09
	 * jiangweiyang
	 */
	public void reloadUserRoleCache(String userCode);
	
	/**
	 * 重新加载URL请求与角色之间的对应关系(重新构建请求与角色之间的映射关系)
	 * 2013-9-6下午8:30:16
	 * jiangweiyang
	 */
	public void reloadUrlRoleCache();
	
	/**
	 * 更新访问URL与角色之间的对应关系
	 * @param roleId					角色ID
	 * @param taskId					功能ID
	 * @param updateType				操作类型(1添加关联、-1去除关联)
	 * 2013-9-6下午8:30:20
	 * jiangweiyang
	 */
	public void updateUrlRoleMatcher(String roleId,String taskId,int updateType);
	
	/**
	 * 重新构建用户与访问URL之间的对应关系
	 * @param userCode					用户代码
	 * 2013-9-6下午8:30:23
	 * jiangweiyang
	 */
	public void reloadUserTaskCache(String userCode);
	
	
	
	
	/**
	 * 在功能管理模块中将现有的URL功能注销,修改访问URL
	 * @param methodCode				访问的URL
	 * @param operateType
	 * 2013-9-8下午7:00:48
	 * jiangweiyang
	 */
	public void updateModifyUrl(String methodCode,int operateType);
	
	
	
	
	
	/**
	 * 更新一下角色,同步更新缓存对象
	 * @param roleId					角色ID
	 * @param roleCode			角色代码
	 * @param operateType		操作类型(1 添加角色  -1删除角色)
	 * 2013-9-8下午9:20:45
	 * jiangweiyang
	 */
	public void updateRole(String roleId,String roleCode,int operateType);
	
	
	
	/**
	* @Title: reloadWhiteListCache				
	* @Description: 重新加载白名单缓存
	* @param     设定文件
	* @return void    返回类型
	* @author jiangweiyang
	* @date 2014/01/13
	 */
	public void reloadWhiteListCache();
	
	
	
	/**
	* @Title: reloadRoleDimeCache
	* @Description:	重新加载角色维度扩展对象
	* @param     
	* @return void    返回类型
	* @author jiangweiyang
	* @date 2014/01/13
	 */
	public void reloadRoleDimeCache();
	
	
	
	/**
	* @Title: reloadCognosCache
	* @Description: 重新加截Cognos缓存信息
	* @param     设定文件
	* @return void    返回类型
	* @author jiangweiyang
	* @date 2014/01/13
	 */
	public void reloadCognosCache(String comId);
	
	
	
	/**
	 * 
	* @Title: reloadLogTypeCache
	* @Description: 重新刷新日志记录信息
	* @param @param comId   对应的机构ID
	* @return void    返回类型
	* @throws
	 */
	public void reloadLogTypeCache(String comId);
	
	
	
	
	/**
	 * 刷新各节点中的缓存数据信息
	* @Title: refreshNodeCache
	* @Description: 轮循刷新各应用节点中的缓存数据
	* @param     cacheList 		需要进行刷新的容器名称
	* @param  	comId				进行刷新操作的省级代码
	* @return 	map    				各节点执行结果
	* @throws
	* @author jiangweiyang
	* @date 2014/01/13
	 */
	public Map<String,String> refreshNodeCache(List<String> cacheList,String comId) throws Exception;
	
	
	/**
	 * 重新加载应用配置缓存
	 * 
	 *@author yaowenfeng
	 */
	public void reloadApplicationCache();
	
}
