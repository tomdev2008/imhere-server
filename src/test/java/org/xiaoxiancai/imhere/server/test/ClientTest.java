/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.test;

import org.xiaoxiancai.imhere.client.ClientFactory;
import org.xiaoxiancai.imhere.client.DefaultClient;
import org.xiaoxiancai.imhere.common.protos.business.AcceptFriendRequestProtos.AcceptFriendRequest;
import org.xiaoxiancai.imhere.common.protos.business.AcceptFriendResponseProtos.AcceptFriendResponse;
import org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest;
import org.xiaoxiancai.imhere.common.protos.business.AddFriendResponseProtos.AddFriendResponse;
import org.xiaoxiancai.imhere.common.protos.business.LocateRequestProtos.LocateRequest;
import org.xiaoxiancai.imhere.common.protos.business.LocateResponseProtos.LocateResponse;
import org.xiaoxiancai.imhere.common.protos.business.LocationProtos.Location;
import org.xiaoxiancai.imhere.common.protos.business.LoginRequestProtos.LoginRequest;
import org.xiaoxiancai.imhere.common.protos.business.LoginResponseProtos.LoginResponse;
import org.xiaoxiancai.imhere.common.protos.business.RegisterRequestProtos.RegisterRequest;
import org.xiaoxiancai.imhere.common.protos.business.RegisterResponseProtos.RegisterResponse;

/**
 * 客户端测试类
 * 
 * @author xiannenglin
 */
public class ClientTest {

    public static void main(String[] args) throws Exception {
        ClientTest tester = new ClientTest();
        ClientFactory factory = ClientFactory.getInstance();
        DefaultClient client = factory.getDefaultClient("localhost", 18080);
        //		 tester.testRegister(client);
        // tester.testLogin(client);
        // tester.testAddFriend(client);
        // tester.testAcceptFriend(client);
        tester.testLocate(client);
    }

    /**
     * 测试:注册
     */
    public void testRegister(DefaultClient client) {
        try {
            for (int i = 1; i < 5; i++) {
                RegisterRequest register = createRegister(i);
                RegisterResponse response = client.register(register);
                System.out.println("register response = " + response);
            }
            client.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    private RegisterRequest createRegister(int index) {
        RegisterRequest.Builder request = RegisterRequest.newBuilder();
        request.setMobile("1565811100" + index);
        request.setNickName("nickName-" + index);
        request.setPassword("pswd-" + index);
        request.setEmail("email-" + index + "@gmail.com");
        request.setSignature("signature-" + index);
        return request.build();
    }

    /**
     * 测试:登录
     */
    public void testLogin(DefaultClient client) {
        LoginRequest request = createLoginRequest();
        try {
            LoginResponse response = client.login(request);
            System.out.println("login response = " + response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    private LoginRequest createLoginRequest() {
        LoginRequest.Builder builder = LoginRequest.newBuilder();
        builder.setMobile("15658111002");
        builder.setPassword("pswd-2");
        return builder.build();
    }

    /**
     * 测试:添加好友
     */
    public void testAddFriend(DefaultClient client) {
        AddFriendRequest request1 = createAddFriendRequest(25, "nickName-1",
            "15658111004");
        AddFriendRequest request2 = createAddFriendRequest(25, "nickName-1",
            "15658111003");
        try {
            AddFriendResponse response1 = client.addFriend(request1);
            System.out.println("add friend response1 = " + response1);
            AddFriendResponse response2 = client.addFriend(request2);
            System.out.println("add friend response2 = " + response2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    private AddFriendRequest createAddFriendRequest(int fromUserId,
        String fromUserNickname, String toUserMobile) {
        AddFriendRequest.Builder builder = AddFriendRequest.newBuilder();
        builder.setFromUserId(fromUserId);
        builder.setFromUserNickname(fromUserNickname);
        builder.setToUserMobile(toUserMobile);
        return builder.build();
    }

    /**
     * 测试:接受好友
     */
    public void testAcceptFriend(DefaultClient client) {
        AcceptFriendRequest request1 = createAcceptFriendRequest(25, 28, true);
        AcceptFriendRequest request2 = createAcceptFriendRequest(25, 27, false);
        try {
            AcceptFriendResponse response1 = client.acceptFriend(request1);
            System.out.println("accept friend response1 = " + response1);
            AcceptFriendResponse response2 = client.acceptFriend(request2);
            System.out.println("accept friend response2 = " + response2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    private AcceptFriendRequest createAcceptFriendRequest(int fromUserId,
        int toUserId, boolean accept) {
        AcceptFriendRequest.Builder builder = AcceptFriendRequest.newBuilder();
        builder.setFromUserId(fromUserId);
        builder.setToUserId(toUserId);
        builder.setAccept(accept);
        return builder.build();
    }

    /**
     * 测试:定位
     */
    public void testLocate(DefaultClient client) {
        LocateRequest request1 = createLocateRequest1();
        LocateRequest request2 = createLocateRequest2();
        LocateRequest request3 = createLocateRequest3();
        try {
            LocateResponse response1 = client.locate(request1);
            System.out.println("locate response1 = " + response1);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            LocateResponse response2 = client.locate(request2);
            System.out.println("locate response2 = " + response2);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            LocateResponse response3 = client.locate(request3);
            System.out.println("locate response3 = " + response3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    private LocateRequest createLocateRequest1() {
        Location.Builder locBuilder = Location.newBuilder();
        locBuilder.setUserId(21);
        locBuilder.setLocType(62);
        locBuilder.setLatitude(31.3333d);
        locBuilder.setLongitude(141.3333d);
        LocateRequest.Builder builder = LocateRequest.newBuilder();
        builder.setCurrentLocation(locBuilder.build());
        return builder.build();
    }

    /**
     * @return
     */
    private LocateRequest createLocateRequest2() {
        Location.Builder locBuilder = Location.newBuilder();
        locBuilder.setUserId(4);
        locBuilder.setLocType(62);
        locBuilder.setLatitude(32.4444d);
        locBuilder.setLongitude(142.4444d);
        LocateRequest.Builder builder = LocateRequest.newBuilder();
        builder.setCurrentLocation(locBuilder.build());
        return builder.build();
    }

    /**
     * @return
     */
    private LocateRequest createLocateRequest3() {
        Location.Builder locBuilder = Location.newBuilder();
        locBuilder.setUserId(5);
        locBuilder.setLocType(62);
        locBuilder.setLatitude(33.5555d);
        locBuilder.setLongitude(143.5555d);
        LocateRequest.Builder builder = LocateRequest.newBuilder();
        builder.setCurrentLocation(locBuilder.build());
        return builder.build();
    }
}
