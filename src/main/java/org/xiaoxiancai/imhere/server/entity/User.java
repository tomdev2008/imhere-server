/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户
 * 
 * @author xiannenglin
 */
public class User extends Entity {

	/**
	 * 移动电话-required
	 */
	private String mobile;

	/**
	 * 昵称-required
	 */
	private String nickName;

	/**
	 * 邮箱-optional
	 */
	private String email;

	/**
	 * 个性签名-optional
	 */
	private String signature;

	private List<UserGroup> groups = new ArrayList<UserGroup>();

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public List<UserGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<UserGroup> groups) {
		this.groups = groups;
	}

}
