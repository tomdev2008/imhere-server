/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.client.handler;

import io.netty.channel.ChannelHandlerContext;

import org.xiaoxiancai.imhere.common.protos.business.AddFriendResponseProtos.AddFriendResponse;

/**
 * @author linxianneng
 */
public class AddFriendHandler extends AbstractClientHandler {

    /**
     * 添加好友响应
     */
    private AddFriendResponse response;

    /**
     * @return
     */
    public AddFriendResponse getResponse() {
        return response;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception {
        if (!(msg instanceof AddFriendResponse)) {
            return;
        }
        synchronized (this) {
            response = (AddFriendResponse) msg;
            this.notifyAll();
        }
        logger.debug("add friend response from server = {}", response);
    }
}
