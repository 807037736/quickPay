package com.picc.um.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * DES加密工具类
 * @author 姜卫洋
 */
public class DESUtils {
	
	private static final String Algorithm = "DESede";
    
	//24位密钥
    private static final byte[] keyBytes = getKeyByStr("AF684A2F31E6E5ADD163DF42D520EBDF92F8F78FECB90746");

    // 定义 加密算法,可用 DES,DESede,Blowfish
    // keybyte为加密密钥，长度为24字节
    // src为被加密的数据缓冲区（源）
    private static byte[] encryptMode(byte[] keybyte, byte[] src) {
        try { // 生成密钥
        	String s = new String(src,"UTF-8");
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            // 加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(s.getBytes());
        }
        catch(java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        catch(javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        }
        catch(java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    } // keybyte为加密密钥，长度为24字节 //src为加密后的缓冲区

    private static byte[] decryptMode(byte[] keybyte, byte[] src) {
        try { // 生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            // 解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        }
        catch(java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        catch(javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        }
        catch(java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    // 转换成十六进制字符串
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for(int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if(stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;            
        }
        return hs.toUpperCase();
    }
    
    private static byte[] getKeyByStr(String str) {
        byte[] bRet = new byte[str.length() / 2];
        for(int i = 0; i < str.length() / 2; i++) {
            Integer itg = new Integer(16 * getChrInt(str.charAt(2 * i))
                    + getChrInt(str.charAt(2 * i + 1)));
            bRet[i] = itg.byteValue();
        }
        return bRet;
    }  
    
    private static int getChrInt(char chr) {
        int iRet = 0;
        if(chr == "0".charAt(0))
            iRet = 0;
        if(chr == "1".charAt(0))
            iRet = 1;
        if(chr == "2".charAt(0))
            iRet = 2;
        if(chr == "3".charAt(0))
            iRet = 3;
        if(chr == "4".charAt(0))
            iRet = 4;
        if(chr == "5".charAt(0))
            iRet = 5;
        if(chr == "6".charAt(0))
            iRet = 6;
        if(chr == "7".charAt(0))
            iRet = 7;
        if(chr == "8".charAt(0))
            iRet = 8;
        if(chr == "9".charAt(0))
            iRet = 9;
        if(chr == "A".charAt(0))
            iRet = 10;
        if(chr == "B".charAt(0))
            iRet = 11;
        if(chr == "C".charAt(0))
            iRet = 12;
        if(chr == "D".charAt(0))
            iRet = 13;
        if(chr == "E".charAt(0))
            iRet = 14;
        if(chr == "F".charAt(0))
            iRet = 15;
        return iRet;
    } 
    
    public static String getEncryptStr(String str){
    	if(str!=null&&!"".equals(str)){
    		return byte2hex(encryptMode(keyBytes, str.getBytes()));
    	}else{
    		return "";
    	}
    }
    
    public static String getDecryptStr(String str){
    	if(str!=null&&!"".equals(str)){
    		return new String(decryptMode(keyBytes, getKeyByStr(str)));
    	}else{
    		return "";
    	}
        
    }
    
    public static String getEncryptStrAndKey(String str,String key) throws Exception{
    	if(str!=null&&!"".equals(str)){
    		return byte2hex(encryptMode(getKeyByStr(key), str.getBytes()));
    	}else{
    		return "";
    	}
    }
    

    public static String getDecryptStrAndKey(String str,String key)throws Exception{
    	if(str!=null&&!"".equals(str)){
    		return new String(decryptMode(getKeyByStr(key), getKeyByStr(str)));
    	}else{
    		return "";
    	}
        
    }
    
    
    
    /**
     * SHA1 加密算法
     * @param passWord			加密明文
     * @return							加密密文
     * 2013-9-12下午4:30:29
     * jiangweiyang
     */
    public static String encodeSHA(String passWord) {
		return SHA1Utils.getDigestOfString(passWord);
    }

}
