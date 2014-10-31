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
import org.xiaoxiancai.imhere.common.protos.BusinessSelectorProtos.BusinessSelector;
import org.xiaoxiancai.imhere.common.protos.BusinessTypeProtos.BusinessType;

/**
 * 客户端处理器
 * 
 * @author xiannenglin
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

	// TODO
	private ImHereClient client;

	public ClientHandler() {
	}

	public ClientHandler(ImHereClient client) {
		this.client = client;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if (msg instanceof BusinessSelector) {
			logger.debug("connection is ok");
			BusinessSelector selector = (BusinessSelector) msg;
			if (selector.getIsSuccess()) {
				synchronized (this) {
					logger.debug("begin to notify waiters");
					this.notify();
				}
			}
			ChannelPipeline pipeline = ctx.pipeline();
			logger.debug("client pipeline before connection = {}", pipeline);
			pipeline.remove(ClientConstant.DECODER_CONNECTION);
			logger.debug("client pipeline after connection = {}", pipeline);
		}
	}

	public void doBusiness(BusinessType businessType) {
		switch (businessType) {
		case REGISTER:
			client.equals("");
			break;

		default:
			break;
		}
	}
}
