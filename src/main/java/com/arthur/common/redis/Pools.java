package com.arthur.common.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.arthur.bean.redisConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.core.env.Environment;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;



public class Pools  {
    private static Pools pools = null;
    private static final Logger logger = Logger.getLogger(Pools.class);
    private static Map<String, JedisPool> poolsMap = new HashMap<String, JedisPool>();
    private static String[] poolType = null;
    public static final String CONNECT_FLAG = "_";
    public static final String SLAVE_IP = "SlaveIP";
    private static String currentConnectIP = "";

    @Autowired
    private Environment env;
    private RelaxedPropertyResolver redisPropertyResolver;

    private Pools() {
        try {
            this.redisPropertyResolver = new RelaxedPropertyResolver(env, "redis");
            currentConnectIP = redisPropertyResolver.getProperty("ip");
            redisConfig config = new redisConfig();
            config.setHost(currentConnectIP);
            config.setPort(Integer.parseInt(redisPropertyResolver.getProperty("port")));
            config.setPwd(redisPropertyResolver.getProperty("pwd"));
            config.setDBIndex(Integer.parseInt(redisPropertyResolver.getProperty("db")));
            logger.info(">>>开始加载redis池");
            loadPools(config);
            logger.info(">>>完成加载redis池");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public static synchronized Pools getInstance() {
        if (pools == null) {
            pools = new Pools();
        }
        return pools;
    }

    public Map<String, JedisPool> getPoolMap() {
        return poolsMap;
    }

    public void reLoadPools(redisConfig config) {
        logger.info(">>>开始销毁redis池");
        destroyPools();
        logger.info("<<<完成重载redis池");

        logger.info(">>>开始重载redis池");
        loadPools(config);
        logger.info("<<<完成重载redis池");
    }

    private void loadPools(redisConfig config) {
        try {
            String pool_type = "redis";
            logger.info("redis池类型:" + pool_type);
            poolType = pool_type.split(";");
            JedisPool pool = null;
            if (poolType != null) {
                for (String pooltype : poolType) {
                    pool = initPool(pooltype, config);
                    if (pool != null) {
                        poolsMap.put(pooltype, pool);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("加载redis池出错", e);
        }
    }

    private void destroyPools() {
        try {
            Iterator<JedisPool> iter = poolsMap.values().iterator();
            JedisPool jedisPool = null;
            while (iter.hasNext()) {
                try {
                    jedisPool = iter.next();
                    if (jedisPool != null)
                        jedisPool.destroy();
                } catch (Exception e) {
                    logger.error("销毁redis池出错", e);
                }
            }
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    private static JedisPool initPool(String poolType, redisConfig config) throws Exception {
        JedisPool pool = null;

        String host = config.getHost();
        int port = config.getPort();
        int DBIndex = config.getDBIndex();
        int timeout = config.getTimeout();
        String pwd = config.getPwd();
        int maxTotal = config.getMaxTotal();
        int maxIdle = config.getMaxIdle();
        long maxWait = config.getMaxWait();

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxTotal(maxTotal);
        // 在borrow一个jedis实例时，是否提前进行alidate操作；如果为true，则得到的jedis实例均是可用的；
        poolConfig.setTestOnBorrow(true);
        // 在还会给pool时，是否提前进行validate操作
        poolConfig.setTestOnReturn(true);
        poolConfig.setMaxWaitMillis(maxWait);
        pool = new JedisPool(poolConfig, host, port, timeout, pwd, DBIndex);

        return pool;
    }




}
