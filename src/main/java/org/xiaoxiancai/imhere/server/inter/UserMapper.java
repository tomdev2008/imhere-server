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
     * 获得好友昵称
     * <p>
     * DB格式
     * <p>
     * id1:nickname1;id2:nickname2...idN:nicknameN
     * 
     * @param userId
     * @return
     */
    public String getFriends(int userId);

}
