package com.picc.platform.dms.service.spring;

import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;
import ins.framework.common.Page;
import ins.framework.dao.GenericDaoHibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.picc.common.utils.AppConfig;
import com.picc.common.vo.TreeNode;
import com.picc.platform.dms.service.facade.ICompanyService;
import com.sinosoft.bpsdriver.util.SystemCode;
import com.sinosoft.dmsdriver.model.PrpDcompany;
import com.sinosoft.dmsdriver.service.server.PageService;

@Service(value = "companyService")
public class CompanyServiceSpringImpl extends
		GenericDaoHibernate<PrpDcompany, String> implements ICompanyService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String query_self = "0";
	private static final String query_sub = "1";
	/**
	 * 初始缓存实例
	 */
	private static CacheService cacheManager = CacheManager
			.getInstance("Company");


	public List<PrpDcompany> findCompanyByQueryType(String comCode,
			String queryType) {
		List<PrpDcompany> companyList = new ArrayList<PrpDcompany>();
		
		try{
			if(query_self.equals(queryType)){
				
				
				//查自身

				companyList = PageService.getCompanys(SystemCode.DMS, comCode, "", "", "1", "", 1, Integer.MAX_VALUE).getData(); 
//				companyList = DictAPIService.findCompanyByCondition(SystemCode.DMS, "comCode = '"+comCode+"'");

			}else if(query_sub.equals(queryType)){
				//查下级
				companyList = PageService.getCompanys(SystemCode.DMS, comCode, "", "", "1", "SUB", 1, Integer.MAX_VALUE).getData();
//				companyList = DictAPIService.findCompanyByCondition(SystemCode.DMS, "upperComCode = '"+comCode+"' order by comCode asc");
			}
			return companyList;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
		}
		
		return null;
	}

	public String findComTreeJsonByQueryType(String comCode, String queryType, List<String> checkedNodes) {
		List<PrpDcompany> companyList = this.findCompanyByQueryType(comCode, queryType);
		List<TreeNode> companyTreeNodeList = new ArrayList<TreeNode>();     //树节点列表
		
		boolean checked = false;
		String state = "closed";
		if(checkedNodes!=null && checkedNodes.size()>0){
			for(PrpDcompany c:companyList){
				String id = c.getComCode();
				if(checkedNodes.contains(id)){
					checked = true;
				}
				
				String text = c.getComCName();
				Map map = new HashMap<String, Object>();
				map.put("initChecked", checked);
				companyTreeNodeList.add(new TreeNode(id,text,state,checked,map));
				
				checked = false;
			}
		}else{
			for(PrpDcompany c:companyList){
				String id = c.getComCode();
				String text = c.getComCName()+"["+c.getComCode()+"]";
				Map map = new HashMap<String, Object>();
				map.put("initChecked", checked);
				companyTreeNodeList.add(new TreeNode(id,text,state,checked,map));
			}
		}
		
		JSONArray jsonArray = JSONArray.fromObject(companyTreeNodeList);
		return jsonArray.toString();
	}
	
	
	public Page findSubComByUpperComCode(String comCode, int page, int pageSize)
			throws Exception {
		if(comCode==null||"".equals(comCode)||comCode.length()!=8){
			throw new Exception("传入的机构代码"+comCode+"格式不正确");
		}else {
			List<PrpDcompany> companyList = findCompanyByQueryType(comCode,query_sub);
			if(page<1){
				page = 1;
			}
			if(pageSize<1){
				page = 20;
			}
			int startIndex = Page.getStartOfPage(page, pageSize);
			List<PrpDcompany> resultList = new ArrayList<PrpDcompany>();
			for(int i=0;i<pageSize;i++){
				if(companyList.size()>(page-1)*pageSize+i){
					resultList.add(companyList.get((page-1)*pageSize+i));
				}else {
					break;
				}
			}
			return new Page(startIndex,-1L,pageSize,resultList);
		}
	}

	/**
	 * 查询省(市)机构树并封装成JSON字符串
	 * @return
	 * @author shenyichan
	 */
	public String findHeadComTree(List<String> checkedNodes) throws Exception{
		//先查询出总公司节点
		List<PrpDcompany> headOfficeList = new ArrayList<PrpDcompany>();
		List<PrpDcompany> subCompanyList = new ArrayList<PrpDcompany>();
		headOfficeList = PageService.getCompanys(SystemCode.DMS, AppConfig.get("um.HEAD_COMID"), "", "", "1", "", 1, Integer.MAX_VALUE).getData();
		
		//再查出所有分公司的节点
		subCompanyList = PageService.getCompanys(SystemCode.DMS, AppConfig.get("um.HEAD_COMID"), "", "", "1", "SUB", 1, Integer.MAX_VALUE).getData();
		
		List<TreeNode> subComTreeList = new ArrayList<TreeNode>();
		//封装子节点
		TreeNode node = null;
		for(PrpDcompany c:subCompanyList) {
			node = new TreeNode();
			node.setId(c.getComCode());
			node.setText(c.getComCName());
			node.setState("closed");
			if(checkedNodes!=null&&checkedNodes.contains(c.getComCode())){
				node.setChecked(true);
				node.putAttributes("initChecked", true);
			}else{
				node.setChecked(false);
				node.putAttributes("initChecked", false);
			}
			subComTreeList.add(node);
		}
		
		//封装根节点
		PrpDcompany headOffice = headOfficeList.get(0);
		TreeNode rootNode = new TreeNode();
		rootNode.setId(headOffice.getComCode());
		rootNode.setText(headOffice.getComCName());
		if(checkedNodes!=null&&checkedNodes.contains(headOffice.getComCode())){
			rootNode.setChecked(true);
			rootNode.putAttributes("initChecked", true);
		}else{
			rootNode.setChecked(false);
			rootNode.putAttributes("initChecked", false);
		}
		rootNode.setState("closed");
		rootNode.setChildren(subComTreeList);
		
		List<TreeNode> comTreeList = new ArrayList<TreeNode>();
		comTreeList.add(rootNode);
		
		JSONArray jsonArray = JSONArray.fromObject(comTreeList);
		return jsonArray.toString().replace("\"", "\'");
	}
}
