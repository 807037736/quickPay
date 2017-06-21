package com.picc.common.utils.igecode;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class ImageCodeUtil {
	
	public static void main(String[] args) throws Exception { 
		
		String text = "宝贝jiekou"; 
		int width = 300; 
		int height = 300; 
		String filepath = "D:"+File.separator;
		String picname = "test";
		//二维码生成方法调用
		boolean aa = createImage( text, filepath, picname, width, height);
		System.out.println("==========success========="+aa);
		
	} 
	
	
	
	
	/**
	 * 生成二维码图片 注意宽=高，同时宽高的设置根据文本的大小而定，比如宽高都设置为300，文本的信息越多，生成的图片越大，所以需要实际调试确定宽高
	 * @param text  需要生成二维码的文本 可以是网址 文本等  
	 * @param filepath 指定生成图片存放的路径
	 * @param picname  指定生成图片的名称
	 * @param width   生成的图片的宽
	 * @param height  生成的图片的高
	 * @return  true false 
	 */
	public static boolean createImage(String text,String filepath,String picname,int width,int height){
		
		try {
			
			//二维码的图片格式 　　
			String format = "gif"; 
			Hashtable hints = new Hashtable(); 
			
			//内容所使用编码 　　
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
			BitMatrix bitMatrix;
			bitMatrix = new MultiFormatWriter().encode(text,BarcodeFormat.QR_CODE, width, height, hints);
			
			//生成二维码 　　
			File outputFile = new File(filepath+picname+".gif"); 
			MatrixToImageWriter.writeToFile(bitMatrix, format,outputFile); 
			
			return true ; 
			
		} catch (WriterException e) {
			
			e.printStackTrace();
			return false ; 
		} catch (IOException e) {
			
			e.printStackTrace();
			return false ; 
		} 
		
	}

}
