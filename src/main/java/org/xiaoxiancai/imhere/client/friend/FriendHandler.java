/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.client.friend;

import org.xiaoxiancai.imhere.client.AbstractClientHandler;
import org.xiaoxiancai.imhere.common.protos.business.FriendResponseProtos.FriendResponse;

/**
 * @author linxianneng
 */
public class FriendHandler extends AbstractClientHandler {

    private FriendResponse response;

    public FriendResponse getResponse() {
        return response;
    }

}
