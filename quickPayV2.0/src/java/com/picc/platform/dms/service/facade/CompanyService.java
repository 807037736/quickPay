package com.picc.platform.dms.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import com.picc.platform.dms.schema.model.PrpDcompany;

public interface CompanyService {
	/**
	 * 根据工号获取机构信息对象
	 * 
	 * @param comCode
	 * @return PrpDcompany
	 */
	public PrpDcompany getCompany(String comCode);

	/**
	 * 判断upperComCode 是否是comCode的父节点
	 * 
	 * @param comCode
	 * @param upperComCode
	 * @return
	 */
	public boolean isUpperComCode(String comCode, String upperComCode);

	/**
	 * 由险种代码查询出险种名称（addBy raoguangpu）
	 */
	public String findComCNameByComCode(String comCode);

	/**
	 * 根据查询对象获取Page对象的机构列表
	 * 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含员工列表的Page对象
	 */
	public Page findCompany(QueryRule queryRule, int pageNo, int pageSize);

	/**
	 * 更新机构信息
	 * 
	 * @param prpDcompany
	 */
	public void update(PrpDcompany prpDcompany);

	/**
	 * 保存机构信息
	 * 
	 * @param prpDcompany
	 */
	public void save(PrpDcompany prpDcompany);

	/**
	 * 刪除机构信息
	 * 
	 * @param prpDcompany
	 */
	public void delete(String comCode);

	/**
	 * 初始机构树
	 */
	public void initCompanyTree();

	/**
	 * 根据机构以代码查询出一条对象
	 * 
	 * @param comCode
	 * @return
	 */
	public PrpDcompany getPrpDcompanyByComCode(String comCode);
}
