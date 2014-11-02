/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server;

import static org.xiaoxiancai.imhere.server.utils.ServerConstant.DECODER_SELECTOR;
import static org.xiaoxiancai.imhere.server.utils.ServerConstant.HANDLER_DISPATCHER;

import com.google.protobuf.MessageLite;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xiaoxiancai.imhere.common.protos.BusinessSelectorProtos.BusinessSelector;
import org.xiaoxiancai.imhere.common.protos.BusinessTypeProtos.BusinessType;
import org.xiaoxiancai.imhere.server.business.BusinessHandler;

import java.lang.reflect.Method;

/**
 * 业务逻辑分发处理器
 * 
 * @author xiannenglin
 */
public class DispatcherHandler extends ChannelInboundHandlerAdapter {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final String SUFFIX_DECODER_NAME = "-decoder";

	private static final String SUFFIX_HANDLER_NAME = "-handler";

	private static final String SUFFIX_HANDLER_QUALIFIED_NAME = "Handler";

	private static final String SUFFIX_PROTOS_QUALIFIED_NAME = "Protos$";

	private static final String BASE_PACKAGE = "org.xiaoxiancai.imhere.server.business";

	private static final String METHOD_GET_DEFAULT_INSTANCE = "getDefaultInstance";

	/**
	 * 服务类
	 */
	private AbstractServer server;

	public DispatcherHandler(AbstractServer server) {
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
		pipeline.remove(HANDLER_DISPATCHER);
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
	private BusinessHandler getBusinessHandler(BusinessType businessType)
			throws Exception {
		String[] names = getBusinessRelatedNames(businessType);
		String businessName = names[0];
		String businessCamelName = names[1];
		StringBuilder builder = new StringBuilder(BASE_PACKAGE);
		String qualifiedName = builder.append(".").append(businessName)
				.append(".").append(businessCamelName)
				.append(SUFFIX_HANDLER_QUALIFIED_NAME).toString();
		Class<?> businessClass = Class.forName(qualifiedName);
		BusinessHandler businessHandler = (BusinessHandler) businessClass
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
		String[] names = getBusinessRelatedNames(businessType);
		String businessName = names[0];
		String businessCamelName = names[1];
		StringBuilder builder = new StringBuilder(BASE_PACKAGE);
		String qualifiedName = builder.append(".").append(businessName)
				.append(".").append(businessCamelName)
				.append(SUFFIX_PROTOS_QUALIFIED_NAME).append(businessCamelName)
				.toString();
		Class<?> businessClass = Class.forName(qualifiedName);
		Method getDefaultInstanse = businessClass
				.getMethod(METHOD_GET_DEFAULT_INSTANCE);
		MessageLite messageLite = (MessageLite) getDefaultInstanse
				.invoke(businessClass);
		return messageLite;
	}

	/**
	 * 获取业务名 {全小写名(包名), 驼峰式名(类名)}
	 * 
	 * @param businessType
	 * @return
	 */
	private String[] getBusinessRelatedNames(BusinessType businessType) {
		String[] names = new String[2];
		String businessName = businessType.name().toLowerCase();
		names[0] = businessName;
		String businessCamelName = null;
		if (businessName.length() > 1) {
			businessCamelName = businessName.substring(0, 1).toUpperCase()
					+ businessName.substring(1);
		} else {
			businessCamelName = businessName.toUpperCase(); // DON'T DO THIS
		}
		names[1] = businessCamelName;
		return names;
	}
}
