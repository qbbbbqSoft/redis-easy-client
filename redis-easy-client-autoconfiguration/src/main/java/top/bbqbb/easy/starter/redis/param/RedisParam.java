package top.bbqbb.easy.starter.redis.param;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @author fqb
 * @program: redis-easy-client
 * 描述:
 * @create 2019-08-07 下午3:08
 */
@Data
public class RedisParam {
    private String key;
    private Object val;
    private long timeout = 0;
    private TimeUnit timeUnit = TimeUnit.DAYS;

    public RedisParam() {
    }

    public RedisParam(String key, String val, long timeout, TimeUnit timeUnit) {
        this.key = key;
        this.val = val;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
    }

    public RedisParam(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public RedisParam(String key, Object val) {
        this.key = key;
        this.val = val;
    }
}
