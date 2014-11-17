/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.business;

import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * 
 * @author xiannenglin
 */
public abstract class AbstractBusinessHandler extends ChannelInboundHandlerAdapter {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * Spring Bean容器
	 */
	protected ApplicationContext applicationContext;

	/**
	 * 设置Spring Bean容器
	 * 
	 * @param applicationContext
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}
