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
public class AddFriendMessage extends Entity {

    /**
     * 发起者id
     */
    private int fromUserId;

    /**
     * 发起者昵称
     */
    private String fromUserNickname;

    /**
     * 被发起者电话
     */
    private String toUserMobile;


    public String getFromUserNickname() {
        return fromUserNickname;
    }

    /**
	 * @return the fromUserId
	 */
	public int getFromUserId() {
		return fromUserId;
	}

	/**
	 * @param fromUserId the fromUserId to set
	 */
	public void setFromUserId(int fromUserId) {
		this.fromUserId = fromUserId;
	}

	public void setFromUserNickname(String fromUserNickname) {
        this.fromUserNickname = fromUserNickname;
    }

    public String getToUserMobile() {
        return toUserMobile;
    }

    public void setToUserMobile(String toUserMobile) {
        this.toUserMobile = toUserMobile;
    }

}
