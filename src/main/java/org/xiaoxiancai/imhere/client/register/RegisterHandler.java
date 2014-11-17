/**
 * @(#)RegisterHandler.java, 2014-11-4.
 *
 * Copyright 2014 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.xiaoxiancai.imhere.client.register;

import io.netty.channel.ChannelHandlerContext;

import org.xiaoxiancai.imhere.client.AbstractClientHandler;
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
        if (msg instanceof RegisterResponse) {
            response = (RegisterResponse) msg;;
            logger.debug("register user response from server = {}", response);
            synchronized (this) {
                this.notify();
            }
        }
    }


    @Override
    public boolean isSuccess() {
        return response.getIsSuccess();
    }

    @Override
    public String getMessage() {
        return response.getMessage();
    }

	@Override
	public int getStatus() {
		return response.getStatus();
	}
}