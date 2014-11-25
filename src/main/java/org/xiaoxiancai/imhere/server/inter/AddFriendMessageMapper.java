/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.inter;

import java.util.List;

import org.xiaoxiancai.imhere.server.entity.AddFriendMessage;

/**
 * @author linxianneng
 */
public interface AddFriendMessageMapper extends EntityMapper {

    /**
     * 保存添加好友消息
     * 
     * @param addFriendMessage
     */
    public void saveAddFriendMessage(AddFriendMessage addFriendMessage);

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
     * @param toUserMobile
     */
    public void removeAddFriendMessage(int fromUserId, String toUserMobile);

}
