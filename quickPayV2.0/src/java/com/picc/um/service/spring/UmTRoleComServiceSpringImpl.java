/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.spring;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.Constants;
import com.picc.common.vo.TreeNode;
import com.picc.um.dao.UmTRoleComDaoHibernate;
import com.picc.um.dao.UmTRoleDaoHibernate;
import com.picc.um.schema.model.UmTRole;
import com.picc.um.schema.model.UmTRoleCom;
import com.picc.um.schema.model.UmTRoleComId;
import com.picc.um.service.facade.IUmTRoleComService;
import com.sinosoft.dmsdriver.model.PrpDcompany;

/**
 * 角色机构关联接口实现类
 * @author 沈一婵
 */
@Service("umTRoleComService")
public class UmTRoleComServiceSpringImpl implements IUmTRoleComService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private UmTRoleComDaoHibernate umTRoleComDao;
	
	@Autowired
	private UmTRoleDaoHibernate umTRoleDao;

	
	/**
	 * 根据主键对象UmTRoleComId获取UmTRoleCom信息
	 * @param UmTRoleComId
	 * @return UmTRoleCom
	 */
	public UmTRoleCom findUmTRoleComByPK(UmTRoleComId id) throws Exception{
			
			return umTRoleComDao.get(UmTRoleCom.class,id);
	}

	/**
	 * 根据umTRoleCom和页码信息，获取Page对象
	 * @param umTRoleCom
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTRoleCom的Page对象
	 */
	public Page findByUmTRoleCom(UmTRoleCom umTRoleCom, int pageNo, int pageSize) throws Exception{
		
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据umTRoleCom生成queryRule
		
		return umTRoleComDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新UmTRoleCom信息
	 * @param UmTRoleCom
	 */
	public void updateUmTRoleCom(UmTRoleCom umTRoleCom) throws Exception{
			
			umTRoleComDao.update(umTRoleCom);
	}

	/**
	 * 保存UmTRoleCom信息
	 * @param UmTRoleCom
	 */
	public void saveUmTRoleCom(UmTRoleCom umTRoleCom) throws Exception{
			
			umTRoleComDao.save(umTRoleCom);
	}

	/**
	 * 根据主键对象，删除UmTRoleCom信息
	 * @param UmTRoleComId
	 */
	public void deleteByPK(UmTRoleComId id) throws Exception{
			
			umTRoleComDao.deleteByPK(UmTRoleCom.class,id);
	}

	
	public String getRoleListByComCode(String comCode, String queryType,
			int start, int limit) {
		
		try{
			if(comCode != null&&!"".equals(comCode)&&comCode.length()==8){
				//comCode格式正确
				List<PrpDcompany> companyList = null;
				List<UmTRole> roleList = new ArrayList<UmTRole>();
				List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
				List<TreeNode> companyArrayList = new ArrayList<TreeNode>();
				List<TreeNode> roleArrayList = new ArrayList<TreeNode>();
				
				if(Constants.QUERY_SELF.equals(queryType)){
					//查询自身信息
					try {
//						companyList = DictAPIService.findCompanyByCondition(SystemCode.DMS, "comCode = '"+comCode+"'");
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("", e);
					}				//顶级菜单信息
				}else {
					//根据上级ID查询所有下级菜单
					try {
//						companyList = DictAPIService.findCompanyByCondition(SystemCode.DMS, "upperComCode = '"+comCode+"' order by comCode asc");
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("", e);
					}				//下级菜单信息
				}
				
				for(PrpDcompany company : companyList){
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("nodeType", Constants.COMPANY_NODE);
					companyArrayList.add(new TreeNode(company.getComCode(),company.getComCName(),"closed",map));
				}
				
				if(Constants.QUERY_SUB.equals(queryType)){
					roleList = umTRoleDao.findRoleListByComCode(comCode);
				}
				
				if(roleList.size()>0){
					for(UmTRole role:roleList){
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("nodeType", Constants.ROLE_NODE);
						roleArrayList.add(new TreeNode(role.getId().getRoleId(),role.getRoleCName(),"open",map));
					}
				}
				
				if(roleArrayList.size()>0){
					for(TreeNode node:roleArrayList){
						treeNodeList.add(node);
					}
				}
				
				if(companyArrayList.size()>0){
					for(TreeNode node:companyArrayList){
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

	public List<UmTRoleCom> findRoleComByRoleId(String roleId) {
		String hql = "from UmTRoleCom ur where ur.roleId=?";
		return umTRoleDao.findByHql(hql, roleId);
	}

}
