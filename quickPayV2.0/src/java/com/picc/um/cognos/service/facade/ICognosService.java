package com.picc.um.cognos.service.facade;

import java.util.List;

import com.picc.common.vo.TreeNode;
import com.picc.um.cognos.shema.CognosMenuVo;
import com.picc.um.cognos.utils.CRNConnect;
import com.picc.um.schema.model.UmTAccount;
import com.picc.um.schema.model.UmTRole;
import com.picc.um.schema.model.UmTUser;

/**
 * Cognos自定义接口
 * @author shenyichan
 */
public interface ICognosService {

	/**
	 * 根据用户代码获取菜单信息
	 * 
	 * @param connection
	 * @param usercode
	 * @return
	 * @author shenyichan
	 */
	public String findMenuByUserCode(CRNConnect connection, String usercode,String comId,String netType)
			throws Exception;

	/**
	 * 将角色(及角色下的人员)同步至Cognos角色
	 * 
	 * @param connection
	 * @param umTRole
	 * @author shenyichan
	 */
	public void addCognosRole(CRNConnect connection, UmTRole umTRole,
			String comId) throws Exception;
	
	
	/**
	 * 将群组(及群组下的人员)同步至Cognos角色
	 * @param connection
	 * @param groupType				群组类型(comcode:机构 monopolycode:车行)
	 * @param groupCode				群组代码
	 * @param comId			
	 * @throws Exception
	 * 2013-10-16下午7:26:35
	 * jiangweiyang
	 */
	public void addCognosGroup(CRNConnect connection, String groupType,String groupCode,
			String comId) throws Exception;
	
	
	/**
	 * 同步单一用户至相应的权限组
	* @Title: addCognosGroup
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param connection
	* @param @param groupType
	* @param @param groupCode
	* @param @param comId
	* @param @param userCode
	* @param @throws Exception    设定文件
	* @return void    返回类型
	* @throws
	 */
	public void addCognosGroup(CRNConnect connection, String groupType,String groupCode,
			String comId,String userCode) throws Exception;
	
	

	/**
	 * 将用户全部添加至Cognos角色
	 * @param connection
	 * @param role
	 * @param userList
	 * @throws Exception
	 */
	public void addUserToCognosRole(CRNConnect connection, UmTRole role,
			List<UmTUser> userList,String comId) throws Exception;
	
	
	
	/**
	 * 将用户添加至Congos群组
	 * @param connection
	 * @param groupCode			群组代码
	 * @param userList				用户List集合
	 * @param comId
	 * @throws Exception
	 * 2013-10-16下午7:29:23
	 * jiangweiyang
	 */
	public void addUserToCognosGroup(CRNConnect connection, String groupCode,
			List<String> userList,String comId) throws Exception;
	
	
	/**
	 * 将部分用户添加至Cognos角色
	 * @param connection
	 * @param role
	 * @param userList
	 * @throws Exception
	 */
	public void addSomeUserToCognosRole(CRNConnect connection, UmTRole role,
			List<UmTUser> userList,String comId) throws Exception;


	/**
	 * 只根据命名空间取菜单
	 * 
	 * @param connection
	 * @param searchPath
	 * @return
	 * @throws Exception
	 */
	public List<TreeNode> getCognosCatalog(CRNConnect connection,
			String searchPath,String comId,String netType) throws Exception;

	/**
	 * 获取Cognos所有菜单
	 * 
	 * @param connection
	 * @return
	 */
	public String findAllMenu(CRNConnect connection, String searchPath,
			String roleName,String comId,String netType) throws Exception;

	/**
	 * 检查当前角色是否是Cognos角色
	 * 
	 * @param connection
	 * @param umTRole
	 * @return
	 * @author shenyichan
	 */
	public boolean checkCognosRole(CRNConnect connection, UmTRole umTRole,String comId)
			throws Exception;

	/**
	 * 将菜单添加至Cognos角色
	 * 
	 * @param connection
	 * @param roleName
	 * @param addSearchPath
	 * @param oc
	 * @throws Exception
	 */
	public void addMenuToCognosRole(CRNConnect connection, String roleName,
			List<String> addSearchPath, List<String> oc,String comId) throws Exception;

	/**
	 * 获取所有父级菜单
	 * 
	 * @param connection
	 * @param pathFolder
	 * @return
	 * @throws Exception
	 */
	public List<String> findSubMenuSearchPath(CRNConnect connection,
			String pathFolder) throws Exception;

	/**
	 * 从Cognos角色中删除菜单
	 * 
	 * @param connection
	 * @param roleName
	 * @param parentSearchPath
	 * @Param delSearchPath
	 * @param oc
	 */
	public void delMenuFromCognosRole(CRNConnect connection, String roleName,
			List<String> parentSearchPath, List<String> delSearchPath,
			List<String> oc,String comId) throws Exception;

	/**
	 * 判断菜单是否在Cognos角色中
	 * 
	 * @param connection
	 * @param roleName
	 * @param folder2
	 * @return
	 */
	public boolean menuBelongToRole(CRNConnect connection, String roleName,
			String folder,String comId) throws Exception;

	/**
	 * 查询出所有的Cognos菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	public void findAllCognosMenu(CRNConnect connection, String searchPath,
			String upperMenuId, List<CognosMenuVo> cognosMenuVoList)
			throws Exception;
	
	public void updateCognosRole(CRNConnect connection,UmTRole initRole, UmTRole role,String comId);
	
	
	/**
	 * 根据默认cognos管理员用户的帐户信息,后台登录cognos获取最大集合的菜单信息
	* @Title: getCognosMaxMenu
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param account
	* @param @throws Exception    设定文件
	* @return void    返回类型
	* @throws
	 */
	public void getCognosMaxMenu(UmTAccount account) throws Exception;
	
	
	
	/**
	 * 初始化Cognos设置信息(缓存失效时进行触发)
	* @Title: initCognosConfigSet
	* @Description: TODO 重新加载Congos缓存信息
	* @param @throws Exception   程序运行过程中抛出的异常信息
	* @return void    返回类型
	* @throws
	 */
	public void initCognosConfigSet() throws Exception;
	
	
}
