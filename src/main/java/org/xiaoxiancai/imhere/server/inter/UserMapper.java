/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.inter;

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
    public String getFriendsById(int userId);

    /**
     * 通过mobile获得所有好友id
     * 
     * @param mobile
     * @return
     */
    public String getFriendsByMobile(String mobile);

    /**
     * 通过id获取昵称
     * 
     * @param userId
     * @return
     */
    public String getNicknameById(int userId);

}
