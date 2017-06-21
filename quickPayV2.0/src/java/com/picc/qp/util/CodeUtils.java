package com.picc.qp.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 
 * @author xiaozhiqiang
 *
 */
public class CodeUtils{
	/**
	 * 根据请求计算密文摘要mac
	 *
	 * <pre>
	 * 根据参数名称，将除签名（mac）外所有请求参数按照字母先后顺序排序
	 * 1、排序若首字母相同，则对第二个字母进行排序，以此类推；
	 * 2、参数值value无需编码。
	 *
	 * 例如将“foo=1&bar=2&baz=三”排序为“bar=2&baz=三&foo=1”，得到拼装字符串“bar2baz三foo1”。
	 * 将分配的得到的密钥（<font color="red">privateKey</font>）同时拼接到参数字符串头、尾部进行md5加密（不区分大小写）
	 *
	 * 签名算法公式： mac= md5(<font color="red">privateKey</font>key1value1key2value2keyNvalueN<font color="red">privateKey</font>)
	 * </pre>
	 *
	 * @param paramsMap
	 *            请求参数以map的方式传入
	 * @return 加密计算过的摘要
	 */
	public static String getSign(String privateKey, Map<String, Object> paramsMap) {
		List<String> list = new ArrayList<String>();
		Set<String> keys = paramsMap.keySet();
		for (String string : keys) {
			list.add(string);
		}

		// 按字母升序排序(a-->z)
		Collections.sort(list);

		StringBuilder sb = new StringBuilder();
		for (String key : list) {
			sb.append(key).append(paramsMap.get(key));
		}

		String sign = MD5Util.encode(privateKey + sb.toString() + privateKey);
		return sign;
	}

	public static byte[] parseHexStrToByte(String hexStr){
		if (hexStr == null) {
			return null;
		}
		if (hexStr.length() < 1) {
			return null;
		}
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = ((byte)(high * 16 + low));
		}
		return result;
	}

	public static byte[] intToBytes(int iSource, int iArrayLen){
		byte[] bLocalArr = new byte[iArrayLen];
		for (int i = 0; (i < 4) && (i < iArrayLen); i++) {
			bLocalArr[i] = ((byte)(iSource >> 8 * i & 0xFF));
		}
		return bLocalArr;
	}

	public static long byteTolong(byte[] buf) {
		try {
			System.out.println(parseByteToHexStr(buf));
			System.out.println(Long.parseLong(parseByteToHexStr(buf), 16));
			return Long.parseLong(parseByteToHexStr(buf), 16);
		}
		catch (Exception e) {
			e.printStackTrace();
		}return 0L;
	}

	public static String hexStr2Str(String hexStr){
		String str = "0123456789ABCDEF";

		char[] hexs = hexStr.toCharArray();

		byte[] bytes = new byte[hexStr.length() / 2];

		for (int i = 0; i < bytes.length; i++)
		{
			int n = str.indexOf(hexs[(2 * i)]) * 16;

			n += str.indexOf(hexs[(2 * i + 1)]);

			bytes[i] = ((byte)(n & 0xFF));
		}

		return new String(bytes);
	}

	public static String parseByteToHexStr(byte[] buf){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	public static Calendar bytes2Calendar(byte[] bytes) {
		int time = bytes[0] << 24 & 0xFF000000 | bytes[1] << 16 & 0xFF0000 |
				bytes[2] << 8 & 0xFF00 | bytes[3] & 0xFF;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time * 1000L);
		return calendar;
	}

	public static byte[] calendar2Bytes(Calendar calendar) {
		int time = (int)(calendar.getTimeInMillis() / 1000L);
		byte[] bytes = new byte[4];
		for (int i = bytes.length - 1; i >= 0; i--) {
			bytes[i] = ((byte)(time & 0xFF));
			time >>= 8;
		}
		return bytes;
	}
}