/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.business;

import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    // TODO
    private Map<Integer, Location> allLatestLocationMap;

    /**
     * 所有用户当前位置
     */
    private Map<Integer, Location> allCurrentLocationMap;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception {
        if (!(msg instanceof LocateRequest)) {
            return;
        }
        linkServer = (LinkServer) applicationContext.getBean("linkServer");
        LocateRequest request = (LocateRequest) msg;
        logger.debug("receive client locate request = {}", request);

        Location currentLocation = request.getCurrentLocation();
        allLatestLocationMap = linkServer.getLatestLocation();
        allCurrentLocationMap = linkServer.getCurrentLocation();
        int userId = currentLocation.getUserId();
        allCurrentLocationMap.put(userId, currentLocation);
        UserMapper userMapper = (UserMapper) applicationContext
            .getBean("userMapper");
        String friendNicknames = userMapper.getFriends(userId);
        Set<Integer> friendIds = getFriendIds(friendNicknames);
        Map<Integer, Location> friendCurLocMap = getFriendCurrentLocations(friendIds);
        LocateResponse response = getResponse(true, 1, "locate success",
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
    private LocateResponse getResponse(boolean isSuccess, int status,
        String message, Map<Integer, Location> friendLocMap) {
        LocateResponse.Builder response = LocateResponse.newBuilder();
        response.setIsSuccess(isSuccess);
        response.setStatus(status);
        response.setMessage(message);
        response.addAllFriendLocations(friendLocMap.values());
        return response.build();

    }

    /**
     * 获取朋友id集合
     * 
     * @param friendNicknames
     * @return
     */
    private Set<Integer> getFriendIds(String friendNicknames) {
        Map<Integer, String> friendNicknamesMap = splitFriendNicknames(friendNicknames);
        return friendNicknamesMap.keySet();
    }

    /**
     * 处理好友昵称
     * 
     * @param friendsNicknames
     * @return
     */
    private Map<Integer, String> splitFriendNicknames(String friendsNicknames) {
        Map<Integer, String> friendNicknameMap = new HashMap<Integer, String>();
        String[] idNickNamePairs = friendsNicknames.split(";");
        for (String pair: idNickNamePairs) {
            int index = pair.indexOf(":");
            if (index > 0) {
                int friendId = Integer.valueOf(pair.substring(0, index));
                String friendNickname = pair.substring(index + 1);
                friendNicknameMap.put(friendId, friendNickname);
            }
        }
        return friendNicknameMap;
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
        if (allCurrentLocationMap != null) {
            for (Integer friendId: friendIds) {
                Location location = allCurrentLocationMap.get(friendId);
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
        if (allLatestLocationMap != null) {
            for (Integer friendId: friendIds) {
                Location location = allLatestLocationMap.get(friendId);
                if (location != null) {
                    friendLatestLocMap.put(friendId, location);
                }
            }
        }
        return friendLatestLocMap;
    }
}
