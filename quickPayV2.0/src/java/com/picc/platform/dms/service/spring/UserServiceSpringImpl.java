package com.picc.platform.dms.service.spring;

import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.exception.BusinessException;
import ins.framework.rule.RuleService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.picc.platform.dms.schema.model.PrpDcompany;
import com.picc.platform.dms.schema.model.PrpDuser;
import com.picc.platform.dms.service.facade.UserService;

@Service(value="userService")
public class UserServiceSpringImpl extends
		GenericDaoHibernate<PrpDuser, String> implements UserService {

	private static CacheService cacheManager = CacheManager.getInstance("User");
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** *******引入规则服务******** */
	private RuleService ruleService;
	
	public RuleService getRuleService() {
		return ruleService;
	}

	public void setRuleService(RuleService ruleService) {
		this.ruleService = ruleService;
	}
 

	// 根据员工代码查询员工信息
	public PrpDuser getUserByUserCode(String userCode) {
		String key = cacheManager.generateCacheKey("userCode", userCode);
		Object result = cacheManager.getCache(key);
		if (result != null) {
			return (PrpDuser) result;
		}
		cacheManager.putCache(key, super.get(userCode));
		return super.get(userCode);
	}

	/**
	 * 根据工号获取员工信息对象
	 * 
	 * @param userCode
	 * @return PrpDuser
	 */
	public PrpDuser getUser(String userCode) {
		
		return super.get(userCode);
	}

	/**
	 * 根据查询对象获取Page对象的员工列表
	 * 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含员工列表的Page对象
	 */
	public Page findUser(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("获取员工信息列表");
		return super.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 刪除员工信息
	 * 
	 * @param userCode
	 */
	@Transactional(propagation = Propagation.NEVER)
	public void delete(String userCode) {
		
		super.deleteByPK(userCode);
	//	System.out.println(super.get(userCode).getUserName());
	}

	/**
	 * 保存员工信息
	 * 
	 * @param prpDuser
	 */
	public void save(PrpDuser prpDuser) {
		logger.debug("保存员工信息");
		if (prpDuser.getValidStatus() == null
				|| "".equals(prpDuser.getValidStatus())) {
			prpDuser.setValidStatus("1");
		}
		super.save(prpDuser);
	}

	/**
	 * 更新员工信息
	 * 
	 * @param prpDuser
	 */
	@Override
	public void update(PrpDuser prpDuser) {
		
		if (prpDuser.getValidStatus() == null
				|| "".equals(prpDuser.getValidStatus())) {
			prpDuser.setValidStatus("1");
		}
		// this.update(prpDuser);
		super.update(prpDuser);
		System.out.println("===============update===============");
	}

	public void updateNothing() {
		System.out.println("===============updateNothing===============");
	}

	public PrpDuser findUserByUserCode(String userCode) {
		return super.get(userCode);
	}

	/**
	 * 得到当前员工的归属机构
	 * 
	 * @param userCode
	 * @return
	 */
	public String getComCodeByUserCode(String userCode) {
		if (userCode == null) {
			return "";
		}
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addLike("userCode", userCode);
		PrpDuser user = this.findUnique(queryRule);
		if (user == null) {
			throw new BusinessException("没有找到员工" + userCode + "的信息，请核实后在此查询",
					false);
		} else if (user.getPrpDcompany() == null) {
			throw new BusinessException("没有找到员工" + userCode + "的机构信息，请核实后在此查询",
					false);
		}
		return user.getPrpDcompany().getComCode();
	}

	/**
	 * 通过规则引擎更新NewUserCode
	 * 
	 * @param prpDuser
	 * @return
	 */
	@SuppressWarnings("unused")
	private PrpDuser updateNewUserCode(PrpDuser prpDuser) {
		try {
			prpDuser = (PrpDuser) ruleService.executeRules("user", prpDuser,
					"/UserRuleApp/UserRule");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return prpDuser;
	}

	/**
	 * 使当前用户变为无效
	 * 
	 * @param userCode
	 */
	public void unvalidUser(String userCode) {
		PrpDuser user = this.get(userCode);
		user.setValidStatus("0");
		this.save(user);
	}

	/**
	 * 获取所有用户
	 */
	public Page findAllUser(String query, String codeType) {
		StringBuilder hql = new StringBuilder();
		hql
				.append("select prpDuser.userCode,prpDuser.userName from PrpDuser prpDuser where ");
		hql.append(" prpDuser.userCode like '");
		hql.append(query);
		hql.append("'");
		Page page = null;
		try {
			page = this.findByHql(hql.toString(), 1, 30);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return page;
	}
	
 
	
//	public List listUserCodeSelect(CodeCondition cond) {
//		Long taskInstanceId = Long.parseLong(cond.getExtraCond());
//		StringBuilder hql = new StringBuilder();
//		List transitionsList = bpmService.findTransitions(taskInstanceId);
//		List<String> taskNameList = new ArrayList<String>();
//		String taskName = "";
//		for(int i = 0 ; i < transitionsList.size() ; i++){
//			taskName = ((Transition)(transitionsList.get(i))).getTo().getName();
//			if(!taskNameList.contains(taskName)){
//				taskNameList.add(taskName);
//			}
//		}
//		hql.append("select distinct saaUserGrade.userCode ").append(" from SaaUserGrade saaUserGrade ");
//		hql.append("where saaUserGrade.saaGrade.id in (");
//		hql.append("select id from SaaGrade saaGrade where ");
//		String preName = "";
//		for(int i = 0 ; i < taskNameList.size() ; i++){
//			hql.append("saaGrade.gradeCName like '%");
//			preName = taskNameList.get(i).substring(0, 2);
//			hql.append(preName).append("%'");
//			if(i < taskNameList.size()-1){
//				hql.append(" or ");
//			}
//		}
//		hql.append(")");
//		List<String> userCodeList = new ArrayList<String>();
//		userCodeList = super.findByHql(hql.toString(), null);
//		hql.setLength(0);
//		hql.append("select prpDuser.userCode,prpDuser.userName from PrpDuser prpDuser where ");
//		hql.append("prpDuser.userCode in ('");
//		for(int i = 0 ; i < userCodeList.size() ;i++){
//			hql.append(userCodeList.get(i)).append("'");
//			if(i < userCodeList.size()-1){
//				hql.append(",'");
//			}
//		}
//		hql.append(")");
//		return this.findByHql(hql.toString(), null);
//	}
//	
	public List listUserCodeSelect(List<String> transitionList) {
		StringBuilder hql = new StringBuilder();
		List<String> userCodeList = new ArrayList<String>();
		hql.append("select distinct saaUserGrade.userCode ").append(" from SaaUserGrade saaUserGrade ");
		hql.append("where saaUserGrade.saaGrade.id in (");
		hql.append("select id from SaaGrade saaGrade where ");
		String preName = "";
		for(int i = 0 ; i < transitionList.size() ; i++){
			hql.append("saaGrade.gradeCName like '%");
			preName = transitionList.get(i).substring(0, 2);
			hql.append(preName).append("%'");
			if(i < transitionList.size()-1){
				hql.append(" or ");
			}
		}
		hql.append(")");
		userCodeList = super.findByHql(hql.toString());
		return userCodeList;
	}
	
	public PrpDuser findByUserCode(String userCode) {
		PrpDuser prpDuser = super.get(userCode);
		return prpDuser;
	}
	
	public List<PrpDuser> findByUserCodeByQueryRule(String userCode) {
		QueryRule queryRule = QueryRule.getInstance().addEqual("userCode", userCode);
		return super.find(queryRule);
	}

	
	
	public List<PrpDuser> findByPasswordSort(String password) {
		QueryRule queryRule = QueryRule.getInstance().addEqual("password", password).addDescOrder("userCode");
		return super.find(queryRule);
	}

	public List<PrpDuser> findByHqlByUserCode(String userCode) {
		List<PrpDuser> list = super.findByHql("from PrpDuser _user where _user.userCode=?", userCode);
		return list;
	}
	
	public Page findByPage(String password,int pageNo, int pageSize) {
		QueryRule queryRule = QueryRule.getInstance().addEqual("password", password);
		return super.find(queryRule,pageNo,pageSize);
	}

	public List<PrpDuser> findByHqlByComCode(String comCode) {
		List<PrpDuser> list = super.findByHql("from PrpDuser _user where _user.prpDcompany.comCode=?", comCode);
		return list;
	}
	
	public List<String> findByHqlByValidStatus(String validStatus) {
		List<String> list = super.findByHql("select user.userName from PrpDuser user where user.validStatus=?", validStatus);
		return list;
	}

	public List<Object[]> findByHqlByArticleCode(String articleCode) {
		List<Object[]> list = super.findByHql("select user.userName,user.password from PrpDuser user where user.articleCode=?", articleCode);
		return list;
	}
	
	public List<Object[]> findByHqlByAllStatus(String userStatus, String comStatus) {
		List<Object[]> list = super.findByHql("select user.userName,com.comCName from PrpDuser user,PrpDcompany com where user.validStatus=? and com.validStatus=?", userStatus,comStatus);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<PrpDuser> findBySql(String password) {
		List<PrpDuser> list = super.getSession().createSQLQuery("select * from prpduser where password=?").addEntity(PrpDuser.class).setParameter(0,"pcpcncdi").list();
		return list;
	}

	public void insertBySql() {
		super.getSession().createSQLQuery("insert into prpdcompany(comcode,comcname,newcomcode,validstatus) values('11000001','分公司','11000001','1')").executeUpdate();
	}

	public void updateBySql() {
		super.getSession().createSQLQuery("update prpdcompany set comcode='11000002' where comcode='11000001'").executeUpdate();	
	}

	public void delBySql() {
		super.getSession().createSQLQuery("delete from  prpdcompany where comcode='11000002'").executeUpdate();	
	}

	public void save() {
		PrpDuser user = new PrpDuser();
		PrpDcompany prpDcompany = (PrpDcompany)(super.findByHql("from PrpDcompany com where com.comCode='00000000'").get(0));
		user.setUserCode("0000000001");
		user.setPrpDcompany(prpDcompany);
		user.setNewUserCode("0000000001");
		user.setValidStatus("1");
		super.save(user);
	}

	public void del() {
		PrpDuser user = super.get("0000000001");
		super.delete(user);
	}

	public void update() {
		PrpDuser user = super.get("0000000001");
		user.setUserName("其他3");
		super.update(user);
		
	}

	public List<PrpDuser> findByHqlSort() {
		List<PrpDuser> list = super.findByHql("from PrpDuser _user order by _user.userCode");
		return list;
		
	}

	public List<PrpDuser> findByHqlTop5() {
		List<PrpDuser> list = super.getSession().createQuery("from PrpDuser _user order by _user.userCode").setMaxResults(5).list();
		return list;
	}
	
}
