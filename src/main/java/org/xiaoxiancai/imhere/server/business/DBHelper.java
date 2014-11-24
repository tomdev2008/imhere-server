/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.business;

import java.util.ArrayList;
import java.util.List;

import org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest;
import org.xiaoxiancai.imhere.server.entity.AddFriendMessage;
import org.xiaoxiancai.imhere.server.inter.UserMapper;

/**
 * 数据库操作帮助类
 * 
 * @author linxianneng
 */
public class DBHelper {
    private DBHelper() {

    }

    /**
     * 从数据库中获取添加好友消息
     * 
     * @param toUserMobile
     * @param userMapper
     * @return
     */
    public static List<AddFriendRequest> getAddFriendRequestFromDB(
        String toUserMobile, UserMapper userMapper) {
        List<AddFriendMessage> messages = userMapper
            .getAddFriendMessage(toUserMobile);
        if (messages != null && !messages.isEmpty()) {
            List<AddFriendRequest> requestList = new ArrayList<AddFriendRequest>();
            for (AddFriendMessage message: messages) {
                AddFriendRequest.Builder builder = AddFriendRequest
                    .newBuilder();
                builder.setFromUserId(message.getFromUserId());
                builder.setFromUserNickname(message.getFromUserNickname());
                builder.setToUserMobile(message.getToUserMobile());
                requestList.add(builder.build());
            }
            return requestList;
        } else {
            return null;
        }
    }

    /**
     * 从数据库中移除添加好友消息
     * 
     * @param fromUserId
     * @param toUserId
     * @param userMapper
     */
    public static void removeAddFriendRequestFromDB(int fromUserId,
        int toUserId, UserMapper userMapper) {
        userMapper.removeAddFriendMessage(fromUserId, toUserId);
    }
}
