/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.business;

import io.netty.channel.ChannelHandlerContext;

import org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest;
import org.xiaoxiancai.imhere.server.entity.AddFriendMessage;
import org.xiaoxiancai.imhere.server.inter.AddFriendMessageMapper;

/**
 * 添加朋友处理器
 * 
 * @author xiannenglin
 */
public class AddFriendHandler extends AbstractBusinessHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception {
        if (!(msg instanceof AddFriendRequest)) {
            return;
        }

        AddFriendRequest request = (AddFriendRequest) msg;
        AddFriendMessageMapper friendMapper = (AddFriendMessageMapper) applicationContext
            .getBean("addFriendMessageMapper");

        int fromUserId = request.getFromUserId();
        String toUserMobile = request.getToUserMobile();

        if (!hasSentFriendMessage(fromUserId, toUserMobile, friendMapper)) {
            AddFriendMessage message = getAddFriendMessage(request);
            friendMapper.saveAddFriendMessage(message);
            logger.debug(
                "save add friend msg, fromUserId = {}, toUserMobile = {}",
                fromUserId, toUserMobile);
        }

    }

    /**
     * 是否已发添加请求
     * 
     * @param fromUserId
     * @param toUserMobile
     * @param mapper
     * @return
     */
    private boolean hasSentFriendMessage(int fromUserId, String toUserMobile,
        AddFriendMessageMapper mapper) {
        AddFriendMessage message = new AddFriendMessage();
        message.setFromUserId(fromUserId);
        message.setToUserMobile(toUserMobile);
        int count = mapper.countAddFriendMessage(message);
        return count == 0 ? false : true;
    }

    /**
     * 生成添加好友信息
     * 
     * @param request
     * @return
     */
    private AddFriendMessage getAddFriendMessage(AddFriendRequest request) {
        AddFriendMessage msg = new AddFriendMessage();
        msg.setFromUserId(request.getFromUserId());
        msg.setFromUserNickname(request.getFromUserNickname());
        msg.setToUserMobile(request.getToUserMobile());
        return msg;
    }
}
