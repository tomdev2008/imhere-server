/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.business.register;

import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xiaoxiancai.imhere.server.business.BusinessHandler;
import org.xiaoxiancai.imhere.server.business.register.RegisterProtos.Register;
import org.xiaoxiancai.imhere.server.inter.UserMapper;

/**
 * 新用户注册处理器
 * 
 * @author xiannenglin
 */
public class RegisterHandler extends BusinessHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if (!(msg instanceof Register)) {
			return;
		}
		UserMapper userMapper = (UserMapper) applicationContext
				.getBean("userMapper");
		logger.debug("user mapper = {}", userMapper);
		logger.debug("register user start");
		Register register = (Register) msg;
		logger.debug("register mobile = {}", register.getMobile());
		logger.debug("register user success");
	}
}
