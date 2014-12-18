/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.client;

import static org.xiaoxiancai.imhere.client.utils.ClientConstant.DECODER_CONNECTION;
import static org.xiaoxiancai.imhere.client.utils.ClientConstant.ENCODER;
import static org.xiaoxiancai.imhere.client.utils.ClientConstant.HANDLER_CONNECTION;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.xiaoxiancai.imhere.client.handler.ConnectionHandler;
import org.xiaoxiancai.imhere.common.protos.common.BusinessSelectorProtos.BusinessSelector;
import org.xiaoxiancai.imhere.common.protos.common.BusinessTypeProtos;
import org.xiaoxiancai.imhere.common.protos.common.BusinessTypeProtos.BusinessType;

/**
 * 客户端基类
 * <p>
 * 提供与服务器端连接
 * 
 * @author xiannenglin
 */
public abstract class AbstractClient implements Client {

    /**
     * 超时时间(ms), 1min
     */
    protected static final int TIMEOUT_IN_MILLIS = 60 * 1000;

    /**
     * 服务器Host
     */
    protected String serverHost;

    /**
     * Channel Map
     */
    private Map<BusinessType, Channel> channelMap = new HashMap<BusinessType, Channel>();

    /**
     * 服务器端口
     */
    protected int serverPort;

    /**
     * ConnectionHandler Map
     */
    private Map<BusinessType, ConnectionHandler> connectionHandlerMap = new HashMap<BusinessTypeProtos.BusinessType, ConnectionHandler>();

    /**
     * 客户端bootstrap
     */
    private Bootstrap bootStrap;

    /**
     * worker group
     */
    private EventLoopGroup workerGroup;

    /**
     * 是否已初始化
     */
    private volatile boolean isInited;

    /**
     * @param serverHost
     * @param serverPort
     */
    protected AbstractClient(String serverHost, int serverPort) {
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
    protected Channel connect(BusinessType businessType)
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
    protected Channel connect(String serverHost, int serverPort,
        BusinessType businessType) throws InterruptedException {
        prepare(businessType);
        if (channelMap.containsKey(businessType)) {
            Channel channel = channelMap.get(businessType);
            if (channel.isActive()) {
                return channel;
            } else {
                channelMap.remove(businessType);
            }
        }
        ChannelFuture connectFuture = bootStrap.connect(serverHost, serverPort);
        Channel channel = connectFuture.channel();
        if (connectFuture.await(TIMEOUT_IN_MILLIS, TimeUnit.MILLISECONDS)) {
            boolean connected = connectFuture.isSuccess();
            if (connected) {
                BusinessSelector.Builder selectorBuilder = BusinessSelector
                    .newBuilder();
                selectorBuilder.setBusinessType(businessType);
                selectorBuilder.setIsSuccess(false);
                ConnectionHandler connectionHandler = connectionHandlerMap
                    .get(businessType);
                synchronized (connectionHandler) {
                    channel.writeAndFlush(selectorBuilder.build()).sync();
                    connectionHandler.wait(TIMEOUT_IN_MILLIS);
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
     * 准备
     * 
     * @param businessType
     */
    private void prepare(BusinessType businessType) {
        if (!isInited) {
            bootStrap = new Bootstrap();
            workerGroup = new OioEventLoopGroup();
            bootStrap.group(workerGroup);
            bootStrap.channel(OioSocketChannel.class);
            bootStrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootStrap.option(ChannelOption.TCP_NODELAY, true);
            isInited = true;
        }
        if (!connectionHandlerMap.containsKey(businessType)) {
            final ConnectionHandler connectionHandler = new ConnectionHandler();
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
            connectionHandlerMap.put(businessType, connectionHandler);
        }
    }

    /**
     * 清理
     */
    public void releaseResource() {
        channelMap.clear();
        connectionHandlerMap.clear();
    }

    /**
     * 关闭
     * 
     * @throws InterruptedException
     */
    public void shutdown() throws InterruptedException {
        if (workerGroup != null) {
            workerGroup.shutdownGracefully().sync();
            isInited = false;
        }
    }
}
