/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.client.locate;

import io.netty.channel.ChannelHandlerContext;

import org.xiaoxiancai.imhere.client.AbstractClientHandler;
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

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception {
        if (msg instanceof LocateResponse) {
            response = (LocateResponse) msg;;
            logger.debug("locate response from server = {}", response);
            synchronized (this) {
                this.notify();
            }
        }
    }

    /**
     * @return the response
     */
    public LocateResponse getResponse() {
        return response;
    }
}
