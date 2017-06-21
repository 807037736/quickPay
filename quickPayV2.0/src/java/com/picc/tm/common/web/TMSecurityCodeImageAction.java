package com.picc.tm.common.web;

import ins.framework.web.Struts2Action;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.picc.tm.common.utils.SecurityCode;
import com.picc.tm.common.utils.SecurityImage;

/**
 * 提供图片验证码
 */
@SuppressWarnings("serial")
public class TMSecurityCodeImageAction extends Struts2Action implements SessionAware{
    
    //Struts2中Map类型的session
    private Map<String, Object> session;
    
    //图片流
    private ByteArrayInputStream imageStream;

    public ByteArrayInputStream getImageStream() {
        return imageStream;
    }

    public void setImageStream(ByteArrayInputStream imageStream) {
        this.imageStream = imageStream;
    }

    
    public String execute() throws Exception {
        //如果开启Hard模式，可以不区分大小写
//        String securityCode = SecurityCode.getSecurityCode(4,SecurityCodeLevel.Hard, false).toLowerCase();
        
        //获取默认难度和长度的验证码
        String securityCode = SecurityCode.getSecurityCode();
        imageStream = SecurityImage.getImageAsInputStream(securityCode);
        //放入session中
        session.put("SESSION_SECURITY_CODE", securityCode);
        getSession().setMaxInactiveInterval(50000);
        return SUCCESS;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}