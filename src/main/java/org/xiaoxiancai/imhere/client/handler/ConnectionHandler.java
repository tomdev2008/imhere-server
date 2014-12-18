/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.client.handler;

import static org.xiaoxiancai.imhere.client.utils.ClientConstant.DECODER_CONNECTION;
import static org.xiaoxiancai.imhere.client.utils.ClientConstant.HANDLER_CONNECTION;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;

import org.xiaoxiancai.imhere.common.protos.common.BusinessSelectorProtos.BusinessSelector;

/**
 * 客户端处理器
 * 
 * @author xiannenglin
 */
public class ConnectionHandler extends AbstractClientHandler {

    /**
     * 是否连接成功
     */
    private boolean connectSuccess;

    /**
     * @return
     */
    public boolean isConnectSuccess() {
        return connectSuccess;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception {
        if (!(msg instanceof BusinessSelector)) {
            return;
        }
        BusinessSelector selector = (BusinessSelector) msg;
        if (selector.getIsSuccess()) {
            synchronized (this) {
                connectSuccess = true;
                this.notifyAll();
            }
            ChannelPipeline pipeline = ctx.pipeline();
            pipeline.remove(DECODER_CONNECTION);
            pipeline.remove(HANDLER_CONNECTION);
        }
    }
}
