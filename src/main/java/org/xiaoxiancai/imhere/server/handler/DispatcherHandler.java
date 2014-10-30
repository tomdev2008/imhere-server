/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xiaoxiancai.imhere.server.protos.BusinessSelectorProtos.BusinessSelector;
import org.xiaoxiancai.imhere.server.protos.BusinessSelectorProtos.BusinessSelector.BusinessType;
import org.xiaoxiancai.imhere.server.protos.ConnectionStatusProtos.ConnectionStatus;
import org.xiaoxiancai.imhere.server.protos.UserProtos.User;
import org.xiaoxiancai.imhere.server.utils.ServerConstant;

/**
 * 业务逻辑分发处理器
 * 
 * @author xiannenglin
 */
public class DispatcherHandler extends ChannelInboundHandlerAdapter {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ChannelPipeline pipeline = ctx.pipeline();
		if (!(msg instanceof BusinessSelector)) {
			return;
		}
		logger.debug("pipeline before select business = {}", pipeline);
		BusinessSelector selector = (BusinessSelector) msg;
		BusinessType businessType = selector.getBusinessType();
		switch (businessType) {
		case REGISTER:
			pipeline.addLast(ServerConstant.DECODER_REGISTER,
					new ProtobufDecoder(User.getDefaultInstance()));
			pipeline.addLast(ServerConstant.HANDLER_REGISTER,
					new RegisterHandler());
			break;
		case UPDATE:
			pipeline.addLast(ServerConstant.DECODER_UPDATE,
					new ProtobufDecoder(User.getDefaultInstance()));
			pipeline.addLast(ServerConstant.HANDLER_UPDATE, new UpdateHandler());
			break;
		case LOCATE:
			// TODO
			break;
		default:
			// TODO
			break;
		}
		pipeline.remove(ServerConstant.DECODER_SELECTOR);
		pipeline.remove(ServerConstant.HANDLER_DISPATCHER);
		logger.debug("pipeline after select business = {}", pipeline);

		ConnectionStatus.Builder statusBuilder = ConnectionStatus.newBuilder();
		statusBuilder.setIsSuccess(true);
		ctx.writeAndFlush(statusBuilder.build());
	}
}
