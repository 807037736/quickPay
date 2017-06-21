package com.picc.common.utils;

import ins.framework.common.DateTime;
import ins.framework.utils.BeanUtils;
import ins.framework.utils.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理SQL语句相关工具类
 */
public final class SqlUtils {
	/**
	 * Logger for this class
	 */
	protected static final Logger logger = LoggerFactory.getLogger(SqlUtils.class);
	/**
	 * 查询字段的后缀标识，如Date型字段startDate,其对应的查询字段为startDateForQuery
	 */
	public static final String QUERY_FIELD_SUFFIX = "ForQuery";
	
	public static final String START_FIELD_SUFFIX = "ForQueryStart";
	
	public static final String END_FIELD_SUFFIX = "ForQueryEnd";
	
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
	private static final String[] EXCEPT_KEY = new String[] { "insert ", "update ", "delete ", "select ", "create ",
			"drop ", "alter ", "truncate ", "use " };
	/**
	 * 标准数据库类型
	 */
	public static final int ANSI_DB = 0;
	/**
	 * Oracle数据库类型
	 */
	public static final int ORACLE_DB = 1;
	/**
	 * DB2数据库类型
	 */
	public static final int DB2_DB = 2;
	/**
	 * 数据库类型,默认为标准数据库类型
	 */
	private static int dbType = 1;
	private static String oracleDateFormat = "YYYY-MM-DD HH24:MI:SS";
	
	private static Map<String, String> ignoreNameMap = new HashMap<String, String>();

	/**
	 * 构造方法，禁止实例化
	 */
	private SqlUtils() {
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
	 * @param alias
	 *            别名
	 * @param object
	 *            object对象
	 * @return 拼好的SQL语句
	 */
	public static String addConditions(final Object object, final String alias)throws Exception {
		if (object == null) {
			return "";
		}
		StringBuilder buffer = new StringBuilder(100);
		Class cl = object.getClass();
		/*if (cl != null && cl.getSuperclass() != null) {
			String className = BeanUtils.getClassNameWithoutPackage(cl);
			String superName = BeanUtils.getClassNameWithoutPackage(cl.getSuperclass());
			if ((className + "Base").equals(superName)) {
				cl = cl.getSuperclass();
			}
		}*/
		List<Method> methodList = BeanUtils.getSetter(cl);
		Method method;
		String sql = null;
		String fieldName = null;
		Method getterMethod;
		Object obj = null;//用于setId处理过程中获取getId对象
		for (int i = 0; i < methodList.size(); i++) {
			method = methodList.get(i);
			//遇到setId，先获取getId的返回对象
			if("setId".equalsIgnoreCase(method.getName())){
				try{
					getterMethod = BeanUtils.getGetterMethod(cl, "id");
					obj = getterMethod.invoke(object, new Object[0]);
					sql = addConditions(obj,alias);
					buffer.append(sql);
				} catch (IllegalAccessException e) {
					throw new IllegalArgumentException("Illegal access exception");
				} catch (InvocationTargetException e) {
					throw new IllegalArgumentException("Invocation target exception"); 
				}
			}else{
				fieldName = method.getName().substring(3);
				if (fieldName.indexOf(QUERY_FIELD_SUFFIX)>-1) {
					continue;
				}
				fieldName = StringUtils.lowerCaseFirstChar(fieldName);
				sql = addCondition(alias, object, fieldName);
				buffer.append(sql);
			}
		}
		return buffer.toString();
	}

	/**
	 * @param object
	 *            object对象
	 * @param propertyName
	 *            属性名
	 * @param alias
	 *            别名
	 * @return 拼好的SQL语句
	 */
	public static String addCondition(final Object object, final String propertyName, final String alias) {
		return addCondition(alias, object, propertyName);
	}

	private static String addCondition(final String alias, final Object object, final String pName) {
		StringBuilder buffer = new StringBuilder();
		String value = "";
		Class type = null;
		Object obj;
		StringBuilder buf = new StringBuilder(pName.length());
		StringBuilder paramName = new StringBuilder(pName.substring(0, 1).toUpperCase()).append(pName.substring(1));
		try {
			buf.append(paramName);
			Method method = BeanUtils.getGetterMethod(object.getClass(), buf.toString());
			if(method!=null&&method.isAnnotationPresent(javax.persistence.Column.class)){//持久层column注解的才进行处理
				type = method.getReturnType();
				obj = method.invoke(object, new Object[0]);
				if (obj == null) {
					value = null;
				} else {
					value = String.valueOf(obj);
				}
			}
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException("Illegal access exception");
		} catch (InvocationTargetException e) {
			throw new IllegalArgumentException("Invocation target exception"); 
		}
		if (value == null && type!=null && type != String.class) {
			try {
				buf.setLength(0);
				buf.append(paramName).append(SqlUtils.QUERY_FIELD_SUFFIX);
				Method method = BeanUtils.getGetterMethod(object.getClass(), buf.toString());  
				if(method==null){//如果字段名+QUERY_FIELD_SUFFIX对应的方法无值，则获取区间处理
					String valueStart = null;
					String valueEnd = null;
					buf.setLength(0);
					buf.append(paramName).append(SqlUtils.START_FIELD_SUFFIX);
					method = BeanUtils.getGetterMethod(object.getClass(), buf.toString());  
					if(method!=null){//获取字段名+START_FIELD_SUFFIX对应的方法
						obj = method.invoke(object, new Object[0]);
						if (obj == null) {
							valueStart = null;
						} else {
							valueStart = String.valueOf(obj);
						}
					}
					
					buf.setLength(0);
					buf.append(paramName).append(SqlUtils.END_FIELD_SUFFIX);
					method = BeanUtils.getGetterMethod(object.getClass(), buf.toString());
					if(method!=null){//获取字段名+END_FIELD_SUFFIX对应的方法
						obj = method.invoke(object, new Object[0]);
						if (obj == null) {
							valueEnd = null;
						} else {
							valueEnd = String.valueOf(obj);
						}
					}
					if(valueStart==null&&valueEnd==null){
						value = null;
					}else{
						if(valueStart!=null&&!"".equals(valueStart)&&valueEnd!=null&&!"".equals(valueEnd)){
							value = valueStart+"~"+valueEnd;
						}else{
							if(valueStart!=null&&!"".equals(valueStart)){
								value = ">="+valueStart;
							}
							if(valueEnd!=null&&!"".equals(valueEnd)){
								value = "<="+valueEnd;
							}
						}
					}
				}else{//获取字段名+QUERY_FIELD_SUFFIX对应的方法
					obj = method.invoke(object, new Object[0]);
					if (obj == null) {
						value = null;
					} else {
						value = String.valueOf(obj);
					}
				}
			} catch (IllegalAccessException e) {
				throw new IllegalArgumentException("Illegal access exception");
			} catch (InvocationTargetException e) {
				throw new IllegalArgumentException("Invocation target exception"); 
			}
		}
		if (value != null && value.length() > 0) {
			value = StringUtils.replace(value, '*', "%");
			value = StringUtils.replace(value, "%%", "%"); // 去掉重复的通配符
			value = StringUtils.replace(value, "??", "?"); // 去掉重复的通配符
			value = value.trim();
			String fieldName = "";
			if (alias != null && alias.trim().length() > 0) {
				fieldName = new StringBuilder().append(alias).append('.').append(pName).toString();
			} else {
				fieldName = pName;
			}
			if (type == Date.class || type == Timestamp.class || type == java.sql.Date.class) {
				buffer.append(SqlUtils.convertDate(fieldName, value));
			} else if (type == String.class) {
				buffer.append(SqlUtils.convertString(fieldName, value));
			} else {
				buffer.append(SqlUtils.convertNumber(fieldName, value));
			}
		}
		return buffer.toString();
	}

	/**
	 * 返回适合GetCount方法的SQL语句的Where部分. <br>
	 * <br>
	 * <b>示例: </b> <br>
	 * SqlUtils.getWherePartForGetCount(&quot;1=1 Order by policyno&quot;);
	 * &quot;1=1&quot;
	 * @param wherePart
	 *            SQL语句的Where部分
	 * @return 适合GetCount方法的SQL语句的Where部分,目前为简单地去掉order by
	 *         及之后的语句,如果有多余的反括号")"，则保留。
	 */
	public static String getWherePartForGetCount(final String wherePart) {
		String sqlForGetCount = wherePart;
		int pos = 0;
		pos = StringUtils.indexOf(sqlForGetCount.toLowerCase(), " order by ", 0, false);
		if (pos > 0) {
			String orderbyStr = sqlForGetCount.substring(pos);
			sqlForGetCount = sqlForGetCount.substring(0, pos);
			int rightCount = StringUtils.timesOf(orderbyStr, ')');
			if (rightCount > 0) {
				int leftCount = StringUtils.timesOf(orderbyStr, '(');
				if (rightCount > leftCount) {
					sqlForGetCount += StringUtils.newString(')', rightCount - leftCount);
				}
			}
		}
		return sqlForGetCount;
	}

	/**
	 * 字符串型条件的拼串函数
	 * @param name
	 *            字段名
	 * @param value
	 *            字段值
	 * @return 条件字串
	 */
	public static String convertString(final String name, final String value) {
		checkParamValue(value);
		String retValue = value;
		if (retValue == null || retValue.equals("")) {
			return "";
		}
		StringBuilder buffer = new StringBuilder();
		retValue = StringUtils.replace(retValue, "*", "%");
		retValue = StringUtils.replace(retValue, "%%", "%"); // 去掉重复的通配符
		retValue = StringUtils.replace(retValue, "??", "?"); // 去掉重复的通配符
		retValue = StringUtils.replace(retValue, "'", "''");
		if (!isIgnoreName(name) && IsCommaExist(retValue)) {
			//String[] values = StringUtils.split(retValue, ",");
			String trueValue = retValue;
			if (retValue.indexOf("!=") != -1) {
				buffer.append(" AND (").append(name).append(" not IN (");
				trueValue = retValue.replace("!=", "");
			}else{
				buffer.append(" AND (").append(name).append(" IN (");
			}
			Object[] values = trueValue.split("[，,]{1,}");
			for (int i = 0, n = values.length; i < n; i++) {
				buffer.append('\'').append(values[i]).append('\'');
				if (i < n - 1) {
					buffer.append(',');
				}
			}
			buffer.append(")) ");
		} else if (retValue.indexOf('?') > -1 || retValue.indexOf('%') > -1) {
//			// 如果是以星号、百分号、问号开头，拼等于
//			if (retValue.startsWith("?") || retValue.startsWith("%")) {
//				buffer.append(" AND (").append(name).append(" = '").append(retValue).append("') ");
//			} else {
//				boolean containsFilteredCharOnly = containsFilteredCharOnly(retValue);
//				// 如果全是%,*,空格，拼等于
//				if (containsFilteredCharOnly) {
//					buffer.append(" AND (").append(name).append(" = '").append(retValue).append("') ");
//				} else {
					buffer.append(" AND (").append(name).append(" LIKE '").append(retValue).append("') ");
//				}
//			}
		} else {
			String[] values = retValue.split("[:：]{1,}");
			if (!isIgnoreName(name) && values.length > 1) {
				buffer.append(" AND (").append(name).append(" BETWEEN '").append(values[0].trim()).append("' AND '").append(
						values[1].trim()).append("') ");
			} else if (hasOperateSign(retValue)) {
				String[] operateSignAndTrueValue = splitOperateSignAndTrueValue(retValue);
				String operateSign = operateSignAndTrueValue[0];
				String trueValue = operateSignAndTrueValue[1];
				buffer.append(" AND (").append(name).append(" ").append(operateSign).append(" '").append(trueValue).append("') ");
			} else {
				buffer.append(" AND (").append(name).append(" = '").append(retValue).append("') ");
			}
		}
		return buffer.toString();
	}

	/**
	 * 日期型条件的拼串函数 (如果要处理年、月、日、时、分、秒、毫秒等单项类型数据的条件区间，可在中间的分号前多加几个空格)
	 * @param name
	 *            字段名
	 * @param value
	 *            字段值
	 * @return 条件字串
	 */
	public static String convertDate(final String name, final String value) {
		checkParamValue(value);
		StringBuilder buffer = new StringBuilder();
		String retValue = value;
		if (retValue == null || retValue.equals("")) {
			return "";
		} else if (!isIgnoreName(name) && IsCommaExist(retValue)) {
			//String[] values = StringUtils.split(retValue, ",");
			String[] values = retValue.split("[，,]{1,}");
			if (dbType == DB2_DB) {
				buffer.append(" AND (date(").append(name).append(") IN (");
			} else {
				buffer.append(" AND (").append(name).append(" IN (");
			}
			for (int i = 0, n = values.length; i < n; i++) {
				String dateValue = values[i].trim();
				dateValue = StringUtils.replace(dateValue, "'", "");
				dateValue = dateValue.trim();
				if (dbType == ORACLE_DB) {
					dateValue = StringUtils.replace(dateValue, "/", "-");
					buffer.append("to_date('").append(dateValue).append("', '").append(oracleDateFormat).append("')");
				} else if (dbType == DB2_DB) {
					buffer.append("date('").append(dateValue).append('\'');
				} else {
					buffer.append('\'').append(dateValue).append('\'');
				}
				if (i < n - 1) {
					buffer.append(',');
				}
			}
			buffer.append(")) ");
		} else {
			String valueStart = "";
			String valueEnd = "";
			retValue = StringUtils.replace(retValue.trim(), "'", "");
			retValue = retValue.trim();
			int foundCount = StringUtils.timesOf(retValue, '~');
			boolean isBetween = false;// 是否是条件区间
			if (!isIgnoreName(name)&& (foundCount > 0) && (foundCount % 2 == 1)) {
				isBetween = true;
				int index = StringUtils.indexOf(retValue, "~", 0, foundCount / 2 + 1);
				valueStart = retValue.substring(0, index);
				valueEnd = retValue.substring(index + 1);
				// 避免处理年、月、日、时、分、秒、毫秒等单项类型数据,如果要处理上述类型数据的条件区间，可在中间的分号前多加几个空格
				if (foundCount == 1 && valueStart.length() < 3) { // 避免处理年、月、日、时、分、秒、毫秒等单项类型数据
					isBetween = false;
				}
			}
			if (isBetween) {
				if (dbType == ORACLE_DB) {
					String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
					java.util.regex.Pattern pp = java.util.regex.Pattern.compile(eL);
					Matcher m = pp.matcher(valueEnd);		
					boolean dateFlag = m.matches();		
					if (dateFlag) {			
						valueEnd =	valueEnd + " 23:59:59";
					}
					
					buffer.append(" AND (").append(name).append(" BETWEEN to_date('").append(valueStart.trim()).append(
							"', '").append(oracleDateFormat).append("') AND to_date('").append(valueEnd.trim()).append(
							"', '").append(oracleDateFormat).append("')) ");
				} else if (dbType == DB2_DB) {
					buffer.append(" AND ( date(").append(name).append(") BETWEEN date('").append(valueStart.trim())
							.append("') AND date('").append(valueEnd.trim()).append("')) ");
				} else {
					buffer.append(" AND (").append(name).append(" BETWEEN '").append(valueStart.trim()).append(
							"' AND '").append(valueEnd.trim()).append("') ");
				}
			} else if (hasOperateSign(retValue)) {
				String[] operateSignAndTrueValue = splitOperateSignAndTrueValue(retValue);
				String operateSign = operateSignAndTrueValue[0];
				String trueValue = operateSignAndTrueValue[1];
				if(operateSign.equals("<=") && !trueValue.contains(":")){
					trueValue = trueValue+" 23:59:59";
				}
				if (dbType == ORACLE_DB) {
					buffer.append(" AND (").append(name).append(operateSign).append("to_date('").append(trueValue)
							.append("', '").append(oracleDateFormat).append("')) ");
				} else if (dbType == DB2_DB) {
					buffer.append(" AND ( date(").append(name).append(')').append(operateSign).append("date('").append(
							trueValue).append("')) ");
				} else {
					buffer.append(" AND (").append(name).append(operateSign).append(" '").append(trueValue).append(
							"') ");
				}
			} else {
				if (dbType == ORACLE_DB) {
					buffer.append(" AND (").append(name).append(">=to_date('").append(
							new DateTime(retValue).toString(DateTime.YEAR_TO_DAY)).append(" 00:00:00', '").append(
							oracleDateFormat).append("') AND ").append(name).append("<=to_date('").append(
							new DateTime(retValue).toString(DateTime.YEAR_TO_DAY)).append(" 23:59:59', '").append(
							oracleDateFormat).append("')) ");
				} else if (dbType == DB2_DB) {
					buffer.append(" AND (date(").append(name).append(") = date('").append(retValue).append("')) ");
				} else {
					buffer.append(" AND (").append(name).append(" = '").append(retValue).append("') ");
				}
			}
		}
		return buffer.toString();
	}

	/**
	 * 数值型条件的拼串函数
	 * @param name
	 *            字段名
	 * @param value
	 *            字段值
	 * @return 条件字串
	 */
	public static String convertNumber(final String name, final String value) {
		StringBuilder strReturn = new StringBuilder();
		checkParamValue(value);
		if (value == null || value.equals("")) {
			return "";
		} else if (!isIgnoreName(name) && IsCommaExist(value)) {
			//String[] values = StringUtils.split(value, ",");
			Object[] values = value.split("[，,]{1,}");
			strReturn.append(" AND (").append(name).append(" IN (");
			for (int i = 0, n = values.length; i < n; i++) {
				strReturn.append('\'').append(values[i]).append('\'');
				if (i < n - 1) {
					strReturn.append(',');
				}
			}
			strReturn.append(")) ");
		} else {
			String[] values = value.split("[:：]{1,}");
			if (!isIgnoreName(name) && values.length > 1) {
				strReturn.append(" AND (").append(name).append(" BETWEEN ").append(values[0]).append(" AND ").append(
						values[1]).append(") ");
			} else if (hasOperateSign(value)) {
				strReturn.append(" AND (").append(name).append(value).append(") ");
			} else {
				strReturn.append(" AND (").append(name).append(" = ").append(value).append(") ");
			}
		}
		return strReturn.toString();
	}

	/**
	 * 检查参数，防止SQL注入攻击
	 * @param paramValue
	 *            传入的参数值
	 */
	private static void checkParamValue(final String value) {
		if (value == null || value.equals("")) {
			return;
		}
		String retValue = value.toLowerCase();
		validateSql(retValue);//增加对关键字的过滤
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

	private static String[] splitOperateSignAndTrueValue(final String value) {
		// 去掉两边空格。
		String newValue = StringUtils.leftTrim(value);
		newValue = StringUtils.rightTrim(newValue);
		String operateSign = null;
		String trueValue = null;
		if (newValue.length() >= 2) // value值大于等于2个字符。
		{
			if (hasOperateSign(newValue.substring(1, 2))) {
				// 如果第2个字符也是操作符，说明前两个字符都是操作符。
				operateSign = newValue.substring(0, 2);
				trueValue = newValue.substring(2);
			} else {
				// 只有第一个字符是操作符。
				operateSign = newValue.substring(0, 1);
				trueValue = newValue.substring(1);
			}
		} else {
			// value只有一个字符。
			operateSign = newValue;
			trueValue = "";
		}
		return new String[] { operateSign, trueValue };
	}

	public static int getDbType() {
		return dbType;
	}

	public static void setDbType(int dbType) {
		SqlUtils.dbType = dbType;
	}

	public static String join(final String selectPart, final String conditionsPart) {
		if (conditionsPart == null) {
			return selectPart;
		}
		String newConditionsPart = trimStartsWithAnd(conditionsPart);
		StringBuilder sql = new StringBuilder(32);
		sql.append(selectPart);
		if (newConditionsPart.trim().length() > 0) {
			sql.append(" where ");
			sql.append(newConditionsPart);
		}
		return sql.toString();
	}

	public static String trimStartsWithAnd(final String sql) {
		if (sql == null) {
			return "";
		}
		StringBuilder value = new StringBuilder(sql);
		try {
			final String regex = "^\\s*and{1}\\s+";
			PatternCompiler compiler = new Perl5Compiler();
			PatternMatcher matcher = new Perl5Matcher();
			Pattern pattern = compiler.compile(regex, Perl5Compiler.CASE_INSENSITIVE_MASK);
			if (matcher.contains(value.toString(), pattern)) {
				MatchResult result = matcher.getMatch();
				int beginOffset = result.beginOffset(0);
				int endOffset = result.endOffset(0);
				value = value.delete(beginOffset, endOffset);
			}
		} catch (Exception e) {
			value = new StringBuilder(sql);
			if (logger.isDebugEnabled()) {
				logger.error("", e);
			}
		}
		return value.toString();
	}

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

	public static Object getObjectWithRealType(String strValue, Class type) {
		Object objectValue = strValue;
		if (type == Integer.class) {
			objectValue = Integer.valueOf(strValue);
		} else if (type == Long.class) {
			objectValue = Long.valueOf(strValue);
		} else if (type == Double.class) {
			objectValue = Double.valueOf(strValue);
		} else if (type == DateTime.class) {
			// 长度为8时是年到日
			if (strValue.length() == 10) {
				objectValue = new DateTime(strValue);
			} else if (strValue.length() == 13) {
				objectValue = new DateTime(strValue, DateTime.YEAR_TO_HOUR);
			} else if (strValue.length() == 16) {
				objectValue = new DateTime(strValue, DateTime.YEAR_TO_MINUTE);
			} else if (strValue.length() == 19) {
				objectValue = new DateTime(strValue, DateTime.YEAR_TO_SECOND);
			}
		}
		return objectValue;
	}

	public static void setOracleDateFormat(String oracleDateFormat) {
		SqlUtils.oracleDateFormat = oracleDateFormat;
	}

	/**
	 * 检测SQL语句是否合法(暂不提供具体检查规则)
	 * @param sql
	 *            传入的sql
	 */
	public static void validateSql(String sql) {
		if (sql == null) {
			return;
		}
		if (sql.indexOf(';') != -1) {
			String[] tempSqls = StringUtils.split(sql, ";");
			boolean multiSql = false;
			for (int i = 1; i < tempSqls.length; i++) {
				String tempSql = tempSqls[i].trim().toLowerCase();
				for (int j = 0; j < EXCEPT_KEY.length; j++) {
					if (tempSql.startsWith(EXCEPT_KEY[j])) {
						multiSql = true;
						logger.warn("Mabye SQL Injection. The sql is [" + sql + "]");
						break;
					}
				}
			}
			if (multiSql) {
				throw new IllegalArgumentException("SQL can't include semicolon.");
			}
		}
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
}