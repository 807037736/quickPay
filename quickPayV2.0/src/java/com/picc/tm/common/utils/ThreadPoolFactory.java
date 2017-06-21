package com.picc.tm.common.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池处理类
 * @author moxiaoguang 更新 2013-12-12
 *
 */
public class ThreadPoolFactory {
	public static ExecutorService executorService;
	
	private ThreadPoolFactory(){
		
	}
	
	static{
		executorService = Executors.newCachedThreadPool();
	}
	
	public static ExecutorService getInstance(){
		return executorService;
	}
}
