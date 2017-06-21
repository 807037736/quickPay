package com.picc.platform.dms.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import com.picc.platform.dms.schema.model.PrpDuser;

public interface UserService {
	public PrpDuser findUserByUserCode(String userCode);

	/**
	 * 根据工号获取员工信息对象
	 * @param userCode
	 * @return PrpDuser
	 */
	public PrpDuser getUser(String userCode);

	/**
	 * 根据查询对象获取Page对象的员工列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含员工列表的Page对象
	 */
	public Page findUser(QueryRule queryRule, int pageNo, int pageSize);

	/**
	 * 根据员工代码查询员工信息
	 * @param userCode
	 * @return PrpDuser
	 */
	public PrpDuser getUserByUserCode(String userCode);

	/**
	 * 更新员工信息
	 * @param prpDuser
	 */
	public void update(PrpDuser prpDuser);

	public void updateNothing();
	/**
	 * 保存员工信息
	 * @param prpDuser
	 */
	public void save(PrpDuser prpDuser);

	/**
	 * 刪除员工信息
	 * @param userCode
	 */
	public void delete(String userCode);

	/**
	 * 使当前用户变为无效
	 * @param userCode
	 */
	public void unvalidUser(String userCode);

	/**
	 * 得到当前员工的归属机构
	 * @param userCode
	 * @return
	 */
	public String getComCodeByUserCode(String userCode);

	/**
	 * 获取所有员工
	 */
	public Page findAllUser(String query, String codeType);
	
	 
	
	public List listUserCodeSelect(List<String> transitionList);
	
	public PrpDuser findByUserCode(String userCode);
	public List<PrpDuser> findByUserCodeByQueryRule(String userCode);
	public List<PrpDuser> findByPasswordSort(String password);
	public Page findByPage(String password,int pageNo, int pageSize);
	public List<PrpDuser> findByHqlByUserCode(String userCode);
	public List<PrpDuser> findByHqlByComCode(String comCode);
	public List<Object[]> findByHqlByAllStatus(String userStatus,String comStatus);
	public List<String> findByHqlByValidStatus(String validStatus);
	public List<Object[]> findByHqlByArticleCode(String articleCode);
	public void save();
	public void del();
	public void update();
	public List<PrpDuser> findByHqlSort();
	public List<PrpDuser> findByHqlTop5();
	public List findBySql(String comCode);
	public void insertBySql();
	public void updateBySql();
	public void delBySql();
}
