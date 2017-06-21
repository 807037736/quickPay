package com.picc.qp.util;

import java.security.MessageDigest;
/**
 * 
 * @author xiaozhiqiang
 *
 */
public class MD5Util {

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"a", "b", "c", "d", "e", "f" };

	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (byte element : b)
			resultSb.append(byteToHexString(element));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * MD5加密算法
	 *
	 * @param origin
	 *            原文
	 * @return 密文
	 */
	public static String encode(String origin) {
		return encode(origin, "utf-8");
	}

	/**
	 * MD5加密算法
	 *
	 * @param origin
	 *            原文
	 * @param charsetname
	 *            编码
	 * @return 密文
	 */
	public static String encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
		} catch (Exception exception) {
			return null;
		}
		return resultString;
	}

	public static void main(String[] args) {
		System.out.println(encode("1822"));
	}
}
