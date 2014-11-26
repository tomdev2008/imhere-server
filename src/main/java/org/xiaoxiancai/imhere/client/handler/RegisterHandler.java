/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.client.handler;

import io.netty.channel.ChannelHandlerContext;

import org.xiaoxiancai.imhere.common.protos.business.RegisterResponseProtos.RegisterResponse;

/**
 * 用户注册处理器
 * 
 * @author linxianneng
 */
public class RegisterHandler extends AbstractClientHandler {

    /**
     * 注册响应
     */
    private RegisterResponse response;

    /**
     * @return the response
     */
    public RegisterResponse getResponse() {
        return response;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception {
        if (!(msg instanceof RegisterResponse)) {
            return;
        }
        synchronized (this) {
            response = (RegisterResponse) msg;;
            this.notifyAll();
        }
        logger.debug("register user response from server = {}", response);
    }
}
