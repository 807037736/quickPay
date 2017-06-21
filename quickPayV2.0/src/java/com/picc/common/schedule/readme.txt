1、放置一些需要定时执行的类，比如：定时清缓存(TimerClearCache.java,仅作示例用)等（本框架已由于已配置缓存定时清理，故不再需要自己清理类）
2、编写定时任务类后，还需要在/PDF-B-POC/src/resources/spring/components/applicationContext-quartz.xml中作相应的定时执行配置，示例见xml文件