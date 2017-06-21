package com.picc.qp.service.spring.wx;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.qp.dao.wxdao.WxTaskDaoHibernate;
import com.picc.qp.schema.model.WxTask;
import com.picc.qp.service.wx.facade.WxTaskService;

@Service("wxTaskService")
public class WxTaskServiceSpringImpl implements WxTaskService {
	
	@Autowired
	private WxTaskDaoHibernate wxTaskDao;
	
	@Override
	public Page getPage(WxTask wxTask, Integer pageNo, Integer pageSize)
			throws Exception {
		QueryRule queryRule = QueryRule.getInstance();
//		queryRule.addEqual("status", "0");
		queryRule.addAscOrder("status");
		queryRule.addAscOrder("createTime");
		return wxTaskDao.find(queryRule, pageNo, pageSize);
	}

	@Override
	public void updateWxTask(WxTask wxTask) {
		wxTaskDao.update(wxTask);

	}

	@Override
	public void addWxTask(WxTask wxTask) {
		wxTaskDao.save(wxTask);
		
	}

	@Override
	public void deleteWxTaskById(Integer id) {
		wxTaskDao.deleteByPK(WxTask.class, id);
	}

	@Override
	public WxTask findWxTaskById(Integer taskId) {
		return wxTaskDao.get(WxTask.class, taskId);
	}
	
	/**
	 * 获取wxTask并进行排序，便于页面查看任务。
	 * 
	 * @return
	 */
	@Override
	public Page getUserCodePage(WxTask wxTask, Integer pageNo,
			Integer pageSize, String userCode) throws Exception {
		QueryRule queryRule = QueryRule.getInstance();
		if(!userCode.isEmpty()){
			queryRule.addEqual("receiveCode", userCode);
		}
		queryRule.addEqual("status", "3");
		queryRule.addDescOrder("createTime");
		List<WxTask> wxTasks = wxTaskDao.find(queryRule);
		
		
		QueryRule queryRule2 = QueryRule.getInstance();
		queryRule2.addNotEqual("status", "4");
		queryRule2.addAscOrder("status");
		queryRule2.addDescOrder("createTime");
		List<WxTask> wxTasks2 = wxTaskDao.find(queryRule2);
		for (int i = 0; i < wxTasks.size(); i++) {
			wxTasks2.remove(wxTasks.get(i));
			wxTasks.get(i).setStatus("0");
		}
		if(!wxTasks2.isEmpty()){
			wxTasks.addAll(wxTasks2);		
		}
		
		List<WxTask> data = new ArrayList<WxTask>();
		int no;
		if(pageSize*pageNo<wxTasks.size()){
			no = pageSize*pageNo;
		}else{
			no = wxTasks.size();
		}
		for (int i = (pageNo-1)*pageSize; i < no; i++) {
			data.add(wxTasks.get(i));
		}
		
		Page page = new Page(pageNo, wxTasks.size(), pageSize, data);

		return page;
	}

	@Override
	public WxTask findWxTaskByCaseId(String caseId) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("caseId", caseId);
		List<WxTask> tasks = wxTaskDao.find(queryRule);
		return tasks.isEmpty() ? null : tasks.get(0);
	}

}
