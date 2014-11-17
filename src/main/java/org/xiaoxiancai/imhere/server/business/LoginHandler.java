/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.business;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;

import org.xiaoxiancai.imhere.common.protos.business.LoginRequestProtos.LoginRequest;

/**
 * 登录处理器
 *
 * @author xiannenglin
 */
public class LoginHandler extends AbstractBusinessHandler {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if (msg instanceof LoginRequest) {
			LoginRequest request = (LoginRequest) msg;
			logger.debug("receive client login request = {}", request);
			ChannelPipeline pipeline = ctx.pipeline();
		}
	}
}
