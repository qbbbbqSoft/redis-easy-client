package top.bbqbb.easy.starter.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static top.bbqbb.easy.starter.redis.RedisProperties.REDIS_EASY_CLIENT_PREFIX;

/**
 * @author fqb
 * @program: redis-easy-client
 * 描述:
 * @create 2019-08-07 上午11:03
 */
@Configuration
@ConfigurationProperties(prefix = REDIS_EASY_CLIENT_PREFIX)
@Data
public class RedisProperties {
    public static final String REDIS_EASY_CLIENT_PREFIX = "redis.easy.client";
    private String host = "127.0.0.1";
    private int port = 6379;
    private String password = "";
    private int database = 0;
    /**
     * 最大连接数
     */
    private int maxTotal = 100;
    /**
     * 最大闲置个数
     */
    private int maxIdle = 10;
    /**
     * 最小闲置个数
     */
    private int minIdle = 0;
    /**
     * 超时时间
     */
    private int timeout = 2000;
}
