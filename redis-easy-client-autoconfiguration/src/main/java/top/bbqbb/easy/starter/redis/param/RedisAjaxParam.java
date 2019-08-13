package top.bbqbb.easy.starter.redis.param;

import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
public class RedisAjaxParam {
    private String key;
    private String value;
    private long timeout = 0;
    private TimeUnit timeUnit = TimeUnit.DAYS;
}
