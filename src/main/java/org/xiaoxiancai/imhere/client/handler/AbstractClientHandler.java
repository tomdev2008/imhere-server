/**
 * @(#)AbstractClientHandler.java, 2014-11-4.
 *
 * Copyright 2014 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.xiaoxiancai.imhere.client.handler;

import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xiaoxiancai.imhere.client.AbstractClient;

/**
 * 客户端抽象处理器
 * 
 * @author linxianneng
 */
public abstract class AbstractClientHandler extends
    ChannelInboundHandlerAdapter {

    /**
     * 客户端
     */
    protected AbstractClient client;

    /**
     * Logger
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public AbstractClientHandler() {}

    public AbstractClientHandler(AbstractClient client) {
        this.client = client;
    }

    public AbstractClient getClient() {
        return client;
    }

    public void setClient(AbstractClient client) {
        this.client = client;
    }

}