/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.client;

import java.util.HashMap;
import java.util.Map;

/**
 * 客户端工厂类
 *
 * @author xiannenglin
 */
public class ClientFactory {

	private ClientFactory() {
	}

	/**
	 * 客户端Map
	 */
	private static Map<String, Client> clientMap = new HashMap<String, Client>();

	/**
	 * 默认客户端名
	 */
	private static final String DEFAULT_CLIENT_NAME = "default";

	/**
	 * 获取默认客户端
	 * 
	 * @param serverHost
	 * @param serverPort
	 * @return
	 * @throws Exception
	 */
	public static DefaultClient getDefaultClient(String serverHost,
			int serverPort) {
		if (clientMap.containsKey(DEFAULT_CLIENT_NAME)) {
			return (DefaultClient) clientMap.get(DEFAULT_CLIENT_NAME);
		} else {
			DefaultClient client = new DefaultClient(serverHost, serverPort);
			clientMap.put(DEFAULT_CLIENT_NAME, client);
			return client;
		}
	}

}
