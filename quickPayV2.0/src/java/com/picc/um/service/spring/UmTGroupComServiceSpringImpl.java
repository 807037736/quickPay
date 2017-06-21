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

import com.picc.um.dao.UmTGroupComDaoHibernate;
import com.picc.um.schema.model.UmTGroup;
import com.picc.um.schema.model.UmTGroupCom;
import com.picc.um.schema.model.UmTGroupComId;
import com.picc.um.schema.vo.GroupComColumn;
import com.picc.um.service.facade.IUmTGroupComService;

/**
 * 自定义组与机构关联接口实现类
 * @author 李明果
 */
@Service("umTGroupComService")
public class UmTGroupComServiceSpringImpl implements IUmTGroupComService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private UmTGroupComDaoHibernate umTGroupComDao;

	/**
	 * 根据主键对象UmTGROUPCOMId获取UmTGROUPCOM信息
	 * @param UmTGroupComId
	 * @return UmTGROUPCOM
	 */
	public List<UmTGroupCom> findUmTGroupComByGroupId(UmTGroupCom umTGroupCom) throws Exception{
		
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance		
		queryRule.addEqual("groupId", umTGroupCom.getGroupId());
		
		return umTGroupComDao.find(queryRule);
	}
	
	public UmTGroupCom findUmTGroupComByPK(UmTGroupComId id) throws Exception{
			
			return umTGroupComDao.get(UmTGroupCom.class,id);
	}

	/**
	 * 根据umTGROUPCOM和页码信息，获取Page对象
	 * @param umTGROUPCOM
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTGROUPCOM的Page对象
	 */
	public Page findByUmTGroupCom(UmTGroupCom umTGroupCom, String comcode,int pageNo, int pageSize) throws Exception{
		
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		
		queryRule.addEqual("groupId", umTGroupCom.getGroupId());
		queryRule.addLike("comCode", comcode.substring(0,4)+"%");
		
		return umTGroupComDao.find(queryRule, pageNo, pageSize);
	}
	
	/**@author limingguo 2013-8-22
	 * 根据comCode和页码信息，获取Page对象
	 * @param comCode
	 * @param pageNo
	 * @param pageSize
	 * @return 通过comcode 查找返回group 和 groupcom 联合有效的流程角色和车行列表
	 */
	
	public Page findGroupAnd4sByComcode(String comCode)throws Exception{
		/*查询流程角色和车行*/
		/*String sql = "select a.groupcomid,b.groupid,b.groupname,b.groupcode,a.comcode,a.creatorcode,a.inserttimeforhis,a.updatercode,a.updatetimeforhis,a.validstatus "+
		"from um_t_groupcom a,um_t_group b where a.groupid = b.groupid and b.validstatus = '1' and a.validstatus = '1' and ((b.grouptype='2' and a.comcode = ?)or(b.grouptype='1' and a.comcode = ?))";*/
		/*只查询流程角色*/
		String sql = "select a.groupcomid,b.groupid,b.groupname,b.groupcode,a.comcode,a.creatorcode,a.inserttimeforhis,a.updatercode,a.updatetimeforhis,a.validstatus "+
		"from um_t_groupcom a,um_t_group b where a.groupid = b.groupid and b.validstatus = '1' and a.validstatus = '1' and b.grouptype='1' and a.comcode = ?";
		
		//String comCode1 = comCode;
		String comCode2 = comCode.substring(0,4).concat("0000");
		return umTGroupComDao.getCommonDao().findObjectPageBySql(sql, GroupComColumn.class, 1, Integer.MAX_VALUE, comCode2);
	}
	
	/**@author limingguo 2013-8-22
	 * 根据comCode和页码信息，获取Page对象
	 * @param comCode
	 * @param pageNo
	 * @param pageSize
	 * @return 通过comcode 查找返回group 和 groupcom 联合有效的车行列表
	 */
	
	public Page findCom4sByComcode(String comCode)throws Exception{

		String sql = "select a.groupcomid,b.groupid,concat(b.groupcode,b.groupname) as groupname,b.groupcode,a.comcode,a.creatorcode,a.inserttimeforhis,a.updatercode,a.updatetimeforhis,a.validstatus "+
		"from um_t_groupcom a,um_t_group b where a.groupid = b.groupid and b.validstatus = '1' and a.validstatus = '1' and b.grouptype='2' and a.comcode like ?";

		return umTGroupComDao.getCommonDao().findObjectPageBySql(sql, GroupComColumn.class, 1, Integer.MAX_VALUE, comCode);
	}
	
	/**@author limingguo 2013-8-22
	 * 根据umTGROUPCOM和页码信息，获取Page对象
	 * @param umTGROUPCOM
	 * @param pageNo
	 * @param pageSize
	 * @return 通过comcode 查找返回group 和 groupcom 联合有效的流程角色
	 */
	public Page findGroupAndComByUmTGroupComCode(UmTGroupCom umTGroupCom, int pageNo, int pageSize) throws Exception{
		
		
		String sql = "select a.groupcomid,b.groupid,b.groupname,b.groupcode,a.comcode,a.creatorcode,a.inserttimeforhis,a.updatercode,a.updatetimeforhis,a.validstatus " +
		"from um_t_groupcom a,um_t_group b " +
		"where a.groupid = b.groupid and b.validstatus = '1' and a.validstatus= '1' and b.grouptype='1' and a.comcode like ?";
		
		return umTGroupComDao.getCommonDao().findObjectPageBySql(sql, GroupComColumn.class, pageNo, pageSize, umTGroupCom.getComCode().substring(0,4).concat("%"));
	}
	
	/**@author limingguo 2013-8-22
	 * 根据umTGROUPCOM和页码信息，获取Page对象
	 * @param umTGROUPCOM
	 * @param pageNo
	 * @param pageSize
	 * @return 通过comcode 查找返回group 和 groupcom 联合有效的车行
	 */
	public Page findGroupAnd4sByUmTGroupComCode(UmTGroupCom umTGroupCom, int pageNo, int pageSize) throws Exception{
		
		
		String sql = "select a.groupcomid,b.groupid,b.groupname,b.groupcode,a.comcode,a.creatorcode,a.inserttimeforhis,a.updatercode,a.updatetimeforhis,a.validstatus " +
		"from um_t_groupcom a,um_t_group b " +
		"where a.groupid = b.groupid and b.validstatus = '1' and a.validstatus= '1' and b.grouptype='2' and a.comcode = ? ";
		
		return umTGroupComDao.getCommonDao().findObjectPageBySql(sql, GroupComColumn.class, pageNo, pageSize, umTGroupCom.getComCode());
	}
	
	/**@author limingguo 2013-8-22
	 * 根据umTGROUPCOM和页码信息，获取Page对象
	 * @param comCode
	 * @return 通过comcode 查找返回group的车行信息
	 */
	public List<UmTGroup> findGroupAnd4sByComCodeEqual(String comCode) throws Exception{
		
		String hql = "select b from um_t_groupcom a,um_t_group b " +
		"where a.groupid = b.groupid and b.validstatus = '1' and a.validstatus= '1' and b.grouptype='2' and a.comcode = ? ";
		
		return umTGroupComDao.findByHql(hql, comCode);
	}
	
	/**@author limingguo 2013-9-22
	 * 根据umTGROUPCOM和页码信息，获取Page对象
	 * @param umTGROUPCOM
	 * @param pageNo
	 * @param pageSize
	 * @return 通过comcode 根据前六位查车行 查找返回group 和 groupcom 联合有效的车行
	 */
	public Page find4sByUmTGroupComCode(UmTGroupCom umTGroupCom, int pageNo, int pageSize) throws Exception{
		
		
		String sql = "select a.groupcomid,b.groupid,b.groupname,b.groupcode,a.comcode,a.creatorcode,a.inserttimeforhis,a.updatercode,a.updatetimeforhis,a.validstatus " +
		"from um_t_groupcom a,um_t_group b " +
		"where a.groupid = b.groupid and b.validstatus = '1' and a.validstatus= '1' and b.grouptype='2' and a.comcode like ? ";
		
		return umTGroupComDao.getCommonDao().findObjectPageBySql(sql, GroupComColumn.class, pageNo, pageSize, umTGroupCom.getComCode());
	}
	
	/**@author limingguo 2013-9-22
	 *判断车行代码是否存在
	 * @param umTGROUPCOM
	 * @param groupCode
	 * @param comCode
	 * @return 通过comcode groupcode 返回false or true
	 */
	public boolean isGroupComCode(String groupCode, String comCode) throws Exception{		
		
		String sql = "select a.groupcomid,b.groupid,b.groupname,b.groupcode,a.comcode,a.creatorcode,a.inserttimeforhis,a.updatercode,a.updatetimeforhis,a.validstatus " +
		"from um_t_groupcom a,um_t_group b " +
		"where a.groupid = b.groupid and b.validstatus = '1' and a.validstatus= '1' and b.grouptype='2' and b.groupcode = ? and a.comcode = ? ";
		
		if(umTGroupComDao.getCommonDao().findObjectPageBySql(sql, GroupComColumn.class, 1, Integer.MAX_VALUE, groupCode, comCode).getTotalCount()==0){
			return false;
		}else{
			return true;
		}		
		
	}

	/**
	 * 更新UmTGROUPCOM信息
	 * @param UmTGroupCom
	 */
	public void updateUmTGroupCom(UmTGroupCom umTGroupCom) throws Exception{
			
			umTGroupComDao.update(umTGroupCom);
	}

	/**
	 * 保存UmTGROUPCOM信息
	 * @param UmTGroupCom
	 */
	public void saveUmTGroupCom(UmTGroupCom umTGroupCom) throws Exception{
			
			UmTGroupComId groupComcId =new UmTGroupComId();
			groupComcId.setGroupComId(umTGroupComDao.getCommonDao().generateID("UMGC", "UM_SEQ_GROUPCOM", 26));
			umTGroupCom.setId(groupComcId);
			umTGroupComDao.save(umTGroupCom);
	}
	
	/**@author limingguo 2013-8-22
	 * 保存UmTGROUPCOM 列表
	 * @param UmTGroupCom
	 */
	public void saveUmTGroupComList(List<UmTGroupCom> groupComList) throws Exception{
		logger.debug("正在执行UmTGROUPCOMServiceImpl.saveUmTGroupComList，保存UmTGroupCom 列表");
		
		for (int i=0;i<groupComList.size();i++)			
		{
			UmTGroupComId groupComId =new UmTGroupComId();
			groupComId.setGroupComId(umTGroupComDao.getCommonDao().generateID("UMGC", "UM_SEQ_GROUPCOM", 26));
			groupComList.get(i).setId(groupComId);
		}
		
		umTGroupComDao.saveAll(groupComList);
}

	/**
	 * 根据主键对象，删除UmTGROUPCOM信息
	 * @param UmTGroupComId
	 */
	public void deleteByPK(UmTGroupComId id) throws Exception{
			
			umTGroupComDao.deleteByPK(UmTGroupCom.class,id);
	}
}
