package com.picc.qp.service.facade;

import java.util.List;

import com.picc.qp.schema.model.QpTCOMDictionary;
import com.picc.qp.schema.model.QpTCOMDictionaryId;

public interface IQpTCOMDictionaryService {
    /**
	 * 获取字典存取的image存放地址
	 * @return
	 */
	public List<QpTCOMDictionary> getImagePath(String configType) throws Exception;
	
	/**
	 * 根据主键对象QpTCOMDictionaryId获取QpTCOMDictionary信息
	 * @param QpTCOMDictionaryId
	 * @return QpTCOMDictionary
	 * @throws Exception
	 */
	public QpTCOMDictionary findQpTCOMDictionaryByPK(QpTCOMDictionaryId id) throws Exception;
	
	public void save(QpTCOMDictionary qpTCOMDictionary) throws Exception;
}
