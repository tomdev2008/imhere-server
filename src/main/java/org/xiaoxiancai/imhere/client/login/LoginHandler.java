/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.client.login;

import org.xiaoxiancai.imhere.client.AbstractClientHandler;
import org.xiaoxiancai.imhere.common.protos.business.LoginResponseProtos.LoginResponse;

/**
 * 登录处理器
 * 
 * @author xiannenglin
 */
public class LoginHandler extends AbstractClientHandler {

    private LoginResponse response;

    /**
     * @return the response
     */
    public LoginResponse getResponse() {
        return response;
    }
}
