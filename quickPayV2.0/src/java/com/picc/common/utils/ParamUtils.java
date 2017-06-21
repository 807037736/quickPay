package com.picc.common.utils;

import ins.framework.common.DateTime;
import ins.framework.utils.BeanUtils;
import ins.framework.utils.FileUtils;
import ins.framework.utils.StringUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Request参数工具类
 */
public class ParamUtils {
	/**
	 * Logger for this class
	 */
	protected static final Logger logger = LoggerFactory.getLogger(ParamUtils.class);
	private HttpServletRequest request;
	private boolean multipartFlag; // 是否文件上传方式
	private Object[] fileItemList; // 解析后的FileItem列表
	private File repository; // 实际存储路径
	private String tempRepository = System.getProperty("java.io.tmpdir"); // 临时存储路径
	private DiskFileUpload fu;
	private static Character defalutCharacter = new Character('0');

	/**
	 * 构造方法
	 * @param request
	 * @param request HttpServletRequest
	 * @param repository 存储路径
	 *            存储路径
	 * @param sizeMax
	 * @param sizeMax 允许用户上传文件大小,单位:字节
	 * @param sizeThreshold
	 * @param sizeThreshold 置最多只允许在内存中存储的数据,单位:字节
	 * @throws Exception
	 */
	public ParamUtils(HttpServletRequest request, File repository, int sizeMax, int sizeThreshold)
			throws ServletException {
		this.request = request;
		multipartFlag = FileUploadBase.isMultipartContent(request);
		if (multipartFlag == false) {
			return;
		}
		// 如果没有值，则设置为默认存储路径
		if (repository == null) {
			this.repository = new File(System.getProperty("java.io.tmpdir"));
			logger.debug("Repository has no value. set to " + this.repository.getPath());
		} else {
			this.repository = repository;
		}
		fu = new DiskFileUpload();
		// 设置允许用户上传文件大小,单位:字节
		fu.setSizeMax(sizeMax);
		// 设置最多只允许在内存中存储的数据,单位:字节
		fu.setSizeThreshold(sizeThreshold);
		// 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
		fu.setRepositoryPath(tempRepository);
		try {
			// 开始解析
			fileItemList = fu.parseRequest(request).toArray();
		} catch (FileUploadException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * 构造方法，允许用户上传文件总的大小为100M，在内存中存储的数据为8K
	 * @param request
	 * @param request HttpServletRequest
	 * @param repository 存储路径
	 *            存储路径
	 * @throws Exception
	 */
	public ParamUtils(HttpServletRequest request, File repository) throws ServletException {
		this(request, repository, 100 * 1024 * 1024, 8192);
	}

	/**
	 * 构造方法，用于获取参数值
	 * @param request
	 * @param request HttpServletRequest
	 * @throws Exception
	 */
	public ParamUtils(HttpServletRequest request) throws ServletException {
		this(request, null, 100 * 1024 * 1024, 8192);
	}

	/**
	 * 获取参数
	 * @param name 参数名称
	 *            参数名称
	 * @return 参数值
	 */
	public String getParameter(String name) throws ServletException {
		// 不是文件上传时的快速方法
		if (multipartFlag == false) {
			return request.getParameter(name);
		}
		String value = null;
		for (int i = 0, n = fileItemList.length; i < n; i++) {
			FileItem item = (FileItem) fileItemList[i];
			if (item.getFieldName().equals(name)) {
				if (item.isFormField()) {
					value = item.getString();
					break;
				}
				if (item.getName().equals("")) {
					value = "";
				} else {
					// 保存上传的文件到存储目录
					File file = FileUtils.getUniqueFile(repository, item.getName());
					try {
						// 写文件
						item.write(file);
					} catch (Exception e) {
						throw new ServletException(e);
					}
					value = file.getPath();
				}
				break;
			}
		}
		return value;
	}

	/**
	 * 获取一个int型的参数.
	 * @param name 参数名称
	 *            参数名称
	 * @param defaultNum 默认的数值
	 *            默认的数值
	 * @return int型参数值
	 */
	public int getIntParameter(String name, int defaultNum) throws ServletException {
		String temp = getParameter(name);
		if (temp != null && !temp.equals("")) {
			int num = defaultNum;
			try {
				num = Integer.parseInt(correctNumber(temp));
			} catch (Exception ignored) {
				logger.debug("ParamUtils.getIntParameter exception:" + ignored.getMessage(), ignored);
			}
			return num;
		}
		return defaultNum;
	}

	/**
	 * 获取一个long型的参数.
	 * @param name 参数名称
	 *            参数名称
	 * @param defaultNum 默认的数值
	 *            默认的数值
	 * @return int型参数值
	 */
	public long getLongParameter(String name, long defaultNum) throws ServletException {
		String temp = getParameter(name);
		if (temp != null && !temp.equals("")) {
			long num = defaultNum;
			try {
				num = Long.parseLong(correctNumber(temp));
			} catch (Exception ignored) {
				logger.debug("ParamUtils.getLongParameter exception:" + ignored.getMessage(), ignored);
			}
			return num;
		}
		return defaultNum;
	}

	/**
	 * 获取一个double型的参数.
	 * @param name 参数名称
	 *            参数名称
	 * @param defaultNum 默认的数值
	 *            默认的数值
	 * @return double型参数值
	 */
	public double getDoubleParameter(String name, double defaultNum) throws ServletException {
		String temp = getParameter(name);
		if (temp != null && !temp.equals("")) {
			double num = defaultNum;
			try {
				num = Double.parseDouble(correctNumber(temp));
			} catch (Exception ignored) {
				logger.debug("ParamUtils.getDoubleParameter exception:" + ignored.getMessage(), ignored);
			}
			return num;
		}
		return defaultNum;
	}

	/**
	 * 获取int型数组
	 * @param name 参数名称
	 *            参数名称
	 * @param defaultNum 默认的数值
	 *            默认的数值
	 * @return int型数组
	 * @throws ServletException
	 */
	public int[] getIntParameterValues(String name, int defaultNum) throws ServletException {
		String[] paramValues = getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		int paramValuesCount = paramValues.length;
		if (paramValuesCount < 1) {
			return new int[0];
		}
		int[] values = new int[paramValuesCount];
		for (int i = 0; i < paramValuesCount; i++) {
			try {
				values[i] = Integer.parseInt(correctNumber(paramValues[i]));
			} catch (Exception e) {
				logger.debug("ParamUtils.getIntParameterValues exception:" + e.getMessage(), e);
				values[i] = defaultNum;
			}
		}
		return values;
	}

	/**
	 * 获取long型数组
	 * @param name 参数名称
	 *            参数名称
	 * @param defaultNum 默认的数值
	 *            默认的数值
	 * @return int型数组
	 * @throws ServletException
	 */
	public long[] getLongParameterValues(String name, long defaultNum) throws ServletException {
		String[] paramValues = getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		int paramValuesCount = paramValues.length;
		if (paramValuesCount < 1) {
			return new long[0];
		}
		long[] values = new long[paramValuesCount];
		for (int i = 0; i < paramValuesCount; i++) {
			try {
				values[i] = Long.parseLong(correctNumber(paramValues[i]));
			} catch (Exception e) {
				logger.debug("ParamUtils.getLongParameterValues exception:" + e.getMessage(), e);
				values[i] = defaultNum;
			}
		}
		return values;
	}

	/**
	 * 获取double型数组
	 * @param name 参数名称
	 *            参数名称
	 * @param defaultNum 默认的数值
	 *            默认的数值
	 * @return double型数组
	 * @throws ServletException
	 */
	public double[] getDoubleParameterValues(String name, double defaultNum) throws ServletException {
		String[] paramValues = getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		int paramValuesCount = paramValues.length;
		if (paramValuesCount < 1) {
			return new double[0];
		}
		double[] values = new double[paramValuesCount];
		for (int i = 0; i < paramValuesCount; i++) {
			try {
				values[i] = Double.parseDouble(correctNumber(paramValues[i]));
			} catch (Exception e) {
				logger.debug("ParamUtils.getDoubleParameterValues exception:" + e.getMessage(), e);
				values[i] = defaultNum;
			}
		}
		return values;
	}

	/**
	 * 获取参数
	 * @param name 参数名称
	 *            参数名称
	 * @return
	 */
	public String[] getParameterValues(String name) throws ServletException {
		// 不是文件上传时的快速方法
		if (multipartFlag == false) {
			return request.getParameterValues(name);
		}
		ArrayList values = new ArrayList();
		String value = "";
		for (int i = 0, n = fileItemList.length; i < n; i++) {
			FileItem item = (FileItem) fileItemList[i];
			if (item.getFieldName().equals(name)) {
				if (item.isFormField()) {
					value = item.getString();
					values.add(value);
				} else {
					if (item.getName().equals("")) {
						value = "";
					} else {
						// 保存上传的文件到存储目录
						File file = FileUtils.getUniqueFile(repository, item.getName());
						try {
							// 写文件
							item.write(file);
						} catch (Exception e) {
							throw new ServletException(e);
						}
						value = file.getPath();
					}
					values.add(value);
				}
			}
		}
		String[] retValues = new String[values.size()];
		if (retValues.length == 0) {
			retValues = null;
		} else {
			values.toArray(retValues);
		}
		return retValues;
	}

	/**
	 * 是否是Multipart形式，即是否包含文件上传
	 * @return 是则返回true,否则返回false
	 */
	public boolean isMultipart() {
		return multipartFlag;
	}

	/**
	 * 返回实际文件存储路径
	 * @return 实际文件存储路径
	 */
	public File getRepository() {
		return repository;
	}

	/**
	 * 设置实际文件存储路径
	 * @param repository 实际文件存储路径
	 *            实际文件存储路径
	 */
	public void setRepository(File repository) {
		this.repository = repository;
	}

	/**
	 * 返回FileItemList
	 * @return FileItemList
	 */
	public Object[] getFileItemList() {
		return fileItemList;
	}

	/**
	 * 获取临时存储路径
	 * @return 临时存储路径
	 */
	public String getTempRepository() {
		return tempRepository;
	}

	/**
	 * 设置临时存储路径
	 * @param tempRepository 临时存储路径
	 *            临时存储路径
	 */
	public void setTempRepository(String tempRepository) {
		File tempDir = new File(tempRepository);
		if (tempDir.exists() && tempDir.canRead() && tempDir.canWrite()) {
			this.tempRepository = tempRepository;
			fu.setRepositoryPath(tempRepository);
		} else {
			logger.debug("ParamUtils.setTempRepository设置临时存储路径失败！");
		}
	}

	/**
	 * 修正数字（如千分位的数字把千分位字符去掉）
	 * @param numberString 原始数字串
	 *            原始数字串
	 * @return 修正后的数字串
	 */
	private synchronized String correctNumber(String numberString) {
		String value = numberString;
		if (value != null && !value.equals("")) {
			value = StringUtils.replace(value, ",", "");
		}
		return value;
	}
	
	/**
	 * @param cl
	 * @param cl 要封装的DTO的Class对象。
	 * @param inputPrefix 输入域的前缀，一般是DTO的类名。
	 *            输入域的前缀，一般是DTO的类名。
	 * @throws Exception
	 * @throws Exception 如果通过cl参数创建DTO对象失败，将抛出InstantiationException异常。
	 */
	public Object generateObject(Class cl,String inputPrefix)throws Exception {
		return generateObject(cl,inputPrefix,0);
	}

	/**
	 * @param cl
	 * @param cl 要封装的DTO的Class对象。
	 * @param inputPrefix 输入域的前缀，一般是DTO的类名。
	 *            输入域的前缀，一般是DTO的类名。
	 * @param index 根据序号，获取序号对应的对象，从0开始        
	 * @throws Exception
	 * @throws Exception 如果通过cl参数创建DTO对象失败，将抛出InstantiationException异常。
	 */
	public Object generateObject(Class cl, String inputPrefix,Integer index) throws Exception {
		Object object = cl.newInstance();
		// 获取所有set方法
		List methodList = BeanUtils.getSetter(cl);
		int methodLength = methodList.size();
		Object[] objs = new Object[1];
		Object subObject = null;
		Method method = null;
		String fieldName = null;
		Object[] parameterValues = null;
		Object parameterValue = null;
		Class parameterType = null;
		String valueString = null;
		StringBuffer stripped = new StringBuffer();
		for (int i = 0; i < methodLength; i++) {
			method = (Method) methodList.get(i);
			fieldName = method.getName().substring(3);
			if("setId".equalsIgnoreCase(method.getName())){//如果遍历到setId,使用inputPrefix+"id."遍历参数
				subObject =  generateObject(method.getParameterTypes()[0],inputPrefix+"id.",index);
				if(subObject!=null){
					method.invoke(object, new Object[] {subObject});
				}
				continue;
			}
			stripped.setLength(0);
			stripped.append(fieldName);// stripped存储实际的Input字段名
			// 如果有前缀则Input字段名添加前缀
			if (inputPrefix != null && inputPrefix.trim().length() > 0) {
				stripped.setLength(0);
				stripped.append(inputPrefix);
				stripped.append(StringUtils.lowerCaseFirstChar(fieldName));
			}
			parameterValues = request.getParameterValues(stripped.toString());
			if(parameterValues ==null||parameterValues.length<0){
				stripped.setLength(0);
				stripped.append(inputPrefix);
				stripped.append(StringUtils.upperCaseFirstChar(fieldName));
				parameterValues = request.getParameterValues(stripped.toString());
			}
			try {
				parameterType = method.getParameterTypes()[0];
				// 如果Input域在request不存在则设置为空并继续
				if(parameterValues==null||parameterValues.length<0||parameterValues.length<index){
					if (parameterType.isPrimitive()) {
						if (parameterType == boolean.class) {
							objs[0] = Boolean.FALSE;
						} else if (parameterType == byte.class) {
							objs[0] = Byte.valueOf("0");
						} else if (parameterType == char.class) {
							objs[0] = defalutCharacter;
						} else if (parameterType == short.class) {// NOPMD
							objs[0] = Short.valueOf("0");
						} else if (parameterType == int.class) {
							objs[0] = Integer.valueOf("0");
						} else if (parameterType == long.class) {
							objs[0] = Long.valueOf("0");
						} else if (parameterType == float.class) {
							objs[0] = Float.valueOf("0");
						} else if (parameterType == double.class) {
							objs[0] = Double.valueOf("0");
						}
						if (objs[0] != null) {
							method.invoke(object, objs);
						}
					} else {
						objs[0] = null;
						method.invoke(object, objs);
					}
				} else {
					valueString = (String) parameterValues[index];
					if (valueString == null) {
						valueString = "";
					}
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
							parameterValue = Double.valueOf(correctNumber(valueString));
						}
					} else if (parameterType == Long.class) {
						if (valueString == null || valueString.equals("")) {
							parameterValue = null;
						} else {
							parameterValue = Long.valueOf(correctNumber(valueString));
						}
					} else if (parameterType == Integer.class) {
						if (valueString == null || valueString.equals("")) {
							parameterValue = null;
						} else {
							parameterValue = Integer.valueOf(correctNumber(valueString));
						}
					} else if (parameterType == BigDecimal.class) {
						if (valueString == null || valueString.equals("")) {
							parameterValue = null;
						} else {
							parameterValue = new BigDecimal(correctNumber(valueString));
						}
					} else {
						parameterValue = valueString;
					}
					objs[0] = parameterValue;
					method.invoke(object, objs);
				}
			} catch (Exception e) {
				if (logger.isDebugEnabled()) {
					logger.debug(e.getMessage(), e);
				}
			}
		}
		return object;
	}

	/**
	 * @see
	 *      <p>
	 *      generateList(Class cl, String inputPrefix, int startIndex)
	 *      </p>
	 *      <p>
	 *      这个方法默认的起始行号是0。
	 *      </p>
	 * @param cl
	 * @param cl 要封装的DTO的Class对象。
	 * @param inputPrefix 输入域的前缀，一般是DTO的类名。
	 *            输入域的前缀，一般是DTO的类名。
	 * @return 封装好的DTO集合。
	 * @throws Exception
	 */
	public List generateList(Class cl, String inputPrefix) throws Exception {
		return generateList(cl, inputPrefix, 0);
	}

	/**
	 * @param cl
	 * @param cl 要封装的DTO的Class对象。
	 * @param inputPrefix 输入域的前缀，一般是DTO的类名。
	 *            输入域的前缀，一般是DTO的类名。
	 * @param start
	 * @param startIndex 要封装的多行输入域的起始行号。 <br>
	 *            对于多行输入域来讲，一般起始行号是1，因为第0行多行输入域的隐藏部分("_Data"部分)；对于普通的多行输入的情况，起始行号是0。
	 *            通过这个参数可以自定义起始行号。
	 * @return 封装好的DTO集合。
	 * @throws Exception
	 * @throws Exception 如果通过cl参数创建DTO对象失败，将抛出InstantiationException异常。
	 */
	public List generateList(Class cl, String inputPrefix, int startIndex) throws Exception {
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
			parameterValues = request.getParameterValues(stripped.toString());
			//只要判断其中一个字段在页面上有值，则直接调用generateObject生成对象，并增加到list中
			if(parameterValues!=null&&parameterValues.length>0&&parameterValues.length>=startIndex){
				for (int j = startIndex; j < parameterValues.length; j++) {
					try {
						object = generateObject(cl,inputPrefix,j);
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

}