/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.business;

import io.netty.channel.ChannelHandlerContext;

import org.xiaoxiancai.imhere.common.protos.business.LocateRequestProtos.LocateRequest;

/**
 * 定位处理器
 *
 * @author xiannenglin
 */
public class LocateHandler extends AbstractBusinessHandler {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if (!(msg instanceof LocateRequest)) {
			return;
		}

		LocateRequest request = (LocateRequest) msg;
		logger.debug("receive client locate request = {}", request);
		
		
	}
}
