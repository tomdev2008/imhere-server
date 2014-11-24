/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.client.handler;

import io.netty.channel.ChannelHandlerContext;

import org.xiaoxiancai.imhere.common.protos.business.LoginResponseProtos.LoginResponse;

/**
 * 登录处理器
 * 
 * @author xiannenglin
 */
public class LoginHandler extends AbstractClientHandler {

    /**
     * 登录响应
     */
    private LoginResponse response;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception {
        if (!(msg instanceof LoginResponse)) {
            return;
        }
        response = (LoginResponse) msg;
        logger.debug("login response from server = {}", response);
        synchronized (this) {
            this.notifyAll();
        }
    }

    /**
     * @return the response
     */
    public LoginResponse getResponse() {
        return response;
    }
}
