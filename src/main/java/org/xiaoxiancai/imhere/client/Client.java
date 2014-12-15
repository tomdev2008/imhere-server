/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.client;

import org.xiaoxiancai.imhere.common.protos.business.AcceptFriendRequestProtos.AcceptFriendRequest;
import org.xiaoxiancai.imhere.common.protos.business.AcceptFriendResponseProtos.AcceptFriendResponse;
import org.xiaoxiancai.imhere.common.protos.business.AddFriendRequestProtos.AddFriendRequest;
import org.xiaoxiancai.imhere.common.protos.business.AddFriendResponseProtos.AddFriendResponse;
import org.xiaoxiancai.imhere.common.protos.business.LocateRequestProtos.LocateRequest;
import org.xiaoxiancai.imhere.common.protos.business.LocateResponseProtos.LocateResponse;
import org.xiaoxiancai.imhere.common.protos.business.LoginRequestProtos.LoginRequest;
import org.xiaoxiancai.imhere.common.protos.business.LoginResponseProtos.LoginResponse;
import org.xiaoxiancai.imhere.common.protos.business.RegisterRequestProtos.RegisterRequest;
import org.xiaoxiancai.imhere.common.protos.business.RegisterResponseProtos.RegisterResponse;

/**
 * 客户端接口
 * <p>
 * 定义所有客户端逻辑接口
 * 
 * @author linxianneng
 */
public interface Client {

    /**
     * 注册
     * 
     * @param request
     * @return
     * @throws Exception
     */
    public RegisterResponse register(RegisterRequest request) throws Exception;

    /**
     * 登录
     * 
     * @param request
     * @return
     * @throws Exception
     */
    public LoginResponse login(LoginRequest request) throws Exception;

    /**
     * 定位
     * 
     * @param request
     * @return
     * @throws Exception
     */
    public LocateResponse locate(LocateRequest request) throws Exception;

    /**
     * 添加好友
     * 
     * @param request
     * @return
     * @throws Exception
     */
    public AddFriendResponse addFriend(AddFriendRequest request) throws Exception;
    
    
    /**
     * 接受好友
     * @param request
     * @return
     * @throws Exception
     */
    public AcceptFriendResponse acceptFriend(AcceptFriendRequest request) throws Exception;

}
