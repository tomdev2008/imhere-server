/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.handler;

import com.google.protobuf.MessageLiteOrBuilder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

import java.util.List;

/**
 * 
 * @author xiannenglin
 */
public class BusinessEncoder extends ProtobufEncoder {

	@Override
	protected void encode(ChannelHandlerContext ctx, MessageLiteOrBuilder msg,
			List<Object> out) throws Exception {
		super.encode(ctx, msg, out);
	}
}
