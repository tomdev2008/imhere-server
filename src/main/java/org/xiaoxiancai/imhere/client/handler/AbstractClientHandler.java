/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.client.handler;

import io.netty.channel.ChannelInboundHandlerAdapter;

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
