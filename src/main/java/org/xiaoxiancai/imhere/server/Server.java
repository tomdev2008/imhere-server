/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server;

/**
 * 服务器接口
 * 
 * @author xiannenglin
 */
public interface Server {

	/**
	 * 开启服务
	 */
	public void start() throws Exception;

	/**
	 * 停止服务
	 */
	public void stop() throws Exception;
}
