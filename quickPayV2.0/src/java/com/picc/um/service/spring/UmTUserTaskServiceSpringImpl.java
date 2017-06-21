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
import com.picc.common.utils.ToolsUtils;
import com.picc.um.dao.UmTUserTaskDaoHibernate;
import com.picc.um.schema.model.UmTUserTask;
import com.picc.um.schema.model.UmTUserTaskId;
import com.picc.um.service.facade.ICacheService;
import com.picc.um.service.facade.IUmTUserTaskService;

/**
 * 用户功能接口实现类
 * @author 姜卫洋
 *
 */
@Service("umTUserTaskService")
public class UmTUserTaskServiceSpringImpl implements IUmTUserTaskService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private UmTUserTaskDaoHibernate umTUserTaskDao;
	
	@Autowired
	private CommonDaoHibernate commonDao;
	
	@Autowired
	private ICacheService cacheService;

	/**
	 * 根据主键对象UmTUserTaskId获取UmTUserTask信息
	 * @param UmTUserTaskId
	 * @return UmTUserTask
	 */
	public UmTUserTask findUmTUserTaskByPK(UmTUserTaskId id) throws Exception{
			
			return umTUserTaskDao.get(UmTUserTask.class,id);
	}

	/**
	 * 根据umTUserTask和页码信息，获取Page对象
	 * @param umTUserTask
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTUserTask的Page对象
	 */
	public Page findByUmTUserTask(UmTUserTask umTUserTask, int pageNo, int pageSize) throws Exception{
		
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据umTUserTask生成queryRule
		
		return umTUserTaskDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新UmTUserTask信息
	 * @param UmTUserTask
	 */
	public void updateUmTUserTask(UmTUserTask umTUserTask) throws Exception{
			
			umTUserTaskDao.update(umTUserTask);
	}

	/**
	 * 保存UmTUserTask信息
	 * @param UmTUserTask
	 */
	public void saveUmTUserTask(UmTUserTask umTUserTask) throws Exception{
		try{
			
			UmTUserTask task = findUserTaskByUserCodeAndTaskId(umTUserTask.getUserCode(),umTUserTask.getTaskId());				//从数据库中获取对象
			if(task==null){
				String utid = commonDao.generateID("UMUT", "UM_SEQ_USERTASK", 26);
				UmTUserTaskId id = new UmTUserTaskId(utid);
				umTUserTask.setId(id);
				umTUserTaskDao.save(umTUserTask);
			}else {
				task.setUpdaterCode(umTUserTask.getUpdaterCode());
				task.setValidStatus("0");
				updateUmTUserTask(task);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public void saveUmTUserTaskByOperateType(UmTUserTask umTUserTask,int operateType) throws Exception{
		try{
			UmTUserTask task = findUserTaskByUserCodeAndTaskId(umTUserTask.getUserCode(),umTUserTask.getTaskId());				//从数据库中获取对象
			if(task==null){
				String utid = commonDao.generateID("UMUT", "UM_SEQ_USERTASK", 26);
				UmTUserTaskId id = new UmTUserTaskId(utid);
				umTUserTask.setId(id);
				umTUserTaskDao.save(umTUserTask);
			}else {
				if(operateType==1){
					//新增
					task.setValidStatus("1");
				}else if(operateType==2){
					//取消
					task.setValidStatus("0");
				}
				task.setUpdaterCode(umTUserTask.getUpdaterCode());
				updateUmTUserTask(task);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	

	/**
	 * 根据主键对象，删除UmTUserTask信息
	 * @param UmTUserTaskId
	 */
	public void deleteByPK(UmTUserTaskId id) throws Exception{
			
			umTUserTaskDao.deleteByPK(UmTUserTask.class,id);
	}
	
	public void deleteByUserCode(String userCode) throws Exception{
		List<UmTUserTask> UmTUserTaskList = this.findUserTaskByUserCode(userCode);
		if(UmTUserTaskList.size()>0&&UmTUserTaskList!=null){
		for(UmTUserTask umTUserTask:UmTUserTaskList){
			this.deleteByPK(umTUserTask.getId());
		}
		}
	}
	
	/**
	 * 给用户添加额外的功能代码
	 */
	public void addTaskToUser(String userCode, String[] taskIdArray,String creatorCode,int operateType)
			throws Exception {
		//对功能代码数组集合进行遍历
		UmTUserTask  userTask = null;
		for(String taskId : taskIdArray){
			userTask = new UmTUserTask();
			userTask.setUserCode(userCode);
			userTask.setTaskId(taskId);
			if(operateType==1){
				//添加功能
				userTask.setValidStatus("1");
				userTask.setInvalidDate(ToolsUtils.getFormatDate("2099-12-31","yyyy-MM-dd"));
				userTask.setCreatorCode(creatorCode);
				userTask.setUpdaterCode(creatorCode);
			}else if(operateType==2){
				userTask.setUpdaterCode(creatorCode);
			}
			saveUmTUserTaskByOperateType(userTask,operateType);
			
		}
	}
	
	
	public List<UmTUserTask> findUserTaskByUserCode(String userCode)
			throws Exception {
		QueryRule rule = QueryRule.getInstance();
		rule.addEqual("validStatus", "1");								//获取有效的check对象
		rule.addEqual("userCode", userCode);
		return umTUserTaskDao.find(rule);
	}
	
	
	
	public UmTUserTask findUserTaskByUserCodeAndTaskId(String userCode,
			String taskId) throws Exception {
		QueryRule rule = QueryRule.getInstance();
		rule.addEqual("userCode", userCode);
		rule.addEqual("taskId", taskId);
		return umTUserTaskDao.findUnique(rule);
	}

}
