/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.client.handler;

import io.netty.channel.ChannelHandlerContext;

import org.xiaoxiancai.imhere.common.protos.business.LocateResponseProtos.LocateResponse;

/**
 * 定位处理器
 * 
 * @author linxianneng
 */
public class LocateHandler extends AbstractClientHandler {

    /**
     * 定位响应
     */
    private LocateResponse response;

    /**
     * @return the response
     */
    public LocateResponse getResponse() {
        return response;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception {
        if (msg instanceof LocateResponse) {
            synchronized (this) {
                response = (LocateResponse) msg;;
                this.notifyAll();
            }
        }
    }
}
