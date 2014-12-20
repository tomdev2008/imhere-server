/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server;

/**
 * 服务选择器
 * 
 * @author xiannenglin
 */
public class ServerSelector extends AbstractServer {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            throw new Exception("no server to start");
        } else {
            ServerSelector selector = new ServerSelector();
            String serverToStart = args[0];
            if (serverToStart.equalsIgnoreCase("all")) {
                selector.startImHereServer();
                selector.startLinkServer();
                selector.startAdminServer();
            } else if (serverToStart.equalsIgnoreCase("imHereServer")) {
                selector.startImHereServer();
                selector.startLinkServer();
            } else if (serverToStart.equalsIgnoreCase("adminServer")) {
                selector.startAdminServer();
            } else {
                throw new Exception("server " + serverToStart + "doesn't exist");
            }
        }
    }

    /**
     * 启动主服务
     * 
     * @throws Exception
     */
    private void startImHereServer() throws Exception {
        ImHereServer imHereServer = applicationContext.getBean("imHereServer",
            ImHereServer.class);
        ImHereServer.ShutdownCleaner cleaner = imHereServer.new ShutdownCleaner();
        Runtime.getRuntime().addShutdownHook(cleaner);
        imHereServer.start();
    }

    /**
     * 启动管理服务
     * 
     * @throws Exception
     */
    private void startAdminServer() throws Exception {
        AdminServer adminServer = applicationContext.getBean("adminServer",
            AdminServer.class);
        AdminServer.ShutdownCleaner cleaner = adminServer.new ShutdownCleaner();
        Runtime.getRuntime().addShutdownHook(cleaner);
        adminServer.start();
    }

    /**
     * 启动连接服务
     * 
     * @throws Exception
     */
    private void startLinkServer() throws Exception {
        LinkServer linkServer = applicationContext.getBean("linkServer",
            LinkServer.class);
        linkServer.start();
    }

    @Override
    protected void doInit() throws Exception {
        // do noting
    }

    @Override
    protected void doStart() throws Exception {
        // do noting
    }

    @Override
    protected void doStop() throws Exception {
        // do noting
    }

}
