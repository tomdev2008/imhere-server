/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.test;

import org.xiaoxiancai.imhere.client.ImHereClient;
import org.xiaoxiancai.imhere.server.protos.UserProtos.User;

/**
 * 客户端测试类
 * 
 * @author xiannenglin
 */
public class ClientTest {

	public static void main(String[] args) throws Exception {
		ImHereClient client = ImHereClient.getInstance();
		client.setServer("localhost", 18080);
		User user = createUser();
		client.register(user);
	}

	/**
	 * @return
	 */
	private static User createUser() {
		User.Builder builder = User.newBuilder();
		builder.setMobile("15658111876");
		return builder.build();
	}

}
