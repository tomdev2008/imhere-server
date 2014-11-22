/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.client.friend;

import static org.xiaoxiancai.imhere.client.utils.ClientConstant.DECODER_ADD_FRIEND;
import static org.xiaoxiancai.imhere.client.utils.ClientConstant.HANDLER_ADD_FRIEND;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

import org.xiaoxiancai.imhere.client.AbstractClient;
import org.xiaoxiancai.imhere.common.protos.business.FriendRequestProtos.FriendRequest;
import org.xiaoxiancai.imhere.common.protos.business.FriendResponseProtos.FriendResponse;
import org.xiaoxiancai.imhere.common.protos.common.BusinessTypeProtos.BusinessType;

/**
 * 添加好友客户端
 * 
 * @author linxianneng
 */
public class FriendClient extends AbstractClient {

    /**
     * 添加好友
     * 
     * @param request
     * @throws InterruptedException
     */
    public FriendResponse addFriend(FriendRequest request)
        throws InterruptedException {
        Channel channel = connect(BusinessType.FRIEND);
        if (channel != null && channel.isActive()) {
            logger.debug("send add friend message to server");
            ChannelPipeline pipeline = channel.pipeline();
            logger.debug(
                "addfriend client pipeline before adding handler = {}",
                pipeline);
            if (pipeline.get(DECODER_ADD_FRIEND) == null) {
                pipeline.addLast(DECODER_ADD_FRIEND, new ProtobufDecoder(
                    FriendResponse.getDefaultInstance()));
            }

            if (pipeline.get(HANDLER_ADD_FRIEND) == null) {
                pipeline.addLast(HANDLER_ADD_FRIEND, new FriendHandler());
            }
            FriendHandler addFriendHandler = (FriendHandler) pipeline
                .get(HANDLER_ADD_FRIEND);
            logger.debug("addfriend client pipeline after adding handler = {}",
                pipeline);
            synchronized (addFriendHandler) {
                channel.writeAndFlush(request).sync();
                addFriendHandler.wait();
            }
            FriendResponse response = addFriendHandler.getResponse();
            logger.info("addFriend response = {}", response);
            return response;
        } else {
            logger.error("channel is null or inactive");
            return null;
        }
    }
}
