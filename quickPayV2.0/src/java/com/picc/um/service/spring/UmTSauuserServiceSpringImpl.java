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
import com.picc.um.dao.UmTSauuserDaoHibernate;
import com.picc.um.schema.model.UmTSauuser;
import com.picc.um.schema.model.UmTSauuserId;
import com.picc.um.service.facade.IUmTSauuserService;
import com.picc.um.service.facade.IUmTUserPowerService;


/** 
* @ClassName: UmTSauuserServiceSpringImpl 
* @Description: TODO 团队成员管理Service层实现类
* @author dijie
* @date 2013-9-3 
*  
*/
/** 
* @ClassName: UmTSauuserServiceSpringImpl 
* @Description: TODO
* @author dijie
* @date 2013-9-3 
*  
*/
@Service("umTSauuserService")
public class UmTSauuserServiceSpringImpl implements IUmTSauuserService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private UmTSauuserDaoHibernate umTSauuserDao;
	@Autowired
	private CommonDaoHibernate commonDao;
	@Autowired
	private IUmTUserPowerService  umTUserPowerService;

	/**
	 * 根据主键对象UmTSauuserId获取UmTSauuser信息
	 * @param UmTSauuserId
	 * @return UmTSauuser
	 */
	public UmTSauuser findUmTSauuserByPK(UmTSauuserId id) throws Exception{
			
			return umTSauuserDao.get(UmTSauuser.class,id);
	}
	/** 
	* added by Jay 2013-8-22
	* @Title: findUmTSauuserByUsercode 
	* @Description: 根据usercode和有效状态选取一条记录
	* @return UmTSauuser 
	* @throws 
	*/
	public UmTSauuser findUmTSauuserByUsercode(String userCode,String comId) throws Exception{
		
		String hql = "select u from UmTSauuser u where u.userCode=? and u.comId=? and u.validStatus='1'";
		List<UmTSauuser> list1 = umTSauuserDao.findByHql(hql, userCode,comId);
		if(list1==null||list1.size()==0){
			return null;
		}else{
			return list1.get(0);
		}
    }
	/** 
	* added by Jay 2013-8-23
	* @Title: findSauuserPageByTeamCode 
	* @Description: 根据团队代码获取该团队下的团队成员page
	* @return Page 
	* @throws 
	*/
	public Page findSauuserPageByTeamCode(String teamCode,int pageNo, int pageSize,String comId) {
		String hql = "select ur from UmTSauuser ur where ur.teamCode=? and ur.validStatus='1' and ur.comId=? ";
		return umTSauuserDao.findByHqlNoLimit(hql, pageNo, pageSize, teamCode,comId);
		 
	}
	
	/** 
	* added by Jay 2013-9-3
	* @Title: findSauuserByTeamCode 
	* @Description: 根据团队代码获取该团队下的团队成员List
	* @return List<UmTSauuser> 
	* @throws 
	*/
	public List<UmTSauuser> findSauuserByTeamCode(String teamCode,String comId) {
		String hql = "select ur from UmTSauuser ur where ur.teamCode=? and ur.validStatus='1' and ur.comId=? ";
		return umTSauuserDao.findByHql(hql, teamCode,comId);
		 
	}
	/** 
	* added by Jay 2013-9-2
	* @Title: getMyTeamMember 
	* @Description: 根据用户代码获取其所在团队的成员Page
	* @return Page 
	* @throws 
	*/
	public Page getMyTeamMember(String userCode,int pageNo,int pageSize,String comId) throws Exception{
		
   String hql = "select u from UmTSauuser u where u.teamCode in (select ur.teamCode from UmTSauuser ur where ur.userCode=? and ur.comId=? and ur.validStatus='1') and u.validStatus='1'";
		
			return umTSauuserDao.findByHqlNoLimit(hql, pageNo, pageSize, userCode,comId);
		
	}
	/** 
	* added by Jay 2013-10-31
	* @Title: getTeamMemberInLimit 
	* @Description: 根据comcode、teamcode、数据权限查询团队成员
	* @return Page 
	* @throws 
	*/
	public Page getTeamMemberInLimit(String userCode,String comCode,String teamCode,String fieldValue,int pageNo,int pageSize,String comId) throws Exception{
		
		  // String hql = "select a from um_t_sauuser a where exists (select 1 from um_t_saugroup b where a.teamcode = b.teamcode and b.comcode like '440301%' and teamcode=?)";
		StringBuffer querySql = new StringBuffer();
		querySql.append("select distinct usercode,username from UM_T_Sauuser a ")
		.append("where a.validStatus='1' and a.comId=? ");
		
		if (fieldValue!=null&&!fieldValue.equals("")) {
			
		   fieldValue = "%" + fieldValue + "%";
	
		   querySql.append(" and (a.userCode like '" + fieldValue + "' or a.userName like '" + fieldValue + "') ");
		}
		String comCodeCond = "";
		
		if (ToolsUtils.notEmpty(teamCode)) {
			
			
			comCodeCond = " and a.teamcode = '"+teamCode+"' "; 
			 
		}else{
			//查询自己团队的成员
			querySql.append(" and a.teamCode in (select ur.teamCode from Um_t_Sauuser ur where ur.userCode= '")
			.append(userCode).append("') ");
//			if (ToolsUtils.notEmpty(comCode)) {
//				comCodeCond = " and exists (select 1 from um_t_saugroup b where a.teamcode = b.teamcode and b.teamType not like 'N%' and b.comCode= '" +comCode+ "' )";
//				
//			}else{
//				Map<String,String> map = new HashMap<String,String>();   
//				map.put("um_t_saugroup","b");
//				 comCodeCond = umTUserPowerService.getCondSQLByUserTable(userCode, map);
//			      comCodeCond = " and exists (select 1 from um_t_saugroup b where a.teamcode = b.teamcode and b.teamType not like 'N%' and " +comCodeCond+ " )";
//			}
			
		}
		if(ToolsUtils.notEmpty(comCodeCond)){
			  querySql.append(comCodeCond);
			}
		
		
		Page page = commonDao.findObjectPageBySql(querySql.toString() ,
				UmTSauuser.class,pageNo,
				pageSize,comId);
		return page;
		
				
			}
	/**
	 * 根据umTSauuser和页码信息，获取Page对象
	 * @param umTSauuser
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTSauuser的Page对象
	 */
	public Page findByUmTSauuser(UmTSauuser umTSauuser, int pageNo, int pageSize) throws Exception{
		
		QueryRule queryRule = null;
		queryRule = QueryRuleHelper.generateQueryRule(umTSauuser);
		
		return umTSauuserDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新UmTSauuser信息
	 * @param UmTSauuser
	 */
	public void updateUmTSauuser(UmTSauuser umTSauuser) throws Exception{
			
			umTSauuserDao.update(umTSauuser);
	}

	/**
	 * 保存UmTSauuser信息
	 * @param UmTSauuser
	 */
	public void saveUmTSauuser(UmTSauuser umTSauuser) throws Exception{
			
			umTSauuserDao.save(umTSauuser);
	}

	/**
	 * 根据主键对象，删除UmTSauuser信息
	 * @param UmTSauuserId
	 */
	public void deleteByPK(UmTSauuserId id) throws Exception{
			
			umTSauuserDao.deleteByPK(UmTSauuser.class,id);
	}
}
