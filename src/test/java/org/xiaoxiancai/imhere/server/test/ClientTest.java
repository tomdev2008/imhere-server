/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.test;

import org.xiaoxiancai.imhere.client.register.RegisterClient;
import org.xiaoxiancai.imhere.common.protos.business.RegisterRequestProtos.RegisterRequest;

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
