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
		for (int i = 0; i < 30; i++) {
			Thread t = new Thread(new Task());
			t.start();
		}
	}

}

class Task implements Runnable {

	@Override
	public void run() {
		ImHereClient client = new ImHereClient();
		client.setServer("localhost", 18080);
		User user = createUser();
		try {
			client.register(user);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 */
	private User createUser() {
		User.Builder builder = User.newBuilder();
		builder.setMobile("15658111876");
		return builder.build();
	}

}
