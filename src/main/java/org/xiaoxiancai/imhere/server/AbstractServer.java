/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 抽象服务类
 * 
 * @author xiannenglin
 */
public abstract class AbstractServer implements Server {

    /**
     * Logger
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 服务配置文件
     */
    private static final String CONFIG_FILE = "conf/imhere-server.properties";

    /**
     * 服务器参数
     */
    protected static Map<String, String> serverSettings = new HashMap<String, String>();

    /**
     * 服务器状态
     */
    protected ServerStatus status;

    /**
     * Spring BeanFactory
     */
    protected static ApplicationContext applicationContext;

    static {
        applicationContext = new ClassPathXmlApplicationContext(
            "classpath*:spring/applicationContext.xml");
        try {
            Properties properties = new Properties();
            Reader reader = new BufferedReader(new FileReader(new File(
                CONFIG_FILE)));
            properties.load(reader);
            Set<Entry<Object, Object>> proSet = properties.entrySet();
            for (Entry<Object, Object> entry: proSet) {
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                serverSettings.put(key, value);
            }
        } catch (IOException e) {
            System.exit(-1);
        }
    }

    @Override
    public void start() throws Exception {
        status = ServerStatus.INITING;
        doInit();
        status = ServerStatus.INITED;
        status = ServerStatus.STARTING;
        doStart();
        status = ServerStatus.STARTED;
        status = ServerStatus.RUNNING;
    }

    @Override
    public void stop() throws Exception {
        status = ServerStatus.STOPPING;
        doStop();
        status = ServerStatus.STOPED;
    }

    /**
     * 执行初始化服务
     * 
     * @throws Exception
     */
    protected abstract void doInit() throws Exception;

    /**
     * 执行启动服务
     * 
     * @throws Exception
     */
    protected abstract void doStart() throws Exception;

    /**
     * 执行停止服务
     * 
     * @throws Exception
     */
    protected abstract void doStop() throws Exception;

    /**
     * 获得Spring Bean容器
     * 
     * @return
     */
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

}
