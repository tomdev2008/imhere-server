/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server;

import static org.xiaoxiancai.imhere.server.utils.ServerConstant.DECODER_SELECTOR;
import static org.xiaoxiancai.imhere.server.utils.ServerConstant.ENCODER;
import static org.xiaoxiancai.imhere.server.utils.ServerConstant.HANDLER_SELECTOR;
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
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.xiaoxiancai.imhere.common.protos.common.BusinessSelectorProtos.BusinessSelector;
import org.xiaoxiancai.imhere.server.business.BusinessSelectorHandler;

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
     * boss event loop group
     */
    private EventLoopGroup bossGroup;

    /**
     * worker event loop group
     */
    private EventLoopGroup workerGroup;

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
    protected void doInit() throws Exception {
        logger.info("imhere server initing...");
        Properties properties = new Properties();
        Reader reader = new BufferedReader(
            new FileReader(new File(CONFIG_FILE)));
        properties.load(reader);
        Set<Entry<Object, Object>> proSet = properties.entrySet();
        for (Entry<Object, Object> entry: proSet) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            settings.put(key, value);
        }
        logger.info("imhere server inited");
    }

    @Override
    protected void doStart() throws Exception {
        logger.info("imhere server starting...");
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        ChannelInitializer<SocketChannel> initializer = new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(ENCODER, new ProtobufEncoder());
                pipeline.addLast(DECODER_SELECTOR, new ProtobufDecoder(
                    BusinessSelector.getDefaultInstance()));
                pipeline.addLast(HANDLER_SELECTOR, new BusinessSelectorHandler(
                    ImHereServer.this));
            }
        };

        bootstrap.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class).childHandler(initializer)
            .option(ChannelOption.SO_BACKLOG, 1000000)
            .childOption(ChannelOption.SO_KEEPALIVE, true);

        ChannelFuture channelFuture = bootstrap.bind(SERVER_PORT).sync();
        ChannelFuture closeFuture = channelFuture.channel().closeFuture();
        closeFuture.addListener(ChannelFutureListener.CLOSE);
        logger.info("imhere server start success");
    }

    @Override
    protected void doStop() throws Exception {
        logger.info("imhere server stopping...");
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
        logger.info("imhere server stopped");
    }

    /**
     * 清理器
     * 
     * @author linxianneng
     */
    class ShutdownCleaner extends Thread {
        @Override
        public void run() {
            logger.info("imhere server status = {}", ImHereServer.this.status);
            if (ImHereServer.this.status != ServerStatus.STOPED) {
                try {
                    ImHereServer.this.doStop();
                } catch (Exception e) {
                    logger.error("shutdown imhere server error, e = {}", e);
                }
            }
        }
    }
}
