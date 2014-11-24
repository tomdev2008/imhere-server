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
     * 获得好友昵称
     * <p>
     * DB格式
     * <p>
     * id1:nickname1;id2:nickname2...idN:nicknameN
     * 
     * @param userId
     * @return
     */
    public String getAllFriends(int userId);

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
     * @param mobile
     * @return
     */
    public List<AddFriendMessage> getAddFriendMessage(String mobile);

    /**
     * 移除添加好友消息
     * 
     * @param mobile
     */
    public void removeAddFriendMessage(String mobile);
}
