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

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.um.dao.UmTRoleTaskDaoHibernate;
import com.picc.um.schema.model.UmTRoleTask;
import com.picc.um.schema.model.UmTRoleTaskId;
import com.picc.um.service.facade.IUmTRoleTaskService;

/**
 * 角色对象关联功能接口实现类
 * @author 沈一婵
 */
@Service("umTRoleTaskService")
public class UmTRoleTaskServiceSpringImpl implements IUmTRoleTaskService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private UmTRoleTaskDaoHibernate umTRoleTaskDao;
	
	@Autowired
	private CommonDaoHibernate commonDao;

	/**
	 * 根据主键对象UmTRoleTaskId获取UmTRoleTask信息
	 * @param UmTRoleTaskId
	 * @return UmTRoleTask
	 */
	public UmTRoleTask findUmTRoleTaskByPK(UmTRoleTaskId id) throws Exception{
			
			return umTRoleTaskDao.get(UmTRoleTask.class,id);
	}

	/**
	 * 根据umTRoleTask和页码信息，获取Page对象
	 * @param umTRoleTask
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTRoleTask的Page对象
	 */
	public Page findByUmTRoleTask(UmTRoleTask umTRoleTask, int pageNo, int pageSize) throws Exception{
		
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据umTRoleTask生成queryRule
		
		return umTRoleTaskDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新UmTRoleTask信息
	 * @param UmTRoleTask
	 */
	public void updateUmTRoleTask(UmTRoleTask umTRoleTask) throws Exception{
			
			umTRoleTaskDao.update(umTRoleTask);
	}

	/**
	 * 保存UmTRoleTask信息
	 * @param UmTRoleTask
	 */
	public void saveUmTRoleTask(UmTRoleTask umTRoleTask) throws Exception{
			
			umTRoleTaskDao.save(umTRoleTask);
	}

	/**
	 * 根据主键对象，删除UmTRoleTask信息
	 * @param UmTRoleTaskId
	 */
	public void deleteByPK(UmTRoleTaskId id) throws Exception{
			
			umTRoleTaskDao.deleteByPK(UmTRoleTask.class,id);
	}
	

	public List<UmTRoleTask> findRoleTaskByRoleId(String roleId) {
		String hql = "from UmTRoleTask utr where roleId=?";
		return umTRoleTaskDao.findByHql(hql, roleId);
	}

}
