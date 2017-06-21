package com.picc.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FileUtils {
	
	protected static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
	private static final File POOL_FILE = getUniqueFile(FileUtils.class,
			".deletefiles");
	private static List deleteFilesPool;

	private static void initPool() {
		if ((POOL_FILE.exists()) && (POOL_FILE.canRead())) {
			ObjectInputStream in = null;
			try {
				in = new ObjectInputStream(new FileInputStream(POOL_FILE));
				deleteFilesPool = (ArrayList) in.readObject();
			} catch (Exception e) {
				deleteFilesPool = new ArrayList();
			} finally {
				if (in != null){
					try {
						in.close();
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("", e);
					}
				}
			}
		} else {
			deleteFilesPool = new ArrayList();
		}
	}

	public static void copyFile(String fromFile, String toFile)
			throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(fromFile);
			out = new FileOutputStream(toFile);
			byte[] b = new byte[1024];

			int len;
			while ((len = in.read(b)) != -1) {
				out.write(b, 0, len);
			}
		} finally {
			if (in != null){
				in.close();
			}

			if (out != null){
				out.close();
			}
		}
	}

	public static String getShortFileName(String fileName) {
		String shortFileName = "";
		int pos = fileName.lastIndexOf(92);
		if (pos == -1){
			pos = fileName.lastIndexOf(47);
		}

		if (pos > -1){
			shortFileName = fileName.substring(pos + 1);
		}
		else{
			shortFileName = fileName;
		}

		return shortFileName;
	}

	public static String getShortFileNameWithoutExt(String fileName) {
		String shortFileName = getShortFileName(fileName);
		shortFileName = getFileNameWithoutExt(shortFileName);
		return shortFileName;
	}

	public static String read(String fileName) throws Exception {
		String fileContent = "";
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(fileName);
			fileContent = read(fin);
		} finally {
			if (fin != null){
				fin.close();
			}
		}

		return fileContent;
	}

	public static String read(File file) throws Exception {
		String fileContent = "";
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(file);
			fileContent = read(fin);
		} finally {
			if (fin != null)
				fin.close();
		}

		return fileContent;
	}

	public static String read(InputStream is) throws Exception {
		byte[] result = readBytes(is);
		return new String(result);
	}

	public static byte[] readBytes(String fileName) throws Exception {
		return readBytes(new FileInputStream(fileName));
	}

	public static byte[] readBytes(File file) throws Exception {
		return readBytes(new FileInputStream(file));
	}

	public static byte[] readBytes(InputStream is) throws Exception {
		if ((is == null) || (is.available() < 1))
			return new byte[0];

		byte[] buff = new byte[8192];
		byte[] result = new byte[is.available()];

		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(is);
			int pos = 0;
			int nch;
			while ((nch = in.read(buff, 0, buff.length)) != -1) {
				System.arraycopy(buff, 0, result, pos, nch);
				pos += nch;
			}
		} finally {
			if (in != null)
				in.close();
		}

		return result;
	}

	public static void write(String content, File file) throws IOException {
		write(content.getBytes(), file);
	}

	public static void write(String content, String file) throws IOException {
		write(content, new File(file));
	}

	public static void write(byte[] bytes, String file) throws IOException {
		write(bytes, new File(file));
	}

	public static void write(byte[] bytes, File file) throws IOException {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write(bytes);
			out.flush();
		} finally {
			if (out != null)
				out.close();
		}
	}

	public static String getFileNameWithoutExt(String fileName) {
		String shortFileName = fileName;
		if (fileName.indexOf(46) > -1)
			shortFileName = fileName.substring(0, fileName.lastIndexOf(46));

		return shortFileName;
	}

	public static String getFileNameExt(String fileName) {
		String fileExt = "";
		if (fileName.indexOf(46) > -1)
			fileExt = fileName.substring(fileName.lastIndexOf(46));

		return fileExt;
	}

	public static synchronized File getUniqueFile(File repository,
			String fileName) {
		String shortFileName = getShortFileName(fileName);
		String tempFileName = getFileNameWithoutExt(shortFileName);
		File file = new File(repository, shortFileName);
		String fileExt = getFileNameExt(shortFileName);
		Random random = new Random();
		while (file.exists()){
			file = new File(repository, tempFileName + "-"
					+ Math.abs(random.nextInt()) + fileExt);
		}

		return file;
	}

	public static void deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.exists())
			deleteFile(file);
	}

	public static void deleteFile(File file) {
		file.delete();
		if (file.exists())
			deleteFilesPool.add(file);

		checkDeletePool();
	}

	private static void checkDeletePool() {
		for (int i = deleteFilesPool.size() - 1; i >= 0; --i) {
			File file = (File) deleteFilesPool.get(i);
			file.delete();
			if (!(file.exists()))
				deleteFilesPool.remove(i);
		}

		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(POOL_FILE));
			out.writeObject(deleteFilesPool);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("", e);
				}
		}
	}

	public static File getUniqueFile(Class cl, String extension) {
		int key = 0;
		URL url = cl.getResource(getClassNameWithoutPackage(cl) + ".class");
		if (url != null)
			key = url.getPath().hashCode();

		File propFile = new File(System.getProperty("java.io.tmpdir"),
				getClassNameWithoutPackage(cl) + key + extension);
		return propFile;
	}

	private static String getClassNameWithoutPackage(Class cl) {
		String className = cl.getName();
		int pos = className.lastIndexOf(46) + 1;
		if (pos == -1)
			pos = 0;

		return className.substring(pos);
	}

	public static boolean deleteFolder(File delFolder) {
		boolean hasDeleted = true;

		File[] allFiles = delFolder.listFiles();
		for (int i = 0; (i < allFiles.length) && (hasDeleted); ++i) {
			if (allFiles[i].isDirectory()) {
				hasDeleted = deleteFolder(allFiles[i]);
			} else if (allFiles[i].isFile()) {
				try {
					if (!(allFiles[i].delete())) {
						hasDeleted = false;
					}
				} catch (Exception e) {
					hasDeleted = false;
				}

			}

		}

		if (hasDeleted) {
			delFolder.delete();
		}
		return hasDeleted;
	}

	public static String getRealPathName(Class cl) {
		URL url = cl.getResource(getClassNameWithoutPackage(cl) + ".class");
		if (url != null)
			return url.getPath();

		return null;
	}

	static {
		try {
			initPool();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
	}
}