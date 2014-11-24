/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.business;

import io.netty.channel.ChannelHandlerContext;

import org.xiaoxiancai.imhere.common.protos.business.AcceptFriendRequestProtos.AcceptFriendRequest;
import org.xiaoxiancai.imhere.common.protos.business.AcceptFriendResponseProtos.AcceptFriendResponse;
import org.xiaoxiancai.imhere.server.inter.UserMapper;

/**
 * 接受好友处理器
 *
 * @author xiannenglin
 */
public class AcceptFriendHandler extends AbstractBusinessHandler {

	private AcceptFriendResponse response;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if (!(msg instanceof AcceptFriendRequest)) {
			return;
		}
		AcceptFriendRequest request = (AcceptFriendRequest) msg;
		int fromUserId = request.getFromUserId();
		int toUserId = request.getToUserId();
		boolean accept = request.getAccept();
		logger.debug("accept friend from = {}, to = {}, accept = {}",
				fromUserId, toUserId, accept);
		UserMapper userMapper = (UserMapper) applicationContext
				.getBean("userMapper");
		
		if (accept) {
			String fromUserFriends = userMapper.getAllFriendsById(fromUserId);
			String toUserFriends = userMapper.getAllFriendsById(toUserId);
			fromUserFriends = fromUserFriends + ";" + toUserFriends;
			toUserFriends = toUserFriends + ";" + fromUserFriends;
			// TODO save to db
		}
		
//		DBHelper.removeAddFriendRequestFromDB(fromUserId, toUserId,
//				userMapper);
	}

	public AcceptFriendResponse getResponse() {
		return response;
	}

}
