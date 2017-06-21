/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.QueryRuleHelper;
import com.picc.common.dao.CommonDaoHibernate;
import com.picc.um.dao.UmTUserRoleDaoHibernate;
import com.picc.um.schema.model.UmTRole;
import com.picc.um.schema.model.UmTUserRole;
import com.picc.um.schema.model.UmTUserRoleId;
import com.picc.um.service.facade.IUmTRolePowerService;
import com.picc.um.service.facade.IUmTUserRoleService;

/**
 * 用户角色关联服务接口实现类
 * @author 姜卫洋
 */
@Service("umTUserRoleService")
public class UmTUserRoleServiceSpringImpl implements IUmTUserRoleService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private UmTUserRoleDaoHibernate umTUserRoleDao;
	
	@Autowired
	private CommonDaoHibernate commonDao;
	
	@Autowired
	private IUmTRolePowerService umTRolePowerService;

	/**
	 * 根据主键对象UmTUserRoleId获取UmTUserRole信息
	 * @param UmTUserRoleId
	 * @return UmTUserRole
	 */
	public UmTUserRole findUmTUserRoleByPK(UmTUserRoleId id) throws Exception{
			
			return umTUserRoleDao.get(UmTUserRole.class,id);
	}

	/**
	 * 根据umTUserRole和页码信息，获取Page对象
	 * @param umTUserRole
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTUserRole的Page对象
	 */
	public Page findByUmTUserRole(UmTUserRole umTUserRole, int pageNo, int pageSize) throws Exception{
		
//		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		QueryRule queryRule = QueryRuleHelper.generateQueryRule(umTUserRole);
	
		//根据umTUserRole生成queryRule
		
		return umTUserRoleDao.find(queryRule, pageNo, pageSize);
	}
	
	public Page findByUmTUserRole(UmTUserRole umTUserRole, int pageNo, int pageSize,String serverCode) throws Exception{
		
//		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		QueryRule queryRule = QueryRuleHelper.generateQueryRule(umTUserRole);
	
		//根据umTUserRole生成queryRule
		
		return umTUserRoleDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新UmTUserRole信息
	 * @param UmTUserRole
	 */
	public void updateUmTUserRole(UmTUserRole umTUserRole,String comId) throws Exception{
			umTUserRoleDao.update(umTUserRole);
			if("1".equals(umTUserRole.getValidStatus())){
				//角色赋予有效状态
				umTRolePowerService.addUserPowerByRolePower(umTUserRole,comId);
			}else {
				//将角色附加的数据权限进行剥离
				umTRolePowerService.removeUserPowerByRolePower(umTUserRole,comId);
			}
	}

	/**
	 * 保存UmTUserRole信息
	 * @param UmTUserRole
	 */
	public void saveUmTUserRole(UmTUserRole umTUserRole,String comId) throws Exception{
			
			umTUserRoleDao.save(umTUserRole);
			
			/**给用户添加角色时,默认关联上角色所附加的数据权限**/
			umTRolePowerService.addUserPowerByRolePower(umTUserRole,comId);
			
	}

	/**
	 * 根据主键对象，删除UmTUserRole信息
	 * @param UmTUserRoleId
	 */
	public void deleteByPK(UmTUserRoleId id) throws Exception{
			
			umTUserRoleDao.deleteByPK(UmTUserRole.class,id);
	}
	
	
	/**
	 * 根据操作用户当前归属机构,获取其机构下的岗位列表
	 * @param comCode							机构代码
	 * @return
	 * @throws Exception
	 * 2013-8-9下午2:45:07
	 * jiangweiyang
	 */
	public Page getRoleListByComCode(String comCode) throws Exception {
		String executeSQL =  "select a.roleid,a.rolecname from um_t_role a where "+
				"exists (select 1 from um_t_rolecom b where a.roleid = b.roleid "+
				"and b.comcode = ? and validstatus = '1') and validstatus = '1'";
		return commonDao.findObjectPageBySql(executeSQL, UmTRole.class, 1, Integer.MAX_VALUE, comCode);
	}
	/** overload by liuyatao 2014年8月1日**/
	public Page getRoleListByComCode(String comCode,String serverCode) throws Exception {
		String executeSQL =  "select a.roleid,a.rolecname from um_t_role a where "+
				"exists (select 1 from um_t_rolecom b where a.roleid = b.roleid and (a.servercode='PUB' or a.servercode= ? )"+
				"and b.comcode = ? and validstatus = '1') and validstatus = '1'";
		List array = new ArrayList();
		array.add(serverCode);
		array.add(comCode);
		return commonDao.findObjectPageBySql(executeSQL, UmTRole.class, 1, Integer.MAX_VALUE, array.toArray());
	}
	
	public UmTUserRole findUserRoleByUserCodeAndRoleId(String userCode,
			String roleId) throws Exception {
		QueryRule rule = QueryRule.getInstance();
		rule.addEqual("userCode", userCode);
		rule.addEqual("roleId", roleId);
		return umTUserRoleDao.findUnique(rule);
	}
	
	public List<UmTUserRole> findUserRoleByUserCode(String userCode) throws Exception{
		QueryRule rule = QueryRule.getInstance();
		rule.addEqual("userCode", userCode);
		return umTUserRoleDao.find(rule);
	}
	
	
	public void saveUmTUserRoleByOperateType(UmTUserRole umTUserRole,int operateType,String comId) throws Exception{
		try{
			UmTUserRole userRole = findUserRoleByUserCodeAndRoleId(umTUserRole.getUserCode(),umTUserRole.getRoleId());				//从数据库中获取对象
			if(userRole==null){
				String urid = commonDao.generateID("UMTR", "UM_SEQ_USERROLE", 26);
				UmTUserRoleId id = new UmTUserRoleId(urid);
				umTUserRole.setId(id);
				saveUmTUserRole(umTUserRole,comId);
			}else {
				if(operateType==1){
					//新增
					userRole.setValidStatus("1");
				}else if(operateType==2){
					//取消
					userRole.setValidStatus("0");
				}
				userRole.setUpdaterCode(umTUserRole.getUpdaterCode());
				userRole.setOperateTimeForHis(new Date());
				updateUmTUserRole(userRole,comId);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	
	/**
	 * 给用户添加角色
	 */
	public void addRoleToUser(String comId,String userCode, String[] roleArray,
			String creatorCode, int operateType) throws Exception {
		UmTUserRole userRole = null;
		for(String roleId : roleArray){
			userRole = new UmTUserRole();
			userRole.setComId(comId);							//设置ComID
			userRole.setUserCode(userCode);
			userRole.setRoleId(roleId);
			if(operateType==1){
				userRole.setValidStatus("1");
				userRole.setCreatorCode(creatorCode);
				userRole.setUpdaterCode(creatorCode);
				userRole.setInsertTimeForHis(new Date());
			}else if(operateType==2){
				userRole.setUpdaterCode(creatorCode);
			}
			
			saveUmTUserRoleByOperateType(userRole,operateType,comId);
		}
	}
	
	
}
