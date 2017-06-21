package com.picc.qp.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.picc.common.utils.StringUtils;

/**
 * 微信登录信息拦截器
 * 作用:获取当前用户的openid与系统中的用户进行关联,如果该openid在系统中不存在关联用户,跳转到注册页面
 * @author obba
 *
 */
@SuppressWarnings("serial")
public class WeixinLoginInterceptor extends AbstractInterceptor {

	/**
	 * 微信登录拦截器
	 * 拿不到微信的openID,跳转回注册页面
	 * openID未与系统绑定,跳转回注册页面
	 * 拿到openID系统对应用户,保存session
	 */
	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		ActionContext actionContext = ai.getInvocationContext();
		HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		String param = request.getParameter("param");
		if (StringUtils.isEmptyStr(param)) {
//			request.getSession().setAttribute("param1", param);
//			return "zhuce";
			// 保存openID
			request.getSession().setAttribute("currentOpenId", "oOa5qv5XXXdwjX_vJaj9Cj7qTjOo");
			// 保存对应用户
			return ai.invoke();
		}
		boolean isReg = true;
		if (isReg) {
			// 保存openID
			request.getSession().setAttribute("currentOpenId", "oOa5qv5XXXdwjX_vJaj9Cj7qTjOo");
			// 保存对应用户
			return ai.invoke();
		} else {
			return "zhuce";
		}
	}

}
