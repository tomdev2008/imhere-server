package org.xiaoxiancai.imhere.server.test;

import org.apache.commons.pool.BaseKeyedPoolableObjectFactory;
import org.apache.commons.pool.KeyedPoolableObjectFactory;
import org.apache.commons.pool.impl.GenericKeyedObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HBaseTableFactory {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private static final int BUFFER_SIZE = 2 * 1024 * 1024;

    private HTableFactory htableFactory = new HTableFactory();

    private Configuration configuration;

    private GenericKeyedObjectPool htablePool;

    private static class FactoryHolder {
        private static HBaseTableFactory hBaseTableFactory = new HBaseTableFactory();
    }

    private HBaseTableFactory() {
        configuration = new Configuration(false);
        configuration.addResource("hbase/hbase-site.xml");

        KeyedPoolableObjectFactory factory = new HTablePoolFactory();
        GenericKeyedObjectPool.Config config = new GenericKeyedObjectPool.Config();
        config.lifo = false;
        config.maxActive = 30;
        config.maxIdle = 10;
        config.minIdle = 5;
        config.maxWait = 5 * 1000;

        htablePool = new GenericKeyedObjectPool(factory, config);
    }

    public static HBaseTableFactory getInstance() {
        return FactoryHolder.hBaseTableFactory;
    }

    /**
     * 获取htable连接对象
     * 
     * @param tableName
     * @return
     * @throws Exception
     */
    public HBaseTable getHTable(String tableName) throws Exception {
        long time1 = System.currentTimeMillis();
        Object obj = htablePool.borrowObject(tableName);
        HBaseTable result = (HBaseTable) obj;
        if (logger.isTraceEnabled()) {
            long time2 = System.currentTimeMillis();
            logger.trace("Get htable time:{}", (time2 - time1));
        }
        return result;
    }

    /**
     * 释放htable连接对象，并不关闭连接
     * 
     * @param hbaseTable
     * @throws Exception
     */
    public void returnHTable(HBaseTable hbaseTable) throws Exception {
        htablePool.returnObject(hbaseTable.getTableName(), hbaseTable);
    }

    public class HTablePoolFactory extends BaseKeyedPoolableObjectFactory
        implements KeyedPoolableObjectFactory {

        @Override
        public Object makeObject(Object key) throws Exception {
            logger.trace("Create htable object, table:{}", key);
            String tableName = (String) key;
            // TODO 给htable对象建立对象池，从对象池取得htable，当使用完闭后放回对象池中
            HTable htable = (HTable) htableFactory.createHTableInterface(
                configuration, tableName.getBytes());
            htable.setWriteBufferSize(BUFFER_SIZE);
            htable.setAutoFlush(false, false);
            HBaseTable result = new HBaseTableImpl(tableName, htable);
            return result;
        }

        @Override
        public void destroyObject(Object key, Object obj) throws Exception {
            logger.trace("Destory htable object, table:{}", key);
            if (obj != null) {
                HBaseTable htable = (HBaseTable) obj;
                htable.close();
            }
        }

        @Override
        public void activateObject(Object key, Object obj) throws Exception {
            logger.trace("Activate htable object, table:{}", key);
            // TODO 激活该连接
        }

        @Override
        public void passivateObject(Object key, Object obj) throws Exception {
            logger.trace("Passivate htable object, table:{}", key);
            // 释放对象时，执行flush方法
            if (obj != null) {
                HBaseTable htable = (HBaseTable) obj;
                htable.flushCommits();
            }
        }

        @Override
        public boolean validateObject(Object key, Object obj) {
            logger.trace("Validate htable object, table:{}", key);
            // TODO 验证有效性
            if (obj != null) {
                HTable htable = (HTable) obj;
                try {
                    htable.flushCommits();
                    return true;
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }
        }

    }

}
