package com.picc.platform.dms.service.facade;

import ins.framework.common.Page;

import java.util.List;
import java.util.Map;

import com.picc.platform.dms.schema.vo.Code;

/**
 * 代码翻译服务
 */
public interface CodeService {
	public String USERCODE = "UserCode";
	public String COMCODE = "ComCode";
	public String OPERATIONSYMBOL = "OperationSymbol";
	public String VALIDFLAG_Y = "1";
	public String VALIDFLAG_N = "0";

	/**
	 * 翻译代码<br>
	 * 支持的代码类型有：<br>
	 * 
	 * <pre>
	 * UserCode 员工代码
	 * ComCode  机构代码
	 * </pre>
	 * 
	 * 例如以下代码，查询性别代码为1的中文名称
	 * 
	 * <pre>
	 * String value = codeService.translateCode(&quot;SexCode&quot;, &quot;1&quot;, &quot;DAA&quot;, &quot;C&quot;);
	 * </pre>
	 * @param codeType
	 *            代码类型
	 * @param codeCode
	 *            代码
	 * @param riskCode
	 *            险种代码
	 * @param language
	 *            语种(C:中文/E:英文)
	 * @return 代码名称
	 */
	public String translateCode(String codeType, String codeCode, String riskCode, String language);

	/**
	 * 代码服务<br>
	 * 支持的代码类型有：<br>
	 * 例如以下代码，查询性别代码为1的中文名称
	 * 
	 * <pre>
	 * Map&lt;String, String&gt; codes = codeService.listCodes(&quot;SexCode&quot;, &quot;DAA&quot;, &quot;C&quot;);
	 * </pre>
	 * @param codeType
	 *            代码类型
	 * @param riskCode
	 *            险种代码
	 * @param language
	 *            语种(C:中文/E:英文)
	 * @return 代码名称
	 */
	public Map<String, String> listCodes(String codeType, String riskCode, String language);

	/**
	 * 代码服务
	 * @param codeType
	 *            代码类型
	 * @param riskCode
	 *            险种
	 * @param language
	 *            语种
	 * @param otherCondition
	 *            其它条件,实体别名为a,例如 a.id.codecode in('01','02','03')
	 * @return
	 */
	public Map<String, String> listCodes(String codeType, String riskCode, String language, String otherCondition);

	/**
	 * 代码服务<br>
	 * 支持的代码类型有：<br>
	 * 例如以下代码，查询性别代码为1的中文名称
	 * 
	 * <pre>
	 * List&lt;Code&gt; codes = codeService.listCodeList(&quot;SexCode&quot;, &quot;DAA&quot;, &quot;C&quot;);
	 * </pre>
	 * @param codeType
	 *            代码类型
	 * @param riskCode
	 *            险种代码
	 * @param language
	 *            语种(C:中文/E:英文)
	 * @return 代码名称
	 */
	public List<Code> listCodeList(String codeType, String riskCode, String language);

	/**
	 * 代码服务<br>
	 * 支持的代码类型有：<br>
	 * 例如以下代码，查询性别代码为1的中文名称
	 * 
	 * <pre>
	 * List&lt;Code&gt; codes = codeService.listCodeList(&quot;SexCode&quot;, &quot;DAA&quot;, &quot;C&quot;, &quot;asc&quot;);
	 * </pre>
	 * @param codeType
	 *            代码类型
	 * @param riskCode
	 *            险种代码
	 * @param language
	 *            语种(C:中文/E:英文)
	 * @param order
	 *            排序(asc:升序/desc:降序)
	 * @return 代码名称
	 */
	public List<Code> listOrderCodeList(String codeType, String riskCode, String language, String order);

	/**
	 * 代码选择服务<br>
	 * @param codeType
	 *            代码类型
	 * @param riskCode
	 *            险种代码
	 * @param language
	 *            语种(C:中文/E:英文)
	 * @param order
	 *            排序(asc:升序/desc:降序)
	 * @param matches
	 *            匹配字符串
	 * @param typeParam
	 *            過濾查詢的參數
	 * @return 代码List
	 */
	public Page listCodeSelect(String codeType, String riskCode, String language, String matches, int pageNo,
			int pageSize, String userCode, String typeParam, String extraCond);
	
	/**
	 * 获取工作流程经过的不包括当前节点的节点名称（tasknode）
	 * @param query 匹配字符串
	 * @param currentNodeName 当前节点名称
	 * @return
	 */
	public List listNodes(String query,String currentNodeName);
	
	/**
	 * @param codeType
	 *            代码类型
	 * @param codeCode
	 *            代码
	 * @param riskCode
	 *            险种代码
	 * @param language
	 *            语种(C:中文/E:英文)
	 * @return 代码名称
	 */
	public String translateUserCode(String codeType, String codeCode, String riskCode, String language);
	/**
	 * @param codeType
	 *            代码类型
	 * @param codeCode
	 *            代码
	 * @param riskCode
	 *            险种代码
	 * @param language
	 *            语种(C:中文/E:英文)
	 * @return 代码名称
	 */
	public String translateComCode(String codeType, String codeCode, String riskCode, String language)throws Exception ;

}
