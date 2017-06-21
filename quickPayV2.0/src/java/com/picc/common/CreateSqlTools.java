package com.picc.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @ClassName: CreateSqlTools
 * @Description: TODO(根据实体类对象生成SQL语句)
 * @author LiYang
 * @date 2012-5-4 下午10:07:03
 */
public class CreateSqlTools {
	
	private static final Logger logger = LoggerFactory.getLogger(CreateSqlTools.class);
	/**
	 * 
	 * @Title: getTableName
	 * @Description: TODO(获取表名)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	private static String getTableName(Object entity) {
		//传入对象不能为空
		Assert.notNull(entity,"entity is required!");
		Class classType = entity.getClass();
		Table   tableAnno   = (Table)classType.getAnnotation(Table.class);
		Assert.notNull(tableAnno, "the table anno is required!");
		return tableAnno.name();
	}

	/**
	 * 
	 * @Title: getAnnoFieldList
	 * @Description: TODO(获取所有有注释的字段,支持多重继承)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return List<Field> 返回类型
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	private static List<Field> getAnnoFieldList(Object obj) {
		List<Field> list = new ArrayList<Field>();
		Class classType = obj.getClass();
		Field[] fields = classType.getDeclaredFields();
		if(fields!=null){
			 String name = "";
			 String methodName = "";
			 Method method = null;
			for(int i=0,length=fields.length; i<length; i++){
				/* 如果注解在字段名上，而不是get方法上，则用此法，否则用下述方法
				Column columnAnno = fields[i].getAnnotation(Column.class);
				if(columnAnno==null){continue;}
				else {list.add(fields[i]);}
				*/
				Field field = fields[i];
				name = field.getName();
				methodName = "get" + name.substring(0, 1).toUpperCase()+ name.substring(1);
				 try {
					 method = obj.getClass().getMethod(methodName);
				 } catch (SecurityException e1) {
				 // TODO Auto-generated catch block
				 } catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				 if (method != null) {
					 Column columnAnno = method.getAnnotation(Column.class);
					 if(columnAnno!=null) {list.add(fields[i]);}
				 }
			}
		}
		return list;
	}

	/**
	 * 
	 * @Title: getFieldValue
	 * @Description: TODO(获取字段的值,支持多重继承)
	 * @param @param obj
	 * @param @param field
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@SuppressWarnings({ "rawtypes" })
	 private static Object getFieldValue(Object obj, Field field) {
		 Object value = null;
		 String name = field.getName();
		 String methodName = "get" + name.substring(0, 1).toUpperCase()
		 + name.substring(1);
		 Method method = null;
		 Object methodValue = null;
		 try {
			 method = obj.getClass().getMethod(methodName);
		 } catch (SecurityException e1) {
		 // TODO Auto-generated catch block
		 } catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
		}
		 if (method != null) {
		 try {
			 methodValue = method.invoke(obj);
		 } catch (IllegalArgumentException e) {
		 // TODO Auto-generated catch block
		 } catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
		}
		 if (methodValue != null) {
			 value = methodValue;
		 } 
		 }
		 return value;
	 }

	/**
	 * 
	 * @Title: getUpdateSql
	 * @Description: TODO(根据实体类对象字段的值生成UPDATE SQL语句,可选更新条件为主键,可选固定更新参数)
	 * @param @param obj
	 * @param @param reqPk 是否指定更新条件为主键(true=是,false=否)
	 * @param @param fixedParams
	 *        固定参数(如该参数与实体类中有相同的字段,则忽略实体类中的对应字段,HashMap<String
	 *        ,String>,key=指定字段名,value=对应字段的值)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String generateUpdateSql(Object obj,Map<String,String> map){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String updateSql = null;
		String tableName = getTableName(obj);
		if (tableName != null) {
			//只取出有get set 方法的字段
			List<Field> annoFieldList = getAnnoFieldList(obj);
			if (annoFieldList != null && annoFieldList.size() > 0) {
				StringBuffer sqlStr = new StringBuffer("UPDATE " + tableName);
				StringBuffer valueStr = new StringBuffer(" SET ");
				StringBuffer whereStr = new StringBuffer(" WHERE 1=1 ");
				//map传递的是主键信息，这里根据map生成where条件(默认都是string类型)
				for (String key : map.keySet()) {
					whereStr.append(" and ").append(key).append("='").append(map.get(key)).append("'");
				}
				//根据过滤后的字段列表找到相应的值
				for (Field field : annoFieldList) {
					if(map.keySet().contains(field.getName().toString())){
						//说明是主键，不需要set
						continue;
					}
					Object fieldValue = getFieldValue(obj, field);
					//若字段值不为空，才进行set，否则忽略
					if (fieldValue != null) {
						valueStr.append(field.getName() + "=");
						if(field.getType() == String.class){
							valueStr.append("'" + fieldValue + "',");
						}
						if(field.getType() == Date.class){
							//System.out.println(field.getName()+"="+fieldValue);
							String currentFieldValue = format.format(fieldValue);
							//仅限于oracle
							valueStr.append(" to_date('" + currentFieldValue + "','yyyy-mm-dd,hh24:mi:ss'),");
							//System.out.println(field.getName()+"="+currentFieldValue);
						}
						//else{
						//	valueStr.append(fieldValue + ",");
						//}
					}
				}
				updateSql = sqlStr.toString()
				+ valueStr.toString().substring(0,
						valueStr.length() - 1)
				+ whereStr;
			}
		}
		return updateSql;
	}

}
