package com.picc.um.service.facade;

import javax.jws.WebService;

@WebService
public interface IUserService {
	
	/**
	 * 根据用户代码（新代码或旧代码）获取用户信息并封装成炎黄的userModel
	 * @param userCode
	 * @return 返回炎黄用户model的json格式
	 * @throws Exception
	 */
	public String findAwsUserByUserCode(String userCode) throws Exception;
	
	
	/**
	 * 根据用户代码和密码去验证用户登录
	 * @param userCode 用户代码
	 * @param password 用户密码
	 * @return 返回是否验证成功的布尔值
	 * @throws Exception
	 */
	public boolean checkLogin(String userCode,String password) throws Exception;
	
	/**
	 * 判断用户是否在授权的AC帐户中，权限取的交集
	 * @param userCode	用户代码
	 * @param acCode	授权AC帐户代码
	 * @return			是否包含
	 * @throws Exception
	 */
	public boolean checkUserByACCode(String userCode,String acCode) throws Exception;
	
	
	/**
	 * 根据用户ID 返回其所配置的菜单信息
	 * @param userCode	用户代码
	 * @return			JSON格式菜单字符串
	 * @throws Exception
	 */
	public String getMenuStrByUserCode(String userCode) throws Exception;
	
	
	/**
	 * 根据机构、岗位、人员等的id获取其相应的名称
	 * @param codeId 字符串：[{"id":"xx","type":"0","id":"xx","type":"0"}]
	 * @return 返回带有名称的字符串和类型名称的字符串：[{"id":"xx","type":"0","typeName":"组织机构","name":"中国人保"}]
	 * @throws Exception
	 */
	public String findCodeNameByCodeId(String codeId) throws Exception;
	
}
