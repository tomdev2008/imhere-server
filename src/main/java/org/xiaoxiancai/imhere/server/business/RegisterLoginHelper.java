/**
 * @(#)RegisterLoginHelper.java, 2014-11-25.
 *
 * Copyright 2014 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.xiaoxiancai.imhere.server.business;

import java.util.ArrayList;
import java.util.List;

import org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest;
import org.xiaoxiancai.imhere.server.entity.AddFriendMessage;
import org.xiaoxiancai.imhere.server.inter.AddFriendMessageMapper;

/**
 * @author linxianneng
 */
public class RegisterLoginHelper {

    private RegisterLoginHelper() {}

    public static List<AddFriendRequest> getAddFriendRequestFromDB(
        String toUserMobile, AddFriendMessageMapper mapper) {
        List<AddFriendMessage> messages = mapper
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
}
