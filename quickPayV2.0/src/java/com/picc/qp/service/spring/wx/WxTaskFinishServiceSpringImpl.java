package com.picc.qp.service.spring.wx;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.utils.StringUtils;
import com.picc.qp.dao.wxdao.WxTaskFinishDaoHibernate;
import com.picc.qp.schema.model.WxTaskFinish;
import com.picc.qp.service.wx.facade.WxTaskFinishService;
import com.picc.qp.service.wx.facade.WxTaskService;

@Service("wxTaskFinishService")
public class WxTaskFinishServiceSpringImpl implements WxTaskFinishService {
	
	@Autowired
	private WxTaskFinishDaoHibernate wxTaskFinishDao;
	@Autowired
	private WxTaskService wxTaskService;
	@Override
	public Page getPage(WxTaskFinish wxTaskFinish, Integer pageNo, Integer pageSize)
			throws Exception {
		QueryRule queryRule = QueryRule.getInstance();
		if (wxTaskFinish != null) {
			if (wxTaskFinish.getStartCompleteTime() != null) {
				queryRule.addGreaterEqual("completeTime", wxTaskFinish.getStartCompleteTime());
			}
			if (wxTaskFinish.getEndCompleteTime() != null) {
				queryRule.addLessEqual("completeTime", wxTaskFinish.getEndCompleteTime());
			}
			if (StringUtils.isNotEmpty(wxTaskFinish.getCompleteCode())) {
				queryRule.addEqual("completeCode", wxTaskFinish.getCompleteCode());
			}
			if (StringUtils.isNotEmpty(wxTaskFinish.getCompleteName())) {
				queryRule.addEqual("completeName", wxTaskFinish.getCompleteName());
			}
			if (StringUtils.isNotEmpty(wxTaskFinish.getReceiveCode())) {
				queryRule.addEqual("receiveCode", wxTaskFinish.getReceiveCode());
			}
			if (StringUtils.isNotEmpty(wxTaskFinish.getReceiveName())) {
				queryRule.addEqual("receiveName", wxTaskFinish.getReceiveName());
			}
			if (!"-1".equals(wxTaskFinish.getStatus())) {
				if("".equals(wxTaskFinish.getStatus())){
					queryRule.addIsNull("status");
				}else{
					queryRule.addEqual("status", wxTaskFinish.getStatus());
				}
			}
		}
		queryRule.addDescOrder("createTime");
		return wxTaskFinishDao.find(queryRule, pageNo, pageSize);
	}


	@Override
	public void addWxTaskFinish(WxTaskFinish wxTaskFinish) {
		wxTaskFinishDao.save(wxTaskFinish);
		wxTaskService.deleteWxTaskById(wxTaskFinish.getFinishId());
	}

	@Override
	public WxTaskFinish findWxTaskFinishById(Integer finishId) {
		return wxTaskFinishDao.get(WxTaskFinish.class, finishId);
	}
	

	@Override
	public WxTaskFinish findWxTaskFinishByCaseId(String caseId) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("caseId", caseId);
		List<WxTaskFinish> tasks = wxTaskFinishDao.find(queryRule);
		return tasks.isEmpty() ? null : tasks.get(0);
	}

}
