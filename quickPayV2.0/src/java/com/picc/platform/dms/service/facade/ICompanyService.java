package com.picc.platform.dms.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.sinosoft.dmsdriver.model.PrpDcompany;

public interface ICompanyService {
	
	
	/**
	 * 根据类型查机构
	 * @param comCode
	 * @param queryType 0或1， 当是0时，查机构本身；
	 * 					当是1时，查父级为comCode的所有机构
	 * @return          机构对象列表 
	 */
	public List<PrpDcompany> findCompanyByQueryType(String comCode, String queryType);
	
	/**
	 * 根据类型查机构
	 * @param comCode
	 * @param queryType
	 * @param checkedNodes
	 * @return  TreeNode的Json格式
	 */
	public String findComTreeJsonByQueryType(String comCode, String queryType, List<String> checkedNodes);
	
	
	
	/**
	 * 根据上级机构查询该机构下属的子机构列表
	 * @param comCode				上级机构
	 * @param page						 查询起始页码
	 * @param pageSize				查询当页数据条数
	 * @return								封装机构信息的Page对象
	 * @throws Exception
	 * 2013-8-23上午11:54:39
	 * jiangweiyang
	 */
	public Page findSubComByUpperComCode(String comCode,int page,int pageSize) throws Exception;

	
	/**
	 * 查询省(市)机构树并封装成JSON字符串
	 * @return
	 * @author shenyichan
	 */
	public String findHeadComTree(List<String> checkedNodes) throws Exception;
}
