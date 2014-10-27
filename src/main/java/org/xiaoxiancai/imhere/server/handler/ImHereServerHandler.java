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
import org.xiaoxiancai.imhere.server.protos.BusinessSelectProtos.Business;
import org.xiaoxiancai.imhere.server.protos.TestProtos.Test;
import org.xiaoxiancai.imhere.server.protos.UserProtos.User;

/**
 * 业务逻辑处理器
 * 
 * @author xiannenglin
 */
public class ImHereServerHandler extends ChannelInboundHandlerAdapter {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if (msg instanceof User) {
			logger.debug("msg is user");
		}
		if (msg instanceof Business) {
			logger.debug("msg is business");
		}
		if (msg instanceof Test) {
			logger.debug("msg is test");
		}
	}
}
