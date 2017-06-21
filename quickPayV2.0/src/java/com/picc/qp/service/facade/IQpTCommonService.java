package com.picc.qp.service.facade;

import java.util.List;

import com.picc.qp.schema.vo.QpSelectDataVo;

public interface IQpTCommonService{

	/**
	 * 获取下拉框数据
	 * @param codeType
	 * @return
	 * @throws Exception
	 */
	public List<QpSelectDataVo> getSelectData(String codeType) throws Exception;
	
	/**
	 * 通过codeType，codeCode取codeCName
	 * @param codeType
	 * @param codeCode
	 * @return
	 * @throws Exception
	 */
	public String getSelectCodeName(String codeType,String codeCode) throws Exception;
	/**
	 * 取codeCName
	 * @param codeType
	 * @param list
	 * @return
	 */
	public String getCodeCName(String codeType,List<QpSelectDataVo> list);
	
}
