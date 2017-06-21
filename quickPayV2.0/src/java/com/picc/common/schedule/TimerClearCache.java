package com.picc.common.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 本类仅做示例用，
 * 本框架已由于已配置缓存定时清理，
 * 故不再需要自己清理类
 * @author syc
 *
 */
public class TimerClearCache {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void clearCache() {
		 logger.info("执行定时清除缓存（演示定时任务用，实际未清除缓存）");
	}
}
