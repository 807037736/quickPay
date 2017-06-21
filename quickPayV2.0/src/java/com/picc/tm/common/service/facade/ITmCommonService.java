package com.picc.tm.common.service.facade;

public interface ITmCommonService {

	/**
	 * 通过传入交互的通讯号以及交互类型判断是否在免打扰名单之内
	 *@author    moxg    2013-9-3
	 * @param interactCode
	 * @param interactType
	 * @return 
	 */
	public boolean isInUnDisturbList(String interactCode,String interactType);
	


	/**
	 * 通过传入交互的通讯号以及交互类型判断是否在免打扰名单之内
	 *@author    moxg    2013-9-10
	 * @param interactCode
	 * @param interactType
	 * @param CustId
	 * @param taskCode
	 * @param processCode
	 * @return
	 */
	public boolean isInUnDisturbList(String interactCode,String interactType,String custId,String taskCode,String processCode,String comId) throws Exception;

	/**
	 * 通过传入交互的通讯号以及交互类型判断是否在免打扰名单之内
	 *@author    moxg    2013-9-10
	 * @param interactCode
	 * @param interactType
	 * @param CustId
	 * @param taskCode
	 * @param processCode
	 * @return
	 */
	public String checkInUndisturb(String interactCode,String interactType,String custId,String taskCode,String processCode,String comId);

	public String checkSelectSQL(String inputsql) throws Exception;
	
	public void doSmsReplyCallBackJob(String messageId,String comId)throws Exception;
	
//	public void testfind()throws Exception;
}
