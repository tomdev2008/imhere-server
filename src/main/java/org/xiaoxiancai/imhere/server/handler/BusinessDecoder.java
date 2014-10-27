/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.handler;

import com.google.protobuf.MessageLite;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

import java.util.List;

/**
 * 业务解码器选择器
 * 
 * @author xiannenglin
 */
public class BusinessDecoder extends ProtobufDecoder {

	public BusinessDecoder(MessageLite prototype) {
		super(prototype);
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg,
			List<Object> out) throws Exception {
		super.decode(ctx, msg, out);
	}
}
