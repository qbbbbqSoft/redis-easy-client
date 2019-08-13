package top.bbqbb.easy.starter.redis.service;

import top.bbqbb.easy.starter.redis.param.RedisParam;
import top.bbqbb.easy.starter.redis.result.RedisTR;

import java.util.List;
import java.util.Set;
/**
 * @author fqb
 * @program: redis-easy-client
 * 描述:
 * @create 2019-08-07 下午3:04
 */
public interface RedisService {

    RedisTR<Boolean> set(RedisParam param);

    RedisTR<String> get(String key);

    RedisTR<Boolean> del(String key);

    RedisTR<Boolean> batchDel(String pre_str);

    //TODO
    RedisTR<Long> setSet(RedisParam param);

    //TODO
    RedisTR<Set<String>> getSet(String key);

    //TODO
    RedisTR<Long> removeSetElements(RedisParam param);

    //TODO
    RedisTR<Boolean> setHash(RedisParam param);

    //TODO
    RedisTR<List> getAllHashVal(RedisParam param);

    //TODO
    RedisTR<List> getHashValByField(RedisParam param);

    //TODO
    RedisTR<Long> removeHashElements(RedisParam param);

    //TODO
    RedisTR<Boolean> keyExist(String key);

    RedisTR<Set<String>> keysListByPattern(String pattern);

    RedisTR<Long> ttl(String key);
}
