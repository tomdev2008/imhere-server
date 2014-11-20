/**
 * @(#)LinkServer.java, 2014-11-20.
 *
 * Copyright 2014 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.xiaoxiancai.imhere.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;
import org.xiaoxiancai.imhere.common.protos.business.LocationProtos.Location;

/**
 * 连接服务器
 * 
 * @author linxianneng
 */
@Component("linkServer")
public class LinkServer extends AbstractServer {

    /**
     * 用户最后定位位置
     */
    private Map<Integer, Location> latestLocation = new HashMap<Integer, Location>();

    /**
     * 用户当前定位位置
     */
    private Map<Integer, Location> currentLocation = new HashMap<Integer, Location>();

    /**
     * 用户位置更新时间
     */
    private Map<Integer, Long> locationUpdateTime = new HashMap<Integer, Long>();

    @Override
    protected void doInit() throws Exception {
        logger.info("link server init");
    }

    @Override
    protected void doStart() throws Exception {
        logger.info("link server start success");
    }

    @Override
    protected void doStop() throws Exception {
        logger.info("link server stop");
    }

    public Map<Integer, Location> getLatestLocation() {
        return latestLocation;
    }

    public Map<Integer, Location> getCurrentLocation() {
        return currentLocation;
    }

    class ExpireOnlineTask implements Runnable {

        @Override
        public void run() {
            for (Entry<Integer, Long> entry: locationUpdateTime.entrySet()) {
                int userId = entry.getKey();
                long updateTime = entry.getValue();
            }
        }
    }
}
