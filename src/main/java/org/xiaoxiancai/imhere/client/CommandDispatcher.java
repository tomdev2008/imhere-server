/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.client;

import java.util.List;

import org.xiaoxiancai.imhere.common.protos.business.CommandProtos.Command;

/**
 * 命令分发器
 * 
 * @author linxianneng
 */
public class CommandDispatcher {

    /**
     * 客户端
     */
    private AbstractClient client;

    public CommandDispatcher() {}

    public CommandDispatcher(AbstractClient client) {
        this.client = client;
    }

    public AbstractClient getClient() {
        return client;
    }

    public void setClient(AbstractClient client) {
        this.client = client;
    }

    /**
     * 分发命令
     * <p>
     * 实际调用客户端命令
     * 
     * @param commands
     */
    public void dispatchCommand(List<Command> commands) {
        for (Command command: commands) {
            switch (command) {
                case UPDATE_INFOLIST:
                    // TODO
                    break;

                case UPDATE_FRIENDLIST:
                    // TODO
                    break;
                default:
                    break;
            }
        }
    }
}
