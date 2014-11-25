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

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
    		throws Exception {
    	if (!(msg instanceof AddFriendResponse)) {
    		return;
    	}
    	response = (AddFriendResponse) msg;
    	synchronized (this) {
    		this.notifyAll();
    	}
    	logger.debug("add friend response from server = {}", response);
    }

    /**
     * @return
     */
    public AddFriendResponse getResponse() {
        return response;
    }

}
