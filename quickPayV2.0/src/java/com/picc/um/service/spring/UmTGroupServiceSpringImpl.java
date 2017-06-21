/*
 * Powered By limingguo
 * Email: limingguo03@chongq.picc.com.cn
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

import com.picc.um.dao.UmTGroupDaoHibernate;
import com.picc.um.schema.model.UmTGroup;
import com.picc.um.schema.model.UmTGroupId;
import com.picc.um.schema.vo.GroupComColumn;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.service.facade.IUmTGroupService;

/**
 * 自定义组接口实现类
 * @author 李明果
 */
@Service("umTGroupService")
public class UmTGroupServiceSpringImpl implements IUmTGroupService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private UmTGroupDaoHibernate umTGroupDao;

	/**
	 * 根据主键对象UmTGroupId获取UmTGroup信息
	 * @param UmTGroupId
	 * @return UmTGroup
	 */
	public UmTGroup findUmTGroupByPK(UmTGroupId id) throws Exception{
			
			return umTGroupDao.get(UmTGroup.class,id);
	}

	/**
	 * 根据umTGroup和页码信息，获取Page对象
	 * @param umTGroup
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTGroup的Page对象
	 */
	public Page findByUmTGroup(UmTGroup umTGroup,String comCode, int pageNo, int pageSize) throws Exception{
		
 		String sql = "select * from um_t_group b " +
				" where b.groupname like ? and b.groupcode like ? and b.validstatus= '1' and b.grouptype='1' "+
				" and exists  (select 1 from um_t_groupcom a where a.groupid = b.groupid  and a.comCode like ? ) ";
				
				String gn = "";
				String gc = "";
				String cc = "";
				if(umTGroup.getGroupName() == null||"".equals(umTGroup.getGroupName())){
					gn = "%";
				}else{
					//userName =new String(userName.getBytes("ISO-8859-1"), "UTF-8");
					if(umTGroup.getGroupName().indexOf("*")==-1){				
						gn = "%"+umTGroup.getGroupName()+"%";
					}else{
						gn = umTGroup.getGroupName().replace('*', '%');
					}		
					
				}
				
				if(umTGroup.getGroupCode() == null||"".equals(umTGroup.getGroupCode())){
					gc = "%";
				}else{
					//userName =new String(userName.getBytes("ISO-8859-1"), "UTF-8");
					if(umTGroup.getGroupCode().indexOf("*")==-1){				
						gc = "%"+umTGroup.getGroupCode()+"%";
					}else{
						gc = umTGroup.getGroupCode().replace('*', '%');
					}		
					
				}
				if(comCode != null&&!"".equals(comCode)){
					cc = SessionUser.getComIdByComCode(comCode);
				}
				//String gn = "%"+umTGroup.getGroupName()+"%";
				//String gc = "%"+umTGroup.getGroupCode()+"%";

				return umTGroupDao.getCommonDao().findObjectPageBySql(sql, UmTGroup.class, pageNo, pageSize, gn,gc,cc);
	}
	
	/**
	 * 判断车行是否存在
	 * @param groupCode 车行代码
	 * @return false or true
	 */
	public boolean isGroupCode(String groupCode)throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance

		queryRule.addEqual("groupType", "2");
		queryRule.addEqual("validStatus", "1");
		queryRule.addEqual("groupCode", groupCode);
		
		if(umTGroupDao.find(queryRule, 1, Integer.MAX_VALUE).getTotalCount()==0){
			return false;
		}else{
			return true;
		}		
	}
	
	/**
	 * 根据umTGroup和页码信息，获取车行结果
	 * @param umTGroup
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTGroup的Page对象
	 */
	public Page findGroupAnd4sComCode(UmTGroup umTGroup, int pageNo, int pageSize) throws Exception{
		
		
		String sql = "select a.groupcomid,b.groupid,b.groupname,b.groupcode,a.comcode,b.creatorcode,b.inserttimeforhis,b.updatercode,b.operatetimeforhis,b.validstatus " +
		"from um_t_groupcom a,um_t_group b " +
		"where a.groupid = b.groupid and b.validstatus= '1' and b.grouptype='2' and b.groupname like ? and b.groupcode like ? ";
		
		String gn = "";
		String gc = "";
		if(umTGroup.getGroupName() == null||"".equals(umTGroup.getGroupName())){
			gn = "%";
		}else{
			//userName =new String(userName.getBytes("ISO-8859-1"), "UTF-8");
			if(umTGroup.getGroupName().indexOf("*")==-1){				
				gn = "%"+umTGroup.getGroupName()+"%";
			}else{
				gn = umTGroup.getGroupName().replace('*', '%');
			}		
			
		}
		
		if(umTGroup.getGroupCode() == null||"".equals(umTGroup.getGroupCode())){
			gc = "%";
		}else{
			//userName =new String(userName.getBytes("ISO-8859-1"), "UTF-8");
			if(umTGroup.getGroupCode().indexOf("*")==-1){				
				gc = "%"+umTGroup.getGroupCode()+"%";
			}else{
				gc = umTGroup.getGroupCode().replace('*', '%');
			}		
			
		}

		//String gn = "%"+umTGroup.getGroupName()+"%";
		//String gc = "%"+umTGroup.getGroupCode()+"%";

		return umTGroupDao.getCommonDao().findObjectPageBySql(sql, GroupComColumn.class, pageNo, pageSize, gn,gc);
	}
	
	/**
	 * 根据umTGroup和页码信息，获取groupid
	 * @param groupCode
	 * @return groupid
	 */
	public String findGroupId(String groupCode) throws Exception{
		
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		if(groupCode!=null){
			queryRule.addLike("groupCode", groupCode);
		}

		return umTGroupDao.find(queryRule).get(0).getId().getGroupId();
	}
	
	public Page findByUmTGroupValid(String sql, Class<?> className, int pageNo, int pageSize) throws Exception{
		
//		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
//		queryRule.addEqual("validStatus", "1");
//		queryRule.addLike("groupType", "1");
		//根据umTGroup生成queryRule
		return umTGroupDao.getCommonDao().findObjectPageBySql(sql, className, pageNo, pageSize);
	}
	
	/**
	 * 更新UmTGroup信息
	 * @param UmTGroup
	 */
	public void updateUmTGroup(UmTGroup umTGroup) throws Exception{
			
			umTGroupDao.update(umTGroup);
	}

	/**
	 * 保存UmTGroup信息
	 * @param UmTGroup
	 */
	public void saveUmTGroup(UmTGroup umTGroup) throws Exception{
			
			
			UmTGroupId id = new UmTGroupId();
			id.setGroupId(umTGroupDao.getCommonDao().generateID("UMGR", "UM_SEQ_GROUP", 26));			
			umTGroup.setId(id);

			umTGroupDao.save(umTGroup);
	}


	/**
	 * 根据主键对象，删除UmTGroup信息
	 * @param UmTGroupId
	 */
	public void deleteByPK(UmTGroupId id) throws Exception{
			
			umTGroupDao.deleteByPK(UmTGroup.class,id);
	}

	public String findGroupName(String groupCode) {
		// TODO Auto-generated method stub
		String sql = "select groupname from um_t_group where groupcode = ? and validstatus = '1'";
		List<String> groupNameList = umTGroupDao.findBySql(sql, groupCode);
		String groupName = "";
		if(groupNameList != null && groupNameList.size() > 0)
			groupName = groupNameList.get(0);
		return groupName;
	}

	/**
	 * 根据组类型获取所有组信息
	 * @return
	 * @author shenyichan
	 */
	public List<String> findAllGroupByType(String groupType,String comId) {
		// 查询语句
		StringBuffer sql = new StringBuffer(
				"select groupCode from (select distinct a.* from um_t_group a,um_t_groupcom " +
				"b where a.groupid=b.groupid and a.grouptype='"+groupType+"' and substr(b.comcode,1,4)='"+comId.substring(0,4)+"')");
		List<String> list = umTGroupDao.findBySql(sql.toString());
		return list;
	}
	
}
