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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.AccessDeniedException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.stereotype.Service;

import com.picc.common.Constants;
import com.picc.common.QueryRuleHelper;
import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.utils.ToolsUtils;
import com.picc.common.vo.TreeNode;
import com.picc.um.dao.UmTUserDaoHibernate;
import com.picc.um.dao.UmTUserPowerDaoHibernate;
import com.picc.um.schema.model.UmTMethodTask;
import com.picc.um.schema.model.UmTRole;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.model.UmTUserPower;
import com.picc.um.schema.model.UmTUserPowerId;
import com.picc.um.schema.vo.FieldOperateValue;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.schema.vo.UmTUserPowerDictName;
import com.picc.um.service.facade.IUmTDictionaryDetailService;
import com.picc.um.service.facade.IUmTMethodTaskService;
import com.picc.um.service.facade.IUmTRoleService;
import com.picc.um.service.facade.IUmTUserPowerService;
import com.sinosoft.dmsdriver.model.PrpDcompany;

/**
 * 用户数据权限接口实现类
 * @author 姜卫洋
 */
@Service("umTUserPowerService")
public class UmTUserPowerServiceSpringImpl implements IUmTUserPowerService{
	
	private static CacheService cacheService = CacheManager.getInstance("UserPower");				//用户数据权限缓存
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private UmTUserPowerDaoHibernate umTUserPowerDao;
	
	@Autowired
    private CommonDaoHibernate commonDao;
	
	@Autowired
	private IUmTDictionaryDetailService umTDictionaryDetailService;
	
	@Autowired
	private UmTUserDaoHibernate umTUserDao;
	
	@Autowired
	private IUmTRoleService umTRoleService = null;
	
	@Autowired
	private IUmTMethodTaskService umTMethodTaskService;
	
	/**
	 * 根据主键对象UmTUserPowerId获取UmTUserPower信息
	 * @param UmTUserPowerId
	 * @return UmTUserPower
	 */
	public UmTUserPower findUmTUserPowerByPK(UmTUserPowerId id) throws Exception{
			return umTUserPowerDao.get(UmTUserPower.class,id);
	}

	/**
	 * 根据umTUserPower和页码信息，获取Page对象
	 * @param umTUserPower
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTUserPower的Page对象
	 */
	public Page findByUmTUserPower(UmTUserPower umTUserPower, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = null;
		if(umTUserPower==null){
			return null;
		}else {
			String symbol = umTUserPower.getOperationSymbol();
			umTUserPower.setOperationSymbol(null);
			queryRule = QueryRuleHelper.generateQueryRule(umTUserPower);
			if(!"".equals(symbol)){
			String operateSymbol = UmTUserPowerServiceSpringImpl.getOperationSymbolByCode(symbol);
			queryRule.addEqual("operationSymbol", operateSymbol);
			}
		}
		return umTUserPowerDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新UmTUserPower信息
	 * @param UmTUserPower
	 */
	public void updateUmTUserPower(UmTUserPower umTUserPower) throws Exception{
		umTUserPower.setOperationSymbol(UmTUserPowerServiceSpringImpl.getOperationSymbolByCode(umTUserPower.getOperationSymbol()));
		try{
			if(!isExists(umTUserPower.getUserCode(),umTUserPower.getOperationSymbol(),umTUserPower.getPowerValue(),umTUserPower.getDictionaryId()))
				umTUserPowerDao.update(umTUserPower);
			else
				deleteByPK(umTUserPower.getId());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	private boolean isExists(String userCode,String operateSymbol,String powerValue,String dictionaryId) throws Exception {
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("dictionaryId", dictionaryId);
			queryRule.addEqual("userCode", userCode);
			queryRule.addEqual("operationSymbol", operateSymbol);
			queryRule.addEqual("powerValue", powerValue);
			if(umTUserPowerDao.find(queryRule).size()>0){
				return true;
			}else {
				return false;
			}
		}catch(Exception ex){
			throw ex;
		}
	}

	/**
	 * 保存UmTUserPower信息
	 * @param UmTUserPower
	 */
	public void saveUmTUserPower(UmTUserPower umTUserPower) throws Exception{
			//设置主键
			UmTUserPowerId id = new UmTUserPowerId();
			id.setUserPowerId(commonDao.generateID("UMUP", "UM_SEQ_USERPOWER", 26));
			umTUserPower.setId(id);
			umTUserPower.setOperationSymbol(UmTUserPowerServiceSpringImpl.getOperationSymbolByCode(umTUserPower.getOperationSymbol()));
			if(!isExists(umTUserPower.getUserCode(),umTUserPower.getOperationSymbol(),umTUserPower.getPowerValue(),umTUserPower.getDictionaryId()))
				umTUserPowerDao.save(umTUserPower);
	}
	
	/**
	 * 根据主键对象，删除UmTUserPower信息
	 * @param UmTUserPowerId
	 */
	public void deleteByPK(UmTUserPowerId id) throws Exception{
			umTUserPowerDao.deleteByPK(UmTUserPower.class,id);
	}

	public void insertUmTUserPowerList(List<UmTUserPower> list) throws Exception {
		//去重处理
		UmTUserPower tempUserValue = null;
		for(int index=list.size()-1;index>=0;index--){
			tempUserValue = list.get(index);
			for(int index2=list.size()-2;index2>=0;index2--){
				if(tempUserValue.getUserCode().equals(list.get(index2).getUserCode())&&
						tempUserValue.getOperationSymbol().equals(list.get(index2).getOperationSymbol())&&
						tempUserValue.getDictionaryId().equals(list.get(index2).getDictionaryId())&&
						tempUserValue.getPowerValue().equals(list.get(index2).getPowerValue())){
					list.remove(index);
					break;
				}
			}
		}
		//保存信息
		for(UmTUserPower umTUserPower : list){
			saveUmTUserPower(umTUserPower);
		}
	}

	public void updateUmTUserPowerList(List<UmTUserPower> list)
			throws Exception {
		//去重处理
		UmTUserPower tempUserValue = null;
		for(int index=list.size()-1;index>=0;index--){
			tempUserValue = list.get(index);
			for(int index2=list.size()-2;index2>=0;index2--){
				if(tempUserValue.getUserCode().equals(list.get(index2).getUserCode())&&
						tempUserValue.getOperationSymbol().equals(list.get(index2).getOperationSymbol())&&
						tempUserValue.getDictionaryId().equals(list.get(index2).getDictionaryId())&&
						tempUserValue.getPowerValue().equals(list.get(index2).getPowerValue())){
					list.remove(index);
					break;
				}
			}
		}
		for(UmTUserPower umTUserPower : list){
			updateUmTUserPower(umTUserPower);
		}
	}

	public void deleteUmTUserPowerList(List<UmTUserPower> list)
			throws Exception {
		for(UmTUserPower umup : list){
			deleteByPK(umup.getId());
		}
	}

	public void deleteUmTUserPower(UmTUserPower umtd) throws Exception {
		deleteByPK(umtd.getId());
	}
	
	
	/**
	 * 根据用户代码查询配置的权限数据列表
	 */
	@SuppressWarnings("unchecked")
	public List<UmTUserPowerDictName> findUserPowerByUserCode(String userCode,String comId)
			throws Exception {
		if(userCode==null||"".equals(userCode.trim())){
			throw new Exception("传入的用户代码为空");
		}else {
			String querySQL = "select a.*,b.dictionaryname from um_t_userpower a,um_t_dictionary b where a.dictionaryid = b.dictionaryid " +
					"and a.usercode = ? and a.validstatus = '1' and b.validstatus = '1' and a.comid = ? and b.comid = ? order by a.userpowerid asc";
			List<UmTUserPowerDictName> list =  commonDao.findObjectPageBySql(querySQL, UmTUserPowerDictName.class, 1, Integer.MAX_VALUE, userCode,comId,comId).getResult();
			for(UmTUserPowerDictName userPower : list){
				userPower.setOperationSymbol(getCodeBySymbol(userPower.getOperationSymbol()));
			}
			return list;
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean checkUserPowerByRequestUrl(String userCode, String comCode,String requestUrl)
			throws Exception {
		if(userCode==null||"".equals(userCode)){
			throw new Exception("用户代码格式不正确");
		}else if(comCode==null||"".equals(comCode)||comCode.length()!=8){
			throw new Exception("机构代码格式不正确");
		}else if(requestUrl==null||"".equals(requestUrl)){
			throw new Exception("请求访问URL不能为空");
		}
		//截取用户访问请求
		CacheService whiteListCacheService  = CacheManager.getInstance("UM_T_WHITELIST");				//白名单缓存对象
		CacheService userRoleCacheService = CacheManager.getInstance("UM_T_USERROLE");				//用户角色缓存对象
		CacheService userTaskCacheService = CacheManager.getInstance("UM_T_USERTASK");					//用户功能缓存对象
		if(requestUrl!=null&&requestUrl.indexOf("?")!=-1){
			requestUrl = requestUrl.substring(0, requestUrl.indexOf("?"));
		}
		if(!requestUrl.endsWith(".do")||whiteListCacheService.containsKey(requestUrl)){
			return true;
		} else {
			GrantedAuthority[] authorityArray = null;
			if(userRoleCacheService.containsKey(userRoleCacheService.generateCacheKey("USERROLE",userCode))){
				//缓存中有数据
				authorityArray = (GrantedAuthority[])userRoleCacheService.getCache(userRoleCacheService.generateCacheKey("USERROLE",userCode));
			}else {
				//若缓存中没有数据,直接进行数据查询
				List<UmTRole> roleList =  umTRoleService.findByUserCode(userCode);
				if(roleList!=null&&roleList.size()>0){
					authorityArray = new GrantedAuthority[roleList.size()];					//构造角色集合
					int index = 0;
					for(UmTRole role : roleList){
						authorityArray[index++] = new GrantedAuthorityImpl(role.getRoleCode());								
					}
				}
			}
			if(authorityArray!=null&&authorityArray.length>0){
				//根据该用户角色及角色扩展的信息进行权限判断
				if(isGrantUrl(requestUrl,SessionUser.getComId4ByComCode(comCode),authorityArray)){
					return true;
				}
				//角色不存在的情况之下，根据直接配置给该用户的功能进行判定处理
				/*List<String> userCacheList = null;
				if(userTaskCacheService.containsKey(userTaskCacheService.generateCacheKey(userCode))){
					userCacheList = (List<String>)userTaskCacheService.getCache(userTaskCacheService.generateCacheKey(userCode));
				}else {
					userCacheList = umTMethodTaskService.getMethodCodeByUserCode(userCode);
				}
				//直接判断用户模型
				if(userCacheList!=null&&userCacheList.size()>0){
					if(userCacheList.contains(requestUrl)){
						return true;
					}
				}else {
					return false;
				}
				*/
				/**
				 * 姚文锋：将userTaskCacheService缓存改为map
				 */
				HashMap<String,UmTMethodTask> taskMap = null;
				try {
					taskMap = umTMethodTaskService.getMethodTaskMapByUserCode(userCode);
				} catch (Exception e) {
					logger.warn(e.getMessage());
				}
				if (taskMap != null){
					userTaskCacheService.clearCache(userCode);
					userTaskCacheService.putCache(userCode, taskMap);
					if(taskMap.containsKey(requestUrl)){
						return true;
					}
				}else{
					return false;
				}
			}else {
				return false;
			}
		}
		return false;
	}
	
	
	
	@SuppressWarnings("unchecked")
	private boolean isGrantUrl(String url,String comId,GrantedAuthority[] authorityArray) throws AccessDeniedException {
		//String roleCacheStr = null;
		HashMap<String,UmTMethodTask> roleTaskMap = null;
		CacheService roleUrlCacheService = CacheManager.getInstance("UM_T_URLROLE");
		CacheService roleDimeCacheService = CacheManager.getInstance("UM_T_ROLEDIME");
		if((roleTaskMap = (HashMap<String,UmTMethodTask>)roleUrlCacheService.getCache(url))!=null){
			Map<String,Map<String,String>> urlRoleMap = null;
			boolean [] authFlag = new boolean[authorityArray.length];					//设置认证标志位(解决一个用户多个角色,对各角色的授权信息进行或运算)
			int index  = 0;
			for(GrantedAuthority ga : authorityArray){
				if(roleTaskMap.containsKey(ga.getAuthority())){
					/**其所在角色在包含的字符串中**/
					urlRoleMap = (Map<String,Map<String,String>>)roleDimeCacheService.getCache(comId);
					if(urlRoleMap!=null){
						Map<String,String> roleDimeMap = urlRoleMap.get(url);
						if(roleDimeMap!=null){
							if("add".equals(roleDimeMap.get(ga.getAuthority()))){
								authFlag[index++]  = true;
							}else {
								authFlag[index++] = false;
							}
						}else {
							authFlag[index++] = false;
						}
					} else {
						authFlag[index++] = false;
					}
				}else {
					urlRoleMap = (Map<String,Map<String,String>>)roleDimeCacheService.getCache(comId);
					if(urlRoleMap!=null){
						Map<String,String> roleDimeMap = urlRoleMap.get(url);
						if(roleDimeMap!=null){
							if("sub".equals(roleDimeMap.get(ga.getAuthority()))){
								authFlag[index++] = false;
							}else {
								authFlag[index++] = true;
							}
						}else {
							authFlag[index++] = true;
						}
					}else {
						authFlag[index++] = true;
					}
				}
			}
			//对授权标志authFlag进行或运算,计算出该用户是否有权限登录系统
			boolean result = false;
			for(boolean value : authFlag){
				result  = result || value;						//对所有的权限验证Value进行或运算得出最后值
			}
			if(result){
				return result;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	
	/**
	 * 根据操作用户代码、操作的目标对象返回该用户可以操作的数据权限控制SQL
	 */
	@SuppressWarnings("unchecked")
	public String getCondSQLByUserTable(String operateCode, Object targetObject,String ...exceptFields)
			throws Exception {
		if(operateCode==null||"".equals(operateCode)){
			throw new Exception("传入的操作者用户代码不能为空");
		}else if(targetObject==null){
			throw new Exception("传入的操作目标对象不能为空");
		}else {
			List<String> exceptFieldList = null;
			//判断业务逻辑中是否有去除维度的处理
			if(exceptFields!=null&&exceptFields.length>0){
				if("comcode".equals(exceptFields[0])){
					//去除机构维度
					exceptFieldList = umTDictionaryDetailService.findTargetFieldByDictName("机构", operateCode);
				}else if("monopolycode".equals(exceptFields[0])){
					//去除车行维度
					exceptFieldList = umTDictionaryDetailService.findTargetFieldByDictName("车行", operateCode);
				}
			}
			//查询该用户有没有在um_t_userpower表中配置数据权限
			Page pageInfo = null;
			boolean exceptflag = false;
			if(targetObject instanceof  String){
				//单表查询
				String cacheKey  =cacheService.generateCacheKey(operateCode,String.valueOf(targetObject),"single");
//				if(!cacheService.containsKey(cacheKey)){
				if(true){
					pageInfo = commonDao.findObjectPageBySql(
							"select a.targetfield,b.operationsymbol,b.powervalue from um_t_dictionarydetail a,um_t_userpower b" +
							" where a.dictionaryid = b.dictionaryid and b.usercode = ? and lower(a.targetname) = lower(?)",
							FieldOperateValue.class, 1, Integer.MAX_VALUE, new Object[]{operateCode,String.valueOf(targetObject)});
					//获取所有的配置对象(进行组装处理)
					StringBuilder buffer = new StringBuilder("1 = 1");
					if(pageInfo==null||pageInfo.getResult()==null||pageInfo.getResult().size()<1){
							buffer = buffer.append(" and 1 = 0 ");
					}else {
						Iterator<FieldOperateValue> it  =pageInfo.getResult().iterator();
						FieldOperateValue value = null;
						String powerValue = null;
						while(it.hasNext()){
							value = it.next();
							if(exceptFields==null||exceptFields.length<1||!exceptFieldList.contains(value.getTargetField().toLowerCase())){
								//对配置的权限值进行判断
								powerValue = value.getPowerValue();
								//如果包含多个车行的
								if(powerValue!=null&&powerValue.indexOf(",")!=-1){
									String[] powerValueArray = powerValue.split(",");
									StringBuffer valueBuffer = new StringBuffer("(");
									for(String powerStr : powerValueArray){
										valueBuffer.append("'").append(powerStr).append("',");
									}
									valueBuffer.delete(valueBuffer.length()-1, valueBuffer.length());
									valueBuffer.append(")");
									powerValue = valueBuffer.toString();
								}
								
								if(value!=null){
									if(powerValue.indexOf("(")!=-1)
										buffer = buffer.append(" and ").append(value.getTargetField())
													.append(" ").append(value.getOperationSymbol()).append(" ")
													.append(powerValue);
									else
										buffer = buffer.append(" and ").append(value.getTargetField())
										.append(" ").append(value.getOperationSymbol())
										.append(" '").append(powerValue).append("'");
								}
							}else {
								exceptflag = true;
							}
						}
					}
					if(exceptflag==true&&"1 = 1".equals(buffer.toString())){
						cacheService.putCache(cacheKey, buffer.toString().concat(" and  1 = 0"));
					}else {
						cacheService.putCache(cacheKey, buffer.toString());
					}
					String retStr = String.valueOf(cacheService.getCache(cacheKey));
					return retStr;						//根据缓存KEY值获取该用户对单表的操作权限SQL
				}
			}else if(targetObject instanceof Map){
				//多表查询情况(Map[prpcmain a,prpcitem_car b])
				String cacheKey = cacheService.generateCacheKey(operateCode,String.valueOf(targetObject),"double");				//获取相对应的缓存主键
				StringBuilder buffer = new StringBuilder("1 = 1");
//				if(!cacheService.containsKey(cacheKey)){
				if(true){								//暂时不通过缓存进行处理
					//缓存中没有数据
					Map<String,String> tablesMap = (Map<String,String>)targetObject;
					Iterator<FieldOperateValue> it  = null;
					FieldOperateValue value = null;
					for(String tableName : tablesMap.keySet()){
						pageInfo = commonDao.findObjectPageBySql(
								"select a.targetfield,b.operationsymbol,b.powervalue from um_t_dictionarydetail a,um_t_userpower b" +
								" where a.dictionaryid = b.dictionaryid and b.usercode = ? and lower(a.targetname) = lower(?)",
								FieldOperateValue.class, 1, Integer.MAX_VALUE, new Object[]{operateCode,String.valueOf(tableName)});
						//获取所有的配置对象(进行组装处理)
						if(pageInfo.getResult()==null||pageInfo.getResult().size()<1){
								buffer = buffer.append(" and 1 = 0 ");
						}else {
							it  =pageInfo.getResult().iterator();
							String powerValue = null;
							while(it.hasNext()){
								value = it.next();
								powerValue = value.getPowerValue();
								if(exceptFields==null||exceptFields.length<1||!exceptFieldList.contains(value.getTargetField().toLowerCase())){
									//如果包含多个车行的
									if(powerValue!=null&&powerValue.indexOf(",")!=-1){
										String[] powerValueArray = powerValue.split(",");
										StringBuffer valueBuffer = new StringBuffer("(");
										for(String powerStr : powerValueArray){
											valueBuffer.append("'").append(powerStr).append("',");
										}
										valueBuffer.delete(valueBuffer.length()-1, valueBuffer.length());
										valueBuffer.append(")");
										powerValue = valueBuffer.toString();
									}
									if(value!=null){
										if(powerValue.indexOf("(")!=-1)
											buffer = buffer.append(" and ").append(tablesMap.get(tableName))
											.append(".").append(value.getTargetField())
											.append(" ").append(value.getOperationSymbol()).append(" ").append(powerValue);
										else
											buffer = buffer.append(" and ").append(tablesMap.get(tableName))
													.append(".").append(value.getTargetField())
													.append(" ").append(value.getOperationSymbol())
													.append(" '").append(powerValue)
													.append("'");
									}
								}else {
									exceptflag = true;
								}
							}
						}
					}
					if(exceptflag==true&&"1 = 1".equals(buffer.toString())){
						cacheService.putCache(cacheKey, buffer.toString().concat(" and  1 = 0"));
					}else {
						cacheService.putCache(cacheKey, buffer.toString());
					}
					String retStr = String.valueOf(cacheService.getCache(cacheKey));
					return retStr;						//根据缓存KEY值获取该用户对单表的操作权限SQL
				}
				
			}
		}
		return null;
	}
	
	
	public List<UmTUserPower> findUserTUserPower(UmTUserPower umTUserPower)
			throws Exception {
		String symbol = umTUserPower.getOperationSymbol();
		QueryRule rule = QueryRuleHelper.generateQueryRule(umTUserPower);
		if(symbol!=null){
			rule.addEqual("operationSymbol", UmTUserPowerServiceSpringImpl.getOperationSymbolByCode(symbol));
		}
		return umTUserPowerDao.find(rule);
	}
	
	
	
	public static String getOperationSymbolByCode(String operateSymbolCode) throws Exception {
		if(operateSymbolCode==null||"".equals(operateSymbolCode)){
			throw new Exception("传入的操作运算符码不能为空");
		}else {
			if("eq".equals(operateSymbolCode)){
				return "=";
			}else if("lt".equals(operateSymbolCode)){
				return "<";
			}else if("in".equals(operateSymbolCode)||"like".equals(operateSymbolCode)){
				return operateSymbolCode;
			}else if("gt".equals(operateSymbolCode)){
				return ">";
			}else if("le".equals(operateSymbolCode)){
				return "<=";
			}else if("ge".equals(operateSymbolCode)){
				return ">=";
			}else if("neq".equals(operateSymbolCode)){
				return "!=";
			}else {
				throw new Exception("操作符号:"+operateSymbolCode+"不支持");
			}
		}
	}
	
	
	
	
	public static String getCodeBySymbol(String operateSymbol) throws Exception {
		if(operateSymbol==null||"".equals(operateSymbol)){
			throw new Exception("传入的操作运算符号不能为空");
		}else {
			if("=".equals(operateSymbol)){
				return "eq";
			}else if("<".equals(operateSymbol)){
				return "lt";
			}else if("in".equals(operateSymbol)||"like".equals(operateSymbol)){
				return operateSymbol;
			}else if(">".equals(operateSymbol)){
				return "gt";
			}else if("<=".equals(operateSymbol)){
				return "le";
			}else if(">=".equals(operateSymbol)){
				return "ge";
			}else if("!=".equals(operateSymbol)){
				return "neq";
			}else {
				throw new Exception("操作符号:"+operateSymbol+"不支持");
			}
		}
	}
	
	/**
	 * add by liuyatao 2013年10月29日 根据comcode查询userlist展示树
	 * @param comCode
	 * @param comId
	 * @param start
	 * @param limit
	 * @return
	 */
	public String getUserListByComCode(String comCode, String comId,
			int start, int limit,String requestUrl) {
		
		try{
			if(comCode != null&&!"".equals(comCode)&&comCode.length()==8){
				//comCode格式正确
				List<PrpDcompany> companyList = null;
				List<UmTUser> userList = new ArrayList<UmTUser>();
				List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
				List<TreeNode> userArrayList = new ArrayList<TreeNode>();
				userList = umTUserDao.findUserListByComCode(comCode,comId);
				if(userList.size()>0){
					for(UmTUser user:userList){
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("nodeType", Constants.USER_NODE);
						//如果具备url的权限控制，则加入该用户是否可以访问该URL的访问标志
						// requestUrl = '/khyx/wf/wfttask/prepareTaskManagement.do';
						if(ToolsUtils.isEmpty(requestUrl) || this.checkUserPowerByRequestUrl(user.getId().getUserCode(), comCode, requestUrl)){
							map.put("allowedFlag", "1");//拥有权限
						}
						else{
							map.put("allowedFlag", "0");//没有权限
						}
						userArrayList.add(new TreeNode(user.getId().getUserCode(),user.getUserName(),"open",map));
					}
				}
				
				if(userArrayList.size()>0){
					for(TreeNode node : userArrayList){
						treeNodeList.add(node);
					}
				}
				
				JSONArray _array = JSONArray.fromObject(treeNodeList);
				return _array.toString();
			}
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
		}
		return null;
		
	}
	
}
