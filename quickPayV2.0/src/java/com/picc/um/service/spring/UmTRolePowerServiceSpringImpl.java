/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.QueryRuleHelper;
import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.utils.AppConfig;
import com.picc.um.dao.UmTRolePowerDaoHibernate;
import com.picc.um.schema.model.UmTGroup;
import com.picc.um.schema.model.UmTRolePower;
import com.picc.um.schema.model.UmTRolePowerId;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.model.UmTUserPower;
import com.picc.um.schema.model.UmTUserRole;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.service.facade.IUmTRolePowerService;
import com.picc.um.service.facade.IUmTUserGroupService;
import com.picc.um.service.facade.IUmTUserPowerService;
import com.picc.um.service.facade.IUmTUserService;

/**
 * 角色关联数据权限服务实现类
 * @author 姜卫洋
 */
@Service("umTRolePowerService")
public class UmTRolePowerServiceSpringImpl implements IUmTRolePowerService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UmTRolePowerDaoHibernate umTRolePowerDao;
	
	@Autowired
	private CommonDaoHibernate commonDao;
	
	@Autowired
	private IUmTUserPowerService umTUserPowerService;
	
	@Autowired
	private IUmTUserService umTUserService;
	
	@Autowired
	private IUmTUserGroupService umTUserGroupService;

	/**
	 * 根据主键对象UmTRolePowerId获取UmTRolePower信息
	 * 
	 * @param UmTRolePowerId
	 * @return UmTRolePower
	 */
	public UmTRolePower findUmTRolePowerByPK(UmTRolePowerId id)
			throws Exception {
		return umTRolePowerDao.get(UmTRolePower.class, id);
	}

	/**
	 * 根据umTRolePower和页码信息，获取Page对象
	 * 
	 * @param umTRolePower
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTRolePower的Page对象
	 */
	public Page findByUmTRolePower(UmTRolePower umTRolePower, int pageNo,
			int pageSize) throws Exception {
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance

		// 根据umTRolePower生成queryRule

		return umTRolePowerDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新UmTRolePower信息
	 * 
	 * @param UmTRolePower
	 */
	public void updateUmTRolePower(UmTRolePower umTRolePower) throws Exception {
		if(!isExists(umTRolePower.getDictionaryId(),umTRolePower.getPowerValue(),umTRolePower.getRoleId(),umTRolePower.getComId()))
			umTRolePowerDao.update(umTRolePower);
		else
			deleteByPK(umTRolePower.getId());
	}

	/**
	 * 保存UmTRolePower信息
	 * 
	 * @param UmTRolePower
	 */
	public void saveUmTRolePower(UmTRolePower umTRolePower) throws Exception {
		if(umTRolePower.getDictionaryId()==null||"".equals(umTRolePower.getDictionaryId())||umTRolePower.getPowerValue()==null||"".equals(umTRolePower.getPowerValue())){
			throw new Exception("传入的UmTRolePower缺少必须项,无法保存");
		}else {
			String rpId = commonDao.generateID("UMRP", "UM_SEQ_ROLEPOWER", 5);
			umTRolePower.setId(new UmTRolePowerId(rpId));
			if(!isExists(umTRolePower.getDictionaryId(),umTRolePower.getPowerValue(),umTRolePower.getRoleId(),umTRolePower.getComId()))
				umTRolePowerDao.save(umTRolePower);
		}
	}

	/**
	 * 根据主键对象，删除UmTRolePower信息
	 * 
	 * @param UmTRolePowerId
	 */
	public void deleteByPK(UmTRolePowerId id) throws Exception {
		umTRolePowerDao.deleteByPK(UmTRolePower.class, id);
	}
	
	public UmTRolePower findUmTRolePower(UmTRolePower rolePower) {
		QueryRule rule = QueryRuleHelper.generateQueryRule(rolePower);
		return umTRolePowerDao.findUnique(rule);
	}
	
	public List<UmTRolePower> findUmTRolePowerList(UmTRolePower rolePower){
		QueryRule rule = QueryRuleHelper.generateQueryRule(rolePower);
		return umTRolePowerDao.find(rule);
	}
	
	/**
	 * 联动添加用户角色对应的数据权限
	 */
	public void addUserPowerByRolePower(UmTUserRole umTUserRole,String comId) throws Exception {
		if(umTUserRole!=null){
			String userCode = umTUserRole.getUserCode();						//获取用户代码
			String roleId = umTUserRole.getRoleId();									//角色ID
			UmTRolePower rolePower = new UmTRolePower();
			rolePower.setRoleId(roleId);
			rolePower.setComId(comId);																					//查询角色附加的数据权限时加入ComID的查询限制条件
			List<UmTRolePower> rolePowerList = findUmTRolePowerList(rolePower);
			if(rolePowerList!=null&&rolePowerList.size()>0){
				//该角色配置相应的数据权限
				/**构建用户权限对象**/
				UmTUserPower umTUserPower  = null;
				String powerRetValue = null;
				for(UmTRolePower rolePowerValue : rolePowerList){
					umTUserPower  = new UmTUserPower();
					umTUserPower.setUserCode(userCode);															//设置用户代码
					umTUserPower.setDictionaryId(rolePowerValue.getDictionaryId());				//设置权限字典代码
					umTUserPower.setOperatorType("PERSON");
					powerRetValue = getPowerValueFromRolePower(userCode,rolePowerValue);
					if(powerRetValue!=null){
						if(AppConfig.get("um.USERCODE").equals(rolePowerValue.getPowerValue())){
							umTUserPower.setOperationSymbol("eq");
						}else if(AppConfig.get("um.ZHIGS").equals(rolePowerValue.getPowerValue())||
								AppConfig.get("um.FENGS").equals(rolePowerValue.getPowerValue())||
								AppConfig.get("um.FENGSZHIGS").equals(rolePowerValue.getPowerValue())){
							umTUserPower.setOperationSymbol("like");
						}else if(AppConfig.get("um.MONOPOLY").equals(rolePowerValue.getPowerValue())){
							if(powerRetValue.indexOf(",")==-1){
								//单车行
								umTUserPower.setOperationSymbol("eq");
							}else {
								umTUserPower.setOperationSymbol("in");
							}
						}
						umTUserPower.setRolePowerId(rolePowerValue.getId().getRpId());
						List<UmTUserPower> userPowerList = umTUserPowerService.findUserTUserPower(umTUserPower);
						if(userPowerList==null||userPowerList.size()<1){
							umTUserPower.setComId(rolePowerValue.getComId());
							umTUserPower.setPowerValue(powerRetValue);
							umTUserPower.setValidStatus("1");
							umTUserPowerService.saveUmTUserPower(umTUserPower);
						}else {
							umTUserPower.setValidStatus("1");
							umTUserPowerService.updateUmTUserPower(umTUserPower);
						}
					}
				}
			}
		}
	}
	
	
	private String getPowerValueFromRolePower(String userCode,UmTRolePower rolePower) throws Exception {
		if(AppConfig.get("um.USERCODE").equals(rolePower.getPowerValue())){
			//基于业务员的查询口径
			return userCode;
		}else if(AppConfig.get("um.ZHIGS").equals(rolePower.getPowerValue())){
			//基于支公司管理员的查询口径(获取该用户comcode前6位外加%)
			String comCode = null;
			try {
				UmTUser umTUser = umTUserService.findUmTUserByUserCode(userCode);
				if(umTUser!=null){
					comCode = umTUser.getComCode();
					return comCode.substring(0,6).concat("%");
				}else {
					return null;
				}
			} catch (Exception e) {
				throw e;
			}
		}else if(AppConfig.get("um.FENGS").equals(rolePower.getPowerValue())){
			String comCode = null;
			try {
				UmTUser umTUser = umTUserService.findUmTUserByUserCode(userCode);
				if(umTUser!=null){
					comCode = umTUser.getComCode();
					return comCode.substring(0,4).concat("%");
				}else {
					return null;
				}
			} catch (Exception e) {
				throw e;
			}
		}else if(AppConfig.get("um.MONOPOLY").equals(rolePower.getPowerValue())){
			String comId  = SessionUser.getComIdByComCode(umTUserService.findUmTUserByUserCode(userCode).getComCode());
			List<UmTGroup> groupList = umTUserGroupService.find4sCodeByUserCode(userCode,comId);
			if(groupList==null||groupList.size()<1){
				return null;
			}else if(groupList.size()>1){
				StringBuffer buffer = new StringBuffer();
				for(UmTGroup group : groupList){
					buffer = buffer.append(group.getGroupCode()).append(",");
				}
				buffer = buffer.delete(buffer.length()-1, buffer.length());
				return buffer.toString();
			}else {
				return groupList.get(0).getGroupCode();
			}
		}else if(AppConfig.get("um.FENGSZHIGS").equals(rolePower.getPowerValue())){
			//根据用户所在机构代码的类型
			String comCode = null;
			try {
				UmTUser umTUser = umTUserService.findUmTUserByUserCode(userCode);
				if(umTUser!=null){
					comCode = umTUser.getComCode();					//查询用户所在的机构代码
					//根据该机构代码查询上级的机构
//					upperComCode =  ((PrpDcompany)PageService.getCompanys(SystemCode.DMS, comCode, "", "", "1", "UPPER", 1, Integer.MAX_VALUE).getData().get(0)).getComCode();
					if(!"00".equals(comCode.substring(4, 6))){
						//支公司组织
						return comCode.substring(0, 6).concat("%");
					}else if(!"00".equals(comCode.substring(2, 4))){
						return comCode.substring(0, 4).concat("%");
					}
				}else {
					return null;
				}
			} catch (Exception e) {
				throw e;
			}
			
			
			
			
			
		}
		
		
		return null;
	}
	
	
	public void removeUserPowerByRolePower(UmTUserRole umTUserRole,String comId)
			throws Exception {
		if(umTUserRole!=null){
			String userCode = umTUserRole.getUserCode();						//获取用户代码
			String roleId = umTUserRole.getRoleId();									//角色ID
			UmTRolePower rolePower = new UmTRolePower();
			rolePower.setRoleId(roleId);
			rolePower.setComId(comId);											//查询条件中加入ComID的限制
			List<UmTRolePower> rolePowerList = findUmTRolePowerList(rolePower);					//根据角色ID查询 所以关联在角色上的数据字典ID
			if(rolePowerList!=null&&rolePowerList.size()>0){
				UmTUserPower umTUserPower  = null;
				List<UmTUserPower>  userPowerList = null;
				for(UmTRolePower rolePowerValue : rolePowerList){
					umTUserPower =  new UmTUserPower();
					umTUserPower.setUserCode(userCode);																	//设置该用
					umTUserPower.setRolePowerId(rolePowerValue.getId().getRpId());
					//删除角色对应的数据权限
					userPowerList = umTUserPowerService.findUserTUserPower(umTUserPower);
					if(userPowerList!=null&&userPowerList.size()>0){
						for(UmTUserPower userPower : userPowerList){
							umTUserPowerService.deleteUmTUserPower(userPower);
						}
					}
				}
			}
		}
	}
	
	
	public List<UmTRolePower> findUmTRolePowerByRoleID(String roleID,String comId)
			throws Exception {
		UmTRolePower rolePower = new UmTRolePower();
		rolePower.setRoleId(roleID);
		rolePower.setValidStatus("1");
		rolePower.setComId(comId);
		QueryRule queryRule = QueryRuleHelper.generateQueryRule(rolePower);
		return umTRolePowerDao.find(queryRule);
	}
	
	
	public UmTRolePower findUmTRolePowerByRoleAndDict(String roleId,
			String dictioinaryId) throws Exception {
		UmTRolePower rolePower = new UmTRolePower();
		rolePower.setRoleId(roleId);
		rolePower.setDictionaryId(dictioinaryId);
		QueryRule queryRule = QueryRuleHelper.generateQueryRule(rolePower);
		return this.umTRolePowerDao.findUnique(queryRule);
	}
	
	
	private boolean isExists(String dictionaryId,String powerValue,String roleId,String comId) throws Exception {
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("dictionaryId", dictionaryId);
			queryRule.addEqual("powerValue", powerValue);
			queryRule.addEqual("roleId", roleId);
			queryRule.addEqual("comId", comId);
			if(umTRolePowerDao.find(queryRule).size()>0){
				return true;
			}else {
				return false;
			}
		}catch(Exception ex){
			throw ex;
		}
	}
	
	public void addUmTRolePowerList(List<UmTRolePower> umTRolePowerList)
			throws Exception {
		//去重处理
		UmTRolePower tmpRolePower = null;
		for(int index=umTRolePowerList.size()-1;index>=0;index--){
			tmpRolePower = umTRolePowerList.get(index);
			for(int index2=umTRolePowerList.size()-2;index2>=0;index2--){
				if(tmpRolePower.getRoleId().equals(umTRolePowerList.get(index2).getRoleId())&&
						tmpRolePower.getDictionaryId().equals(umTRolePowerList.get(index2).getDictionaryId())&&
						tmpRolePower.getPowerValue().equals(umTRolePowerList.get(index2).getPowerValue())){
					umTRolePowerList.remove(index);
					break;
				}
			}
		}
		for(UmTRolePower umTRolePower : umTRolePowerList){
			saveUmTRolePower(umTRolePower);
		}
	}
	
	
	public void updateUmTRolePowerList(List<UmTRolePower> umTRolePowerList)
			throws Exception {
		//去重处理
		UmTRolePower tmpRolePower = null;
		for(int index=umTRolePowerList.size()-1;index>=0;index--){
			tmpRolePower = umTRolePowerList.get(index);
			for(int index2=umTRolePowerList.size()-2;index2>=0;index2--){
				if(tmpRolePower.getRoleId().equals(umTRolePowerList.get(index2).getRoleId())&&
						tmpRolePower.getDictionaryId().equals(umTRolePowerList.get(index2).getDictionaryId())&&
						tmpRolePower.getPowerValue().equals(umTRolePowerList.get(index2).getPowerValue())){
					umTRolePowerList.remove(index);
					break;
				}
			}
		}
		for(UmTRolePower umTRolePower : umTRolePowerList){
			updateUmTRolePower(umTRolePower);
		}
	}
	
	
	public void deleteUmTRolePowerList(List<UmTRolePower> umTRolePowerList)
			throws Exception {
		for(UmTRolePower umTRolePower : umTRolePowerList){
			deleteByPK(umTRolePower.getId());
		}
	}
	
	
	
}
