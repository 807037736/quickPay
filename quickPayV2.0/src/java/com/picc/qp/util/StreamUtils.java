package com.picc.qp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 * @Desc 
 * @author xiaozhiqiang
 * @Date 2015-11-10
 */
public class StreamUtils {
	/**
	 * @Desc
	 * @param is
	 * @param encoding
	 * @param lineAppend
	 * @return
	 * @throws Exception 
	 * @author xiaozhiqiang
	 * @Date 2015-11-10
	 */
	public static String convertStreamToString(InputStream is,String encoding,String lineAppend) throws Exception{   
		BufferedReader reader = null;
		if(encoding!=null&&!"".equals(encoding)){
			reader = new BufferedReader(new InputStreamReader(is,encoding));   
		}else{
			reader = new BufferedReader(new InputStreamReader(is));      
		}
		StringBuffer sBuffer = new StringBuffer();
		String line = null;      
		try {      
			while ((line = reader.readLine()) != null) {  
				sBuffer.append(line);
				if(lineAppend!=null&&!"".equals(lineAppend)){
					sBuffer.append(lineAppend);
				}
			}      
		} catch (IOException e) {      
			e.printStackTrace();      
		} finally {      
			try {      
				is.close();      
			} catch (IOException e) {      
				e.printStackTrace();      
			}      
		}      
		return sBuffer.toString();      
	} 
	/**
	 * @Desc
	 * @param is
	 * @return
	 * @throws Exception 
	 * @author xiaozhiqiang
	 * @Date 2015-11-10
	 */
	public static String convertStreamToString(InputStream is) throws Exception{   
		return convertStreamToString(is,null,null);
	}
}
