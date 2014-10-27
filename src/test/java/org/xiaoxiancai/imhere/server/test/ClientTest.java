/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.xiaoxiancai.imhere.server.handler.BusinessEncoder;
import org.xiaoxiancai.imhere.server.protos.BusinessSelectProtos.Business;

/**
 * 
 * @author xiannenglin
 */
public class ClientTest {

	public static void main(String[] args) throws Exception {
		String host = "localhost";
		int port = 18080;
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast(new BusinessEncoder());
					pipeline.addLast(new ClientHandler());
				}
			});

			// Start the client.
			ChannelFuture f = b.connect(host, port).sync(); // (5)

			Channel channel = f.channel();

			Business.Builder builder = Business.newBuilder();
			builder.setBusiness("imhere");

			channel.write(builder.build());
			channel.flush();
			System.out.println("client send business to server");
			channel.closeFuture().addListener(ChannelFutureListener.CLOSE);

		} finally {
			workerGroup.shutdownGracefully();
		}
	}

}

class ClientHandler extends ChannelOutboundHandlerAdapter {
	/**
	 * {@inheritDoc}
	 * 
	 * @see io.netty.channel.ChannelOutboundHandlerAdapter#write(io.netty.channel.ChannelHandlerContext,
	 *      java.lang.Object, io.netty.channel.ChannelPromise)
	 */
	@Override
	public void write(ChannelHandlerContext ctx, Object msg,
			ChannelPromise promise) throws Exception {
		// TODO Auto-generated method stub
		super.write(ctx, msg, promise);
	}
}
