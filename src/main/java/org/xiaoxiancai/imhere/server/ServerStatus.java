/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server;

/**
 * 服务器状态
 * 
 * @author linxianneng
 */
public enum ServerStatus {
    /**
     * 正在初始化
     */
    INITING,

    /**
     * 初始化完成
     */
    INITED,

    /**
     * 启动中
     */
    STARTING,

    /**
     * 启动中
     */
    STARTED,

    /**
     * 运行中
     */
    RUNNING,

    /**
     * 已停止
     */
    STOPPING,

    /**
     * 已停止
     */
    STOPED
}
