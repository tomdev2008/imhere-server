/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.springframework.stereotype.Component;
import org.xiaoxiancai.imhere.server.handler.BusinessDecoder;
import org.xiaoxiancai.imhere.server.handler.BusinessEncoder;
import org.xiaoxiancai.imhere.server.handler.ImHereServerHandler;
import org.xiaoxiancai.imhere.server.protos.BusinessSelectProtos.Business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * 应用服务启动类
 * 
 * @author xiannenglin
 */
@Component("imHereServer")
public class ImHereServer extends AbstractServer {

	/**
	 * 服务器参数
	 */
	private Map<String, String> settings = new HashMap<String, String>();

	/**
	 * 服务端口
	 */
	private final static int SERVER_PORT = 18080;

	/**
	 * 服务配置文件
	 */
	private static final String CONFIG_FILE = "conf/imhere-server.properties";

	/**
	 * 更新服务配置
	 * 
	 * @param key
	 * @param value
	 */
	public synchronized void updateSettings(String key, String value) {
		String oldValue = settings.put(key, value);
		logger.info("update settings {} = {} to {}", key, oldValue, value);
	}

	@Override
	public void doInit() throws Exception {
		logger.info("load properties start");
		Properties properties = new Properties();
		Reader reader = new BufferedReader(
				new FileReader(new File(CONFIG_FILE)));
		properties.load(reader);
		Set<Entry<Object, Object>> proSet = properties.entrySet();
		for (Entry<Object, Object> entry : proSet) {
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			settings.put(key, value);
		}
		logger.info("load properties success, properties = {}", properties);
	}

	@Override
	public void doStart() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		ServerBootstrap bootstrap = new ServerBootstrap();
		ChannelInitializer<SocketChannel> initializer = new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel ch) throws Exception {
				// TODO
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast(new BusinessEncoder());
				pipeline.addLast(new BusinessDecoder(Business
						.getDefaultInstance()));
				pipeline.addLast(new ImHereServerHandler());
			}
		};
		bootstrap.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(initializer)
				.option(ChannelOption.SO_BACKLOG, 128)
				.childOption(ChannelOption.SO_KEEPALIVE, true);

		ChannelFuture channelFuture = bootstrap.bind(SERVER_PORT).sync();
		ChannelFuture closeFuture = channelFuture.channel().closeFuture();
		closeFuture.addListener(ChannelFutureListener.CLOSE);
		logger.info("imhere server start success");
	}

	@Override
	public void doStop() throws Exception {
		// TODO
	}
}
