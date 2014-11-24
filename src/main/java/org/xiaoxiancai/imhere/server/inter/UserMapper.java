/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.inter;

import java.util.List;

import org.xiaoxiancai.imhere.server.entity.AddFriendMessage;
import org.xiaoxiancai.imhere.server.entity.User;

/**
 * 用户操作接口
 * 
 * @author xiannenglin
 */
public interface UserMapper extends EntityMapper {

	/**
	 * 根据Mobile获得用户
	 * 
	 * @param mobile
	 * @return
	 */
	public User getUserByMobile(String mobile);

	/**
	 * 注册用户
	 * 
	 * @param user
	 */
	public void registerUser(User user);

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * 通过id获得所有好友id
	 * 
	 * @param userId
	 * @return
	 */
	public String getAllFriendsById(int userId);

	/**
	 * 通过mobile获得所有好友id
	 * 
	 * @param mobile
	 * @return
	 */
	public String getAllFriendsByMobile(String mobile);

	/**
	 * 添加好友
	 * 
	 * @param addFriendMessage
	 */
	public void addFriend(AddFriendMessage addFriendMessage);

	/**
	 * 发添加好友消息数
	 * 
	 * @param fromUserMobile
	 * @param toUserMobile
	 * @return
	 */
	public int countAddFriendMessage(AddFriendMessage addFriendMessage);

	/**
	 * 获得添加好友消息
	 * 
	 * @param toUserMobile
	 * @return
	 */
	public List<AddFriendMessage> getAddFriendMessage(String toUserMobile);

	/**
	 * 移除添加好友消息
	 * 
	 * @param fromUserId
	 * @param toUserId
	 */
	public void removeAddFriendMessage(int fromUserId, int toUserId);

	/**
	 * 通过id获取昵称
	 */
	public void getNicknameById(int userId);
}
