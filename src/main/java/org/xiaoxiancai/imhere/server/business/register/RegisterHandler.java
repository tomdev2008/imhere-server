/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.business.register;

import io.netty.channel.ChannelHandlerContext;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xiaoxiancai.imhere.server.business.BusinessHandler;
import org.xiaoxiancai.imhere.server.business.register.RegisterProtos.Register;
import org.xiaoxiancai.imhere.server.entity.User;
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
		logger.debug("register user start");
		UserMapper userMapper = (UserMapper) applicationContext
				.getBean("userMapper");
		Register register = (Register) msg;
		userMapper.registerUser(createUser(register));
		logger.debug("register user success");
	}

	private User createUser(Register register) {
		User user = new User();
		user.setMobile(register.getMobile());
		user.setNickName(register.getNickName());
		String email = register.getEmail();
		if (!StringUtils.isBlank(email)) {
			user.setEmail(email);
		}
		String signature = register.getSignature();
		if (!StringUtils.isBlank(signature)) {
			user.setSignature(signature);
		}
		return user;
	}
}
