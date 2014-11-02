/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.test;

import org.xiaoxiancai.imhere.client.ImHereClient;
import org.xiaoxiancai.imhere.server.business.register.RegisterProtos.Register;

/**
 * 客户端测试类
 * 
 * @author xiannenglin
 */
public class ClientTest {

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 1; i++) {
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
		Register register = createRegister();
		try {
			client.register(register);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 */
	private Register createRegister() {
		Register.Builder builder = Register.newBuilder();
		builder.setMobile("156*****876");
		return builder.build();
	}

}
