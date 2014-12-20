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
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;

import org.springframework.stereotype.Component;
import org.xiaoxiancai.imhere.server.business.AdminHandler;

/**
 * 管理服务
 * 
 * @author xiannenglin
 */
@Component("adminServer")
public class AdminServer extends AbstractServer {

    /**
     * 管理端口
     */
    private final static int ADMIN_PORT = 18081;

    /**
     * 管理命令最大长度
     */
    private final static int MAX_COMMAND_LENGTH = 1024;

    @Override
    public void doInit() throws Exception {
        logger.info("admin server initing...");
        logger.info("admin server inited");
    }

    /**
     * boss event loop group
     */
    private EventLoopGroup bossGroup;

    /**
     * worker event loop group
     */
    private EventLoopGroup workerGroup;

    @Override
    public void doStart() throws Exception {
        logger.info("admin server starting...");
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        ChannelInitializer<SocketChannel> initializer = new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new LineBasedFrameDecoder(MAX_COMMAND_LENGTH,
                    true, true));
                pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                pipeline.addLast(applicationContext.getBean("adminHandler",
                    AdminHandler.class));
            }
        };
        bootstrap.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class).childHandler(initializer)
            .childOption(ChannelOption.SO_KEEPALIVE, true);

        ChannelFuture channelFuture = bootstrap.bind(ADMIN_PORT).sync();
        ChannelFuture closeFuture = channelFuture.channel().closeFuture();
        closeFuture.addListener(ChannelFutureListener.CLOSE);
        logger.info("admin server start success");
    }

    @Override
    public void doStop() throws Exception {
        logger.info("admin server stopping...");
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
        logger.info("admin server stopped");
    }

    /**
     * 清理器
     * 
     * @author linxianneng
     */
    class ShutdownCleaner extends Thread {
        @Override
        public void run() {
            logger.info("admin server status = {}", AdminServer.this.status);
            if (AdminServer.this.status != ServerStatus.STOPED) {
                try {
                    AdminServer.this.doStop();
                } catch (Exception e) {
                    logger.error("shutdown admin server error, e = {}", e);
                }
            }
        }
    }
}
