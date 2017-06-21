/*
 * Powered By limingguo
 * Email: limingguo03@chongq.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

import com.picc.um.dao.UmTGroupDaoHibernate;
import com.picc.um.dao.UmTUserDaoHibernate;
import com.picc.um.dao.UmTUserGroupDaoHibernate;
import com.picc.um.schema.model.UmTGroup;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.model.UmTUserGroup;
import com.picc.um.schema.model.UmTUserGroupId;
import com.picc.um.schema.vo.GroupComColumn;
import com.picc.um.schema.vo.UmTUserVo;
import com.picc.um.schema.vo.UserGroupColumn;
import com.picc.um.service.facade.IUmTGroupComService;
import com.picc.um.service.facade.IUmTUserGroupService;

/**
 * 用户自定义组关联接口实现类
 * @author 李明果
 */
@Service("umTUserGroupService")
public class UmTUserGroupServiceSpringImpl implements IUmTUserGroupService{
	protected final Logger logger = (Logger) LoggerFactory.getLogger(this
			.getClass());
	
	@Autowired
    private UmTUserGroupDaoHibernate umTUserGroupDao;
	@Autowired
    private UmTUserDaoHibernate umTUserDao;
	@Autowired
    private UmTGroupDaoHibernate umTGroupDao;
	@Autowired
	private IUmTGroupComService umTGroupComService;
	private List<String> checkedNodes = null;
	
/****************************************author limingguo 功能区**********************************************************************/	
	/**
	 * 根据主键对象UmTUserGroupId获取UmTUserGroup信息
	 * @param UmTUserGroupId
	 * @return UmTUserGroup
	 */
	public UmTUserGroup findUmTUserGroupByPK(UmTUserGroupId id) throws Exception{
			
			return umTUserGroupDao.get(UmTUserGroup.class,id);
	}

	/**
	 * 根据umTUserGroup和页码信息，获取Page对象
	 * @param umTUserGroup
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTUserGroup的Page对象
	 */
	public Page findByUmTUserGroup(UmTUserGroup umTUserGroup, int pageNo, int pageSize) throws Exception{
		
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		return umTUserGroupDao.find(queryRule, pageNo, pageSize);
	}
	
	public List<UmTUserGroup> findByUmTUserCode(UmTUserGroup umTUserGroup) throws Exception{
		
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		queryRule.addEqual("groupId", umTUserGroup.getGroupId());
		queryRule.addLike("validStatus", umTUserGroup.getValidStatus());
	
		return umTUserGroupDao.find(queryRule);
	}
	
	public List<UmTUserGroup> findByUmTGroupId(UmTUserGroup umTUserGroup) throws Exception{
		
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		queryRule.addEqual("userCode", umTUserGroup.getUserCode());
		queryRule.addLike("validStatus", umTUserGroup.getValidStatus());
	
		return umTUserGroupDao.find(queryRule);
	}
	
	/**@author limingguo 2013-8-22
	 * 根据umTUserGroup和页码信息，获取Page对象
	 * @param groupId comCode
	 * @param pageNo
	 * @param pageSize
	 * @return 根据groupId comCode 返回含有人员名字的usergroup PAGE
	 */
	/*根据人员代码和groupid查询usergroup关系表*/
	public List<UmTUserGroup> find4sUserByGroupIdAndUserCode(String groupId, String userCode)throws Exception{
		String hql = "select uug from UmTUserGroup uug where uug.groupId=? and uug.validStatus='1' and uug.userCode=? ";
		return umTUserGroupDao.findByHql(hql,groupId,userCode);
	}
	
	/**@author limingguo 2013-8-22
	 * 根据umTUserGroup和页码信息，获取Page对象
	 * @param comId userCode
	 * @return 根据comId userCode 返回group List
	 */
	public List<UmTGroup> find4sCodeByUserCode(String userCode,String comId)throws Exception{
		String hql = "select ug from UmTGroup ug,UmTUserGroup uug where ug.id.groupId=uug.groupId and uug.userCode=? and uug.comId=? and ug.validStatus='1' and ug.groupType='2'";
		return umTUserGroupDao.findByHql(hql,userCode,comId);
	}
	
	/**@author limingguo 2013-8-22
	 * 根据umTUserGroup和页码信息，获取Page对象
	 * @param comId userCode
	 * @return 根据comId userCode 返回group List
	 */
	public List<UmTGroup> find4sCodeByUserCodeValid(String userCode,String comId)throws Exception{
		String hql = "select ug from UmTGroup ug,UmTUserGroup uug where ug.id.groupId=uug.groupId and uug.userCode=? and uug.comId=? and ug.validStatus='1' and uug.validStatus = '1' and ug.groupType='2'";
		return umTUserGroupDao.findByHql(hql,userCode,comId);
	}
	
	/**@author limingguo 2013-8-22
	 * 根据车行代码查询车行管理的人员
	 * @param groupCode comId
	 * @return 返回车行代码查询车行管理的人员list
	 */
	public List<UmTUserGroup> find4sUserByGroupCode(String groupCode, String comId)throws Exception{
		String hql = "select uug from UmTGroup ug,UmTUserGroup uug where ug.id.groupId=uug.groupId and ug.groupCode=? and ug.validStatus='1' and uug.validStatus='1' and ug.groupType='2' and uug.comId=? ";
		return umTUserGroupDao.findByHql(hql,groupCode,comId);
	}
	
	/**@author limingguo 2013-10-22
	 * 判断团队经理
	 * @param groupCode userCode
	 * @return false or true
	 */
	public boolean is4sGroupLeader(String groupCode, String userCode) throws Exception{
		String sql = "select a.usergroupid,a.groupid,a.usercode,a.creatorcode " +
		"from um_t_usergroup a,um_t_group b " +
		"where a.groupid = b.groupid and a.validstatus = '1' and b.validstatus = '1' and a.usercode = ? and b.groupcode = ? and b.grouptype='2' and a.isleader='1' ";
		
		if(umTUserGroupDao.getCommonDao().findObjectPageBySql(sql, UmTUserGroup.class, 1, Integer.MAX_VALUE, userCode, groupCode).getTotalCount()==1){
			return true;
		}else{
			return false;
		}		
		
	}

	/**@author limingguo 2013-8-22
	 * 某流程角色关联的人
	 * @param groupId comCode
	 * @param pageNo
	 * @param pageSize
	 * @return 根据groupId comCode 返回程角色关联的人
	 */
	public Page findUserGroupByGroupIdCom(int pageNo, int pageSize, String groupId, String comCode) throws Exception{
		String sql = "select a.usergroupid,a.groupid,b.groupname,a.usercode,c.username,a.isleader,c.comcode,a.creatorcode,a.inserttimeforhis,a.updatercode,a.operatetimeforhis,a.validstatus " +
		"from um_t_usergroup a,um_t_group b,um_t_user c " +
		"where a.groupid = b.groupid and a.validstatus = '1' and c.validstatus = '1' and a.usercode=c.usercode and b.groupid=? and c.comcode like ?";
		
		return umTUserGroupDao.getCommonDao().findObjectPageBySql(sql, UserGroupColumn.class, pageNo, pageSize, groupId, comCode);
	}

	/**@author limingguo 2013-8-22
	 * 某人关联的车行表格
	 * @param userCode comId
	 * @param pageNo
	 * @param pageSize
	 * @return 根据userCode 返回人关联的车行Page
	 */
	public Page find4sByUserCode(int pageNo, int pageSize, String userCode, String comId) throws Exception{
		String sql = "select a.groupcomid,b.groupid,b.groupname,b.groupcode,a.comcode,c.creatorcode,c.inserttimeforhis,c.updatercode,a.updatetimeforhis,c.validstatus " +
		"from um_t_groupcom a,um_t_group b,um_t_usergroup c " +
		"where a.groupid = b.groupid and b.groupid=c.groupid and b.validstatus = '1' and a.validstatus= '1' and b.grouptype='2' and c.usercode = ? and c.comid=?";
		
		return umTUserGroupDao.getCommonDao().findObjectPageBySql(sql, GroupComColumn.class, pageNo, pageSize, userCode,comId);
	}
	
	/**
	 * 根据umTUserGroup和页码信息，获取Page对象
	 * @param umTUserGroup
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTUserGroup的Page对象
	 */
	public Page findGroupByComCodeMuilt(String sql, Class<?> className, int pageNo, int pageSize,String comCode)throws Exception{
		return umTUserGroupDao.getCommonDao().findObjectPageBySql(sql, className, pageNo, pageSize, comCode);
		
	}
	
	/**
	 * 根据umTUserGroup和页码信息，获取Page对象
	 * @param umTUserGroup
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTUserGroup的Page对象
	 */
	public Page findGroupByUserMuilt(String sql, Class<?> className, int pageNo, int pageSize,String userCode) throws Exception{
		return umTUserGroupDao.getCommonDao().findObjectPageBySql(sql, className, pageNo, pageSize, userCode);
	}
	
	/**
	 * 根据umTUserGroup和页码信息，查询机构的下属人员
	 * @param comCode userName userCode
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTUserGroup的Page对象
	 */
	public Page findUserPageByComCode(int pageNo, int pageSize, String comCode, String userName, String userCode)throws Exception{
		String sql = "select usercode,username,comcode,identitynumber,validstatus " +
		"from um_t_user " + 
		"where validstatus = '1' and comcode =? and username like ? and usercode like ?";
		
		return umTUserGroupDao.getCommonDao().findObjectPageBySql(sql, UmTUser.class, pageNo, pageSize, comCode, userName, userCode);
	}
	
	/**
	 * 根据umTUserGroup和页码信息，查询机构的车行人员
	 * @param comCode 
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTUserGroup的Page对象
	 */
	public Page find4sUserPageByComCode(int pageNo, int pageSize, String comCode)throws Exception{
		String sql = "select usercode,username,comcode,identitynumber,validstatus " +
		"from um_t_user " + 
		"where validstatus = '1' and comcode like ? ";
		
		return umTUserGroupDao.getCommonDao().findObjectPageBySql(sql, UmTUser.class, pageNo, pageSize, comCode);
	}
	
	/**@author limingguo 2013-8-22
	 * UmTUserGroup查询，查询返回机构下属人员。
	 * @param comCode
	 * @return 包含UmTUserGroup的Page对象
	 */
	public Page findUmTUserByComCode(String comCode)throws Exception{
		String sql = "select usercode,username,comcode " +
		"from um_t_user " +
		"where validstatus = '1' and comcode =?";
		return umTUserGroupDao.getCommonDao().findObjectPageBySql(sql, UmTUserVo.class, 1, Integer.MAX_VALUE, comCode);
	}

	/**
	 * 更新UmTUserGroup信息
	 * @param UmTUserGroup
	 */
	public void updateUmTUserGroup(UmTUserGroup umTUserGroup) throws Exception{
			
			umTUserGroupDao.update(umTUserGroup);
	}

	/**
	 * 保存UmTUserGroup信息
	 * @param UmTUserGroup
	 */
	public void saveUmTUserGroup(UmTUserGroup umTUserGroup) throws Exception{
			
			UmTUserGroupId userGroupId = new UmTUserGroupId();
			userGroupId.setUserGroupId(umTUserGroupDao.getCommonDao().generateID("UMUG", "UM_SEQ_USERGROUP", 26));
			umTUserGroup.setId(userGroupId);
			umTUserGroupDao.save(umTUserGroup);
	}
	
	/**
	 * 保存UmTUserGroup列表
	 * @param UmTUserGroup List
	 */
	public void saveUmTUserGroupList(List<UmTUserGroup> userGroupList) throws Exception{
		
		
		for (int i=0;i<userGroupList.size();i++)
		{
			UmTUserGroupId userGroupId = new UmTUserGroupId();
			userGroupId.setUserGroupId(umTUserGroupDao.getCommonDao().generateID("UMUG", "UM_SEQ_USERGROUP", 26));
			userGroupList.get(i).setId(userGroupId);
		}
		
		umTUserGroupDao.saveAll(userGroupList);
	}

	/**
	 * 根据主键对象，删除UmTUserGroup信息
	 * @param UmTUserGroupId
	 */
	public void deleteByPK(UmTUserGroupId id) throws Exception{
			

			umTUserGroupDao.deleteByPK(UmTUserGroup.class,id);

	}
	public List<String> find4sUserByGroupCode2(String groupCode, String comId) {
		String sql = "select trim(b.usercode) from um_t_group a,um_t_usergroup b where a.groupid = b.groupid and a.groupCode='"+groupCode+"' and a.validstatus='1' and b.validstatus='1' and a.grouptype='2' and b.comId='"+comId+"'";
		List<String> list = umTUserGroupDao.findBySql(sql);
		return list;
	}
}
