package com.picc.common.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字符串编码转换,空字符串,空白字符串转换
 * @author chenjin
 * 2010-7-8
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class CodeUtil {
	protected static final Logger logger = LoggerFactory.getLogger(CodeUtil.class);
	
	public static String iso8859ToGBK(String src){
		if(src != null){
			try {
				//从数据库取出数据时,将回车换行符进行替换
				src = src.trim();
				byte[] line = new byte[]{13,10};
				src = src.replaceAll("<br>", new String(line));
				return new String(src.getBytes("ISO-8859-1"),"GBK");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		return "";
	}
	
	public static String iso8859ToUTF(String src){
		if(src != null){
			try {
				//从数据库取出数据时,将回车换行符进行替换
				src = src.trim();
				byte[] line = new byte[]{13,10};
				src = src.replaceAll("<br>", new String(line));
				return new String(src.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		return "";
	}
	
	/**
	 * 返回src,第index个字符转换的字符串
	 * @param src
	 * @param index
	 * @return
	 */
	public static String charAt(String src,int index){
		if(src==null){return "";}
		if(index > src.length()-1){return "";}
		return String.valueOf(src.charAt(index));
	}
	
	/**
	 * 判断数组src是否包含element
	 * @param src
	 * @param element
	 * @return
	 */
	public static boolean contain(String[] src,String element){
		if(src==null || element==null){return false;}
		for(int i=0,length=src.length; i<length; i++){
			if(src[i].equals(element)){return true;}
		}
		return false;
	}
	
	public static boolean isDateFormat(String src){
		if(CodeUtil.nullOrBlank(src)){
			return false;
		}else{
			String dateFormat = "^\\d{4}\\-\\d{2}\\-\\d{2}$";
			src = src.trim();
			return src.matches(dateFormat);
		}
	}
	
	public static String gbkToISO8859(String src){
		if(src != null){
			try {
				return new String(src.getBytes("GBK"),"ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		return "";
	}
	
	/**
	 * @param src
	 * @param charset
	 * @param strict 是否严格筛选
	 * @return
	 */
	public static String gbkToDBCharSet(String src,String charset,boolean strict){
		if(src != null){
			try {
				if(!strict){return new String(src.getBytes("GBK"),charset);}
				byte[] line = new byte[]{13,10};
				src = src.replaceAll(new String(line), "<br>");
				src = src.replaceAll("'", "''");
				if("GBK".equalsIgnoreCase(charset) || nullOrBlank(charset)){return src;}
				else{return new String(src.getBytes("GBK"),charset);}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		return "";
	}
	
	public static String utfToDBCharSet(String src,String charset){
		if(src != null){
			try {
				byte[] line = new byte[]{13,10};
				src = src.replaceAll(new String(line), "<br>");
				src = src.replaceAll("'", "''");
				if("UTF-8".equalsIgnoreCase(charset) || nullOrBlank(charset)){return src;}
				else{return new String(src.getBytes("UTF-8"),charset);}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		return "";
	}
	
	public static String[] gbkToISO8859(String[] src){
		if(CodeUtil.nullOrBlank(src)){return null;}
		String[] des = new String[src.length];
		for(int i=0,length=src.length; i<length; i++){
			des[i]=gbkToISO8859(src[i]);
		}
		return des;
	}
	
	/**
	 * @param src
	 * @param strict 是否严格筛换
	 * @return
	 */
	public static String gbkToISO8859(String src,boolean strict){
		if(!strict){return gbkToISO8859(src);}
		else{
			if(src != null){
				try{
					//去掉字符前后空白
					src = src.trim();
					//存入数据库时,替换特殊字符,如换行引号
					byte[] line = new byte[]{13,10};
					src = src.replaceAll(new String(line), "<br>");
					src = src.replaceAll("'", "''");
					return new String(src.getBytes("GBK"),"ISO-8859-1");
				}catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					logger.error("", e);
				}
			}
			return "";
		}
	}
	
	public static String toGBK(String src){
		if(src!=null){
			try{return new String(src.getBytes(),"GBK");
			}catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		return "";
	}
	
	public static String toGBK(String src,String charset){
		if(src != null){
			try {
				//从数据库取出数据时,将回车换行符进行替换
				src = src.trim();
				byte[] line = new byte[]{13,10};
				src = src.replaceAll("<br>", new String(line));
				return new String(src.getBytes(charset),"GBK");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		return "";
	}
	
	public static String toUTF(String src,String charset){
		if(src != null){
			try {
				//从数据库取出数据时,将回车换行符进行替换
				src = src.trim();
				byte[] line = new byte[]{13,10};
				src = src.replaceAll("<br>", new String(line));
				return new String(src.getBytes(charset),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		return "";
	}
	
	public static String toISO8859(String src){
		if(src!=null){
			try{return new String(src.getBytes(),"ISO-8859-1");
			}catch(UnsupportedEncodingException e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		return "";
	}
	
	/**
	 * 将空指针转成空字符串
	 * @param src
	 * @return
	 */
	public static String nullToBlank(String src) {
		if (src == null) {
			return new String("");
		}else if ("null".equals(src)) {
			return new String("");
		} else {
			return src;
		}
	}
	
	/**
	 * 字符串是否为空或空串
	 * @param src
	 * @return
	 */
	public static boolean nullOrBlank(String src){
		if(src==null){return true;}
		if(src.equals("")||"null".equals(src)){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断源对象数组是否为空
	 * @param src
	 * @return
	 */
	public static boolean nullOrBlank(Object[] src){
		if(src==null){return true;}
		if(src.length==0){return true;}
		return false;
	}
	
	/**
	 * 判断输入的源List对象是否为空
	 * @param src
	 * @return
	 */
	public static boolean nullOrBlank(List src){
		if(src==null){return true;}
		return src.isEmpty();
	}
	
	/**
	 * 判断是否数字
	 * @return
	 */
	public static boolean isNum(String src){
		for(int i=0,length=src.length(); i<length; i++){
			if(!Character.isDigit(src.charAt(i)))
				return false;
		}
		return true;
	}
	
	public static String getSubString(String src,int length){
		if(src==null){return "";}
		if(src.length()>length){
			return src.substring(0, length)+ "...";
		}
		return src;
	}
	
	public static String makeSysTime(int field,int amount){
		Calendar calendar = Calendar.getInstance();
		calendar.add(field, amount);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(calendar.getTime());
	}
	
	/**
	 * 根据日期格式返回日期类型,可用于组合随机数
	 * @param format 日期格式 如：yyyyMMddHHmmssSSS
	 * @return
	 */
	public static String makeSysTime(String format){
		Calendar calendar = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat(format);
		return df.format(calendar.getTime());
	}
	
	public static String makeSysTime(int pyear,
			int pmonth,int pday,String format){
		Calendar calendar = Calendar.getInstance();
		if(pyear>0){calendar.set(Calendar.YEAR, pyear);}
		if(pmonth>=0&&pmonth<13){calendar.set(Calendar.MONTH, pmonth);}
		if(pday>0&&pday<32){calendar.set(Calendar.DAY_OF_MONTH, pday);}
		
		DateFormat df = new SimpleDateFormat(format);
		return df.format(calendar.getTime());
	}
	
	public static Calendar makeSysTime(String src,String format){
		Calendar calendar = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat(format);
		try {calendar.setTime(df.parse(src));
		} catch (ParseException e) {e.printStackTrace();
		logger.error("", e);}
		return calendar;
	}
	
	public static String makeSysTime(Calendar calendar,String format){
		DateFormat df = new SimpleDateFormat(format);
		return df.format(calendar.getTime());
	}
	
	public static Date makeSysTime(String src,boolean flag){
		String format;
		if(src.indexOf("-")>0){format = "yyyy-MM-dd";}
		else if(src.indexOf("/")>0){format = "yyyy/MM/dd";}
		else{format = "yyyyMMdd";}
		DateFormat df = new SimpleDateFormat(format);
		try {return df.parse(src);}
		catch (ParseException e) {e.printStackTrace();
		logger.error("", e);}
		return null;
	}
	
	public static String makeSysTime(){
		Calendar calendar = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(calendar.getTime());
	}
	
	
	public static String makeSysTime(Date src,String format){
		DateFormat df = new SimpleDateFormat(format);
		return df.format(src);
	}
	
	public static String makeSysTime(long src,String format){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(src);
		return makeSysTime(calendar, format);
	}
	
	public static long makeRanNum(){
		long prefixNum = Long.parseLong(makeSysTime("yyyyMMdd")) * 10000000;
		long suffixNum = Long.parseLong(makeSysTime("HH"))*360000 + 
			Long.parseLong(makeSysTime("mm"))*6000 + 
			Long.parseLong(makeSysTime("ss"))*100  +
			Math.round(Long.parseLong(makeSysTime("SSS"))*0.1);
		
		return prefixNum + suffixNum;
	}
	
	/**
	 * 利用时间返回9位的短ID序号,9位ID序号可保证3年内不重复
	 * @return
	 */
	public static int makeSID(){
		long num = Calendar.getInstance().getTimeInMillis()/100;
		long xid = (num%1000000000L) + 100000000;
 		return (int)(xid);
	}
	
	
	/**
	 * 利用时间返回11位的长ID序号,11位长ID序号可保证300年内不重复
	 * @return
	 */
	public static long makeLID(){
		return (Calendar.getInstance().getTimeInMillis()+1000000000000L)/100;
	}
	
	public static String makeRelativePath(){
		return CodeUtil.class.getResource("/").getPath();
	}
	
	public static Method makeGetMethod(Class classType, String fieldName)throws Exception {
		
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		String getMethodName = "get" + firstLetter + fieldName.substring(1);
		Method getMethod = classType.getMethod(getMethodName, new Class[] {});
		return getMethod;
	}

	public static Method makeSetMethod(Class classType, String fieldName)throws Exception {
		
		Field field = classType.getDeclaredField(fieldName);
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		String setMethodName = "set" + firstLetter + fieldName.substring(1);
		Method setMethod = classType.getMethod(setMethodName,
				new Class[] { field.getType() });
		return setMethod;
	}
	
	/**
	 * 获取客户端IP地址
	 * @return
	 */
	public static String getClientIP(HttpServletRequest request){
		String clientIP = request.getHeader("x-forwarded-for");
		if(CodeUtil.nullOrBlank(clientIP)||"unknown".equalsIgnoreCase(clientIP)){
			clientIP = request.getHeader("Proxy-Client-IP");
		}
		if(CodeUtil.nullOrBlank(clientIP)||"unknown".equalsIgnoreCase(clientIP)){
			clientIP = request.getHeader("WL-Proxy-Client-IP");
		}
		if(CodeUtil.nullOrBlank(clientIP)||"unknown".equalsIgnoreCase(clientIP)){
			clientIP = request.getRemoteAddr();
		}
		return clientIP;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		String paramValue = "*奥迪*";
//		paramValue = paramValue.replace('*', '%');
		System.out.println("value = " + Calendar.getInstance().getTimeInMillis()/10000);
	}

}
