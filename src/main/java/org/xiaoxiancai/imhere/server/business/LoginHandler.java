/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.business;

import io.netty.channel.ChannelHandlerContext;

import java.security.MessageDigest;
import java.util.List;

import org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest;
import org.xiaoxiancai.imhere.common.protos.business.LoginRequestProtos.LoginRequest;
import org.xiaoxiancai.imhere.common.protos.business.LoginResponseProtos.LoginResponse;
import org.xiaoxiancai.imhere.server.entity.User;
import org.xiaoxiancai.imhere.server.inter.AddFriendMessageMapper;
import org.xiaoxiancai.imhere.server.inter.UserMapper;

import sun.misc.BASE64Encoder;

/**
 * 登录处理器
 * 
 * @author xiannenglin
 */
public class LoginHandler extends AbstractBusinessHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception {
        if (!(msg instanceof LoginRequest)) {
            return;
        }
        LoginRequest request = (LoginRequest) msg;
        String mobile = request.getMobile();
        String password = request.getPassword();
        logger.debug("receive client login request, mobile = {}", mobile);
        UserMapper userMapper = (UserMapper) applicationContext
            .getBean("userMapper");
        AddFriendMessageMapper friendMapper = (AddFriendMessageMapper) applicationContext
            .getBean("addFriendMessageMapper");

        if (checkPassword(mobile, password, userMapper)) {
            logger.debug("user login success, mobile = {}", mobile);
            List<AddFriendRequest> addFriendRequestList = RegisterLoginHelper
                .getAddFriendRequestFromDB(mobile, friendMapper);

            LoginResponse successResponse = getResponse(true, 1,
                "login success", addFriendRequestList);
            ctx.channel().writeAndFlush(successResponse);
        } else {
            logger.debug("user login failed, mobile = {}", mobile);
            LoginResponse failResponse = getResponse(false, -1, "login fail",
                null);
            ctx.channel().writeAndFlush(failResponse);
        }
    }

    /**
     * 获得Response
     * 
     * @param isSuccess
     * @param status
     * @param message
     * @param addFriendRequestList
     * @return
     */
    private LoginResponse getResponse(boolean isSuccess, int status,
        String message, List<AddFriendRequest> addFriendRequestList) {
        LoginResponse.Builder builder = LoginResponse.newBuilder();
        builder.setIsSuccess(isSuccess);
        builder.setStatus(status);
        builder.setMessage(message);
        if (addFriendRequestList != null) {
            builder.addAllAddFriendRequest(addFriendRequestList);
        }
        return builder.build();
    }

    /**
     * 验证用户名密码
     * 
     * @param password
     * @param passwordFromDB
     * @throws Exception
     */
    private boolean checkPassword(String mobile, String password,
        UserMapper userMapper) throws Exception {
        try {
            User user = userMapper.getUserByMobile(mobile);
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder encoder = new BASE64Encoder();
            String newPassword = encoder.encode(md5.digest(password
                .getBytes("UTF-8")));
            if (newPassword.equals(user.getPassword())) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception("Encrypt password exception", e);
        }
    }
}
