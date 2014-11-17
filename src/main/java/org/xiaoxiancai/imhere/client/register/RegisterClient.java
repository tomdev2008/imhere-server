/**
 * @(#)RegisterClient.java, 2014-11-17.
 *
 * Copyright 2014 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.xiaoxiancai.imhere.client.register;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

import org.xiaoxiancai.imhere.client.AbstractClient;
import org.xiaoxiancai.imhere.client.utils.ClientConstant;
import org.xiaoxiancai.imhere.common.protos.business.RegisterRequestProtos.RegisterRequest;
import org.xiaoxiancai.imhere.common.protos.business.RegisterResponseProtos.RegisterResponse;
import org.xiaoxiancai.imhere.common.protos.common.BusinessTypeProtos.BusinessType;

/**
 * 用户注册客户端
 * 
 * @author linxianneng
 */
public class RegisterClient extends AbstractClient {

	/**
	 * 注册用户
	 * 
	 * @param request
	 * @throws InterruptedException
	 */
	public boolean register(RegisterRequest request) throws InterruptedException {
		Channel channel = connect(BusinessType.REGISTER);
		if (channel != null && channel.isActive()) {
			logger.debug("send register user to server");
			ChannelPipeline pipeline = channel.pipeline();
			logger.debug("register client pipeline before adding handler = {}",
					pipeline);
			if (pipeline.get(ClientConstant.DECODER_REGISTER) == null) {
				pipeline.addLast(
						ClientConstant.DECODER_REGISTER,
						new ProtobufDecoder(RegisterResponse
								.getDefaultInstance()));
			}

			if (pipeline.get(ClientConstant.HANDLER_REGISTER) == null) {
				pipeline.addLast(ClientConstant.HANDLER_REGISTER,
						new RegisterHandler());
			}
			RegisterHandler registerHandler = (RegisterHandler) pipeline
					.get(ClientConstant.HANDLER_REGISTER);
			logger.debug("register client pipeline after adding handler = {}",
					pipeline);
			synchronized (registerHandler) {
				channel.writeAndFlush(request).sync();
				registerHandler.wait();
			}
			boolean isSuccess = registerHandler.isSuccess();
			int status = registerHandler.getStatus();
			String message = registerHandler.getMessage();
			logger.info(
					"register user is success = {}, status = {}, message = {}",
					isSuccess, status, message);
			return isSuccess;
		} else {
			logger.error("channel is null or inactive");
			return false;
		}
	}
}