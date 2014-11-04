/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.client;

import static org.xiaoxiancai.imhere.client.ClientConstant.DECODER_CONNECTION;
import static org.xiaoxiancai.imhere.client.ClientConstant.ENCODER;
import static org.xiaoxiancai.imhere.client.ClientConstant.HANDLER_CONNECTION;
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
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xiaoxiancai.imhere.common.protos.BusinessSelectorProtos.BusinessSelector;
import org.xiaoxiancai.imhere.common.protos.BusinessTypeProtos.BusinessType;
import org.xiaoxiancai.imhere.server.business.register.RegisterRequestProtos.RegisterRequest;
import org.xiaoxiancai.imhere.server.business.register.RegisterResponseProtos.RegisterResponse;

/**
 * 客户端
 * 
 * @author xiannenglin
 */
public class ImHereClient {

    private Logger logger = LoggerFactory.getLogger(getClass());

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
    private Map<BusinessType, Channel> channelMap = new HashMap<BusinessType, Channel>();

    /**
     * 服务器端口
     */
    private int serverPort;

    /**
     * 客户端连接处理器
     */
    private ConnectionHandler connectionHandler;

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
     * @return
     * @throws InterruptedException
     */
    private Channel connect(BusinessType businessType)
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
    private Channel connect(String serverHost, int serverPort,
        BusinessType businessType) throws InterruptedException {
        if (channelMap.containsKey(businessType)) {
            Channel channel = channelMap.get(businessType);
            if (channel.isActive()) {
                return channel;
            } else {
                channelMap.remove(businessType);
            }
        }
        logger.debug("create new channel for business = {}",
            businessType.name());
        connectionHandler = new ConnectionHandler();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootStrap = new Bootstrap();
        bootStrap.group(workerGroup);
        bootStrap.channel(NioSocketChannel.class);
        bootStrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootStrap.option(ChannelOption.TCP_NODELAY, true);
        bootStrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(ENCODER, new ProtobufEncoder());
                pipeline.addLast(DECODER_CONNECTION, new ProtobufDecoder(
                    BusinessSelector.getDefaultInstance()));
                pipeline.addLast(HANDLER_CONNECTION, connectionHandler);
            }
        });

        ChannelFuture connectFuture = bootStrap.connect(serverHost, serverPort);
        Channel channel = connectFuture.channel();
        if (connectFuture.await(MAX_WAIT_MIN, TimeUnit.MINUTES)) {
            boolean connected = connectFuture.isSuccess();
            if (connected) {
                BusinessSelector.Builder selectorBuilder = BusinessSelector
                    .newBuilder();
                selectorBuilder.setBusinessType(businessType);
                selectorBuilder.setIsSuccess(false);

                synchronized (connectionHandler) {
                    channel.writeAndFlush(selectorBuilder.build()).sync();
                    connectionHandler.wait();
                }
                channelMap.put(businessType, channel);
            }
            channel.closeFuture().addListener(ChannelFutureListener.CLOSE);
            return channel;
        } else {
            return null;
        }
    }

    /**
     * 注册用户
     * 
     * @param request
     * @throws InterruptedException
     */
    public void register(RegisterRequest request) throws InterruptedException {
        Channel channel = connect(BusinessType.REGISTER);
        if (channel != null && channel.isActive()) {
            logger.debug("send register user to server");
            ChannelPipeline pipeline = channel.pipeline();
            logger
                .debug("client pipeline before adding handler = {}", pipeline);
            if (pipeline.get(ClientConstant.DECODER_REGISTER) == null) {
                pipeline.addLast(ClientConstant.DECODER_REGISTER,
                    new ProtobufDecoder(RegisterResponse.getDefaultInstance()));
            }

            if (pipeline.get(ClientConstant.HANDLER_REGISTER) == null) {
                pipeline.addLast(ClientConstant.HANDLER_REGISTER,
                    new RegisterHandler());
            }
            RegisterHandler registerHandler = (RegisterHandler) pipeline
                .get(ClientConstant.HANDLER_REGISTER);
            logger.debug("client pipeline after adding handler = {}", pipeline);
            synchronized (registerHandler) {
                channel.writeAndFlush(request).sync();
                registerHandler.wait();
            }
            boolean isSuccess = registerHandler.isSuccess();
            String message = registerHandler.getMessage();
            logger.info("register user is success = {}, message = {}",
                isSuccess, message);
        } else {
            logger.error("channel is null or inactive");
        }
    }
}
