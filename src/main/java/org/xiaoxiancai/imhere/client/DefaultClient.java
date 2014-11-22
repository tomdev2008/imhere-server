/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.client;

import static org.xiaoxiancai.imhere.client.utils.ClientConstant.DECODER_ADD_FRIEND;
import static org.xiaoxiancai.imhere.client.utils.ClientConstant.HANDLER_ADD_FRIEND;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

import org.xiaoxiancai.imhere.client.handler.AddFriendHandler;
import org.xiaoxiancai.imhere.client.handler.LocateHandler;
import org.xiaoxiancai.imhere.client.handler.LoginHandler;
import org.xiaoxiancai.imhere.client.handler.RegisterHandler;
import org.xiaoxiancai.imhere.client.utils.ClientConstant;
import org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest;
import org.xiaoxiancai.imhere.common.protos.business.AddFriendResponseProtos.AddFriendResponse;
import org.xiaoxiancai.imhere.common.protos.business.LocateRequestProtos.LocateRequest;
import org.xiaoxiancai.imhere.common.protos.business.LocateResponseProtos.LocateResponse;
import org.xiaoxiancai.imhere.common.protos.business.LoginRequestProtos.LoginRequest;
import org.xiaoxiancai.imhere.common.protos.business.LoginResponseProtos.LoginResponse;
import org.xiaoxiancai.imhere.common.protos.business.RegisterRequestProtos.RegisterRequest;
import org.xiaoxiancai.imhere.common.protos.business.RegisterResponseProtos.RegisterResponse;
import org.xiaoxiancai.imhere.common.protos.common.BusinessTypeProtos.BusinessType;

/**
 * 默认客户端实现
 * 
 * @author linxianneng
 */
public class DefaultClient extends AbstractClient {

    /**
     * 注册用户
     * 
     * @param request
     * @throws InterruptedException
     */
    @Override
	public RegisterResponse register(RegisterRequest request)
        throws InterruptedException {
        Channel channel = connect(BusinessType.REGISTER);
        if (channel != null && channel.isActive()) {
            logger.debug("send register user to server");
            ChannelPipeline pipeline = channel.pipeline();
            logger.debug("register client pipeline before adding handler = {}",
                pipeline);
            if (pipeline.get(ClientConstant.DECODER_REGISTER) == null) {
                pipeline.addLast(ClientConstant.DECODER_REGISTER,
                    new ProtobufDecoder(RegisterResponse.getDefaultInstance()));
            }

            if (pipeline.get(ClientConstant.HANDLER_REGISTER) == null) {
                pipeline.addLast(ClientConstant.HANDLER_REGISTER,
                    new RegisterHandler());
            }
            RegisterHandler registerHandler = (RegisterHandler) pipeline
                .get(ClientConstant.HANDLER_REGISTER);
            logger.debug("register client pipeline after adding handler = {}",
                pipeline);
            synchronized (registerHandler) {
                channel.writeAndFlush(request).sync();
                registerHandler.wait();
            }
            RegisterResponse response = registerHandler.getResponse();
            boolean isSuccess = response.getIsSuccess();
            int status = response.getStatus();
            String message = response.getMessage();
            logger.info(
                "register user is success = {}, status = {}, message = {}",
                isSuccess, status, message);
            return response;
        } else {
            logger.error("channel is null or inactive");
            return null;
        }
    }

    /**
     * 登录
     * 
     * @param request
     * @return
     * @throws InterruptedException
     */
    @Override
	public LoginResponse login(LoginRequest request)
        throws InterruptedException {
        Channel channel = connect(BusinessType.LOGIN);
        if (channel != null && channel.isActive()) {
            logger.debug("send login request to server");
            ChannelPipeline pipeline = channel.pipeline();
            logger.debug("login client pipeline before adding handler = {}",
                pipeline);
            if (pipeline.get(ClientConstant.DECODER_LOGIN) == null) {
                pipeline.addLast(ClientConstant.DECODER_LOGIN,
                    new ProtobufDecoder(LoginResponse.getDefaultInstance()));
            }

            if (pipeline.get(ClientConstant.HANDLER_LOGIN) == null) {
                pipeline.addLast(ClientConstant.HANDLER_LOGIN,
                    new LoginHandler());
            }
            LoginHandler loginHandler = (LoginHandler) pipeline
                .get(ClientConstant.HANDLER_LOGIN);
            logger.debug("login client pipeline after adding handler = {}",
                pipeline);
            synchronized (loginHandler) {
                channel.writeAndFlush(request).sync();
                loginHandler.wait();
            }
            LoginResponse response = loginHandler.getResponse();
            boolean isSuccess = response.getIsSuccess();
            int status = response.getStatus();
            String message = response.getMessage();
            logger.info("login success = {}, status = {}, message = {}",
                isSuccess, status, message);

            return response;
        } else {
            logger.error("channel is null or inactive");
            return null;
        }
    }

    /**
     * 定位
     * <p>
     * 将自己位置信息发给服务器, 并接受服务器其他人的位置信息
     * 
     * @param request
     * @throws InterruptedException
     */
    @Override
	public LocateResponse locate(LocateRequest request)
        throws InterruptedException {
        Channel channel = connect(BusinessType.LOCATE);
        if (channel != null && channel.isActive()) {
            logger.debug("send location to server");
            ChannelPipeline pipeline = channel.pipeline();
            logger.debug("locate client pipeline before adding handler = {}",
                pipeline);
            if (pipeline.get(ClientConstant.DECODER_LOCATE) == null) {
                pipeline.addLast(ClientConstant.DECODER_LOCATE,
                    new ProtobufDecoder(LocateResponse.getDefaultInstance()));
            }

            if (pipeline.get(ClientConstant.HANDLER_LOCATE) == null) {
                pipeline.addLast(ClientConstant.HANDLER_LOCATE,
                    new LocateHandler());
            }
            LocateHandler locateHandler = (LocateHandler) pipeline
                .get(ClientConstant.HANDLER_LOCATE);
            logger.debug("locate client pipeline after adding handler = {}",
                pipeline);
            synchronized (locateHandler) {
                channel.writeAndFlush(request).sync();
                locateHandler.wait();
            }
            LocateResponse response = locateHandler.getResponse();
            boolean isSuccess = response.getIsSuccess();
            int status = response.getStatus();
            String message = response.getMessage();
            logger.info("locate success = {}, status = {}, message = {}",
                isSuccess, status, message);
            executeCommand(response.getCommandsList());
            return response;
        } else {
            logger.error("channel is null or inactive");
            return null;
        }
    }

    /**
     * 添加好友
     * 
     * @param request
     * @throws InterruptedException
     */
    @Override
	public AddFriendResponse addFriend(AddFriendRequest request)
        throws InterruptedException {
        Channel channel = connect(BusinessType.ADD_FRIEND);
        if (channel != null && channel.isActive()) {
            logger.debug("send add friend message to server");
            ChannelPipeline pipeline = channel.pipeline();
            logger.debug(
                "addfriend client pipeline before adding handler = {}",
                pipeline);
            if (pipeline.get(DECODER_ADD_FRIEND) == null) {
                pipeline.addLast(DECODER_ADD_FRIEND, new ProtobufDecoder(
                		AddFriendResponse.getDefaultInstance()));
            }

            if (pipeline.get(HANDLER_ADD_FRIEND) == null) {
                pipeline.addLast(HANDLER_ADD_FRIEND, new AddFriendHandler());
            }
            AddFriendHandler addFriendHandler = (AddFriendHandler) pipeline
                .get(HANDLER_ADD_FRIEND);
            logger.debug("addfriend client pipeline after adding handler = {}",
                pipeline);
            synchronized (addFriendHandler) {
                channel.writeAndFlush(request).sync();
                addFriendHandler.wait();
            }
            AddFriendResponse response = addFriendHandler.getResponse();
            logger.info("addFriend response = {}", response);
            return response;
        } else {
            logger.error("channel is null or inactive");
            return null;
        }
    }
}
