/**
 * 此类为工具类，把实体对象转换成QueryRule对象。
 * @author RenXiaoPeng
 * Last Modified RongXiaozheng 2013-07-29
 */

package com.picc.common;

import ins.framework.common.QueryRule;
import ins.framework.utils.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class QueryRuleHelper {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(QueryRuleHelper.class);
	/**
	 * SQL条件中禁止使用的字符串(全为小写)
	 */
	private static final String[] FORBID_STRING = new String[] { " or " };
	/**
	 * SQL中的操作符
	 */
	public static final String[] OPERATE_SIGN = new String[] { ">=", "<=", "!=", "=", ">", "<" };// 从长到短的顺序
	/**
	 * SQL条件中如果仅包含下列字符将拼成等于而不会做模糊查询。
	 */
	private static final String[] FILTERED_STRING = new String[] { " ", "%", "*", "?" };
	/**
	 * 反SQL注入Key
	 */
//	private static final String[] EXCEPT_KEY = new String[] { "insert ", "update ", "delete ", "select ", "create ", "drop ", "alter ", "truncate ", "use ", "call " };

	private static SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	
	private static Map<String, String> ignoreNameMap = new HashMap<String, String>();
	
	/**
	 * 下拉框全选
	 */
	private static int SELECT_ALL_VALUE = -1;
	
	private static String SELECT_ALL_VALUE_STR = "-1";
	
	/**
	 * 根据obj对象自动生成queryRule对象
	 * @param obj
	 * @return
	 */
	public static QueryRule generateQueryRule(Object obj) {
		return generateQueryRule(obj,null);
	}

	/**
	 * 根据obj对象自动生成queryRule对象
	 * @param obj
	 * @param tableAlias
	 * @return
	 */
	public static QueryRule generateQueryRule(Object obj,String tableAlias) {
		QueryRule queryRule = QueryRule.getInstance();
		String preFieldName = "";
		if(tableAlias!=null&&!"".equals(tableAlias)){
			preFieldName = tableAlias + ".";
		}
		
		Class<?> classType = obj.getClass();
		
		Method[] methods = classType.getDeclaredMethods();
		Object val = null;
		Class<?> type;
		
		for (Method method : methods) {
			String methodName = method.getName();
			if (methodName.startsWith("get")) {
				// 根绝函数名称获取列名，此处hibernate会根据名称做对应
				String fieldName = methodName.substring(3,4).toLowerCase() + methodName.substring(4);
				type = method.getReturnType();
				try {
					val = method.invoke(obj);
				} catch (Exception e) {
					logger.error("ERROR in getting object value: ", e);
				}
				if (val == null) {
					continue;
				} else {
					if(type.getClassLoader()!=null){ 		//解决ID字段无法映射查询的问题
						//直接实例化返回值
						try {
							for(Field field : type.getDeclaredFields()){
								if(!"serialVersionUID".equals(field.getName())){
									field.setAccessible(true);
									if(field.get(val)!=null){
										if(StringUtils.isNotBlank(String.valueOf(field.get(val)))){
											queryRule.addEqual(fieldName.concat(".").concat(field.getName()), field.get(val));
										}
									}
								}
							}
						} catch (IllegalArgumentException e) {
								logger.error("", e);
						} catch (IllegalAccessException e) {
								logger.error("", e);
						}
					} else if (String.class==type) {
						if(StringUtils.isNotBlank((String)val)) {
							//晓鹏的方法中未对首尾空格做处理,String的其他处理不变
							Pattern p = Pattern.compile("[*%]{1,}"); // 正则表达式
							Matcher m = p.matcher(((String)val).trim()); // 操作的字符串
							String strVal = m.replaceAll("%"); //替换后的字符串
							convertString(preFieldName+fieldName, strVal, queryRule);
						}
					} else if (Date.class==type) {
						// 对于date类型，做between处理
						Date dateVal = (Date) val;
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(dateVal);
						calendar.set(Calendar.HOUR_OF_DAY, 0);
						calendar.set(Calendar.MINUTE, 0);
						calendar.set(Calendar.SECOND, 0);
						Date startOfDay = calendar.getTime();
						calendar.set(Calendar.HOUR_OF_DAY, 23);
						calendar.set(Calendar.MINUTE, 59);
						calendar.set(Calendar.SECOND, 59);
						Date endOfDay = calendar.getTime();
						queryRule.addBetween(preFieldName+fieldName, startOfDay, endOfDay);
					} else if (Boolean.class==type) {
						queryRule.addEqual(preFieldName+fieldName, val);
					} else if (val instanceof Number) {
						if (!val.equals(SELECT_ALL_VALUE)) {
							queryRule.addEqual(preFieldName+fieldName, val);
						}
					}
				}
			}
		}
		
		return queryRule;
	}
	

	/**
	 * 若startVal和endVal均不为空，增加between的queryRule；如果startVal为null，增加小于等于的val；如果endVal为null，增加大于等于的val；
	 * @param queryRule
	 * @param fieldName
	 * @param startVal
	 * @param endVal
	 * @return
	 */
	public static QueryRule addBetweenRule(QueryRule queryRule, String fieldName, Object startVal, Object endVal) {
		Assert.isTrue(queryRule!=null);
		Assert.isTrue(StringUtils.isNotBlank(fieldName));
		
		if (startVal!=null && endVal!=null) {
			// 前后两个值的类型应一致
			Assert.isTrue(startVal.getClass()==endVal.getClass());
			queryRule.addBetween(fieldName, startVal, endVal);
		} else if (startVal!=null) {
			queryRule.addGreaterEqual(fieldName, startVal);
		} else if (endVal!=null) {
			queryRule.addLessEqual(fieldName, endVal);
		}
		return queryRule;
	}
	
    /**
     * 只对object中的String，Number类型的字段，组合成QueryQule查询条件，并返回。
     * 如果为Date，需要另外增加查询条件。
     * 没有实现对某个字段进行升降序排列，也没有实现某字段是否不为null的查询。
     * @param object
     * @return QueryRule
     */
	@Deprecated
	public static QueryRule getQueryRule(Object object){
		
		QueryRule queryRule = QueryRule.getInstance();
		Class<?> classType = object.getClass();

		Method[] methods = classType.getDeclaredMethods();
		String methodName;
		Object obj = null;
		Class<?> type;

		String value = "";
		String fieldName = null;
		for (Method method : methods) {
			if (method != null) {
				methodName = method.getName();

				if (methodName.startsWith("get")) {
					fieldName = methodName.substring(3,4).toLowerCase() + methodName.substring(4);				
					type = method.getReturnType();
					try {
						obj = method.invoke(object);
					} catch (IllegalArgumentException e1) {
						logger.warn(e1.getMessage());
					} catch (IllegalAccessException e1) {
						logger.warn(e1.getMessage());
					} catch (InvocationTargetException e1) {
						logger.warn(e1.getMessage());
					}
					if (obj == null) {
						value = null;
					} else {
						value = String.valueOf(obj);
					}
					if (StringUtils.isNotEmpty(value)) {
						/*
						value = StringUtils.replace(value, '*', "%");
						value = StringUtils.replace(value, "%%", "%"); // 去掉重复的通配符
						value = StringUtils.replace(value, "??", "?"); // 去掉重复的通配符
						*/
						Pattern p = Pattern.compile("[*%]{1,}"); // 正则表达式
						Matcher m = p.matcher(value); // 操作的字符串
						value = m.replaceAll("%"); //替换后的字符串
						//value = StringUtils.replace(value, "?", "_");
						
						if (type == Date.class || type == Timestamp.class
								|| type == java.sql.Date.class) {
							try {
								convertDate(fieldName,value,queryRule);
							} catch (ParseException e) {
								logger.error("", e);
								logger.error("parse date fail! class:" + object + " method: " + methodName + "input value: " +value);
							}
						} else if (type == String.class) {
							convertString(fieldName,value,queryRule);
						} else if (type == BigDecimal.class|| type == BigInteger.class || 
								type == Integer.class || type == Long.class || type == int.class || type == long.class){
							convertNumber(fieldName,value,queryRule);
						}
					}
				}
			}
		}
		return queryRule;
		
	}
    /**
     * 转换日期，日期格式如下： ～日期；日期～；开始日期～结束日期。 三种日期查询格式。
     * 日期格式：new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
     * @param name
     * @param value
     * @param queryRule
     * @throws ParseException
     */
	private static void convertDate(final String name, final String value,QueryRule queryRule) throws ParseException {
		int index = -1;
		checkParamValue(value);
		String retValue = value;
		if (retValue == null || retValue.equals("")) {
			return;
		}
		index = retValue.indexOf("~");
		if(index == 0){
			Date date = dateFormat.parse(retValue.substring(1));
			queryRule.addLessEqual(name, date);
		}else if(index == (retValue.length()-1)){
			String temp = retValue.substring(0, (retValue.length()-1));
			Date date = dateFormat.parse(temp);
			queryRule.addGreaterEqual(name, date);
		}else{
			String[] str = retValue.split("~");
			if(str.length == 2){
				Date startDate = null;
				Date endDate = null;
				startDate = dateFormat.parse(str[0]);
				endDate = dateFormat.parse(str[1]);
				queryRule.addBetween(name, startDate,endDate);
			}	
		}
	}
	/**
	 * 字符串型条件的拼串函数
	 * @param name
	 *            字段名
	 * @param value
	 *            字段值
	 * @return 条件字串
	 */
	private static void convertString(final String name, final String value,QueryRule queryRule) {
		checkParamValue(value);
		String retValue = value;
		if (retValue == null || retValue.equals("") || retValue.equalsIgnoreCase(SELECT_ALL_VALUE_STR)) { //支持前台的下拉列表为“请选择”时，此处忽略相应的0
			return ;
		}
		retValue = StringUtils.replace(retValue, "'", "''");
		if (!isIgnoreName(name) && IsCommaExist(retValue)) {
			//String[] values = StringUtils.split(retValue, ",");
			//支持逗号的的两种输入方式 ,和，
			Object[] values = retValue.split("[，,]{1,}");
			queryRule.addIn(name, values);
		} else if (retValue.indexOf('%') > -1) {
			//如果全部为通配符，如何处理？

			//经过测试，QueryRule封装后的SQL语句并不会默认追加一个%，所以此处不再需要删除最后的%号。
			//所以注释这三行代码
			//2013-08-13 renxiaopeng01
//			if (retValue.endsWith("%")){
//				retValue = retValue.substring(0, retValue.length()-1);
//			}
				
            queryRule.addLike(name, retValue);
		} else {
			Object[] values = retValue.split("[:：]{1,}");
			int length = values.length;
			if (!isIgnoreName(name) && length > 1) {
				if(length == 2){
					queryRule.addBetween(name, values);
				}else{
					//如果分割出来的值超过2个，需要做额外处理。
					Object[] values1 = new Object[2];
					values1[0] = values[0];
					values1[1] = values[1];
					queryRule.addBetween(name, values1);
				}
			} else if (hasOperateSign(retValue)) {
				addOperateSignValue(name,retValue,queryRule);
			} else {
				queryRule.addEqual(name, retValue);
			}
		}
	}
	

	/**
	 * 数值型条件的拼串函数
	 * @param name
	 *            字段名
	 * @param value
	 *            字段值
	 * @return 条件字串
	 */
	private static void convertNumber(final String name, final String value,QueryRule queryRule) {
		checkParamValue(value);
		String retValue = value;
		if (retValue == null || retValue.equals("")) {
			return ;
		} 
		if (!isIgnoreName(name) && IsCommaExist(retValue)) {
			String[] values = retValue.split("[，,]{1,}");
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(String str:values){
				if(str.matches("[0-9]{1,}")){
					Integer strInt = Integer.parseInt(str);
					list.add(strInt);
				}
			}
			queryRule.addIn(name, list);
		} else {
			Object[] values = retValue.split("[:：]{1,}");
			int length = values.length;
			if (!isIgnoreName(name) && length > 1) {
				if(length == 2){
	                queryRule.addBetween(name, values);
				}else{
					//如果分割出来的值超过2个，需要做额外处理。
					Object[] values1 = new Object[2];
					values1[0] = new Integer((Integer) values[0]);
					values1[1] = new Integer((Integer) values[1]);
					queryRule.addBetween(name, values1);
				}
			} else if (hasOperateSign(value)) {
				addOperateSignValue(name,value,queryRule);
			} else {
				queryRule.addEqual(name, new Integer(Integer.parseInt(value)));
			}
		}
	}
	/**
	 * 处理带有操作符的字符串，如：< <= > >= = != <>
	 * 
	 * @param propertyName
	 * @param retValue
	 * @param queryRule
	 */
	private static void addOperateSignValue(String propertyName,final String retValue, QueryRule queryRule) {
		if(retValue.startsWith(">")){
			if(retValue.substring(1, 2).equalsIgnoreCase("=")){
				queryRule.addGreaterEqual(propertyName, retValue.substring(2));
			}else{
				queryRule.addGreaterThan(propertyName, retValue.substring(1));
			}
		}else if(retValue.startsWith("<")){
			if(retValue.substring(1, 2).equalsIgnoreCase("=")){
				queryRule.addLessEqual(propertyName, retValue.substring(2));
			}else{
				queryRule.addLessThan(propertyName, retValue.substring(1));
			}
		}else if(retValue.startsWith("!=")||retValue.startsWith("<>")){
			queryRule.addNotEqual(propertyName, retValue.substring(2));
		}else if(retValue.startsWith("=")){
			queryRule.addEqual(propertyName, retValue.substring(1));
		}else {
			//加入Default操作方式
			queryRule.addEqual(propertyName, retValue);
		}
		
	}
	/**
	 * 检查参数，防止SQL注入攻击
	 * @param value
	 *            传入的参数值
	 */
	private static void checkParamValue(final String value) {
		if (value == null || value.equals("")) {
			return;
		}
		String retValue = value.toLowerCase();
		for (int i = 0; i < FORBID_STRING.length; i++) {
			if (retValue.indexOf(FORBID_STRING[i]) != -1) {
				throw new IllegalArgumentException("Illegal query string " + FORBID_STRING[i]);
			}
		}
	}
	/**
	 * 是否含有操作符
	 * @param value
	 *            语句
	 * @return 是返回true，否则返回false
	 */
	private static boolean hasOperateSign(final String value) {
		boolean result = false;
		String newValue = StringUtils.absoluteTrim(value);
		for (int i = 0; i < OPERATE_SIGN.length; i++) {
			if (newValue.indexOf(OPERATE_SIGN[i]) != -1) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * 添加忽略处理Convert中的逗号，冒号方式的字段名（大小写无关）
	 * @param name 字段名
	 */
	public static void addIgnoreName(String name){
		String key ;
		int pos = name.lastIndexOf('.');
		if(pos>-1){
			key = name.substring(pos+1);
		}else{
			key = name;
		}
		key =key.toLowerCase(); 
		ignoreNameMap.put(key, "");
	} 
	/**
	 * 是否忽略处理Convert中的逗号，冒号方式的字段名（大小写无关）
	 * @param name 字段名
	 * @return 是返回ture，否则返回false
	 */
	public static boolean isIgnoreName(String name){
		String key ;
		int pos = name.lastIndexOf('.');
		if(pos>-1){
			key = name.substring(pos+1);
		}else{
			key = name;
		}
		key =key.toLowerCase(); 
		return ignoreNameMap.containsKey(key);
	}
	/**
	 * 是否全部都是通配符
	 * @param condition
	 * @return
	 */
	public static boolean containsFilteredCharOnly(String condition) {
		boolean containsFilteredCharOnly = true;
		for (int i = 0; i < condition.length(); i++) {
			String str = condition.substring(i, i + 1);
			if (!org.apache.commons.lang.ArrayUtils.contains(FILTERED_STRING, str)) {
				containsFilteredCharOnly = false;
				break;
			}
		}
		return containsFilteredCharOnly;
	}
    /**
     * 判断是否字符串中存在逗号。如，,两种格式。
     * @param value
     * @return
     */
	private static boolean IsCommaExist(String value){
		java.util.regex.Pattern p = java.util.regex.Pattern.compile("[，,]{1,}");
		Matcher m = p.matcher(value);
		return m.find();
	}
	/*
	public static void main(String[] args){

		RenXiaopeng rxp = new RenXiaopeng();
		rxp.setAddress("beijing:nanjing");
		rxp.setId("2012:2013");
		rxp.setName("testName");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println(formatter.format(new Date()));
		System.out.println(formatter1.format(new Date()));
		rxp.setStartTime(new Date());
		getQueryRule(rxp);
	}
	*/
}
