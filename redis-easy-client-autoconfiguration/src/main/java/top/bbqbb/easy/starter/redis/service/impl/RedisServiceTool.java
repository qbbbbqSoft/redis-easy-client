package top.bbqbb.easy.starter.redis.service.impl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import top.bbqbb.easy.starter.redis.param.RedisParam;
import top.bbqbb.easy.starter.redis.result.RedisTR;
import top.bbqbb.easy.starter.redis.service.RedisService;
import top.bbqbb.easy.starter.redis.utils.TimeoutUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author fqb
 * @program: redis-easy-client
 * 描述:
 * @create 2019-08-07 下午2:57
 */

public class RedisServiceTool implements RedisService {
    private String host;
    private int port;
    private String password;
    private int database;
    private int maxTotal;
    private int maxIdle;
    private int minIdle;
    private int timeout;
    private static JedisPool pool;

    public RedisServiceTool() {
    }

    public RedisServiceTool(String host, int port, String password, int database) {
        this.host = host;
        this.port = port;
        this.password = password;
        this.database = database;
    }

    public RedisServiceTool(String host, int port, String password, int database, int maxTotal, int maxIdle, int minIdle, int timeout) {
        this.host = host;
        this.port = port;
        this.password = password;
        this.database = database;
        this.maxTotal = maxTotal;
        this.maxIdle = maxIdle;
        this.minIdle = minIdle;
        this.timeout = timeout;
    }

    public void Init() {
        JedisPoolConfig poolconfig = new JedisPoolConfig();
        /**
         * 最大闲置个数
         */
        poolconfig.setMaxIdle(this.maxIdle);
        /**
         * 最小闲置个数
         */
        poolconfig.setMinIdle(this.minIdle);
        /**
         * 最大连接数
         */
        poolconfig.setMaxTotal(this.maxTotal);
        pool = new JedisPool(poolconfig,this.host,this.port,100,this.password,this.database);
        getJedis();
        closeJedis();
    }

    @Override
    public RedisTR<Boolean> set(RedisParam param) {
        getJedis().set(param.getKey(),param.getVal().toString());
        if (param.getTimeout() > 0L) {
            getJedis().expire(param.getKey(), TimeoutUtils.toSeconds(param.getTimeout(),param.getTimeUnit()).intValue());
        }
        closeJedis();
        return new RedisTR<>(param.getKey(),true);
    }

    @Override
    public RedisTR<String> get(String key) {
        String value = getJedis().get(key);
        closeJedis();
        return new RedisTR<>(key,value);
    }

    @Override
    public RedisTR<Boolean> del(String key) {
        getJedis().del(key);
        closeJedis();
        return new RedisTR<>(key,true);
    }

    @Override
    public RedisTR<Boolean> batchDel(String pre_str) {
        Set<String> keys = getJedis().keys(pre_str);
        for (String k:keys) {
            this.del(k);
        }
        closeJedis();
        return new RedisTR<>(pre_str,true);
    }

    @Override
    public RedisTR<Long> setSet(RedisParam param) {
        return null;
    }

    @Override
    public RedisTR<Set<String>> getSet(String key) {
        return null;
    }

    @Override
    public RedisTR<Long> removeSetElements(RedisParam param) {
        return null;
    }

    @Override
    public RedisTR<Boolean> setHash(RedisParam param) {
        return null;
    }

    @Override
    public RedisTR<List> getAllHashVal(RedisParam param) {
        return null;
    }

    @Override
    public RedisTR<List> getHashValByField(RedisParam param) {
        return null;
    }

    @Override
    public RedisTR<Long> removeHashElements(RedisParam param) {
        return null;
    }

    @Override
    public RedisTR<Boolean> keyExist(String key) {
        return null;
    }

    @Override
    public RedisTR<Set<String>> keysListByPattern(String pattern) {
        Set<String> keys = getJedis().keys(pattern);
        closeJedis();
        return new RedisTR<>(pattern,keys);
    }


    @Override
    public RedisTR<Long> ttl(String key) {
        Long ttl = getJedis().ttl(key);
        getJedis().close();
        return new RedisTR<>(key,ttl);
    }

    @Override
    public String toString() {
        return "RedisServiceTool{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", password='" + password + '\'' +
                ", database=" + database +
                '}';
    }
    public static Jedis getJedis() {
        return pool.getResource();
    }
    public static void closeJedis() {
        getJedis().close();
    }
}
