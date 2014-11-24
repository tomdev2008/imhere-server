/**
 * @(#)RegisterHandler.java, 2014-11-4.
 *
 * Copyright 2014 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
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

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception {
        if (!(msg instanceof RegisterResponse)) {
            return;
        }
        response = (RegisterResponse) msg;;
        logger.debug("register user response from server = {}", response);
        synchronized (this) {
            this.notifyAll();
        }
    }

    /**
     * @return the response
     */
    public RegisterResponse getResponse() {
        return response;
    }

}
