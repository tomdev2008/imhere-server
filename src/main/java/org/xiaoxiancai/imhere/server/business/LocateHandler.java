/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.business;

import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.xiaoxiancai.imhere.common.protos.business.LocateRequestProtos.LocateRequest;
import org.xiaoxiancai.imhere.common.protos.business.LocateResponseProtos.LocateResponse;
import org.xiaoxiancai.imhere.common.protos.business.LocationProtos.Location;
import org.xiaoxiancai.imhere.server.LinkServer;
import org.xiaoxiancai.imhere.server.inter.UserMapper;

/**
 * 定位处理器
 * 
 * @author xiannenglin
 */
public class LocateHandler extends AbstractBusinessHandler {

    /**
     * 连接服务器
     */
    private LinkServer linkServer;

    /**
     * TODO
     */
    private Map<Integer, Location> allLatestLocation;

    /**
     * 所有用户当前位置
     */
    private Map<Integer, Location> allCurrentLocation;

    /**
     * 所有用户位置更新时间
     */
    private Map<Integer, Long> allLocationUpdateTime;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception {
        if (!(msg instanceof LocateRequest)) {
            return;
        }
        LocateRequest request = (LocateRequest) msg;
        logger.debug("receive client locate request = {}", request);

        Location currentLocation = request.getCurrentLocation();

        linkServer = (LinkServer) applicationContext.getBean("linkServer");
        // TODO
        allLatestLocation = linkServer.getLatestLocation();
        allCurrentLocation = linkServer.getCurrentLocation();
        allLocationUpdateTime = linkServer.getLocationUpdateTime();

        int userId = currentLocation.getUserId();
        allCurrentLocation.put(userId, currentLocation);
        allLocationUpdateTime.put(userId, System.currentTimeMillis());

        logger.debug("all location update time = {}", allLocationUpdateTime);
        UserMapper userMapper = (UserMapper) applicationContext
            .getBean("userMapper");

        String friendIdsStr = userMapper.getFriendsById(userId);
        Map<Integer, Location> friendCurLocMap = null;
        if (StringUtils.isBlank(friendIdsStr)) {
            logger.debug("user {} has no friends", userId);
        } else {
            String[] friendIds = friendIdsStr.split(";");
            Set<Integer> friendIdSet = new HashSet<Integer>();
            for (String friendId: friendIds) {
                friendIdSet.add(Integer.valueOf(friendId));
            }
            logger.debug("user {} has {} friends", userId, friendIdSet.size());
            friendCurLocMap = getFriendCurrentLocations(friendIdSet);
        }
        LocateResponse response = createResponse(true, 1, "locate success",
            friendCurLocMap);
        ctx.channel().writeAndFlush(response);
        logger.debug("locate response send to client, response = {}", response);
    }

    /**
     * 获得Response
     * 
     * @param isSuccess
     * @param status
     * @param message
     * @param friendLocMap
     * @return
     */
    private LocateResponse createResponse(boolean isSuccess, int status,
        String message, Map<Integer, Location> friendLocMap) {
        LocateResponse.Builder response = LocateResponse.newBuilder();
        response.setIsSuccess(isSuccess);
        response.setStatus(status);
        response.setMessage(message);
        if (friendLocMap != null) {
            response.addAllFriendLocations(friendLocMap.values());
        }
        return response.build();

    }

    /**
     * 获得朋友当前定位位置(在线)
     * 
     * @param friendIds
     * @return
     */
    private Map<Integer, Location> getFriendCurrentLocations(
        Set<Integer> friendIds) {
        Map<Integer, Location> friendCurLocMap = new HashMap<Integer, Location>();
        if (allCurrentLocation != null) {
            for (Integer friendId: friendIds) {
                Location location = allCurrentLocation.get(friendId);
                if (location != null) {
                    friendCurLocMap.put(friendId, location);
                }
            }
        }
        return friendCurLocMap;
    }

    /**
     * 获得朋友最后定位位置
     * 
     * @param friendNicknamesMap
     * @return
     */
    @SuppressWarnings("unused")
    private Map<Integer, Location> getFriendLatestLocations(
        Set<Integer> friendIds) {
        Map<Integer, Location> friendLatestLocMap = new HashMap<Integer, Location>();
        if (allLatestLocation != null) {
            for (Integer friendId: friendIds) {
                Location location = allLatestLocation.get(friendId);
                if (location != null) {
                    friendLatestLocMap.put(Integer.valueOf(friendId), location);
                }
            }
        }
        return friendLatestLocMap;
    }
}
