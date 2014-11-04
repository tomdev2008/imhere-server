/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.test;

import org.xiaoxiancai.imhere.client.ImHereClient;
import org.xiaoxiancai.imhere.server.business.register.RegisterRequestProtos.RegisterRequest;

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
        RegisterRequest register = createRegister();
        try {
            client.register(register);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    private RegisterRequest createRegister() {
        RegisterRequest.Builder request = RegisterRequest.newBuilder();
        request.setMobile("137*****758");
        request.setNickName("xxoo");
        request.setPassword("xxoo");
        request.setEmail("xxoo@gmail.com");
        request.setSignature("xxoo");
        return request.build();
    }

}
