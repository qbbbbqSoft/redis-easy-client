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

    RedisTR<Long> setSet(RedisParam param);

    RedisTR<Set<String>> getSet(String key);

    RedisTR<Long> removeSetElements(RedisParam param);

    RedisTR<Boolean> setHash(RedisParam param);

    RedisTR<List> getAllHashVal(RedisParam param);

    RedisTR<List> getHashValByField(RedisParam param);

    RedisTR<Long> removeHashElements(RedisParam param);

    RedisTR<Boolean> keyExist(String key);

    RedisTR<Set<String>> keysListByPattern(String pattern);
}
