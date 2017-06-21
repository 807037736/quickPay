package com.picc.common.utils;

import ins.framework.common.DateTime;
import ins.framework.utils.BeanUtils;
import ins.framework.utils.StringUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bsh.ParseException;

public class ToolsUtils {
	protected static final Logger logger = LoggerFactory.getLogger(ToolsUtils.class);
	public static final String NONE = "none";
	public static final String SUCCESS = "success";
	public static final String FAILED = "failed";
	public static final String QueryToDoTaskColor_imer = "red";
	public static final String QueryToDoTaskColor_imerAndWant = "#CD0000";
	public static final String QueryToDoTaskColor_Want = "#00CD00";
	public static final String QueryToDoTaskColor_normal = "#000000";

	/**
	 * 判断字符是否存在数组中
	 * 
	 * @param months
	 * @param CurrentMonth
	 *            当前月
	 * @return
	 */
	public static boolean checkArrayIsStr(String[] months, String CurrentMonth) {
		boolean flag = false;
		for (int i = 0; i < months.length; i++) {
			if (months[i].equals(CurrentMonth)) {
				flag = true;
				break;
			}
		}
		return flag;
	}


	/**
	 * 获取机构
	 * 
	 * @param string
	 * @return string
	 */
	public static String getcomcode(String comcode) {
		String comoCde=null;
		 if(notEmpty(comcode)){
	        	if("44030000".equals(comcode)){
	        		comoCde=comcode.substring(0, comcode.length()-4);
	        	}else{
	        		comoCde="00".equals(comcode.substring(comcode.length()-2, comcode.length()))
	                    ? comcode.substring(0, comcode.length()-2)
	                    : comcode;
	        	}
	        }
		 return comoCde;
	}
	/**
	 * 获取当前月份
	 * 
	 * @return
	 */
	public static String getCurrentMonth() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		java.util.Date currTime = new java.util.Date();
		return (currTime.getMonth() + 1) + "";
	}

	/**
	 * 获取当月的第day天
	 * 
	 * @param day
	 * @return
	 */
	public static String getFirstDayOfDayTime(String day, String type) {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		if ("start".equals(type)) {
			return date.format(today).substring(0, 8) + day + " 00:00:00";
		} else {
			return date.format(today).substring(0, 8) + day + " 23:59:59";
		}
	}

	/**
	 * 传入小时 返回日期str
	 * 
	 * @param HH
	 * @return
	 */
	public static String getHHDateStr(String HH, String type) {
		if ("start".equals(type)) {
			return getDate() + " " + HH + ":00:00";
		} else {
			return getDate() + " " + HH + ":59:59";
		}
	}

	/**
	 * 传入小时 返回日期str
	 * 
	 * @param HH
	 * @return
	 */
	public static String getMmDateStr(String mm, String type) {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date today = new Date();
		if ("start".equals(type)) {
			return date.format(today) + " " + mm + ":00";
		} else {
			return date.format(today) + " " + mm + ":59";
		}
	}

	/**
	 * 返回日期
	 * 
	 * @param dateStr
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date getFormatDate(String dateStr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateTemp = null;
		try {
			dateTemp = format.parse(dateStr);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			logger.error("", e);
			
		}
		return dateTemp;
	}

	/**
	 * 返回日期
	 * 
	 * @param
	 * @return
	 */
	public static Date getFormatDate(String dateStr, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date dateTemp = null;
		try {
			dateTemp = format.parse(dateStr);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return dateTemp;
	}

	/**
	 * 获取当前日期
	 * 
	 * @return
	 */
	public static String getDate() {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		return date.format(today);
	}
	
	/**
	 * 获取字符串类型的当前时间
	 * 
	 * @return
	 */
	public static String getTime() {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		return date.format(today);
	}
	/**
	 * 获取时间类型的当前时间
	 * @return
	 */
	public static Date getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		Date dateTemp = null;
		try {
			dateTemp = format.parse(format.format(today));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return dateTemp;
	}

	/**
	 * 获取时间类型的当前日期
	 * @return
	 */
	public static Date getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		Date dateTemp = null;
		try {
			dateTemp = format.parse(format.format(today));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return dateTemp;
	}
	
	/**
	 * 获得当月第一天
	 * 
	 * @return
	 */
	public static String getFirstDayOfMonth() {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		return date.format(today).substring(0, 8) + "01";
	}

	/**
	 * 取得当前日期，格式由参数format决定
	 * 
	 * @return String
	 */
	public static String getCurrentDate(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

		return simpleDateFormat.format(cal.getTime());
	}

	/**
	 * 获得给定年月的最后一天
	 * 
	 * @param firstDay
	 * @return
	 */
	public static String getLastDayOfMonth(String startDate) {
		String[] t = startDate.split("-");
		String year = t[0];
		String month = t[1];
		Calendar cal = Calendar.getInstance();
		// 年
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		// 月，因为Calendar里的月是从0开始，所以要-1
		cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		// 日，设为一号
		cal.set(Calendar.DATE, 1);
		// 月份加一，得到下个月的一号
		cal.add(Calendar.MONTH, 1);
		// 下一个月减一为本月最后一天
		cal.add(Calendar.DATE, -1);
		return t[0] + "-" + t[1] + "-"
				+ String.valueOf(cal.get(Calendar.DAY_OF_MONTH));// 获得月末是几号
	}

	/**
	 * 判断闰年
	 * 
	 * @param year
	 * @return
	 * 
	 * 
	 */
	public static boolean isLeap(int year) {
		if (((year % 100 == 0) && year % 400 == 0)
				|| ((year % 100 != 0) && year % 4 == 0)){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * 获取给定月份的天数
	 * 
	 * @param date
	 * @return
	 * 
	 * 
	 */
	public static String getDayOfMonth(String date) {
		return subString(getLastDayOfMonth(date), 2);
	}

	public static String getFirstDayOfNextMonth(String lastDayOfMonth) {
		String[] t = lastDayOfMonth.split("-");
		String year = t[0];
		String month = t[1];
		if (month.equals("12")) {
			month = "01";
			year = String.valueOf(Integer.parseInt(year) + 1);
		} else {
			month = String.valueOf(Integer.parseInt(month) + 1);
			if (month.length() == 1) {
				month = "0" + month;
			}
		}
		return year + "-" + month + "-" + "01";
	}

	/**
	 * 文件压缩
	 * 
	 * @param dir
	 * @param zipfile
	 * @param DirectoryName
	 * @return
	 * @throws IOException
	 */
	public static void zipDirectory(String dir, String zipfile)
			throws IOException {
		// 建立压缩文件输入流
		File dFile = new File(dir);
		if (!dFile.isDirectory()) {
			throw new IllegalArgumentException("Exception: not a directory!");
		}
		String[] entries = dFile.list();
		// 设置缓冲区大小
		byte[] buffer = new byte[4096];
		int bytes_read;
		// 创建一个流来压缩数据并存入zipfile文件中
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
		// 遍历目录所有项
		int entriesLength = entries.length;
		for (int i = 0; i < entriesLength; i++) {
			File f = new File(dFile, entries[i]);
			if (f.isDirectory()) {
				// System.out.println("this file is a directory!");
				continue;
			}
			// 只打包excel文件
			if (f.getName().indexOf(".zip") != -1) {
				continue;
			}
			FileInputStream in = new FileInputStream(f);
			ZipEntry entry = new ZipEntry(entries[i]);
			out.putNextEntry(entry);
			while ((bytes_read = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytes_read);
			}
			in.close();
		}
		out.close();
		// 删除生成的excel文件
		File[] child = dFile.listFiles();
		if (child != null && child.length != 0) {
			for (int j = 0; j < child.length; j++) {
				if (child[j].getName().indexOf(".zip") != -1) {
					continue;
				}
				child[j].delete();
			}
		}
	}

	public static String getSelectTime() {
		StringBuffer selectTime = new StringBuffer();
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		Date year = new Date();
		String thisYear = df.format(year);
		selectTime.append("<select name=year>");
		selectTime
				.append("<option value=" + (Integer.parseInt(thisYear) + 1)
						+ ">").append(Integer.parseInt(thisYear) + 1)
				.append("</option>");
		selectTime.append("<option selected value=" + thisYear + ">")
				.append(Integer.parseInt(thisYear)).append("</option>");
		selectTime
				.append("<option value=" + (Integer.parseInt(thisYear) - 1)
						+ ">").append(Integer.parseInt(thisYear) - 1)
				.append("</option>");
		selectTime
				.append("<option value=" + (Integer.parseInt(thisYear) - 2)
						+ ">").append(Integer.parseInt(thisYear) - 2)
				.append("</option>");
		selectTime
				.append("<option value=" + (Integer.parseInt(thisYear) - 3)
						+ ">").append(Integer.parseInt(thisYear) - 3)
				.append("</option>");
		selectTime
				.append("<option value=" + (Integer.parseInt(thisYear) - 4)
						+ ">").append(Integer.parseInt(thisYear) - 4)
				.append("</option>");
		selectTime
				.append("<option value=" + (Integer.parseInt(thisYear) - 5)
						+ ">").append(Integer.parseInt(thisYear) - 5)
				.append("</option>");
		selectTime
				.append("<option value=" + (Integer.parseInt(thisYear) - 6)
						+ ">").append(Integer.parseInt(thisYear) - 6)
				.append("</option>");
		selectTime
				.append("<option value=" + (Integer.parseInt(thisYear) - 7)
						+ ">").append(Integer.parseInt(thisYear) - 7)
				.append("</option>");
		selectTime
				.append("<option value=" + (Integer.parseInt(thisYear) - 8)
						+ ">").append(Integer.parseInt(thisYear) - 8)
				.append("</option>");
		selectTime.append("</select>");
		return selectTime.toString();
	}

	/**
	 * 上传文件至服务器指定路径,并返回上传成功文件的名称
	 * 
	 * @param filePath
	 * @param fileName
	 * @throws Exception
	 * 
	 */
	public static String uploadFileToServer(String filePath,
			File customerImpFile) throws Exception {
			
		String changeFileName = changeFileName(customerImpFile.getName());// 修改上传文件名称
		File descFile = new File(filePath, changeFileName);
		InputStream fis = null;
		OutputStream fos = null;
		try {
			byte[] buffer = new byte[1024];
			fis = new FileInputStream(customerImpFile);
			fos = new FileOutputStream(descFile);
			while ((fis.read(buffer, 0, buffer.length)) != -1) {
				fos.write(buffer, 0, buffer.length);
			}
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			throw e;
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (Exception ne) {
				throw ne;
			}
		}
		return changeFileName;
	}
	
	
	/**
	 * 上传文件至服务器指定路径,并返回上传成功文件的名称
	 * 
	 * @param filePath	目的文件路径
	 * @param fileName	目的文件名
	 * @param uploadFile	上传的原文件
	 * @throws Exception
	 * 
	 */
	public static String uploadFileToServer(String filePath, String fileName, 
			File uploadFile) throws Exception {
			
		if(isEmpty(fileName)) {
			fileName = changeFileName(uploadFile.getName());// 修改上传文件名称
		}
		File descFile = new File(filePath, fileName);
		InputStream fis = null;
		OutputStream fos = null;
		try {
			byte[] buffer = new byte[1024];
			fis = new FileInputStream(uploadFile);
			fos = new FileOutputStream(descFile);
			while ((fis.read(buffer, 0, buffer.length)) != -1) {
				fos.write(buffer, 0, buffer.length);
			}
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			throw e;
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (Exception ne) {
				throw ne;
			}
		}
		return descFile.getAbsolutePath();
	}
	
	
	/**
	 * 上传图片至服务器指定路径,并返回上传成功文件的名称
	 * 
	 * @param filePath
	 * @param fileName
	 * @throws Exception
	 * 
	 */
	public static String uploadPictureToServer(String filePath,
			File customerImpFile,String uploadContentType) throws Exception {
		
		String changeFileName = changePictureName(customerImpFile.getName(),uploadContentType);// 修改上传文件名称
		File descFile = new File(filePath, changeFileName);
		InputStream fis = null;
		OutputStream fos = null;
		try {
			byte[] buffer = new byte[1024];
			fis = new FileInputStream(customerImpFile);
			fos = new FileOutputStream(descFile);
			while ((fis.read(buffer, 0, buffer.length)) != -1) {
				fos.write(buffer, 0, buffer.length);
			}
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			throw e;
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (Exception ne) {
				throw ne;
			}
		}
		return changeFileName;
	}
	
	
	/**
	 * 统一修改文件名
	 * 
	 * @param outputFileName
	 * @return String Author Skify Jun 13, 2011
	 */
	public static String changePictureName(String outputFileName,String uploadContentType) {
		
		int index = outputFileName.lastIndexOf(".");
		String fileType = "";
		
		if (index != -1) {
			if("image/jpeg".equals(uploadContentType)){
				fileType=".jpg";
			}else if("image/bmp".equals(uploadContentType)){
				fileType=".bmp";
			}else if("image/png".equals(uploadContentType)){
				fileType=".png";
			}else if("image/gif".equals(uploadContentType)){
				fileType=".gif";
			}else{
				fileType=outputFileName.substring(index);
			}
		}
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");

		return simpleDateFormat.format(date) + fileType;
	}
	
	/**
	 * 下载附件
	 * 
	 * @param filePath
	 *            ,filePath+fileName 路径需加上文件名
	 * @param fileName
	 * @param response
	 * @throws Exception
	 *             void Author Skify Jun 13, 2011
	 */
	public static void download(String filePath, String fileName,
			HttpServletResponse response) throws Exception {

		// 转化为UTF-8避免文件名乱码
		// fileName = changeFileName(fileName);

		response.reset();
		response.setContentType("application/x-msdownload");
		response.addHeader("Content-Disposition", "attachment;   filename=\""
				+ new String(fileName.getBytes(), "ISO8859-1") + "\"");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(filePath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[20480];
			while ((bis.read(buff, 0, buff.length)) != -1) {
				bos.write(buff, 0, buff.length);
			}
			bos.flush();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			throw e;
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (Exception ne) {
				throw ne;
			}
		}
	}

	/**
	 * 统一修改文件名
	 * 
	 * @param outputFileName
	 * @return String Author Skify Jun 13, 2011
	 */
	public static String changeFileName(String outputFileName) {
		int index = outputFileName.lastIndexOf(".");
		String fileType = "";
		if (index != -1) {
			fileType = outputFileName.substring(index);
		}
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");

		return simpleDateFormat.format(date) + fileType;
	}

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 *            void Author Skify Jun 13, 2011
	 */
	public static void deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
	}

	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(List list) {
		if (list == null || list.size() < 1) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Double dou) {
		if (dou == null || "".equals(dou)) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(String[] items) {
		if (items == null || items.length < 1) {
			return true;
		}
		return false;
	}

	public static boolean notEmpty(String str) {
		if (str != null && !"".equals(str.trim())) {
			return true;
		}
		return false;
	}

	public static boolean notEmpty(List list) {
		if (list != null && list.size() > 0&&list.get(0)!=null) {
			return true;
		}
		return false;
	}

	public static boolean notEmpty(Double dou) {
		if (dou != null || !"".equals(dou)) {
			return true;
		}
		return false;
	}

	public static boolean notEmpty(String[] items) {
		if (items != null && items.length > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 取得默认的日期格式
	 * 
	 * @return
	 */
	public static String getDefaultDateFormat() {
		// 用方法的形式提供默认的日期格式，便于维护日期格式的取得来源和格式内容。
		return "yyyy-MM-dd";
	}

	/**
	 * 取得空字符串
	 * 
	 * @return
	 */
	public static String getDefaultString(String str) {
		if (str != null && !"".equals(str.trim())) {
			return str;
		}
		return " ";
	}

	public static Double getDefaultData(Double dou) {
		if (dou != null || !"".equals(dou)) {
			return dou;
		}
		return dou = 0.0000;
	}

	/**
	 * 本方法用于将yyyy-MM-dd型的字符串转换成日期类型 取得参数日期的偏移日期，参数date格式为yyyy-MM-dd
	 * 当输入addDay为正数时，为基准日期的后N天；负数时，为为基准日期的前N天
	 * 列1：参数date为2008-01-01，addDay为-1，返回值为2007-12-31
	 * 列2：参数date为2008-01-01，addDay为 1，返回值为2008-01-02
	 * 列3：参数date为2008-01-01，addDay为 0，返回值为2008-01-01
	 * 
	 * @param strDate
	 * @param addDay
	 * @return
	 * @throws Exception
	 *             Date
	 */
	public static Date getDateObjectAfter(String strDate, int addDay)
			throws Exception {

		if (strDate == null || strDate.trim().length() == 0) {
			return null;
		}

		Date date = null;
		SimpleDateFormat simdf = new SimpleDateFormat(getDefaultDateFormat());
		try {
			date = simdf.parse(strDate);

			Calendar cal = Calendar.getInstance();
			// String[] strArray = strDate.split("-");
			// int year = Integer.parseInt(strArray[0]);
			// int month = Integer.parseInt(strArray[1]);
			// int day = Integer.parseInt(strArray[2]);

			// Calendar cal = Calendar.getInstance();

			// cal.set(year, month - 1, day, 0, 0, 0);
			cal.setTime(date);
			// 计算出相应的日期
			cal.add(Calendar.DATE, addDay);

			return cal.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			throw e;
		}
	}

	/**
	 * 选择对年或者月或者日进行增减操作 选择1-年 选择2-月 选择3-日
	 * 
	 * @param choice
	 * @param strDate
	 * @param values
	 * @return
	 * @throws Exception
	 * 
	 *             return Date
	 */
	public static Date getNewDate(int choice, String strDate, int values)
			throws Exception {
		if (strDate == null || strDate.trim().length() == 0) {
			return null;
		}
		Date date = null;
		SimpleDateFormat simdf = new SimpleDateFormat(getDefaultDateFormat());
		try {
			date = simdf.parse(strDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			if (1 == choice) {
				cal.add(Calendar.YEAR, values);
			} else if (2 == choice) {
				cal.add(Calendar.MONTH, values);
			} else if (3 == choice) {
				cal.add(Calendar.DATE, values);
			}
			return java.sql.Date.valueOf(simdf.format(cal.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			throw e;
		}
	}

	/**
	 * Date类型格式化
	 * 
	 * @return String
	 */
	public static Date getFormatDate(Date date, String format) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		String s1 = simpleDateFormat.format(date);
		return java.sql.Date.valueOf(simpleDateFormat.format(date));
	}

	/**
	 * 转换Date为指定格式的字符串
	 * 
	 * @return String
	 */
	public static String convertDateToString(Date date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}

	/**
	 * 本方法用于将yyyy-MM-dd型的字符串转换成日期类型，如果strDate为Null或者为空字符串,则返回null
	 * 
	 * @param strDate
	 * @return
	 * @throws Exception
	 *             Date
	 */
	public static Date getDateObject(String strDate) throws Exception {
		Date dt_Date1 = null;
		if (strDate == null || strDate.trim().length() == 0) {
			return null;
		}
		SimpleDateFormat simdf = new SimpleDateFormat(getDefaultDateFormat());
		try {
			dt_Date1 = simdf.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			throw e;
		}
		return dt_Date1;
	}

	/**
	 * 检测是否数字
	 * 
	 * @param string
	 * @return boolean
	 */
	public static boolean checkIsInteger(String string) {
		if (isEmpty(string)) {
			return false;
		}
		char[] c = string.toCharArray(); // 把输入的字符串转成字符数组
		for (int i = 0; i < c.length; i++) {
			if (!Character.isDigit(c[i])) { // 判断是否为数字
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取时间年月日时分+4位随机数
	 * 
	 * @param number
	 * @return String
	 */
	public static String getRandomNo() {
		Random random = new Random();
		String numbers = getCurrentDate("yyyyMMddhhmmss");
		int ranInt = 0;
		while (true) {
			ranInt = random.nextInt(9999);
			if (ranInt > 999) {
				return numbers + String.valueOf(ranInt);
			}
		}
	}

	/**
	 * 检测是否浮点数字
	 * 
	 * @param string
	 * @return boolean
	 */
	public static boolean checkIsFloat(String string) {
		if (isEmpty(string)) {
			return false;
		}
		if ("-".equals(string.substring(0, 1))) {
			string = string.substring(1, string.length());
		}
		char[] c = string.toCharArray(); // 把输入的字符串转成字符数组
		if (string.indexOf(".") != -1) {
			String[] items = string.split(".");
			if (items.length != 2) {
				return false;
			} else {
				char[] cc = (items[0] + items[1]).toCharArray();
				for (int i = 0; i < cc.length; i++) {
					if (!Character.isDigit(cc[i])) { // 判断是否为数字
						return false;
					}
				}
			}
		} else {
			for (int i = 0; i < c.length; i++) {
				if (!Character.isDigit(c[i])) { // 判断是否为数字
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 检测是否yyyy-MM-dd日期类型
	 * 
	 * @param string
	 * @return boolean
	 */
	public static boolean checkIsDate(String string) {
		if (isEmpty(string)) {
			return false;
		}
		string = string.trim();
		if (string.length() != 10) {
			return false;
		}
		// 年
		String year = string.substring(0, 4);
		char[] years = string.substring(0, 4).toCharArray();
		for (int i = 0; i < years.length; i++) {
			if (!Character.isDigit(years[i])) { // 判断是否为数字
				return false;
			}
		}
		Integer yearIn = Integer.valueOf(year);
		// 符号
		if (!"-".equals(string.substring(4, 5))
				|| !"-".equals(string.substring(7, 8))) {
			return false;
		}
		// 月
		String month = string.substring(5, 7);
		char[] months = month.toCharArray();
		for (int i = 0; i < months.length; i++) {
			if (!Character.isDigit(months[i])) { // 判断是否为数字
				return false;
			}
		}
		Integer monthIn = Integer.valueOf(month);
		if (monthIn > 12) {
			return false;
		}
		// 日
		String day = string.substring(8, 10);
		char[] days = day.toCharArray();
		for (int i = 0; i < days.length; i++) {
			if (!Character.isDigit(days[i])) { // 判断是否为数字
				return false;
			}
		}
		Integer dayIn = Integer.valueOf(day);
		if (dayIn > 31) {
			return false;
		}
		// 月日
		if ("02".equals(month)) {
			if ("00".equals(year.substring(2, 4))) {
				if (yearIn % 400 == 0) {
					if (dayIn > 29) {
						return false;
					}
				} else {
					if (dayIn > 28) {
						return false;
					}
				}
			} else {
				if (yearIn % 4 == 0) {
					if (dayIn > 29) {
						return false;
					}
				} else {
					if (dayIn > 28) {
						return false;
					}
				}
			}
		} else if ("01".equals(month) || "03".equals(month)
				|| "05".equals(month) || "07".equals(month)
				|| "08".equals(month) || "10".equals(month)
				|| "12".equals(month)) {
			if (dayIn > 31) {
				return false;
			}
		} else {
			if (dayIn > 30) {
				return false;
			}
		}
		return true;
	}


	/**
	 * 校验手机号码是否正确
	 * 
	 * @param phone
	 * @return
	 * 
	 */
	public static boolean isMobile(String phone) {
		// Pattern pattern =
		// Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Pattern pattern = Pattern.compile("^1[3|4|5|8][0-9]\\d{8}$");
		Matcher matcher = pattern.matcher(phone);

		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 校验固定电话是否正确
	 * 
	 * @param phone
	 * @return
	 * 
	 */
	public static boolean isTel(String phone) {
//			String a[] = phone.split("-");

//			if (a.length != 1) {
			Pattern pattern = Pattern.compile("^((\\d{2,3})|(\\d{3}-))?((0\\d{2,3})|(0\\d{2,3}-))?[1-9]\\d{6,7}(-\\d{1,4})?$");
//				Pattern pattern = Pattern.compile("^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})(\\-[0-9]{1,4})?$");
				//Pattern pattern = Pattern.compile("^([0-9]{3,4}-)?([0-9]{7,10})*$");
				Matcher matcher = pattern.matcher(phone);

				if (matcher.matches()) {
					return true;
				} else {
					return false;
				}
//			} else {
//				char[] no = phone.toCharArray();
//				for (int i = 0; i < no.length; i++) {
//					if (!Character.isDigit(no[i])) {
//						return false;
//					}
//				}
//				return true;
//			}
//			return false;
		}


	/**
	 * 校验车牌号是否正确
	 * 
	 * @param no
	 * @return
	 * 
	 */
	public static boolean isLicenseNo(String no) {
		// Pattern pattern = Pattern.compile("^({7,10})*$");
		// Matcher matcher = pattern.matcher(no);

		if (no.length() < 10) {
			return true;
		}
		return false;
	}

	/**
	 * 校验日期是否是日期格式，如2012-12-21
	 * 
	 * @param sDate
	 * @return
	 * 
	 */
	public static boolean isValidDate(String sDate) {
		String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
		String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
				+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
				+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
				+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		if ((sDate != null)) {
			Pattern pattern = Pattern.compile(datePattern1);
			Matcher match = pattern.matcher(sDate);
			if (match.matches()) {
				pattern = Pattern.compile(datePattern2);
				match = pattern.matcher(sDate);
				return match.matches();
			} else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 校验日期是否是日期格式，如2012/12/21
	 * @param strDate
	 * @return
	 */
	public static boolean isValidDate1(String strDate) {
		String re = "^(([0-9]{4}|[1-9][0-9]{3})/(((0[13578]|1[02])/(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)/(0[1-9]|[12][0-9]|30))|(02/(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))/02/29)";
		if (strDate.matches(re)) {
			return true;
		}
		return false;
	}


	/**
	 * 处理字符串中间存在多个逗号或者最后一位是逗号的问题
	 * 
	 * @param str
	 * @return
	 */
	public static String dealSymbol(String str) {
		if (str != null && str.trim().length() > 0) {
			while (str.indexOf(",,") > -1) {
				str.replaceAll(",,", ",");
			}
			if (str.charAt(str.length() - 1) == ',') {
				str = str.substring(0, str.length() - 1);
			}
		}
		return str;
	}

	/**
	 * 传递的日期大于今天,则把年份+1返回
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDate5FlowNode(Date date) {
		if (null == date) {
			return null;
		}
		Calendar objCalendar = Calendar.getInstance();
		Date dReturn = null;
		try {
			// 当前年份
			int year = objCalendar.get(objCalendar.YEAR);
			objCalendar.setTime(date);
			objCalendar.set(Calendar.YEAR, year);
			// 当前日期如果比传递过来的时间早,则把传递过来的时间+1年返回.
			if (objCalendar.getTime().getTime() - new Date().getTime() < 0) {
				objCalendar.set(Calendar.YEAR, year + 1);
			}
			dReturn = objCalendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return dReturn;
	}

	/**
	 * 简单校验身份证号码，为15、18位，仅校验位数即可
	 * 
	 * @param card
	 * @return
	 * 
	 * @throws ParseException
	 */
	public static boolean isIDCard(String card) {
		if (!"".equals(card)) {
			if (card.length() == 15 || card.length() == 18) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	/**
	 *  判断15位身份证的合法性
	 * 
	 * @param card
	 * @return
	 * 
	 * @throws ParseException
	 */
//	public static boolean isValidate15Idcard(String idcard) {
//		Pattern pattern = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
//		Matcher matcher = pattern.matcher(idcard);
//		if (matcher.matches()) {
//			return true;
//		} else {
//			return false;
//		}
//	}

	/**
	 * 判断18位身份证的合法性
	 * 
	 * @param idcard
	 * @return
	 */
//	public static boolean isValidate18Idcard(String idcard) {
//		boolean flag = true;
//		// 非18位为假
//		if (idcard.length() != 18) {
//			return flag = false;
//		}
//		// 获取前17位
//		String idcard17 = idcard.substring(0, 17);
//		// 获取第18位
//		String idcard18Code = idcard.substring(17, 18);
//		char c[] = null;
//		String checkCode = "";
//		// 是否都为数字
//		if (isDigital(idcard17)) {
//			c = idcard17.toCharArray();
//		} else {
//			return flag = false;
//		}
//
//		if (null != c) {
//			int bit[] = new int[idcard17.length()];
//
//			bit = converCharToInt(c);
//
//			int sum17 = 0;
//
//			sum17 = getPowerSum(bit);
//
//			// 将和值与11取模得到余数进行校验码判断
//			checkCode = getCheckCodeBySum(sum17);
//			if (null == checkCode) {
//				return flag = false;
//			}
//			// 将身份证的第18位与算出来的校码进行匹配，不相等就为假
//			if (!idcard18Code.equalsIgnoreCase(checkCode)) {
//				return flag = false;
//			}
//		}
//		return flag;
//	}
	
	/**
	 * 身份证合法性校验
	 * @param StrNo
	 * @return
	 */
	public static boolean isChinaIDCard(String StrNo) {
	  	String num = StrNo;
	  	num = num.toUpperCase();  
		StrNo = num.toString();
		if (StrNo.length() == 15) {
	    	String arrSplit1 = StrNo.substring(6, 8);
	    	String arrSplit2 = StrNo.substring(8, 10);
	    	String arrSplit3 = StrNo.substring(10, 12);
	    	
	    	// 检查生日日期是否正确
	    	String dtmBirth = "19" + arrSplit1 + "-" + arrSplit2 + "-" + arrSplit3; 
			if (!isValidDate(dtmBirth)) {
				return false;
			}
			
		} else if (StrNo.length() == 18) {
			String arrSplit1 = StrNo.substring(6, 10);
	    	String arrSplit2 = StrNo.substring(10, 12);
	    	String arrSplit3 = StrNo.substring(12, 14);
	      	// 检查生日日期是否正确
	     	String dtmBirth = arrSplit1 + "-" + arrSplit2 + "-" + arrSplit3; 
			if (!isValidDate(dtmBirth)) {
				return false;
			}
		} else {
			//"输入的身份证号码必须为15位或者18位！");
			return false;
		}

		if (StrNo.length() == 18) {
			//var a, b, c
			int a = 0;
			int b = 0;
			String c1 ="";
			int c = 0;
			if (!checkIsInteger(StrNo.substring(0, 17))) {
				//"身份证号码错误,前17位不能含有英文字母！");
				return false;
			}
			a = Integer.parseInt(StrNo.substring(0, 1)) * 7 + Integer.parseInt(StrNo.substring(1, 2)) * 9
					+ Integer.parseInt(StrNo.substring(2, 3)) * 10;
			a = a + Integer.parseInt(StrNo.substring(3, 4)) * 5 + Integer.parseInt(StrNo.substring(4, 5))
					* 8 + Integer.parseInt(StrNo.substring(5, 6)) * 4;
			a = a + Integer.parseInt(StrNo.substring(6, 7)) * 2 + Integer.parseInt(StrNo.substring(7, 8))
					* 1 + Integer.parseInt(StrNo.substring(8, 9)) * 6;
			a = a + Integer.parseInt(StrNo.substring(9, 10)) * 3
					+ Integer.parseInt(StrNo.substring(10, 11)) * 7
					+ Integer.parseInt(StrNo.substring(11, 12)) * 9;
			a = a + Integer.parseInt(StrNo.substring(12, 13)) * 10
					+ Integer.parseInt(StrNo.substring(13, 14)) * 5
					+ Integer.parseInt(StrNo.substring(14, 15)) * 8;
			a = a + Integer.parseInt(StrNo.substring(15, 16)) * 4
					+ Integer.parseInt(StrNo.substring(16, 17)) * 2;
			b = a % 11;
			if (b == 2) // 最后一位为校验位
			{
				c1 = StrNo.substring(17, 18).toUpperCase(); // 转为大写X
			} else {
				int c2 = Integer.parseInt(StrNo.substring(17, 18));
				c = c2;
			}
			switch (b) {
			case 0:
				if (c != 1) {
					//"身份证号码校验位错:最后一位应该为:1");
					return false;
				}
				break;
			case 1:
				if (c != 0) {
					//"身份证号码校验位错:最后一位应该为:0");
					return false;
				}
				break;
			case 2:
				if (!("X".equals(c1))) {
					//"身份证号码校验位错:最后一位应该为:X");
					return false;
				}
				break;
			case 3:
				if (c != 9) {
					//"身份证号码校验位错:最后一位应该为:9");
					return false;
				}
				break;
			case 4:
				if (c != 8) {
					//"身份证号码校验位错:最后一位应该为:8");
					return false;
				}
				break;
			case 5:
				if (c != 7) {
					//"身份证号码校验位错:最后一位应该为:7");
					return false;
				}
				break;
			case 6:
				if (c != 6) {
					//"身份证号码校验位错:最后一位应该为:6");
					return false;
				}
				break;
			case 7:
				if (c != 5) {
					//"身份证号码校验位错:最后一位应该为:5");
					return false;
				}
				break;
			case 8:
				if (c != 4) {
					//"身份证号码校验位错:最后一位应该为:4");
					return false;
				}
				break;
			case 9:
				if (c != 3) {
					//"身份证号码校验位错:最后一位应该为:3");
					return false;
				}
				break;
			case 10:
				if (c != 2) {
					//"身份证号码校验位错:最后一位应该为:2");
					return false;
				}
			}
		} else {// 15位身份证号
			if (!checkIsInteger(StrNo)) {
				//"身份证号码错误,前15位不能含有英文字母！");
				return false;
			}
		}
		return true;

	}

	/**
	 * 数字验证
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDigital(String str) {
		return str == null || "".equals(str) ? false : str.matches("^[0-9]*$");
	}

	/**
	 * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
	 * 
	 * @param bit
	 * @return
	 */
	public static int getPowerSum(int[] bit) {
		int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
		int sum = 0;

		if (power.length != bit.length) {
			return sum;
		}

		for (int i = 0; i < bit.length; i++) {
			for (int j = 0; j < power.length; j++) {
				if (i == j) {
					sum = sum + bit[i] * power[j];
				}
			}
		}
		return sum;
	}

	/**
	 * 将和值与11取模得到余数进行校验码判断
	 * 
	 * @param checkCode
	 * @param sum17
	 * @return 校验位
	 */
	public static String getCheckCodeBySum(int sum17) {
		String checkCode = null;
		switch (sum17 % 11) {
		case 10:
			checkCode = "2";
			break;
		case 9:
			checkCode = "3";
			break;
		case 8:
			checkCode = "4";
			break;
		case 7:
			checkCode = "5";
			break;
		case 6:
			checkCode = "6";
			break;
		case 5:
			checkCode = "7";
			break;
		case 4:
			checkCode = "8";
			break;
		case 3:
			checkCode = "9";
			break;
		case 2:
			checkCode = "x";
			break;
		case 1:
			checkCode = "0";
			break;
		case 0:
			checkCode = "1";
			break;
		}
		return checkCode;
	}

	/**
	 * 将字符数组转为整型数组
	 * 
	 * @param c
	 * @return
	 * @throws NumberFormatException
	 */
	public static int[] converCharToInt(char[] c) throws NumberFormatException {
		int[] a = new int[c.length];
		int k = 0;
		for (char temp : c) {
			a[k++] = Integer.parseInt(String.valueOf(temp));
		}
		return a;
	}

	/**
	 * 得到between之间的查询条件
	 * 
	 * @param start
	 * @param end
	 * @param column
	 * @param type
	 *            可以为日期或者为浮点型
	 * @return
	 * 
	 */
	public static String getBetweenSqlCondition(String start, String end,
			String column, String type) {
		StringBuilder sql = new StringBuilder(100);
		if ("Date".equals(type)) {
			if ((start != null && start.length() > 0)
					&& (end == null || end.length() <= 0)) {
				sql.append("and ")
						.append(column)
						.append(">=to_date('" + start
								+ " 00:00:00', 'YYYY-MM-DD HH24:MI:SS') ");
			} else if ((start == null || start.length() <= 0)
					&& (end != null && end.length() > 0)) {
				sql.append("and ")
						.append(column)
						.append("<=to_date('" + end
								+ " 23:59:59', 'YYYY-MM-DD HH24:MI:SS') ");
			} else if ((start != null && start.length() > 0)
					&& (end != null && end.length() > 0)) {
				sql.append("and ")
						.append(column)
						.append(" between to_date('"
								+ start
								+ " 00:00:00', 'YYYY-MM-DD HH24:MI:SS') and to_date('"
								+ end + " 23:59:59', 'YYYY-MM-DD HH24:MI:SS') ");
			}
		}
		if ("Float".equals(type)) {
			if ((start != null && start.length() > 0)
					&& (end == null || end.length() <= 0)) {
				sql.append("and ").append(column).append(">='" + start + "' ");
			} else if ((start == null || start.length() <= 0)
					&& (end != null && end.length() > 0)) {
				sql.append("and ").append(column).append("<='" + end + "' ");
			} else if ((start != null && start.length() > 0)
					&& (end != null && end.length() > 0)) {
				sql.append("and ").append(column)
						.append(" between '" + start + "' and '" + end + "' ");
			}
		}
		return sql.toString();
	}

	/**
	 * 把字符串中所有特殊字符，全角半角，统统都干掉，且字母变大写＞＿＜
	 * 
	 * @param str
	 * @return
	 * 
	 */
	public static String isFilter(String str) {
		String s = str.toUpperCase();// 將所有字母大写
		// 所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',_\\[\\].<>/?＄＆＠％＊＾＿－＋＝］［＼｜~！／．＞＜@#￥%……&*（）——+|{}【】‘；：”“’。，、 \\-？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(s);
		return m.replaceAll("").trim();
	}

	/**
	 * 截取字符串指定后num位
	 * 
	 * @param tempStr
	 * @param num
	 * @return
	 * 
	 */
	public static String subString(String tempStr, int num) {
		return tempStr.substring(tempStr.length() - num);
	}

	/**
	 * 根据userposition截取机构代码
	 * 
	 * @param userPosition
	 * @param comCode
	 * @return
	 */
	public static String getComCodeByPosition(String userPosition,
			String comCode) {
		String resultComCode = "";

		if ("0".equals(userPosition) || "8".equals(userPosition)
				|| "9".equals(userPosition)) {// 分公司权限,8-分公司质检员，9-普通质检员
			if (tailEvenTrim(comCode).length() >= 4) {// 如果去除0长度大于等于4位，加上%返回，比如44030000，将返回4403%
				resultComCode = tailEvenTrim(comCode) + "%";
			} else {// 如果去除0长度大于等于4位，加上%返回，比如44000000，将返回4400%
				resultComCode = comCode.substring(0, 4) + "%";
			}
		} else if ("1".equals(userPosition)) {// 支公司权限
			if (tailEvenTrim(comCode).length() >= 6) {// 如果去除0长度大于等于6位，加上%返回，比如44030100，将返回440301%
				resultComCode = tailEvenTrim(comCode) + "%";
			} else {// 如果去除0长度大于等于6位，加上%返回，比如44030000，将返回4403%
				resultComCode = comCode.substring(0, 6) + "%";
			}
		} else if ("2".equals(userPosition)) {// 办事处权限：直接返回机构代码
			resultComCode = comCode;
		}
		return resultComCode;
	}

	/**
	 * 尾部偶数截零法处理机构代码
	 * 
	 * @param companyCode
	 *            机构代码
	 * @return 尾部偶数截零法处理后的机构代码
	 */
	private static String tailEvenTrim(String companyCode) {
		if (companyCode == null || companyCode.trim().length() == 0) {
			return "";
		}
		while (companyCode.endsWith("00")) {
			companyCode = companyCode.substring(0, companyCode.length() - 2);
		}
		return companyCode;
	}

	/**
	 * 判断object是否为空（null或""）
	 * 
	 * @param object
	 * @param propertyArray
	 * @return boolean（true-全为空 false-至少有一个不为空
	 * 
	 */
	public static String isObjectEmpty(Object object) {
		// 数组为空或没有指定属性也返回true
		if (object == null) {
			return "";
		} else {
			return object.toString();
		}

	}

	/**
	 * 去除字符串中的空格、换行、制表符、回车
	 * 
	 * @param str
	 * @return
	 * 
	 * 
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 判断日期是否包含时间
	 * 
	 * @param date
	 * @return
	 */
	public static boolean hasTime(Date date) {
		if (date.getHours() == 0 && date.getMinutes() == 0
				&& date.getSeconds() == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 创建文件夹 void@param fileName
	 * 
	 */
	public static void makeDirectory(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			// 如果要创建的多级目录不存在才需要创建。
			file.mkdirs();
			;
		}
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
		}
		return flag;
	}

	/**
	 * 检测邮箱地址是否合法
	 * 
	 * @param email
	 * @return true合法 false不合法
	 */
	public static boolean isEmail(String email) {
		if (null == email || "".equals(email)) {
			return false;
		} else {
			// Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
			Pattern p = Pattern
					.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
			Matcher m = p.matcher(email);
			return m.matches();
		}

	}

	/**
	 * 功能：设置地区编码
	 * 
	 * @return Hashtable 对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Hashtable GetAreaCode() {
		Hashtable hashtable = new Hashtable();
		hashtable.put("11", "北京");
		hashtable.put("12", "天津");
		hashtable.put("13", "河北");
		hashtable.put("14", "山西");
		hashtable.put("15", "内蒙古");
		hashtable.put("21", "辽宁");
		hashtable.put("22", "吉林");
		hashtable.put("23", "黑龙江");
		hashtable.put("31", "上海");
		hashtable.put("32", "江苏");
		hashtable.put("33", "浙江");
		hashtable.put("34", "安徽");
		hashtable.put("35", "福建");
		hashtable.put("36", "江西");
		hashtable.put("37", "山东");
		hashtable.put("41", "河南");
		hashtable.put("42", "湖北");
		hashtable.put("43", "湖南");
		hashtable.put("44", "广东");
		hashtable.put("45", "广西");
		hashtable.put("46", "海南");
		hashtable.put("50", "重庆");
		hashtable.put("51", "四川");
		hashtable.put("52", "贵州");
		hashtable.put("53", "云南");
		hashtable.put("54", "西藏");
		hashtable.put("61", "陕西");
		hashtable.put("62", "甘肃");
		hashtable.put("63", "青海");
		hashtable.put("64", "宁夏");
		hashtable.put("65", "新疆");
		hashtable.put("71", "台湾");
		hashtable.put("81", "香港");
		hashtable.put("82", "澳门");
		hashtable.put("91", "国外");
		return hashtable;
	}

	/**
	 * 验证日期字符串是否是YYYY-MM-DD格式
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDataFormat(String str) {
		boolean flag = false;
		String regxStr = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		Pattern pattern1 = Pattern.compile(regxStr);
		Matcher isNo = pattern1.matcher(str);
		if (isNo.matches()) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 检查身份证号是否合法
	 * 详细验证，位数，地区码，最后一位码
	 * @param idcard
	 * @return
	 * @throws java.text.ParseException
	 * @throws NumberFormatException
	 */
	public static String isIdcard(String IDStr) throws NumberFormatException,
			java.text.ParseException {
		String errorInfo = "";// 记录错误信息
		String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
				"3", "2" };
		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
				"9", "10", "5", "8", "4", "2" };
		String Ai = "";
		// ================ 号码的长度 15位或18位 ================
		if (IDStr.length() != 15 && IDStr.length() != 18) {
			errorInfo = "身份证号码长度应该为15位或18位。";
			return errorInfo;
		}

		// ================ 数字 除最后以为都为数字 ================
		if (IDStr.length() == 18) {
			Ai = IDStr.substring(0, 17);
		} else if (IDStr.length() == 15) {
			Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
		}
		if (isDigital(Ai) == false) {
			errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
			return errorInfo;
		}

		// ================ 地区码时候有效 ================
		Hashtable h = GetAreaCode();
		if (h.get(Ai.substring(0, 2)) == null) {
			errorInfo = "身份证地区编码错误。";
			return errorInfo;
		}

		// ================ 出生年月是否有效 ================
		String strYear = Ai.substring(6, 10);// 年份
		String strMonth = Ai.substring(10, 12);// 月份
		String strDay = Ai.substring(12, 14);// 月份
		if (isDataFormat(strYear + "-" + strMonth + "-" + strDay) == false) {
			errorInfo = "身份证生日无效。";
			return errorInfo;
		}
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
				|| (gc.getTime().getTime() - s.parse(
						strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
			errorInfo = "身份证生日不在有效范围。";
			return errorInfo;
		}
		if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
			errorInfo = "身份证月份无效";
			return errorInfo;
		}
		if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
			errorInfo = "身份证日期无效";
			return errorInfo;
		}
		
		// ================ 判断最后一位的值 ================   
        int TotalmulAiWi = 0;   
        for (int i = 0; i < 17; i++) {   
            TotalmulAiWi = TotalmulAiWi   
                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))   
                    * Integer.parseInt(Wi[i]);   
        }   
        int modValue = TotalmulAiWi % 11;   
        String strVerifyCode = ValCodeArr[modValue];   
        Ai = Ai + strVerifyCode;   
  
        if (IDStr.length() == 18) {   
             if (Ai.equals(IDStr) == false) {   
                 errorInfo = "身份证无效，不是合法的身份证号码";   
                 return errorInfo;   
             }   
         } else {   
             return errorInfo;   
         }   

		return errorInfo;

	}
	/**
	 * 验证电话
	 * @param agrs
	 * @throws NumberFormatException
	 * @throws java.text.ParseException
	 */
	public static boolean isTelephone(String phonenumber) {
//        String phone = "0\\d{2,3}-\\d{7,8}";
//        Pattern p = Pattern.compile(phone);
        Pattern p = Pattern.compile("^((\\d{2,3})|(\\d{3}-))?((0\\d{2,3})|(0\\d{2,3}-))?[1-9]\\d{6,7}(-\\d{1,4})?$");
        Matcher m = p.matcher(phonenumber);
        return m.matches();
    }
	
	/**
	 * 验证手机号码
	 * @param mobiles
	 * @return
	 */
	
	public static boolean isMobileNO(String mobiles) {
      //  Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Pattern p = Pattern.compile("^(13[0-9]|15[0123456789]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
/*	/^(13[0-9]|15[0123456789]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$/*/
	/**
	 * 验证IP是否合法
	 * @param mobiles
	 * @return
	 */
	public static boolean isIP(String ip){
		Pattern p = Pattern.compile("((25[0-5]|2[0-4]\\d|1?\\d?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1?\\d?\\d)");
		Matcher m = p.matcher(ip);
		return m.matches();
	}
	

	/**
	 * 将一个对象的属性转换为另一个对象的相应属性，注意该方法仅限于代码工具自动生成的model层代码，用于将CM对象以及MC对象之间相互转换
	 *@author    moxg    2013-8-6
	 * @param source
	 * @param target
	 * @param ignoreFields
	 * @throws Exception 
	 */
	public static void copyProperties(Object dest,Object orig,String[] ignoreFields)
	{
	    if (dest == null) {
	        throw new IllegalArgumentException("No destination bean specified");
	      }

	      if (orig == null) {
	        throw new IllegalArgumentException("No origin bean specified");
	      }
	      
	      
	      List<Method> origGettermethods = BeanUtils.getGetter(orig.getClass()); 
	      if(null!=origGettermethods)
	      {
	    	  Method t_method = null;
	    	  Object result = null;
	    	  String fieldName = "";
	    	  String target_idClassName = "";
		      for(int i  = 0;i<origGettermethods.size();i++)
		      {
		    	  if(!"getId".equals(origGettermethods.get(i).getName()))
		    	  {    
		    		  //因为Id属性比较复杂暂时忽略掉Id属性，在返回值中另行处理
		    		  t_method = origGettermethods.get(i);
		    		  fieldName = t_method.getName().split("get")[1];
		    		  try {
		    			  result = t_method.invoke(orig, null);//通过getter获取对应属性值
		    			  
		    			  if(null!=result)
		    			  {
		    				  if("Timestamp".equalsIgnoreCase(BeanUtils.getClassNameWithoutPackage(result.getClass())))
		    				  {
		    					  //将TimesStamp类型转换成Date类型,日期特殊处理
//		    					  Date d ToolsUtils.getFormatDateFromTimestamp(timestamp, formatStr)
		    					  Date d  = ToolsUtils.getFormatDateFromTimestamp(result, "yyyy-MM-d HH:mm:ss");
//		    					  Date d = (Date)result;
		    					  ToolsUtils.getDateObject(d.toString());
		    					  BeanUtils.invoke(dest, "set"+fieldName, new Class[]{ToolsUtils.getDateObject(d.toString()).getClass()}, new Object[]{ToolsUtils.getDateObject(d.toString())});
		    					  
		    				  }
		    				  else{
		    					  BeanUtils.invoke(dest, "set"+fieldName, new Class[]{result.getClass()}, new Object[]{ result}); 
				    			  
		    				  }
		    				  }
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
						logger.error("", e);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						logger.error("", e);
					} catch (InvocationTargetException e) {
						e.printStackTrace();
						logger.error("", e);
					} catch (SecurityException e) {
						e.printStackTrace();
						logger.error("", e);
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
						logger.error("", e);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("", e);
					}
		    	  }else
		    	  {
		    		  //处理Id字段
		    		  t_method = origGettermethods.get(i);
//		    		  fieldName = t_method.getName().split("get")[1];
		    		  try {
						result = t_method.invoke(orig, null);
						if(null!=result)
						{
							target_idClassName = dest.getClass().getName()+"Id";
							try {
								Object idObject = Class.forName(target_idClassName).newInstance();
								copyProperties(idObject, result, null);
								BeanUtils.invoke(dest, "setId", new Class[]{idObject.getClass()}, new Object[]{idObject}); 
							} catch (Exception e) {
								//TODO:异常抛出后续处理
								e.printStackTrace();//Id对象复制出错
								logger.error("", e);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();//非Id对象初始化出错
						logger.error("", e);
					}
		    		  
		    	  }
		      }
	      }
	}
	
	public static DateTime getDateTime(String value) {
		DateTime d = null;
		if (value.trim().length() == 0) {
			return d;
		}
		try {
			d = new DateTime(value, DateTime.YEAR_TO_MILLISECOND);
		} catch (IllegalArgumentException e1) {
			try {
				d = new DateTime(value, DateTime.YEAR_TO_SECOND);
			} catch (IllegalArgumentException e2) {
				try {
					d = new DateTime(value, DateTime.YEAR_TO_HOUR);
				} catch (IllegalArgumentException e3) {
					try {
						d = new DateTime(value, DateTime.YEAR_TO_DAY);
					} catch (IllegalArgumentException e4) {
						if (logger.isDebugEnabled()) {
							logger.debug(e4.getMessage(), e4);
						}
					}
				}
			}
		}
		return d;
	}
	/**
	 * 获取客户端IP
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {  
		 String ip = request.getHeader("x-forwarded-for");  
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		  ip = request.getHeader("Proxy-Client-IP");  
		 }  
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		  ip = request.getHeader("WL-Proxy-Client-IP");  
		 }  
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		  ip = request.getRemoteAddr();  
		 }  
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		  ip = request.getHeader("http_client_ip");  
		 }  
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		  ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
		 }  
		 // 如果是多级代理，那么取第一个ip为客户ip   
		 if (ip != null && ip.indexOf(",") != -1) {  
		  ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();  
		 }  
		 return ip;  
		}  

	
	public static void main(String[] agrs){
//		System.out.println(isIP("255.255.255.255"));	
//Pattern pattern = Pattern.compile("^((\\d{2,3})|(\\d{3}-))?((0\\d{2,3})|(0\\d{2,3}-))?[1-9]\\d{6,7}(-\\d{1,4})?$");
//		Pattern pattern = Pattern.compile("^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})(\\-[0-9]{1,4})?$");
		//Pattern pattern = Pattern.compile("^([0-9]{3,4}-)?([0-9]{7,10})*$");
//		Matcher matcher = pattern.matcher("075513333333");
//
//		System.out.println(matcher.matches());
		
//		boolean flag = isChinaIDCard("34040319680408143X");
//		boolean flag = isChinaIDCard("130503670401001");
//		System.out.println("flag===="+flag);
		
	}
	/**
	 * 屏蔽电话号码
	 * @param commAccount   屏蔽前的号码
	 * @return finalAccount  屏蔽之后的号码
	 * @author WangTing
	 */
	public static String hiddenPhoneNumber(String commAccount){
		if(commAccount == null){
			return NONE;
		}else{
			String firstTel = commAccount.substring(0, 4);
			String finalTel = commAccount.substring(commAccount.length()-3, commAccount.length());
//			String finalAccount = firstTel + "******" + finalTel;
			StringBuffer finalAccount = new StringBuffer(firstTel).append("****").append(finalTel);
			return finalAccount.toString();
		}
	}
	
	/**
	 * Timestamp返回日期
	 * 
	 * @param
	 * @return
	 */
	public static Date getFormatDateFromTimestamp(Object timestamp, String formatStr) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
		String s1 = simpleDateFormat.format(timestamp);
		try {
			return simpleDateFormat.parse(s1);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			logger.error("", e);
			return null;
		}
	}
	
	/**
	 * 转换Timestamp为指定格式的字符串
	 * 
	 * @return String
	 */
	public static String convertTimestampToString(Object timestamp, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(timestamp);
	}
	/**
	 * 根据comcode转换获取comId
	 *@author        2013-9-14
	 * @param comCode
	 * @return
	 */
	public static String getComIdByComCode(String comCode){
		if(StringUtils.isNotEmpty(comCode)&&comCode.length()==8){
			 String sigleCityCode = "";
			if(StringUtils.isEmpty(sigleCityCode))
				sigleCityCode = AppConfig.get("sysconst.SINGLECITY");
			if(StringUtils.isNotEmpty(sigleCityCode)){
				if(sigleCityCode.indexOf(comCode.substring(0, 4))!=-1){					//截取机构代码前4位进行判断
					return comCode.substring(0, 4).concat("0000");									//直接返回单列市接后4位代码
				}else {
					return comCode.substring(0, 2).concat("000000");
				}
			}else {
				logger.warn("没有读取到Common-SINGLECITY常量");
			}
		}else {
			logger.warn("传入的机构代码为空或不符合8位代码格式");
		}
		return null;
	}
	/**
	 * 根据comcode转换获取comId 取4位
	 *@author    moxg    2013-9-14
	 * @param comCode
	 * @return
	 */
	public static String getComId4ByComCode(String comCode){
		 String sigleCityCode = "";
		if(StringUtils.isNotEmpty(comCode)&&comCode.length()==8){
			if(StringUtils.isEmpty(sigleCityCode))
				sigleCityCode = AppConfig.get("sysconst.SINGLECITY");
			if(StringUtils.isNotEmpty(sigleCityCode)){
				if(sigleCityCode.indexOf(comCode.substring(0, 4))!=-1){					//截取机构代码前4位进行判断
					return comCode.substring(0, 4);									//直接返回单列市接后4位代码
				}else {
					return comCode.substring(0, 2).concat("00");
				}
			}else {
				logger.warn("没有读取到Common-SINGLECITY常量");
			}
		}else {
			logger.warn("传入的机构代码为空或不符合8位代码格式");
		}
		return null;
	}
	/**
	 * xss转换之后的String转换
	 *@author    moxg    2013-9-27
	 * @param str
	 * @return
	 */
	public static String xssDecode(String str)
	{
		
		if ((str == null) || ("".equals(str))) {
		      return str;
		    }
		    StringBuilder sb = new StringBuilder();
		      for (int i = 0; i < str.length(); i++) {
		        char c = str.charAt(i);
//		        System.out.println(c+":");
		        switch (c) {
		        case '＞':
		          sb.append('>');
		          break;
		        case '＜':
		          sb.append('<');
		          break;
		        case '‘':
		          sb.append("'");
		          break;
		        case '（':
		          sb.append('(');
		          break;
		        case '）':
		          sb.append(')');
		          break;
		        default:
		          sb.append(c);
		        }
		      }
		    return sb.toString();
	}
	
	/**
	* 将容易引起xss漏洞的半角字符直接替换成全角字符
	*
	* @param s
	* @return
	*/
	public static String xssEncode(String s) {
		if (s == null || "".equals(s)) {
			return s;
		}
		StringBuilder sb = new StringBuilder();
		if (s.indexOf('>') != -1 || s.indexOf('<') != -1 || s.indexOf('\'') != -1) {

			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				switch (c) {
				case '>':
					sb.append('＞');//全角大于号
					break;
				case '<':
					sb.append('＜');//全角小于号
					break;
				case '\'':
					sb.append('‘');//全角单引号
					break;
				case '(':
					sb.append('（');//全角左括号
					break;
				case ')':
					sb.append('）');//全角右括号
					break;
				// 下面三个不常用
				//				case '\"':
				//					sb.append('“');//全角双引号
				//					break;
				//				case '&':
				//					sb.append('＆');//全角
				//					break;
				//				case '\\':
				//					sb.append('＼');//全角斜线
				//					break;
				//				case '#':
				//					sb.append('＃');//全角井号
				//					break;
				default:
					sb.append(c);
					break;
				}
			}
		} else {
			sb.append(s);
		}

		return sb.toString();
	}
	
	public static String trimAll(String str) {
        StringBuilder sb = new StringBuilder();
        char c = ' ';
        for (int i = 0; i < str.length(); i++) {
            char s = str.charAt(i);
            if (s != c) {
                sb.append(s);
            }
        }
        return sb.toString();
    }
	

	
	/**
	 * 将字符串转为16进制
	 * @param str
	 * @return
	 */
	public static String toHexString(String str){
		
		byte[] b = str.getBytes();
		
		String s = "";
		for(int i=0;i<b.length;i++){
			s = s + Integer.toHexString(b[i]);
		}
		
		return s;
		
	}
	
	/**
	 * 将16进制字符串转换为原始的字符串
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String toStringHex(String str) throws UnsupportedEncodingException{
		
		byte[] b = new byte[str.length()/2];
		
		for(int i = 0;i<b.length;i++){
			b[i] = (byte)(0xff & Integer.parseInt(str.substring(i*2, i*2+2),16));
		}
		
		return new String(b,"utf-8");
		
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
	
	/**
	 * 例如解析字符串 fdf;fdfdf;yutyuty 为数组 [fdf fdfdf yutyuty]
	 * 
	 * @param agrs
	 *            字符串
	 * @param separator
	 *            分隔符
	 * @return
	 */

	public static List<String> stringtoList(String agrs, String separator) {

		List<String> result = new ArrayList<String>();

		int index = agrs.indexOf(separator);

		while (index >= 0) {

			String part = new String();
			part = agrs.substring(0, index);
			result.add(part);
			agrs = agrs.substring(index + 1);
			index = agrs.indexOf(separator);
		}

		if (!"".equals(agrs)) {
			result.add(agrs);
		}

		return result;

	}
	
	

	
	
}