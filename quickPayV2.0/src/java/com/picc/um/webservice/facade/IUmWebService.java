package com.picc.um.webservice.facade;

import javax.jws.WebService;

@WebService
public interface IUmWebService {
	/**
	 * 根据用户代码查询用户对象并封装成炎黄AWS的userModel对象
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String findAwsUserModel(String code) throws Exception;
	
	
	
	
	
	
	
	/**********************未用到的接口************************/
	/**
	 * 根据机构、岗位、人员等的id解析并返回其相应的名称，展现在AWS授权列表中
	 * @param codeId 字符串：[{"id":"xx","type":"0","id":"xx","type":"0"}]
	 * @return 返回带有名称的字符串和类型名称的字符串：[{"id":"xx","type":"0","typeName":"组织机构","name":"中国人保"}]
	 * @throws Exception
	 */
	public String findCodeNameByCodeId(String codeId) throws Exception;
	
	
	/**
	 * 判断用户是否在授权的AC帐户中，权限取的交集
	 * @param userCode	用户代码
	 * @param acCode	授权AC帐户代码，格式为：{type1:[id11,id12],type2:[id21,id22]}
	 * @return			是否包含
	 * @throws Exception
	 */
	public boolean checkUserByACCode(String userCode,String acCode) throws Exception;
	
	
	/**
	 * 根据用户代码 查询该用户的机构根节点
	 * @param userCode   用户代码
	 * @param isExcept   是否为除外机构
	 * @return
	 */
	public String findRootCompany(String userCode) throws Exception;
	
	
	/**
	 * 根据父节点机构代码 查询机构子节点
	 * @param upperComCode  父机构代码
	 * @param isExcept      是否为除外机构
	 * @return
	 */
	public String findSubCompany(String upperComCode) throws Exception;
	
	
	/**
	 * 根据机构代码查询机构下的人员
	 * @param comCodes
	 * @return
	 * @throws Exception
	 */
	public String findUserByComCode(String comCode) throws Exception;
	
	
	/**
	 * 根据授权类型解析授权类型下的所有人员代码
	 * @param chooseStr 0:xx|xx 1:xx|xx
	 * @return  返回形式为："用户代码1<用户姓名1> 用户代码2<用户姓名2>"
	 * @throws Exception
	 */
	public String parseParticipants(String chooseStr) throws Exception;
	
	
	/**
	 * 根据用户代码和密码去验证用户登录
	 * @param userCode 用户代码
	 * @param password 用户密码
	 * @return 返回是否验证成功的布尔值
	 * @throws Exception
	 */
	public boolean checkLogin(String userCode,String password) throws Exception;
	
	
	/**
	 * 根据用户代码 返回其所配置的菜单信息
	 * @param userCode	用户代码
	 * @return			JSON格式菜单字符串
	 * @throws Exception
	 */
	public String getMenuStrByUserCode(String userCode) throws Exception;
	
}
