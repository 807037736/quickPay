package com.picc.common;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author diyvan
 * 
 */
public class CodeChange {
	
	private static final Logger logger = LoggerFactory.getLogger(CodeChange.class);

	/**
	 * @param args
	 * 用于转换文件的编码格式，如将一个GBK编码的文件转换成utf-8编码的文件
	 */
	public static void main(String[] args) {
        try {
            String srcDirPath = "E:\\pdfbpoc_gbk\\";
         // 转为UTF-8编码格式源码路径
            String utf8DirPath = "E:\\pdfbpoc_utf\\";
            File dirPath = new File(srcDirPath);
            
//            String suffix[] = new String[] { "java","js","jsp","properties","txt","css","sql","htm","html"};
            String suffix[] = new String[] { "jsp"};
            
         // 获取所有java文件
            Collection javaGbkFileCol = FileUtils.listFiles(dirPath, suffix, true);
         

            for (File javaGbkFile : (Collection<File>)javaGbkFileCol) {
                // UTF8格式文件路径
                String utf8FilePath = utf8DirPath + javaGbkFile.getAbsolutePath().substring(srcDirPath.length());
                // 使用GBK读取数据，然后用UTF-8写入数据
                FileUtils.writeLines(new File(utf8FilePath),"utf-8", FileUtils.readLines(javaGbkFile,"gbk"));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
			logger.error("", e);
        }

    }

}
