package com.picc.qp.service.spring;

import ins.framework.common.QueryRule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.qp.dao.QpTCOMDictionaryDaoHibernate;
import com.picc.qp.schema.model.QpTCOMDictionary;
import com.picc.qp.schema.model.QpTCOMDictionaryId;
import com.picc.qp.service.facade.IQpTCOMDictionaryService;

@Service("iQpTCOMDictionaryService")
public class QpTCOMDictionaryServiceImpl implements IQpTCOMDictionaryService {
    
	@Autowired
    private QpTCOMDictionaryDaoHibernate qpTCOMDictionaryDao;
    @Override
    public List<QpTCOMDictionary> getImagePath(String configType) throws Exception {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("id.configType", configType);
		List<QpTCOMDictionary> lists = qpTCOMDictionaryDao.find(queryRule);
		//---------------------------------------
//		String path = "";
//		QpTCOMDictionaryId id = new QpTCOMDictionaryId();
//		id.setConfigType(configType);
//		QpTCOMDictionary qpTCOMDictionary = this.findQpTCOMDictionaryByPK(id);
//		if(qpTCOMDictionary!=null){
//			path = qpTCOMDictionary.getValue();
//		}
		return lists;
    }
	
	/**
	 * 根据主键对象QpTCOMDictionaryId获取QpTCOMDictionary信息
	 * @param QpTCOMDictionaryId
	 * @return QpTCOMDictionary
	 * @throws Exception
	 */
    @Override
	public QpTCOMDictionary findQpTCOMDictionaryByPK(QpTCOMDictionaryId id)
			throws Exception {
		// TODO Auto-generated method stub
		return qpTCOMDictionaryDao.get(QpTCOMDictionary.class, id);
	}
    
    @Override
    public void save(QpTCOMDictionary qpTCOMDictionary) throws Exception {
    	qpTCOMDictionaryDao.save(qpTCOMDictionary);
    }

}
