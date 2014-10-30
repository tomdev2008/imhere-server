/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xiaoxiancai.imhere.server.protos.ConnectionStatusProtos.ConnectionStatus;

/**
 * 客户端处理器
 * 
 * @author xiannenglin
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if (msg instanceof ConnectionStatus) {
			logger.debug("connection is ok");
			ConnectionStatus status = (ConnectionStatus) msg;
			if (status.getIsSuccess()) {
				synchronized (this) {
					this.notifyAll();
				}
			}
			ChannelPipeline pipeline = ctx.pipeline();
			logger.debug("client pipeline before connection = {}", pipeline);
			pipeline.remove("connection-decoder");
			logger.debug("client pipeline after connection = {}", pipeline);
		}
	}
}
