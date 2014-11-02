/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.inter;

import org.xiaoxiancai.imhere.server.entity.Entity;

/**
 * 实体类接口
 * 
 * @author xiannenglin
 */
public interface EntityMapper {

	/**
	 * 通过id获得实体类
	 * 
	 * @param id
	 * @return
	 */
	public Entity getById(int id);

}
