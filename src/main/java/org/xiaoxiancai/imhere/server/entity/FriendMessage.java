/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.entity;

/**
 * 添加朋友消息实体类
 * 
 * @author linxianneng
 */
public class FriendMessage extends Entity {

    /**
     * 发起者id
     */
    private int fromUserId;

    /**
     * 发起者电话
     */
    private String fromUserMobile;

    /**
     * 发起者昵称
     */
    private String fromUserNickname;

    /**
     * 被发起者id
     */
    private int toUserId;

    /**
     * 被发起者电话
     */
    private String toUserMobile;

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUserMobile() {
        return fromUserMobile;
    }

    public void setFromUserMobile(String fromUserMobile) {
        this.fromUserMobile = fromUserMobile;
    }

    public String getFromUserNickname() {
        return fromUserNickname;
    }

    public void setFromUserNickname(String fromUserNickname) {
        this.fromUserNickname = fromUserNickname;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public String getToUserMobile() {
        return toUserMobile;
    }

    public void setToUserMobile(String toUserMobile) {
        this.toUserMobile = toUserMobile;
    }

}
