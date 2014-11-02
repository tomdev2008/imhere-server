/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 抽象服务类
 * 
 * @author xiannenglin
 */
public abstract class AbstractServer implements Server {

	/**
	 * Logger
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Spring BeanFactory
	 */
	protected static ApplicationContext applicationContext;

	static {
		applicationContext = new ClassPathXmlApplicationContext(
				"classpath*:spring/applicationContext.xml");
	}

	public void start() throws Exception {
		doInit();
		doStart();
	}

	public void stop() throws Exception {
		doStop();
	}

	/**
	 * 执行初始化服务
	 * 
	 * @throws Exception
	 */
	protected abstract void doInit() throws Exception;

	/**
	 * 执行启动服务
	 * 
	 * @throws Exception
	 */
	protected abstract void doStart() throws Exception;

	/**
	 * 执行停止服务
	 * 
	 * @throws Exception
	 */
	protected abstract void doStop() throws Exception;

	/**
	 * 获得Spring Bean容器
	 * 
	 * @return
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

}
