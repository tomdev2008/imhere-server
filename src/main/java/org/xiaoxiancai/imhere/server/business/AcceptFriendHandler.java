/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.business;

import io.netty.channel.ChannelHandlerContext;

import org.apache.commons.lang.StringUtils;
import org.xiaoxiancai.imhere.common.protos.business.AcceptFriendRequestProtos.AcceptFriendRequest;
import org.xiaoxiancai.imhere.common.protos.business.AcceptFriendResponseProtos.AcceptFriendResponse;
import org.xiaoxiancai.imhere.server.entity.User;
import org.xiaoxiancai.imhere.server.inter.AddFriendMessageMapper;
import org.xiaoxiancai.imhere.server.inter.UserMapper;

/**
 * 接受好友处理器
 * 
 * @author xiannenglin
 */
public class AcceptFriendHandler extends AbstractBusinessHandler {

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
		AddFriendMessageMapper friendMapper = (AddFriendMessageMapper) applicationContext
				.getBean("addFriendMessageMapper");
		User toUser = (User) userMapper.getById(toUserId);
		if (accept) {
			String fromUserFriendIds = userMapper.getFriendsById(fromUserId);
			String toUserFriendIds = toUser.getFriends();
			if (StringUtils.isBlank(fromUserFriendIds)) {
				fromUserFriendIds = String.valueOf(toUserId);
			} else {
				fromUserFriendIds = fromUserFriendIds + ";" + toUserId;
			}
			if (StringUtils.isBlank(toUserFriendIds)) {
				toUserFriendIds = String.valueOf(fromUserId);
			} else {
				toUserFriendIds = toUserFriendIds + ";" + fromUserId;
			}

			// 更新好友关系
			userMapper
					.updateUser(getUserToUpdate(fromUserId, fromUserFriendIds));
			userMapper.updateUser(getUserToUpdate(toUserId, toUserFriendIds));
			AcceptFriendResponse response = createResponse(true, 1,
					"accept friend");
			ctx.channel().writeAndFlush(response);
		} else {
			AcceptFriendResponse response = createResponse(false, -1,
					"reject friend");
			ctx.channel().writeAndFlush(response);
		}

		// 删除添加好友消息
		friendMapper.removeAddFriendMessage(fromUserId, toUser.getMobile());

	}

	/**
	 * 生成响应
	 * 
	 * @param isSuccess
	 * @param status
	 * @param message
	 * @return
	 */
	private AcceptFriendResponse createResponse(boolean isSuccess, int status,
			String message) {
		AcceptFriendResponse.Builder builder = AcceptFriendResponse
				.newBuilder();
		builder.setIsSuccess(isSuccess);
		builder.setStatus(status);
		builder.setMessage(message);
		return builder.build();
	}

	/**
	 * 获得待更新用户
	 * 
	 * @param userId
	 * @param friendIds
	 * @return
	 */
	private User getUserToUpdate(int userId, String friendIds) {
		User user = new User();
		user.setId(userId);
		user.setFriends(friendIds);
		return user;
	}

}
