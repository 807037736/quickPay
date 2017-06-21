package com.picc.qp.util;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
/**
 * 
 * @author xiaozhiqiang
 *
 */
public final class CopyUtils {
	/**
	 * 只复制基本类型的属性值，基本类型必须是包装类
	 * @param source
	 * @param target
	 * @throws Exception
	 */
	public static void copyObject(Object source, Object target) throws Exception {
		PropertyDescriptor[] sourcePs = Introspector.getBeanInfo(
				source.getClass()).getPropertyDescriptors();
		PropertyDescriptor[] targetPs = Introspector.getBeanInfo(
				target.getClass()).getPropertyDescriptors();
		for (PropertyDescriptor sourceDesc : sourcePs) {
			String sourceName = sourceDesc.getName();
			Class sourceClazz = sourceDesc.getPropertyType();
			Method sourceGetter = sourceDesc.getReadMethod();
			if (isBaseType(sourceClazz)) {
				for (PropertyDescriptor targetDesc : targetPs) {
					String targetName = targetDesc.getName();
					Class targetClazz = targetDesc.getPropertyType();
					if (isBaseType(targetClazz)) {
						if (sourceName.equals(targetName)) {
							Method targetSetter = targetDesc.getWriteMethod();
							targetSetter.invoke(target, sourceGetter
									.invoke(source));
							break;
						}
					}
				}
			}
		}
	}

	public static boolean isBaseType(Class clazz) {
		if (clazz == String.class) {
			return true;
		} else if (clazz == Byte.class) {
			return true;
		} else if (clazz == Integer.class) {
			return true;
		} else if (clazz == Long.class) {
			return true;
		} else if (clazz == Short.class) {
			return true;
		} else if (clazz == Character.class) {
			return true;
		} else if (clazz == Date.class) {
			return true;
		} else if (clazz == Timestamp.class) {
			return true;
		} else if (clazz == Double.class) {
			return true;
		} else if (clazz == Float.class) {
			return true;
		} else if (clazz == Boolean.class) {
			return true;
		}else if (clazz == BigDecimal.class) {
			return true;
		}else if (clazz == BigInteger.class) {
			return true;
		} else {
			return false;
		}
	}

}
