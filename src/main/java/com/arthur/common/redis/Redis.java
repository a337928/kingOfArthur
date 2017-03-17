package com.arthur.common.redis;

import com.arthur.exception.MngException;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.*;

public class Redis {

    // pool types
    public static final String MAIN_POOL = "redis";

    public static final String LOG_POOL = "log";

    public static final String ISMP_POOL = "ismp";

    public static final String READ_POOL = "read";

    public static final String SEND_POOL = "send";

    public static final String RECEIVE_POOL = "receive";

    public static final String ATTACHMENT_POOL = "attachment";

    public static final String ALL_TRAFFIC_KEY = "all_traffic_";
    public static final String SI_TRAFFIC_KEY = "si_traffic_";
    public static final String ALL_DAY_TRAFFIC_KEY = "all_day_traffic";
    public static final String SI_DAY_TRAFFIC_KEY = "si_day_traffic";

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(Redis.class);

    private static JedisPool getPool(String poolType) {
        return Pools.getInstance().getPoolMap().get(poolType);
    }

    // 从列表尾部插入队列
    public static void addItemToList(String key, byte[] value, String poolType) throws MngException {
        Jedis jedis = null;
        try {
            jedis = getPool(poolType).getResource();
            jedis.connect();
            jedis.rpush(key.getBytes(), value);
        } catch (Exception e) {
            throw new MngException("数据插入队列出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }
    }

    public static Map<String, String> getHashMap(String key, String poolType) throws MngException {
        Map<String, String> map = new HashMap<String, String>();
        Jedis jedis = null;
        try {
            jedis = getPool(poolType).getResource();
            map = jedis.hgetAll(key);
        } catch (Exception e) {
            throw new MngException("哈希表数据读取出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }
        return map;
    }

    public static void setHashMap(String key, Map<String, String> map, String poolType) throws MngException {
        Jedis jedis = null;
        try {
            jedis = getPool(poolType).getResource();
            Pipeline p = jedis.pipelined();
            if (map != null && !map.isEmpty()) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    p.hset(key, entry.getKey(), entry.getValue());
                }
            }
            p.sync();
        } catch (Exception e) {
            throw new MngException("数据插入哈希表出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }
    }

    public static void delItem(String key, String poolType) throws MngException {
        Jedis jedis = null;
        try {
            jedis = getPool(poolType).getResource();
            jedis.del(key.getBytes());

        } catch (Exception e) {
            throw new MngException("删除" + key + "数据出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }

    }

    public static void delItems(String[] keys, String poolType) throws MngException {
        Jedis jedis = null;
        StringBuffer sb = new StringBuffer();
        try {
            jedis = getPool(poolType).getResource();
            Pipeline p = jedis.pipelined();
            for (String key : keys) {
                p.del(key.getBytes());
                sb.append(key + "|");
            }
            p.sync();

        } catch (Exception e) {
            throw new MngException("删除" + sb.toString() + "数据出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }

    }

    public static long hincrBy(String key, String field, long value, String poolType) throws MngException {
        Jedis jedis = null;
        try {
            jedis = getPool(poolType).getResource();
            return jedis.hincrBy(key, field, value);

        } catch (Exception e) {
            throw new MngException("计数增加出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }

    }

    public static String hget(String key, String field, String poolType) throws MngException {
        Jedis jedis = null;
        try {
            jedis = getPool(poolType).getResource();
            return jedis.hget(key, field);

        } catch (Exception e) {
            throw new MngException("哈希表取值出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }

    }

    // 从列表尾部插入队列
    public static void addItemToList(String key, String value, String poolType) throws MngException {
        Jedis jedis = null;
        try {
            jedis = getPool(poolType).getResource();
            jedis.connect();
            jedis.rpush(key, value);
        } catch (Exception e) {
            throw new MngException("数据插入队列出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }
    }

    @SuppressWarnings("finally")
    public static List<String> getItemFromList(String key, String poolType) throws MngException {
        Jedis jedis = null;
        // byte[] s=null;
        List<String> ss = null;
        try {
            jedis = getPool(poolType).getResource();
            long len = jedis.llen(key);
            if (len == 0)
                return null;
            ss = jedis.lrange(key, 0, (int) len);
        } catch (Exception e) {

            throw new MngException("获取队列所有数据出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
            return ss;
        }
    }

    @SuppressWarnings("finally")
    public static byte[] lpopItemFromList(byte key[], String poolType) throws MngException {
        Jedis jedis = null;
        byte[] ss = null;
        try {
            jedis = getPool(poolType).getResource();
            long len = jedis.llen(key);
            if (len == 0)
                return null;
            ss = jedis.lpop(key);
        } catch (Exception e) {

            throw new MngException("弹出列所头元素出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
            return ss;
        }
    }

    @SuppressWarnings("finally")
    public static String lpopItemFromList(String key, String poolType) throws MngException {
        Jedis jedis = null;
        String ss = null;
        try {
            jedis = getPool(poolType).getResource();
            long len = jedis.llen(key);
            if (len == 0)
                return null;
            ss = jedis.lpop(key);
        } catch (Exception e) {
            throw new MngException("弹出列所头元素出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
            return ss;
        }
    }

    public static void setHashMap(String key, String field, byte[] value, String poolType) throws MngException {
        Jedis jedis = null;
        try {
            jedis = getPool(poolType).getResource();
            long result = jedis.hset(key.getBytes(), field.getBytes(), value);
            System.out.println(result);
        } catch (Exception e) {
            throw new MngException("数据插入哈希表出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }
    }

    public static byte[] getHashMap(String key, String field, String poolType) throws MngException {
        byte[] bytes = null;
        Jedis jedis = null;
        try {
            jedis = getPool(poolType).getResource();
            bytes = jedis.hget(key.getBytes(), field.getBytes());
        } catch (Exception e) {
            throw new MngException("哈希表数据读取出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }
        return bytes;
    }

    public static void expire(String key, int seconds, String poolType) throws MngException {
        Jedis jedis = null;
        try {
            jedis = getPool(poolType).getResource();
            jedis.expire(key.getBytes(), seconds);
        } catch (Exception e) {
            throw new MngException(key + "设置失效时间出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }
    }

    public static void expireKeys(String[] keys, int seconds, String poolType) throws MngException {
        Jedis jedis = null;
        StringBuffer sb = new StringBuffer();

        try {
            jedis = getPool(poolType).getResource();
            Pipeline p = jedis.pipelined();
            for (String key : keys) {
                p.expire(key.getBytes(), seconds);
                sb.append(key + "|");
            }
            p.sync();

        } catch (Exception e) {
            throw new MngException(sb + "设置失效时间出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }
    }

    public static void setStrValue(String key, String value, String poolType) throws MngException {
        Jedis jedis = null;
        try {
            jedis = getPool(poolType).getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            throw new MngException("数据插入出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }
    }

    public static void setStrValue(byte[] key, byte[] value, String poolType) throws MngException {
        Jedis jedis = null;
        try {
            jedis = getPool(poolType).getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            throw new MngException("数据插入出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }
    }

    public static String getStrValue(String key, String poolType) throws MngException {
        String ss = "";
        Jedis jedis = null;
        try {
            jedis = getPool(poolType).getResource();
            ss = jedis.get(key);
        } catch (Exception e) {
            throw new MngException("数据读取出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }
        return ss;
    }

    public static void addSet(String key, String value, String poolType) throws MngException {
        Jedis jedis = null;
        try {
            jedis = getPool(poolType).getResource();
            jedis.sadd(key, value);
        } catch (Exception e) {
            throw new MngException("写入set数据出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }
    }

    public static String getHashMapValue(String key, String field, String poolType) throws MngException {
        String result = "";
        Jedis jedis = null;
        try {
            jedis = getPool(poolType).getResource();
            result = jedis.hget(key, field);
        } catch (Exception e) {
            throw new MngException("数据获取出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }
        return result;
    }

    public static boolean hset(String key, String field, String value, String poolType) throws MngException {
        Jedis jedis = null;
        try {
            jedis = getPool(poolType).getResource();
            long ret=jedis.hset(key, field, value);
            if(ret==0||ret==1)
                return true;
            else
                return false;
        } catch (Exception e) {
            throw new MngException("写入数据出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }
    }

    public static Set<String> hKeys(String key, String poolType) throws MngException {
        Set<String> set = new HashSet<String>();
        Jedis jedis = null;
        try {
            jedis = getPool(poolType).getResource();
            set = jedis.hkeys(key);
        } catch (Exception e) {
            throw new MngException("获取子key出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }
        return set;
    }

    public static List<String> hmget(String key, List<String> fields, String poolType) throws MngException {
        List<String> list = new ArrayList<String>();
        Jedis jedis = null;
        String[] a = new String[fields.size()];
        try {
            jedis = getPool(poolType).getResource();
            list = jedis.hmget(key, fields.toArray(a));
        } catch (Exception e) {
            throw new MngException("获取子key的值出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }
        return list;
    }

    public static Set<String> getSet(String key, String poolType) throws MngException {
        Set<String> sets = new HashSet<String>();
        Jedis jedis = null;
        try {
            jedis = getPool(poolType).getResource();
            sets = jedis.smembers(key);
        } catch (Exception e) {
            throw new MngException("数据获取出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }

        return sets;
    }

    public static boolean sismember(String key, String value, String poolType) throws MngException {
        Jedis jedis = null;
        try {
            jedis = getPool(poolType).getResource();
            return jedis.sismember(key, value);
        } catch (Exception e) {
            throw new MngException("sismember数据获取出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }
    }

    public static boolean exists(String key, String poolType) throws MngException {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getPool(poolType).getResource();
            result = jedis.exists(key);
        } catch (Exception e) {
            throw new MngException("判断key是否存在出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }
        return result;
    }

    @SuppressWarnings("finally")
    public static long getListSize(String key, String poolType) throws MngException {
        Jedis jedis = null;
        long ss = 0;
        try {
            jedis = getPool(poolType).getResource();
            ss = jedis.llen(key);
        } catch (Exception e) {
            throw new MngException("获取队列长度出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
            return ss;
        }
    }

    public static Map<byte[], byte[]> getByteHashMap(String key, String poolType) throws MngException {
        Map<byte[], byte[]> map = new HashMap<byte[], byte[]>();
        Jedis jedis = null;
        try {
            jedis = getPool(poolType).getResource();
            map = jedis.hgetAll(key.getBytes());
        } catch (Exception e) {
            throw new MngException("附件数据获取出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }
        return map;
    }

    public static void setByteHashMap(String key, Map<byte[], byte[]> map, String poolType) throws MngException {
        Jedis jedis = null;
        try {

            jedis = getPool(poolType).getResource();
            Pipeline p = jedis.pipelined();
            if (map != null && !map.isEmpty()) {
                for (Map.Entry<byte[], byte[]> entry : map.entrySet()) {
                    p.hset(key.getBytes(), entry.getKey(), entry.getValue());
                }
            }
            p.sync();
        } catch (Exception e) {
            throw new MngException("写入附件数据出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }
    }

    public static void hdelItem(String key,String poolType,String field) throws MngException{
        Jedis jedis = null;
        try {

            jedis = getPool(poolType).getResource();
            jedis.hdel(key, field);
        } catch (Exception e) {
            throw new MngException("删除hash数据出错！", e);
        } finally {
            if (jedis != null)
                getPool(poolType).close();
        }
    }
}
