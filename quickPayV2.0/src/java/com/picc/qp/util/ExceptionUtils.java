package com.picc.qp.util;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Desc 异常处理工具
 * @author xiaozhiqiang
 * @Date 2015-11-10
 */
public class ExceptionUtils {

	/**
	 * @Desc 获取异常堆栈信息
	 * @param e
	 * @return 
	 * @author xiaozhiqiang
	 * @Date 2015-11-10
	 */
	public static String getStackTraceMessage(Exception e){
		if (e == null) {
			return null;
		}
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}

}