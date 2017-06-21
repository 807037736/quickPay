package com.picc.um.security;

import org.springframework.dao.DataAccessException;
import org.springframework.security.providers.encoding.PasswordEncoder;

/**
 * 用户密码加密处理机制
 * 对于前台提交的密SHA密码进行3DES的加密处理
 * @author 姜卫洋
 */
public class UserPasswordEncoder implements PasswordEncoder {

	public String encodePassword(String arg0, Object arg1) throws DataAccessException {
		return null;
	}

	public boolean isPasswordValid(String encPass, String rawPass, Object arg2) throws DataAccessException {
		if(rawPass!=null){
			if(rawPass.indexOf(CustomAuthenticationProcessingFilter.OUTER_PASSWORD_PREFIX_BINDING)>-1){//绑定方式,不用进行密码验证
				return true;
			}else if(rawPass.indexOf(CustomAuthenticationProcessingFilter.OUTER_PASSWORD_PREFIX_ANONYMOUS)>-1){
				return encPass.equals(rawPass.replace(CustomAuthenticationProcessingFilter.OUTER_PASSWORD_PREFIX_ANONYMOUS, ""));
			}
		}
			
		return DESUtils.getEncryptStr(rawPass).equals(encPass);
	}
	public static void main(String[] args) {
		System.out.println(DESUtils.getEncryptStr("3d4f2bf07dc1be38b20cd6e46949a1071f9d0e3d"));
	}

}
