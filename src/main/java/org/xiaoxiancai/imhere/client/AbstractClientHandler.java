/**
 * @(#)AbstractClientHandler.java, 2014-11-4.
 *
 * Copyright 2014 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.xiaoxiancai.imhere.client;

import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    protected ImHereClient client;

    /**
     * Logger
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public AbstractClientHandler() {}

    public AbstractClientHandler(ImHereClient client) {
        this.client = client;
    }

    public ImHereClient getClient() {
        return client;
    }

    public void setClient(ImHereClient client) {
        this.client = client;
    }

}
