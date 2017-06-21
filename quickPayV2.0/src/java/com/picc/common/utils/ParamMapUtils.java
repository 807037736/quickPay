package com.picc.common.utils;

import ins.framework.common.DateTime;
import ins.framework.utils.BeanUtils;
import ins.framework.utils.StringUtils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 该工具类用于处理 组件化视图框架 中前台参数传到后台的处理
 * @author yaowenfeng
 *
 */
public class ParamMapUtils {
	
	/**
	 * Logger for this class
	 */
	protected static final Logger logger = LoggerFactory.getLogger(ParamMapUtils.class);
	
	/**
	 * 得到Integer类型的参数值
	 * 
	 * @param paramMap
	 *            参数Map
	 * @param paramName
	 *            参数名
	 * @param defalutValue
	 *            默认值
	 * @return Integer类型的值，如果转换失败返回传入的默认值
	 */
	public static Integer getIntegerParameter(Map paramMap, String paramName,
			Integer defalutValue) {
		String valueStr = getParameter(paramMap, paramName);
		Integer value = null;
		if (valueStr != null) {
			try {
				value = new Integer(valueStr);
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		if (value == null) {
			value = defalutValue;
		}
		return value;
	}

	/**
	 * 得到Long类型的参数值
	 * 
	 * @param paramMap
	 *            参数Map
	 * @param paramName
	 *            参数名
	 * @param defalutValue
	 *            默认值
	 * @return Long类型的值，如果转换失败返回传入的默认值
	 */
	public static Long getLongParameter(Map paramMap, String paramName,
			Long defalutValue) {
		String valueStr = getParameter(paramMap, paramName);
		Long value = null;
		if (valueStr != null) {
			try {
				value = new Long(valueStr);
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		if (value == null) {
			value = defalutValue;
		}
		return value;
	}

	/**
	 * 得到String类型的参数值
	 * 
	 * @param paramMap
	 *            参数Map
	 * @param paramName
	 *            参数名
	 * @return String类型的值
	 */
	public static String getParameter(Map paramMap, String paramName) {
		String[] values = getParameterValues(paramMap, paramName);
		if (values != null && values.length > 0) {
			return values[0];
		}
		return null;
	}

	/**
	 * 设置String类型的参数
	 * 
	 * @param paramMap
	 *            参数Map
	 * @param paramName
	 *            参数名
	 * @param paramValue
	 *            String类型的值
	 */
	public static void setParameter(Map paramMap, String paramName,
			String paramValue) {
		paramMap.put(paramName, new String[] { paramValue });
	}

	/**
	 * 得到String数组类型的参数值
	 * 
	 * @param paramMap
	 *            参数Map
	 * @param paramName
	 *            参数名
	 * @return String数组类型的值
	 */
	public static String[] getParameterValues(Map paramMap, String paramName) {
		String[] values = (String[]) paramMap.get(paramName);
		return values;
	}

	/**
	 * @param paramMap
	 *            参数Map
	 * @param cl
	 *            要封装的对象Class。
	 * @param inputPrefix
	 *            输入域的前缀，一般是对象的类名。
	 * @throws Exception
	 *             如果通过cl参数创建对象失败，将抛出异常。
	 */
	public static Object generateObject(Map paramMap, Class cl,
			String inputPrefix)throws Exception {
		return generateObject(paramMap,cl,inputPrefix,0);
	}
	
	/**
	 * @param paramMap
	 *            参数Map
	 * @param cl
	 *            要封装的对象Class。
	 * @param inputPrefix
	 *            输入域的前缀，一般是对象的类名。、
	 * @param index 根据序号，获取序号对应的对象，从0开始          
	 * @throws Exception
	 *             如果通过cl参数创建对象失败，将抛出异常。
	 */
	public static Object generateObject(Map paramMap, Class cl,
			String inputPrefix,Integer index)throws Exception {
		Object object = null;
		Object subObject = null;
		try {
			object = cl.newInstance();
			// 获取所有set方法
			List methodList = BeanUtils.getSetter(cl);
			int methodLength = methodList.size();
			Object[] obj = new Object[] {""}; 
			StringBuffer stripped = new StringBuffer();
			Method method = null;
			String fieldName = null;
			Object[] parameterValues = null;
			Object parameterValue = null;
			Class parameterType = null;
			String valueString = null;
			for (int i = 0; i < methodLength; i++) {
				method = (Method) methodList.get(i);
				fieldName = method.getName().substring(3);
				if("setId".equalsIgnoreCase(method.getName())){//如果遍历到setId,使用inputPrefix+"id."遍历参数
					subObject =  generateObject(paramMap,method.getParameterTypes()[0],inputPrefix+"id.",index);
					if(subObject!=null){
						method.invoke(object, new Object[] {subObject});
					}
					continue;
				}
				stripped.setLength(0);
				stripped.append(fieldName);		//stripped存储实际的Input字段名
				// 如果有前缀则Input字段名添加前缀
				if (inputPrefix != null && inputPrefix.trim().length() > 0) {
					stripped.setLength(0);
					stripped.append(inputPrefix);
					stripped.append(StringUtils.lowerCaseFirstChar(fieldName));
				}
				parameterValues = ParamMapUtils.getParameterValues(paramMap,
						stripped.toString());
				if(parameterValues ==null||parameterValues.length<0){
					stripped.setLength(0);
					stripped.append(inputPrefix);
					stripped.append(StringUtils.upperCaseFirstChar(fieldName));
					parameterValues = ParamMapUtils.getParameterValues(paramMap,
							stripped.toString());
				}

				try {
					parameterType = method.getParameterTypes()[0];
					if(parameterValues!=null&&parameterValues.length>0&&parameterValues.length>index){
						valueString = (String) parameterValues[index];
						
						if (valueString.equalsIgnoreCase("null")) {
							valueString = "";
						}
						valueString = StringUtils.rightTrim(valueString);

						// 日期值的特殊处理
						if (parameterType == Timestamp.class) {

							DateTime d = ToolsUtils.getDateTime(valueString);
							if (d == null) {
								parameterValue = null;
							} else {
								parameterValue = new Timestamp(d.getTime());
							}
						} else if (parameterType == Date.class) {

							DateTime d = ToolsUtils.getDateTime(valueString);
							if (d == null) {
								parameterValue = null;
							} else {
								parameterValue = d;
							}
						} else if (parameterType == Double.class) {
							if (valueString == null || valueString.equals("")) {
								parameterValue = null;
							} else {
								parameterValue = Double
										.valueOf(correctNumber(valueString));
							}
						} else if (parameterType == Long.class) {
							if (valueString == null || valueString.equals("")) {
								parameterValue = null;
							} else {
								parameterValue = Long
										.valueOf(correctNumber(valueString));
							}
						} else if (parameterType == Integer.class) {
							if (valueString == null || valueString.equals("")) {
								parameterValue = null;
							} else {
								parameterValue = Integer
										.valueOf(correctNumber(valueString));
							}
						} else if (parameterType == BigDecimal.class) {
							if (valueString == null || valueString.equals("")) {
								parameterValue = null;
							} else {
								parameterValue = new BigDecimal(correctNumber(valueString));
							}
						} else if (parameterType == Boolean.class) {
							if (valueString == null || valueString.equals("")) {
								parameterValue = null;
							} else {
								parameterValue = new Boolean(valueString);
							}
						}else {
							parameterValue = valueString;
						}
						method.invoke(object, new Object[] { parameterValue });
					}else{
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("", e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return object;
	}
	
	/**
	 * 按起始行号为0获取对象集合
	 * @param paramMap
	 *            参数Map
	 * @param cl
	 *            要封装的对象Class。
	 * @param inputPrefix
	 *            输入域的前缀，一般是对象的类名。
	 * @return 封装好的对象list。
	 * @throws Exception
	 *             如果通过cl参数创建对象失败，将抛出异常。
	 */
	public static List generateList(Map paramMap, Class cl, String inputPrefix)throws Exception{
		return generateList(paramMap,cl,inputPrefix,0);
	}

	/**
	 * @param paramMap
	 *            参数Map
	 * @param cl
	 *            要封装的对象Class。
	 * @param inputPrefix
	 *            输入域的前缀，一般是对象的类名。
	 * @param startIndex
	 *            要封装的多行输入域的起始行号。 <br>
	 *            对于多行输入域来讲，一般起始行号是1，因为第0行多行输入域的隐藏部分("_Data"部分)；对于普通的多行输入的情况，起始行号是0。
	 *            通过这个参数可以自定义起始行号。
	 * @return 封装好的对象集合。
	 * @throws Exception
	 *             如果通过cl参数创建对象失败，将抛出异常。
	 */
	public static List generateList(Map paramMap, Class cl, String inputPrefix,
			int startIndex)throws Exception {
		List resultList = new ArrayList();
		// 获取所有set方法
		List methodList = BeanUtils.getSetter(cl);
		// 存储所有Object对象的Map,Key为序号
		TreeMap treeMap = new TreeMap();
		if (startIndex < 0) {
			startIndex = 0;
		}
		int methodLength = methodList.size();
		StringBuffer stripped = new StringBuffer();
		Method method = null;
		String fieldName = null;
		Object[] parameterValues = null;
		Object object = null;
		Class parameterType = null;
		for (int i = 0; i < methodLength; i++) {
			method = (Method) methodList.get(i);
			fieldName = method.getName().substring(3);
			stripped.setLength(0);
			stripped.append(fieldName);
			if (inputPrefix != null && inputPrefix.trim().length() > 0) {
				stripped.setLength(0);
				stripped.append(inputPrefix);
				stripped.append(StringUtils.lowerCaseFirstChar(fieldName));
			}
			parameterValues = ParamMapUtils.getParameterValues(paramMap,
					stripped.toString());
			//只要判断其中一个字段在页面上有值，则直接调用generateObject生成对象，并增加到list中
			if(parameterValues!=null&&parameterValues.length>0&&parameterValues.length>=startIndex){
				for (int j = startIndex; j < parameterValues.length; j++) {
					try {
						
						object = generateObject(paramMap, cl,inputPrefix,j);
						resultList.add(object);
					} catch (Exception e) {
						throw new IllegalStateException(e);
					}
				}
				break;
			}
		}
		return resultList;
	}
	
	
	public static String correctNumber(String numberString) {
		String value = numberString;
		if (value != null && !value.equals("")) {
			value = StringUtils.replace(value, ",", "");
		}
		return value;
	}

	
}
