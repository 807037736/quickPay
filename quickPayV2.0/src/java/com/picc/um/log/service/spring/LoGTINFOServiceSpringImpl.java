/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.log.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.QueryRuleHelper;
import com.picc.um.log.dao.LoGTINFODaoHibernate;
import com.picc.um.log.schema.model.LoGTINFO;
import com.picc.um.log.schema.model.LoGTINFOId;
import com.picc.um.log.service.facade.ILoGTINFOService;

/**
 * 日志信息自定义接口实现类
 * @author 杨联
 */
@Service("loGTINFOService")
public class LoGTINFOServiceSpringImpl implements ILoGTINFOService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private LoGTINFODaoHibernate loGTINFODao;
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 根据主键对象LoGTINFOId获取LoGTINFO信息
	 * @param LoGTINFOId
	 * @return LoGTINFO
	 */
	public LoGTINFO findLoGTINFOByPK(LoGTINFOId id) throws Exception{
			
			return loGTINFODao.get(LoGTINFO.class,id);
	}

	/**
	 * 根据loGTINFO和页码信息，获取Page对象
	 * @param loGTINFO
	 * @param pageNo
	 * @param pageSize
	 * @return 包含LoGTINFO的Page对象
	 */
	public Page findByLoGTINFO(LoGTINFO loGTINFO, int pageNo, int pageSize) throws Exception{
		
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		queryRule=QueryRuleHelper.generateQueryRule(loGTINFO);

		//根据loGTINFO生成queryRule
		
		return loGTINFODao.find(queryRule, pageNo, pageSize);
	}
	
	public Page findByLoGTINFO(LoGTINFO loGTINFO,Date date,int pageNo, int pageSize) throws Exception{
		
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		if (!("").equals(loGTINFO.getUserCode())
				&& !(loGTINFO.getUserCode() == null)) {
			queryRule.addEqual("userCode", loGTINFO.getUserCode());
		}
		if (!("").equals(loGTINFO.getUserName())
				&& !(loGTINFO.getUserName() == null)) {
			queryRule.addEqual("userName", loGTINFO.getUserName());
		}
		if (!("").equals(loGTINFO.getOperateTypeName())
				&& !(loGTINFO.getOperateTypeName() == null)) {
			queryRule.addEqual("operateTypeName", loGTINFO.getOperateTypeName());
		}
		
		if(loGTINFO.getOperateTime()!=null)
			queryRule.addGreaterEqual("operateTime", loGTINFO.getOperateTime());
		if(date!=null){
			//查询区间段后者的数据 在填入的时间上面 加上1天
			//Between start and end 应在00:00:00
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, 1);
			queryRule.addLessEqual("operateTime", cal.getTime());
		}
//			queryRule.addLessEqual("operateTime", date);
		
//		QueryRuleHelper.addBetweenRule(queryRule, "operateTime", loGTINFO.getOperateTime(), date);
		
		//根据loGTINFO生成queryRule
		
		return loGTINFODao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新LoGTINFO信息
	 * @param LoGTINFO
	 */
	public void updateLoGTINFO(LoGTINFO loGTINFO) throws Exception{
			
			loGTINFODao.update(loGTINFO);
	}

	/**
	 * 保存LoGTINFO信息
	 * @param LoGTINFO
	 */
	public void saveLoGTINFO(LoGTINFO loGTINFO) throws Exception{
			
			loGTINFODao.save(loGTINFO);
	}

	/**
	 * 根据主键对象，删除LoGTINFO信息
	 * @param LoGTINFOId
	 */
	public void deleteByPK(LoGTINFOId id) throws Exception{
			
			loGTINFODao.deleteByPK(LoGTINFO.class,id);
	}
}
