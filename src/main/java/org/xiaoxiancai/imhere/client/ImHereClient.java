/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

import org.xiaoxiancai.imhere.server.protos.BusinessSelectorProtos.BusinessSelector;
import org.xiaoxiancai.imhere.server.protos.BusinessSelectorProtos.BusinessSelector.BusinessType;
import org.xiaoxiancai.imhere.server.protos.UserProtos.User;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 客户端 TODO 客户端连接池
 * 
 * @author xiannenglin
 */
public class ImHereClient {

	private EventLoopGroup workerGroup;

	private Bootstrap bootStrap;

	/**
	 * 最长等待时间, 3min
	 */
	private static final int MAX_WAIT_MIN = 3;

	/**
	 * 服务器Host
	 */
	private String serverHost;

	/**
	 * Channel Map
	 */
	private ConcurrentHashMap<BusinessType, Channel> channelMap = new ConcurrentHashMap<BusinessType, Channel>();

	/**
	 * 服务器端口
	 */
	private int serverPort;

	/**
	 * 单例
	 */
	private ImHereClient() {
		workerGroup = new NioEventLoopGroup();
		bootStrap = new Bootstrap();
		bootStrap.group(workerGroup);
		bootStrap.channel(NioSocketChannel.class);
		bootStrap.option(ChannelOption.SO_KEEPALIVE, true);
		bootStrap.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast(new ProtobufEncoder());
				pipeline.addLast(new ClientHandler());
			}
		});
	}

	private static class ClientHolder {
		private static ImHereClient instance = new ImHereClient();
	}

	public static ImHereClient getInstance() {
		return ClientHolder.instance;
	}

	/**
	 * 设置服务器地址
	 * 
	 * @param serverHost
	 * @param serverPort
	 */
	public void setServer(String serverHost, int serverPort) {
		this.serverHost = serverHost;
		this.serverPort = serverPort;
	}

	/**
	 * 连接服务器
	 * 
	 * @param businessType
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	private synchronized Channel connect(BusinessType businessType)
			throws InterruptedException {
		return connect(this.serverHost, this.serverPort, businessType);
	}

	/**
	 * 连接服务器
	 * 
	 * @param serverHost
	 * @param serverPort
	 * @param businessType
	 * @return
	 * @throws InterruptedException
	 */
	private synchronized Channel connect(String serverHost, int serverPort,
			BusinessType businessType) throws InterruptedException {
		if (channelMap.containsKey(businessType)) {
			return channelMap.get(businessType);
		}
		ChannelFuture connectFuture = bootStrap.connect(serverHost, serverPort);
		Channel channel = connectFuture.channel();
		// TODO
		if (connectFuture.await(MAX_WAIT_MIN, TimeUnit.MINUTES)) {
			boolean connected = connectFuture.isSuccess();
			if (connected) {
				BusinessSelector.Builder selBuilder = BusinessSelector
						.newBuilder();
				selBuilder.setBusinessType(businessType);
				channel.writeAndFlush(selBuilder.build()).sync();
				channelMap.putIfAbsent(businessType, channel);
			}
			channel.closeFuture().addListener(ChannelFutureListener.CLOSE);
			return channel;
		} else {
			return null;
		}
	}

	/**
	 * 注册用户 TODO
	 * 
	 * @param user
	 * @throws InterruptedException
	 */
	public void register(User user) throws InterruptedException {
		Channel channel = connect(BusinessType.REGISTER);
		if (channel != null && channel.isActive()) {
			channel.writeAndFlush(user);
		}
	}
}
