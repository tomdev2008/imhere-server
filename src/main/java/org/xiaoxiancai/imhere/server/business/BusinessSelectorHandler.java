/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.business;

import static org.xiaoxiancai.imhere.server.utils.ServerConstant.DECODER_SELECTOR;
import static org.xiaoxiancai.imhere.server.utils.ServerConstant.HANDLER_SELECTOR;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xiaoxiancai.imhere.common.protos.common.BusinessSelectorProtos.BusinessSelector;
import org.xiaoxiancai.imhere.common.protos.common.BusinessTypeProtos.BusinessType;
import org.xiaoxiancai.imhere.server.AbstractServer;

import com.google.protobuf.MessageLite;

/**
 * 业务逻辑选择处理器
 * 
 * @author xiannenglin
 */
public class BusinessSelectorHandler extends ChannelInboundHandlerAdapter {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final String SUFFIX_DECODER_NAME = "-decoder";

	private static final String SUFFIX_HANDLER_NAME = "-handler";

	private static final String SUFFIX_HANDLER_QUALIFIED_NAME = "Handler";

	private static final String SUFFIX_PROTOS_QUALIFIED_NAME = "Protos$";

	private static final String SUFFIX_BUSINESS_REQUEST_NAME = "Request";

	private static final String BASE_PACKAGE_FOR_PROTOS = "org.xiaoxiancai.imhere.common.protos.business";

	private static final String BASE_PACKAGE_FOR_HANDLERS = "org.xiaoxiancai.imhere.server.business";

	private static final String METHOD_GET_DEFAULT_INSTANCE = "getDefaultInstance";

	/**
	 * 服务类
	 */
	private AbstractServer server;

	public BusinessSelectorHandler(AbstractServer server) {
		this.server = server;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ChannelPipeline pipeline = ctx.pipeline();
		if (!(msg instanceof BusinessSelector)) {
			return;
		}
		logger.debug("receive client connection");
		BusinessSelector selector = (BusinessSelector) msg;
		logger.debug("pipeline before select business = {}", pipeline);
		BusinessType businessType = selector.getBusinessType();
		// -----------------方案1:switch---------------------------
		// switch (businessType) {
		// case REGISTER:
		// pipeline.addLast(ServerConstant.DECODER_REGISTER,
		// new ProtobufDecoder(User.getDefaultInstance()));
		// pipeline.addLast(ServerConstant.HANDLER_REGISTER,
		// new RegisterHandler());
		// break;
		// case UPDATE:
		// pipeline.addLast(ServerConstant.DECODER_UPDATE,
		// new ProtobufDecoder(User.getDefaultInstance()));
		// pipeline.addLast(ServerConstant.HANDLER_UPDATE, new UpdateHandler());
		// break;
		// case LOCATE:
		// // TODO
		// break;
		// default:
		// // TODO
		// break;
		// }

		// -----------------方案2:反射------------------------------
		pipeline.addLast(getBusinessDecoderName(businessType),
				getBusinessDecoder(businessType));
		pipeline.addLast(getBusinessHandlerName(businessType),
				getBusinessHandler(businessType));
		pipeline.remove(DECODER_SELECTOR);
		pipeline.remove(HANDLER_SELECTOR);
		logger.debug("pipeline after select business = {}", pipeline);

		BusinessSelector.Builder statusBuilder = BusinessSelector.newBuilder();
		statusBuilder.setIsSuccess(true);
		statusBuilder.setBusinessType(businessType);
		ctx.writeAndFlush(statusBuilder.build());
	}

	/**
	 * 获得业务处理器名称
	 * 
	 * @param businessType
	 * @return
	 */
	private String getBusinessHandlerName(BusinessType businessType) {
		return businessType.name().toLowerCase() + SUFFIX_HANDLER_NAME;
	}

	/**
	 * 获得编码器名称
	 * 
	 * @param businessType
	 * @return
	 */
	private String getBusinessDecoderName(BusinessType businessType) {
		return businessType.name().toLowerCase() + SUFFIX_DECODER_NAME;
	}

	/**
	 * 获得业务处理器
	 * 
	 * @param businessType
	 * @return
	 * @throws Exception
	 */
	private AbstractBusinessHandler getBusinessHandler(BusinessType businessType)
			throws Exception {
		String businessCamelName = getBusinessCameName(businessType);
		StringBuilder builder = new StringBuilder(BASE_PACKAGE_FOR_HANDLERS);
		String qualifiedName = builder.append(".").append(businessCamelName)
				.append(SUFFIX_HANDLER_QUALIFIED_NAME).toString();
		Class<?> businessClass = Class.forName(qualifiedName);
		AbstractBusinessHandler businessHandler = (AbstractBusinessHandler) businessClass
				.newInstance();
		businessHandler.setApplicationContext(server.getApplicationContext());
		return businessHandler;
	}

	/**
	 * 获得业务解码器
	 * 
	 * @param businessType
	 * @return
	 * @throws Exception
	 */
	private ProtobufDecoder getBusinessDecoder(BusinessType businessType)
			throws Exception {
		MessageLite messageLite = getDecoderMessageLite(businessType);
		ProtobufDecoder decoder = new ProtobufDecoder(messageLite);
		return decoder;

	}

	/**
	 * 获得解码器MessageLite
	 * 
	 * @param businessType
	 * @return
	 * @throws Exception
	 */
	private MessageLite getDecoderMessageLite(BusinessType businessType)
			throws Exception {
		String businessCamelName = getBusinessCameName(businessType);
		StringBuilder builder = new StringBuilder(BASE_PACKAGE_FOR_PROTOS);
		String qualifiedName = builder.append(".").append(businessCamelName)
				.append(SUFFIX_BUSINESS_REQUEST_NAME)
				.append(SUFFIX_PROTOS_QUALIFIED_NAME).append(businessCamelName)
				.append(SUFFIX_BUSINESS_REQUEST_NAME).toString();
		Class<?> businessClass = Class.forName(qualifiedName);
		Method getDefaultInstanse = businessClass
				.getMethod(METHOD_GET_DEFAULT_INSTANCE);
		MessageLite messageLite = (MessageLite) getDefaultInstanse
				.invoke(businessClass);
		return messageLite;
	}

	/**
	 * 获取驼峰式业务名
	 * 
	 * @param businessType
	 * @return
	 */
	private String getBusinessCameName(BusinessType businessType) {
		String businessName = businessType.name().toLowerCase();

		String[] nameItems = businessName.split("_");
		StringBuilder buffer = new StringBuilder();
		for (String item : nameItems) {
			if (item.length() > 1) {
				buffer.append(item.substring(0, 1).toUpperCase()).append(
						item.substring(1));
			} else {
				buffer.append(item.toUpperCase());
			}
		}
		return buffer.toString();
	}
}
