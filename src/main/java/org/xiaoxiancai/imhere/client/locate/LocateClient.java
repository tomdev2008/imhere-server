/**
 * @(#)LocateClient.java, 2014-11-17.
 *
 * Copyright 2014 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.xiaoxiancai.imhere.client.locate;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

import org.xiaoxiancai.imhere.client.AbstractClient;
import org.xiaoxiancai.imhere.client.utils.ClientConstant;
import org.xiaoxiancai.imhere.common.protos.business.LocateRequestProtos.LocateRequest;
import org.xiaoxiancai.imhere.common.protos.business.LocateResponseProtos.LocateResponse;
import org.xiaoxiancai.imhere.common.protos.common.BusinessTypeProtos.BusinessType;


/**
 * 定位客户端
 * 
 * @author linxianneng
 */
public class LocateClient extends AbstractClient {

	/**
	 * 定位
	 * <p>
	 * 将自己位置信息发给服务器, 并接受服务器其他人的位置信息
	 * 
	 * @param request
	 * @throws InterruptedException
	 */
	public LocateResponse locate(LocateRequest request) throws InterruptedException {
		Channel channel = connect(BusinessType.LOCATE);
		if (channel != null && channel.isActive()) {
			logger.debug("send location to server");
			ChannelPipeline pipeline = channel.pipeline();
			logger.debug("locate client pipeline before adding handler = {}",
					pipeline);
			if (pipeline.get(ClientConstant.DECODER_LOCATE) == null) {
				pipeline.addLast(
						ClientConstant.DECODER_LOCATE,
						new ProtobufDecoder(LocateResponse.getDefaultInstance()));
			}

			if (pipeline.get(ClientConstant.HANDLER_LOCATE) == null) {
				pipeline.addLast(ClientConstant.HANDLER_LOCATE,
						new LocateHandler());
			}
			LocateHandler locateHandler = (LocateHandler) pipeline
					.get(ClientConstant.HANDLER_LOCATE);
			logger.debug("locate client pipeline after adding handler = {}",
					pipeline);
			synchronized (locateHandler) {
				channel.writeAndFlush(request).sync();
				locateHandler.wait();
			}
			boolean isSuccess = locateHandler.isSuccess();
			int status = locateHandler.getStatus();
			String message = locateHandler.getMessage();
			logger.info("locate success = {}, status = {}, message = {}",
					isSuccess, status, message);
			
			return locateHandler.getResponse();
		} else {
			logger.error("channel is null or inactive");
			return null;
		}
	}
}
