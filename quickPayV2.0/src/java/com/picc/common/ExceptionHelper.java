package com.picc.common;

import ins.framework.exception.BaseException;
import ins.framework.exception.ExceptionCause;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 异常辅助类，用于异常信息获取
 * @author diyvan(yaowenfeng@shenz.picc.com.cn)
 *
 */
public class ExceptionHelper {

	
	/**
	 * 根据异常，获取异常信息
	 * @param exception
	 * @return String 异常信息 
	 */
	public static String getExceptionMessage(Throwable exception){
		String message = null;
		if(exception!=null){
			if(exception.getLocalizedMessage()!=null){
				message = "系统异常，请记录异常信息并联系管理员！<br/>"+exception.getLocalizedMessage();
			}else if(exception instanceof BaseException){//继承于框架异常：BaseException、BusinessException、DataVerifyException、PermissionException
		        BaseException baseException = (BaseException)exception;
		        List causeList = baseException.getCauseList();
		        StringBuffer buffer = new StringBuffer();
		        if(causeList.size() > 0) {
		            for(int i = 0; i < causeList.size(); i++) {
		                ExceptionCause exceptionCause = (ExceptionCause)causeList.get(i);
		                String messageKey = exceptionCause.getMessageKey();
		                String msg = null;
		                // exceptionCause的resource属性为true，表明message是资源文件的key；
		                // 为false，表明message本身就是错误消息。默认是true。
		                if(exceptionCause.isResource()) {
		                	//TODO:由于pdf-b一开始未使用资源文件，待使用资源文件后可启用
		                    //Object[] messageArgs = exceptionCause.getMessageArgs();
		                    //msg = resources.getMessage(locale, messageKey, messageArgs);
		                    if(msg == null) {
		                        msg = messageKey;
		                    }
		                    // 处理数组下标
		                    /*if(exceptionCause.getIndex() > -1 && messageArgs.length > 0) {
		                        String arg0 = (String)messageArgs[0];
		                        int pos = msg.indexOf(arg0);
		                        if(pos > -1) {
		                            pos = pos + arg0.length();
		                            msg = msg.substring(0, pos) + (exceptionCause.getIndex() + 1)
		                                    + msg.substring(pos);
		                        }
		                    }*/
		                }
		                else {
		                    msg = messageKey;
		                }
		                buffer.append(msg).append("<br/>");
		            }
		        }
		        message = buffer.toString();
			}else if(exception instanceof InvocationTargetException){//针对反射抛出来的异常，根据获取反射的原始异常后迭代该函数获取异常信息。
				InvocationTargetException inTargetexception = (InvocationTargetException)exception;
				exception = inTargetexception.getTargetException();
				message = ExceptionHelper.getExceptionMessage(exception);
			}else{
				message = "系统异常，请记录异常信息并联系管理员！";
			}
		}else{
			message = "未知异常，请记录异常信息并联系管理员！";
		}
		return message;
	}
}
