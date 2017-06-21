/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.log.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.um.log.dao.LoGTTYPEDaoHibernate;
import com.picc.um.log.schema.model.LoGTTYPE;
import com.picc.um.log.schema.model.LoGTTYPEId;
import com.picc.um.log.service.facade.ILoGTTYPEService;

/**
 * 日志类型自定义接口实现类
 * @author 杨联
 */
@Service("loGTTYPEService")
public class LoGTTYPEServiceSpringImpl implements ILoGTTYPEService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private LoGTTYPEDaoHibernate loGTTYPEDao;

	/**
	 * 根据主键对象LoGTTYPEId获取LoGTTYPE信息
	 * @param LoGTTYPEId
	 * @return LoGTTYPE
	 */
	public LoGTTYPE findLoGTTYPEByPK(LoGTTYPEId id) throws Exception{
			
			return loGTTYPEDao.get(LoGTTYPE.class,id);
	}

	/**
	 * 根据loGTTYPE和页码信息，获取Page对象
	 * @param loGTTYPE
	 * @param pageNo
	 * @param pageSize
	 * @return 包含LoGTTYPE的Page对象
	 */
	public Page findByLoGTTYPE(LoGTTYPE loGTTYPE, int pageNo, int pageSize) throws Exception{
		
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据loGTTYPE生成queryRule
		
		return loGTTYPEDao.find(queryRule, pageNo, pageSize);
	}
	
	public List<LoGTTYPE> findAllLogTypeList()throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		
		return loGTTYPEDao.find(queryRule);
		
	}

	/**
	 * 更新LoGTTYPE信息
	 * @param LoGTTYPE
	 */
	public void updateLoGTTYPE(LoGTTYPE loGTTYPE) throws Exception{
			
			loGTTYPEDao.update(loGTTYPE);
	}

	/**
	 * 保存LoGTTYPE信息
	 * @param LoGTTYPE
	 */
	public void saveLoGTTYPE(LoGTTYPE loGTTYPE) throws Exception{
			
			loGTTYPEDao.save(loGTTYPE);
	}

	/**
	 * 根据主键对象，删除LoGTTYPE信息
	 * @param LoGTTYPEId
	 */
	public void deleteByPK(LoGTTYPEId id) throws Exception{
			
			loGTTYPEDao.deleteByPK(LoGTTYPE.class,id);
	}
}
