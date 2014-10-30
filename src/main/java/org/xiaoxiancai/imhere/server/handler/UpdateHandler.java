/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 更新处理器
 * 
 * @author xiannenglin
 */
public class UpdateHandler extends ChannelInboundHandlerAdapter {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		logger.debug("update user start");
		System.out.println("update user start");
		logger.debug("update channel = {}", ctx.channel());
		System.out.println("update channel = " + ctx.channel());
		logger.debug("update user success");
		System.out.println("update user success");
	}
}
