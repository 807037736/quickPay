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
import com.picc.common.utils.ToolsUtils;
import com.picc.um.dao.UmTSaugroupDaoHibernate;
import com.picc.um.schema.model.UmTSaugroup;
import com.picc.um.schema.model.UmTSaugroupId;
import com.picc.um.service.facade.IUmTSaugroupService;
import com.picc.um.service.facade.IUmTUserPowerService;


/** 
* @ClassName: UmTSaugroupServiceSpringImpl 
* @Description: TODO 团队信息管理Service层实现类
* @author dijie
* @date 2013-12-3 
*  
*/
@Service("umTSaugroupService")
public class UmTSaugroupServiceSpringImpl implements IUmTSaugroupService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommonDaoHibernate commonDao;
	@Autowired
    private UmTSaugroupDaoHibernate umTSaugroupDao;
	@Autowired
	private IUmTUserPowerService  umTUserPowerService;

	public IUmTUserPowerService getUmTUserPowerService() {
		return umTUserPowerService;
	}

	public void setUmTUserPowerService(IUmTUserPowerService umTUserPowerService) {
		this.umTUserPowerService = umTUserPowerService;
	}
	/**
	 * 根据主键对象UmTSaugroupId获取UmTSaugroup信息
	 * @param UmTSaugroupId
	 * @return UmTSaugroup
	 */
	public UmTSaugroup findUmTSaugroupByPK(UmTSaugroupId id) throws Exception{
			
			return umTSaugroupDao.get(UmTSaugroup.class,id);
	}
	/** 
	* added by Jay 2013-8-22
	* @Title: findUmTSaugroupByTeamCode 
	* @Description: 根据teamcode查找团队信息
	* @return UmTSaugroup 
	* @throws 
	*/
	public UmTSaugroup findUmTSaugroupByTeamCode(String teamCode,String comId) throws Exception{
		
		String hql = "select u from UmTSaugroup u where u.teamCode=? and u.comId=? and u.validStatus='1' and u.teamType not like 'N%'";
		return (UmTSaugroup)umTSaugroupDao.findByHql(hql, teamCode,comId).get(0);
}
	
	/** 
	* added by Jay 2013-9-2
	* @Title: isManager 
	* @Description: 判断是否为团队经理
	* @return Boolean 
	* @throws 
	*/
	public Boolean isManager(String userCode,String comId) throws Exception{
		
		String hql = "select u from UmTSaugroup u where u.managerCode=? and u.validStatus='1' and u.comId=? ";
		
		List<UmTSaugroup> list1 = umTSaugroupDao.findByHql(hql, userCode,comId);
		if(list1!=null&&list1.size()>0){
			return true;
		}else{
			return false;
		} 
	}
	/** 
	* added by Jay 2013-9-10
	* @Title: findAllTeam 
	* @Description: 根据机构代码查询该机构极其所有下属机构的团队信息
	* @return Page 
	* @throws 
	*/
	public Page findAllTeam(String userCode,String comCode,String fieldValue,int pageNo,int pageSize,String comId) throws Exception{
		StringBuffer querySql = new StringBuffer();
		querySql.append("select distinct teamcode,teamname from UM_T_Saugroup ")
		.append("where validStatus='1' and teamType not like 'N%' and comId=? ");
		
		if (fieldValue!=null&&!fieldValue.equals("")) {
			
		   fieldValue = "%" + fieldValue + "%";
	
		   querySql.append(" and (teamCode like '" + fieldValue + "' or teamName like '" + fieldValue + "') ");
		}
		String comCodeCond = "";
		//String[] singleCity={"4403","3302","3502","3702","2102","3301","4401"};
			    //省市以上拼like条件
		if (ToolsUtils.notEmpty(comCode)) {
			comCodeCond = " and comcode = '"+comCode+"' "; 
			 
		}else{
			
			comCodeCond = umTUserPowerService.getCondSQLByUserTable(userCode, "UM_T_Saugroup");
			comCodeCond = " and "+comCodeCond;
			
		}
		if(ToolsUtils.notEmpty(comCodeCond)){
			  querySql.append(comCodeCond);
			}
		
		
		Page page = commonDao.findObjectPageBySql(querySql.toString() ,
				UmTSaugroup.class,pageNo,
				pageSize,comId);
		return page;
	}
	/**
	 * 根据umTSaugroup和页码信息，获取Page对象
	 * @param umTSaugroup
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTSaugroup的Page对象
	 */
	public Page findByUmTSaugroup(UmTSaugroup umTSaugroup, int pageNo, int pageSize) throws Exception{
		
		QueryRule queryRule = null;
		queryRule = QueryRuleHelper.generateQueryRule(umTSaugroup);
		//根据umTSaugroup生成queryRule
		
		return umTSaugroupDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新UmTSaugroup信息
	 * @param UmTSaugroup
	 */
	public void updateUmTSaugroup(UmTSaugroup umTSaugroup) throws Exception{
			
			umTSaugroupDao.update(umTSaugroup);
	}

	/**
	 * 保存UmTSaugroup信息
	 * @param UmTSaugroup
	 */
	public void saveUmTSaugroup(UmTSaugroup umTSaugroup) throws Exception{
			
			umTSaugroupDao.save(umTSaugroup);
	}

	/**
	 * 根据主键对象，删除UmTSaugroup信息
	 * @param UmTSaugroupId
	 */
	public void deleteByPK(UmTSaugroupId id) throws Exception{
			
			umTSaugroupDao.deleteByPK(UmTSaugroup.class,id);
	}
}
