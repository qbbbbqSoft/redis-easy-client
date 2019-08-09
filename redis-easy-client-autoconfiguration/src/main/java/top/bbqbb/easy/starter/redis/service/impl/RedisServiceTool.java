package top.bbqbb.easy.starter.redis.service.impl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import top.bbqbb.easy.starter.redis.param.RedisParam;
import top.bbqbb.easy.starter.redis.result.RedisTR;
import top.bbqbb.easy.starter.redis.service.RedisService;

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
    private String host = "127.0.0.1";
    private int port = 6379;
    private String password = "";
    private int database = 1;
    private static JedisPool pool;

    public RedisServiceTool(String host, int port, String password, int database) {
        this.host = host;
        this.port = port;
        this.password = password;
        this.database = database;
    }

    public void Init() {
        JedisPoolConfig poolconfig = new JedisPoolConfig();
        poolconfig.setMaxIdle(100);//最大闲置个数
        poolconfig.setMinIdle(50);//最小闲置个数
        poolconfig.setMaxTotal(10000);//最大连接数
        pool = new JedisPool(poolconfig,this.host,this.port,100,"root",15);
    }

    @Override
    public RedisTR<Boolean> set(RedisParam param) {
        getJedis().set(param.getKey(),param.getVal().toString());
        return new RedisTR<>(param.getKey(),true);
    }

    @Override
    public RedisTR<String> get(String key) {
        return new RedisTR<>(key,getJedis().get(key));
    }

    @Override
    public RedisTR<Boolean> del(String key) {
        return null;
    }

    @Override
    public RedisTR<Boolean> batchDel(String pre_str) {
        return null;
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
        return new RedisTR<>(pattern,keys);
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
}
