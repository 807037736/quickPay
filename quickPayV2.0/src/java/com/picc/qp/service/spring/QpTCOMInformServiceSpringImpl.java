package com.picc.qp.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.utils.ToolsUtils;
import com.picc.qp.dao.QpTCOMInformDaoHibernate;
import com.picc.qp.schema.model.QpTCOMInform;
import com.picc.qp.service.facade.IQpTCOMInformService;
@Service("qpTCOMInformService")
public class QpTCOMInformServiceSpringImpl implements IQpTCOMInformService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	QpTCOMInformDaoHibernate qpTCOMInformDao;
	
	/**
	 * 根据查询内容查找 QpTCOMInform
	 * @param QpTCOMInform
	 * @return List<QpTCOMInform>
	 */
	@Override
	public List<QpTCOMInform> findByQpTCOMInform(QpTCOMInform qpTCOMInform)
			throws Exception {
		QueryRule queryRule = QueryRule.getInstance();
		if(ToolsUtils.notEmpty(qpTCOMInform.getTitle())){
			queryRule.addEqual("title", qpTCOMInform.getTitle());
		}
		if(qpTCOMInform.getInformId() != 0){
			queryRule.addEqual("informId", qpTCOMInform.getInformId());
		}
		return qpTCOMInformDao.find(queryRule);
	}
	
	/**
	 * 根据QpTCOMInform和页码信息，获取Page对象
	 * @param QpTCOMInform
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTCOMInform的Page对象
	 */
	@Override
	public Page findQpTCOMInformByQpTCOMInform(QpTCOMInform qpTCOMInform,
			int pageNo, int pageSize) throws Exception {
		QueryRule queryRule = QueryRule.getInstance();
		if(ToolsUtils.notEmpty(qpTCOMInform.getTitle())){
			queryRule.addEqual("title", qpTCOMInform.getTitle());
		}
		if(ToolsUtils.notEmpty(qpTCOMInform.getCreator())){
			queryRule.addEqual("creator", qpTCOMInform.getCreator());
		}
		return qpTCOMInformDao.find(queryRule, pageNo, pageSize);
	}
	
	/**
	 * 根据state查找 QpTCOMInform
	 * @param state
	 * @return List<QpTCOMInform>
	 */
	@Override
	public List<QpTCOMInform> findQpTCOMInformByState(String state) throws Exception {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("state", state);
		if(!qpTCOMInformDao.find(queryRule).isEmpty()){
			return qpTCOMInformDao.find(queryRule);
		}
		return null;
	}
	
	/**
	 * 添加QpTCOMInform信息
	 * @param QpTICCompany
	 */
	@Override
	public void addQpTCOMInform(QpTCOMInform qpTCOMInform) throws Exception {
		qpTCOMInformDao.save(qpTCOMInform);
	}
	
	/**
	 * 删除QpTCOMInform信息
	 * @param qpTCOMInform
	 */
	@Override
	public void deleteQpTCOMInform(int informId) throws Exception {
		qpTCOMInformDao.deleteByPK(QpTCOMInform.class,informId);
	}
	
	/**
	 * 更新QpTCOMInform信息
	 * @param qpTCOMInform
	 */
	@Override
	public void updateQpTCOMInform(QpTCOMInform qpTCOMInform) throws Exception {
		qpTCOMInformDao.update(qpTCOMInform);
	}
	
	/**
	 * 手写SQL 根据state删除QpTICCompany
	 * @param 状态state
	 */
	@Override
	public void deleteBySQL(String state) throws Exception {
		String sql = "delete from qp_t_com_inform where state='"+state+"'";
		qpTCOMInformDao.execute(sql, null);
	}
	
	/**
	 * 手写SQL 根据state更新QpTICCompany 中的state为2
	 * @param 状态state
	 */
	@Override
	public void updateBySQL(String state) throws Exception {
		String sql = "update qp_t_com_inform set state=2 where state='"+state+"'";
		qpTCOMInformDao.execute(sql, null);
	}
	
	/**
	 * 手写SQL 根据主键更新QpTICCompany 中的state为2
	 * @param informId
	 */
	@Override
	public void updateByPk(int informId) throws Exception {
		String sql = "update qp_t_com_inform set state= ? where informId= ? ";
		qpTCOMInformDao.execute(sql,new Object[]{"2",informId});
	}
	
	
	/**
	 * 判断endTime是否超过nowTime。
	 * @param nowTime
	 * @param endTime
	 */
	@Override
	public boolean isTimeOut(Date nowTime,String endTime) throws Exception {
		endTime = endTime.substring(0,endTime.indexOf('.'));
		Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
		if(nowTime.getTime()>end.getTime()){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 选出startTime离当前时间最接近的QpTCOMInform。
	 * @param List<QpTCOMInform>
	 */
	@Override
	public List<QpTCOMInform> getNearTimeInform(List<QpTCOMInform> informs)
			throws Exception {
		Date nowTime = new Date();
		for (int i = 0; i < informs.size(); i++) {
			String startTime = informs.get(i).getStartTime();
			startTime = startTime.substring(0,startTime.indexOf('.'));
			Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime);
			informs.get(i).setCompareTime(nowTime.getTime() - start.getTime());
		}
		
		int size = informs.size();
		for (int i = 0; i < size-1; i++) {
			
			for (int u = 0; u < size-1; u++) {
				if(informs.get(u).getCompareTime()>informs.get(u+1).getCompareTime()){
					QpTCOMInform temp = informs.get(u);
					informs.set(u, informs.get(u+1));
					informs.set(u+1, temp);
				}
			}
		}
		if(!informs.isEmpty() && informs.size()>0){
			return informs;
		}
		return null;
	}


}
