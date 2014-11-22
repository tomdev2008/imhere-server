/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.business;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;

import org.xiaoxiancai.imhere.common.protos.business.FriendRequestProtos.FriendRequest;
import org.xiaoxiancai.imhere.common.protos.business.LocationProtos.Location;
import org.xiaoxiancai.imhere.server.LinkServer;
import org.xiaoxiancai.imhere.server.entity.FriendMessage;
import org.xiaoxiancai.imhere.server.inter.UserMapper;

/**
 * 添加朋友处理器
 * 
 * @author xiannenglin
 */
public class FriendHandler extends AbstractBusinessHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception {
        if (!(msg instanceof FriendRequest)) {
            return;
        }

        FriendRequest request = (FriendRequest) msg;
        UserMapper userMapper = (UserMapper) applicationContext
            .getBean("userMapper");

        String fromUserMobile = request.getFromUserMobile();
        String toUserMobile = request.getToUserMobile();

        int toUserId = request.getToUserId();
        if (toUserId != 0) {
            LinkServer linkServer = (LinkServer) applicationContext
                .getBean("linkServer");
            Map<Integer, Location> currentLocationMap = linkServer
                .getCurrentLocation();

            // 被添加好友在线, 及时通知
            if (currentLocationMap.containsKey(toUserId)) {
                // TODO
            }
        }

        // 添加自己
        if (fromUserMobile.equals(toUserMobile)) {
            return;
        }
        if (!hasSentFriendMessage(fromUserMobile, toUserMobile, userMapper)) {
            FriendMessage message = getAddFriendMessage(request);
            userMapper.addFriend(message);
            logger.debug(
                "save add friend msg, fromUserMobile = {}, toUserMobile = {}",
                fromUserMobile, toUserMobile);
        }

    }

    /**
     * 是否已发添加请求
     * 
     * @param fromUserMobile
     * @param toUserMobile
     * @param userMapper
     * @return
     */
    private boolean hasSentFriendMessage(String fromUserMobile,
        String toUserMobile, UserMapper userMapper) {
        FriendMessage message = new FriendMessage();
        message.setFromUserMobile(fromUserMobile);
        message.setToUserMobile(toUserMobile);
        int count = userMapper.countFriendMessage(message);
        return count == 0 ? false : true;
    }

    /**
     * 生成添加好友信息
     * 
     * @param request
     * @return
     */
    private FriendMessage getAddFriendMessage(FriendRequest request) {
        FriendMessage msg = new FriendMessage();
        msg.setFromUserId(request.getFromUserId());
        msg.setFromUserMobile(request.getFromUserMobile());
        msg.setFromUserNickname(request.getFromUserNickname());
        msg.setToUserMobile(request.getToUserMobile());
        int toUserId;
        if ((toUserId = request.getToUserId()) != 0) {
            msg.setToUserId(toUserId);
        }
        return msg;
    }
}
