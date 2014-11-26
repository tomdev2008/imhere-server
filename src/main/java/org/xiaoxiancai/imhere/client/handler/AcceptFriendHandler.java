/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.client.handler;

import io.netty.channel.ChannelHandlerContext;

import org.xiaoxiancai.imhere.common.protos.business.AcceptFriendResponseProtos.AcceptFriendResponse;

/**
 * 接受好友请求处理器
 * 
 * @author xiannenglin
 */
public class AcceptFriendHandler extends AbstractClientHandler {

    /**
     * 接受好友请求响应
     */
    private AcceptFriendResponse response;

    /**
     * @return the response
     */
    public AcceptFriendResponse getResponse() {
        return response;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception {
        if (!(msg instanceof AcceptFriendResponse)) {
            return;
        }
        synchronized (this) {
            response = (AcceptFriendResponse) msg;
            this.notifyAll();
        }
        logger.debug("accept friend response from server = {}", response);
    }
}
