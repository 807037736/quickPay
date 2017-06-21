package com.picc.um.cognos.utils;


/**
 * Cognos配置定义类
 * @author shenyichan
 */
public class BIConfig {

	private static String cognosURL;

	private static String cognosUIGateway;
	
	private static String biwebIndexURL;

	public BIConfig() {
	}


	/**
	 * @return the biwebIndexURL
	 */
	public static String getBiwebIndexURL() {
		return biwebIndexURL;
	}


	/**
	 * @return the cognosURL
	 */
	public  static String getCognosURL() {
		return cognosURL;
	}

	/**
	 * @return the cognosUIGateway
	 */
	public static String getCognosUIGateway() {
		return cognosUIGateway;
	}

}
