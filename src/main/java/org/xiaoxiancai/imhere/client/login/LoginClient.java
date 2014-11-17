/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.client.login;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

import org.xiaoxiancai.imhere.client.AbstractClient;
import org.xiaoxiancai.imhere.client.utils.ClientConstant;
import org.xiaoxiancai.imhere.common.protos.business.LoginRequestProtos.LoginRequest;
import org.xiaoxiancai.imhere.common.protos.business.LoginResponseProtos.LoginResponse;
import org.xiaoxiancai.imhere.common.protos.common.BusinessTypeProtos.BusinessType;

/**
 * 登录客户端
 *
 * @author xiannenglin
 */
public class LoginClient extends AbstractClient {

	public LoginResponse login(LoginRequest request) throws InterruptedException {
		Channel channel = connect(BusinessType.LOGIN);
		if (channel != null && channel.isActive()) {
			logger.debug("send login request to server");
			ChannelPipeline pipeline = channel.pipeline();
			logger.debug("login client pipeline before adding handler = {}",
					pipeline);
			if (pipeline.get(ClientConstant.DECODER_LOGIN) == null) {
				pipeline.addLast(
						ClientConstant.DECODER_LOGIN,
						new ProtobufDecoder(LoginResponse.getDefaultInstance()));
			}

			if (pipeline.get(ClientConstant.HANDLER_LOGIN) == null) {
				pipeline.addLast(ClientConstant.HANDLER_LOGIN,
						new LoginHandler());
			}
			LoginHandler loginHandler = (LoginHandler) pipeline
					.get(ClientConstant.HANDLER_LOGIN);
			logger.debug("login client pipeline after adding handler = {}",
					pipeline);
			synchronized (loginHandler) {
				channel.writeAndFlush(request).sync();
				loginHandler.wait();
			}
			boolean isSuccess = loginHandler.isSuccess();
			int status = loginHandler.getStatus();
			String message = loginHandler.getMessage();
			logger.info("login success = {}, status = {}, message = {}",
					isSuccess, status, message);
			
			return loginHandler.getResponse();
		} else {
			logger.error("channel is null or inactive");
			return null;
		}
	}
}
