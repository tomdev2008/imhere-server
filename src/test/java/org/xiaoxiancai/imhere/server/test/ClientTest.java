/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.test;

import org.xiaoxiancai.imhere.client.locate.LocateClient;
import org.xiaoxiancai.imhere.client.login.LoginClient;
import org.xiaoxiancai.imhere.client.register.RegisterClient;
import org.xiaoxiancai.imhere.common.protos.business.LocateRequestProtos.LocateRequest;
import org.xiaoxiancai.imhere.common.protos.business.LocateResponseProtos.LocateResponse;
import org.xiaoxiancai.imhere.common.protos.business.LocationProtos.Location;
import org.xiaoxiancai.imhere.common.protos.business.LoginRequestProtos.LoginRequest;
import org.xiaoxiancai.imhere.common.protos.business.LoginResponseProtos.LoginResponse;
import org.xiaoxiancai.imhere.common.protos.business.RegisterRequestProtos.RegisterRequest;

/**
 * 客户端测试类
 * 
 * @author xiannenglin
 */
public class ClientTest {

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 1; i++) {
//			Thread t = new Thread(new RegisterTask());
//			Thread t = new Thread(new LocateTask());
			Thread t = new Thread(new LoginTask());
			t.start();
		}
	}

}

class LoginTask implements Runnable {

	@Override
	public void run() {
		LoginClient client = new LoginClient();
		client.setServer("localhost", 18080);
		LoginRequest request = createLoginRequest();
		try {
			LoginResponse response = client.login(request);
			System.out.println(response.getIsSuccess());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 */
	private LoginRequest createLoginRequest() {
		LoginRequest.Builder builder = LoginRequest.newBuilder();
		builder.setMobile("13758781751");
		builder.setPassword("pswd-2");
		return builder.build();
	}
}

class LocateTask implements Runnable {

	@Override
	public void run() {
		LocateClient client = new LocateClient();
		client.setServer("localhost", 18080);
		LocateRequest request = createLocateRequest();
		try {
			LocateResponse response = client.locate(request);
			System.out.println(response.getIsSuccess());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 */
	private LocateRequest createLocateRequest() {
		Location.Builder locBuilder = Location.newBuilder();
		locBuilder.setLocType(61);
		locBuilder.setLatitude(30.7783d);
		locBuilder.setLongitude(120.7783d);
		LocateRequest.Builder builder = LocateRequest.newBuilder();
		builder.setCurrentLocation(locBuilder.build());
		return builder.build();
	}
}

class RegisterTask implements Runnable {

	@Override
	public void run() {
		RegisterClient client = new RegisterClient();
		client.setServer("localhost", 18080);
		for (int i = 11; i < 12; i++) {
			RegisterRequest register = createRegister(i);
			try {
				client.register(register);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return
	 */
	private RegisterRequest createRegister(int index) {
		RegisterRequest.Builder request = RegisterRequest.newBuilder();
		request.setMobile("1375878175" + index);
		request.setNickName("nickName-" + index);
		request.setPassword("pswd-" + index);
		request.setEmail("email-" + index + "@gmail.com");
		request.setSignature("signature-" + index);
		return request.build();
	}

}
