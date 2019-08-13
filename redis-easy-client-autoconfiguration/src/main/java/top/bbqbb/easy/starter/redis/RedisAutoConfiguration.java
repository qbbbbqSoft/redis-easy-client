package top.bbqbb.easy.starter.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.bbqbb.easy.starter.redis.controller.UIDataController;
import top.bbqbb.easy.starter.redis.service.impl.RedisServiceTool;

import static top.bbqbb.easy.starter.redis.RedisProperties.REDIS_EASY_CLIENT_PREFIX;

/**
 * @author fqb
 * @program: redis-easy-client
 * 描述:
 * @create 2019-08-07 上午10:55
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
@ConditionalOnProperty(prefix = REDIS_EASY_CLIENT_PREFIX,name = {"host"},matchIfMissing = true)
public class RedisAutoConfiguration {
    @Autowired
    RedisProperties redisProperties;

    @Bean("RedisServiceTool")
    @ConditionalOnMissingBean
    RedisServiceTool redisServiceTool() {
        RedisServiceTool redisServiceTool =
                new RedisServiceTool(redisProperties.getHost(),redisProperties.getPort(),redisProperties.getPassword(),redisProperties.getDatabase(),redisProperties.getMaxTotal(),redisProperties.getMaxIdle(),redisProperties.getMinIdle(),redisProperties.getTimeout());
        redisServiceTool.Init();
        return redisServiceTool;
    }

    @Bean
    @ConditionalOnMissingBean
    UIDataController htmlController() {
        return new UIDataController();
    }


}
