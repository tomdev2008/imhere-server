/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.admin;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.xiaoxiancai.imhere.server.ImHereServer;
import org.xiaoxiancai.imhere.server.utils.ImHereConstant;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 管理处理器
 * 
 * @author xiannenglin
 */
@Component("adminHandler")
@Scope("prototype")
public class AdminHandler extends ChannelInboundHandlerAdapter {

	/**
	 * Logger
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ImHereServer imHereServer;

	private Pattern commandPattern = Pattern
			.compile("(\\w+) (-(\\w+) (\\w+))+");

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		String command = (String) msg;
		logger.info("admin command = {}", command);

		/**
		 * command: {commandName} -{paramName} {paramValue}
		 */
		Map<String, String> newSettings = getNewSettings(command);
		for (Entry<String, String> entry : newSettings.entrySet()) {
			String key = entry.getKey();
			String newValue = entry.getValue();
			imHereServer.updateSettings(key, newValue);
		}
	}

	/**
	 * 获得需更新的服务配置Map
	 * 
	 * @param commandLine
	 * @return
	 */
	private Map<String, String> getNewSettings(String commandLine) {
		Map<String, String> updatingSettings = new HashMap<String, String>();
		Matcher matcher = commandPattern.matcher(commandLine);
		if (matcher.matches()) {
			String command = matcher.group(1);
			if (!StringUtils.isBlank(command)
					&& command.equals(ImHereConstant.COMMAND_UPDATE)) {
				String paramKey = matcher.group(3);
				String paramValue = matcher.group(4);
				logger.debug("command = {}, key = {}, value = {}", command,
						paramKey, paramValue);
				updatingSettings.put(paramKey, paramValue);
			}
		}
		return updatingSettings;
	}
}
