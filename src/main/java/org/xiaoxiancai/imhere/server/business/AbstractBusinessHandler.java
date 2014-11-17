/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.business;

import io.netty.channel.ChannelInboundHandlerAdapter;

import org.springframework.context.ApplicationContext;

/**
 * 
 * @author xiannenglin
 */
public abstract class AbstractBusinessHandler extends ChannelInboundHandlerAdapter {

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
