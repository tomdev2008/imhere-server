/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.inter;

import org.xiaoxiancai.imhere.server.entity.User;

/**
 * 
 * 
 * @author xiannenglin
 */
public interface UserMapper extends EntityMapper {

	/**
	 * 
	 * @param mobile
	 * @return
	 */
	public User getUserByMobile(String mobile);

	/**
	 * 
	 * @param user
	 */
	public void addUser(User user);

	/**
	 * 
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * 
	 * @param id
	 */
	public void deleteUser(int id);

}
