/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.business;

import io.netty.channel.ChannelHandlerContext;

import java.security.MessageDigest;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest;
import org.xiaoxiancai.imhere.common.protos.business.RegisterRequestProtos.RegisterRequest;
import org.xiaoxiancai.imhere.common.protos.business.RegisterResponseProtos.RegisterResponse;
import org.xiaoxiancai.imhere.server.entity.User;
import org.xiaoxiancai.imhere.server.inter.AddFriendMessageMapper;
import org.xiaoxiancai.imhere.server.inter.UserMapper;

import sun.misc.BASE64Encoder;

/**
 * 新用户注册处理器
 * 
 * @author xiannenglin
 */
public class RegisterHandler extends AbstractBusinessHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception {
        if (!(msg instanceof RegisterRequest)) {
            return;
        }
        logger.debug("register user start");
        UserMapper userMapper = (UserMapper) applicationContext
            .getBean("userMapper");
        AddFriendMessageMapper friendMapper = (AddFriendMessageMapper) applicationContext
            .getBean("addFriendMessageMapper");
        RegisterRequest request = (RegisterRequest) msg;

        // 先查询数据库是否存在相同mobile的记录
        String mobile = request.getMobile();
        if (!isMobileRegistered(userMapper, mobile)) {
            userMapper.registerUser(createUser(request));
            List<AddFriendRequest> addFriendRequestList = RegisterLoginHelper
                .getAddFriendRequestFromDB(mobile, friendMapper);
            RegisterResponse successResponse = getResponse(true, 1,
                "register success", addFriendRequestList);
            ctx.channel().writeAndFlush(successResponse);
            logger.debug("register user success, mobile = {}", mobile);
        } else {
            RegisterResponse successResponse = getResponse(false, -1,
                "already registered", null);
            ctx.channel().writeAndFlush(successResponse);
            logger.debug("register user fail, mobile {} already registered",
                mobile);
        }
    }

    /**
     * 手机号码是否已注册
     * 
     * @param userMapper
     * @param mobile
     * @return
     */
    private boolean isMobileRegistered(UserMapper userMapper, String mobile) {

        // TODO 优化(resultMap里面没有配置password, 这里怎么会把password也取出来?)
        User user = userMapper.getUserByMobile(mobile);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获得返回值
     * 
     * @param isSuccess
     * @param status
     * @param message
     * @param addFriendRequestList
     * @return
     */
    private RegisterResponse getResponse(boolean isSuccess, int status,
        String message, List<AddFriendRequest> addFriendRequestList) {
        RegisterResponse.Builder builder = RegisterResponse.newBuilder();
        builder.setIsSuccess(isSuccess);
        builder.setStatus(status);
        builder.setMessage(message);
        if (addFriendRequestList != null) {
            builder.addAllAddFriendRequest(addFriendRequestList);
        }
        return builder.build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
        throws Exception {
        super.exceptionCaught(ctx, cause);
        RegisterResponse response = getResponse(false, -1, "server error", null);
        ctx.channel().writeAndFlush(response);
        logger.error("register user fail, server error");
    }

    private User createUser(RegisterRequest request) throws Exception {
        User user = new User();
        user.setMobile(request.getMobile());
        user.setPassword(getEncryptedPassword(request.getPassword()));
        user.setNickname(request.getNickName());
        String email = request.getEmail();
        if (!StringUtils.isBlank(email)) {
            user.setEmail(email);
        }
        String signature = request.getSignature();
        if (!StringUtils.isBlank(signature)) {
            user.setSignature(signature);
        }
        return user;
    }

    /**
     * 密码加密// TODO
     * 
     * @param password
     * @return
     * @throws Exception
     */
    private String getEncryptedPassword(String password) throws Exception {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder encoder = new BASE64Encoder();
            String newPassword = encoder.encode(md5.digest(password
                .getBytes("UTF-8")));
            return newPassword;
        } catch (Exception e) {
            throw new Exception("Encrypt password exception", e);
        }
    }
}
