package com.picc.common.utils;

import javax.servlet.ServletContextEvent;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;

/**
 * @author chenjin
 * 2011-7-18
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class ServiceLocator extends ContextLoaderListener implements ApplicationContextAware{
	
	
	/**
	 * 说明:Spring应用上下文环境  
	 */
	private static ApplicationContext applicationContext; 
	
	private ContextLoader contextLoader;
	
	public void contextInitialized(ServletContextEvent event){
		this.contextLoader = createContextLoader();
		applicationContext = this.contextLoader.initWebApplicationContext(event.getServletContext());
	}
      
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException { 
		  ServiceLocator.applicationContext = applicationContext;    
	}    
	     
	public static ApplicationContext getApplicationContext() {    
	    return applicationContext;    
	}    
	     
	public static Object getBean(String name) throws BeansException {  
		if(applicationContext==null){
			applicationContext = new ClassPathXmlApplicationContext("classpath*:spring/*.xml");
		}
		return applicationContext.getBean(name);
	}
	
	public static Object getBean(Class<?> classType){
		if(classType==null){return null;}
		return getBean(classType.getSimpleName());
	}
	
	public static Object getBean(String name, Class requiredType) throws BeansException {    
	    return applicationContext.getBean(name,requiredType);
	}    
	     
	public static boolean containsBean(String name) {    
		return applicationContext.containsBean(name);
	  }    
	     
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {    
	    return applicationContext.isSingleton(name);
	  }    
	     
	public static Class getType(String name) throws NoSuchBeanDefinitionException {    
		return applicationContext.getType(name);
	  }    
	     
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {    
		return applicationContext.getAliases(name);
	  }    
}
